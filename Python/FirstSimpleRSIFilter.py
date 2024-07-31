import websocket, json, pprint, talib, numpy, config
from binance.client import Client
from binance.enums import *

#Ashton Daniels
#Practice Stock And Crypto Bots
#Python 3.12.1
#Dependncies file: requirements.txt


#Notes
#Look into bitcoin over a 1 day timeframe and run tests on it with this RSI indicator

#initiate a connection to binance using python-binance library

#this declaration of the client is configured to use the testnet setting and API keys
#when I want to actually implement this then I will want remove testnet=True and get other api keys for my actual account
client = Client(config.api_key, config.api_secret, tld='us', testnet=True)


#Variables

#Create the socket variable here i am looking at btc over 1 min intervals
cc = 'btcusdt'
interval = '1m'
#websocket to connect to on the main Binance.US site
socket = f'wss://stream.binance.us:9443/ws/{cc}@kline_{interval}'

#test websocket to connect to using binance testnet to test strategies
socket2 = f'wss://testnet.binance.vision/ws/{cc}@kline_{interval}'

#Create RSI variables to make it dynamic
rsi_period = 14
rsi_overbought = 70
rsi_oversold = 30
trade_symbol = 'BTCUSDT'
trade_quantity = 0.005

#initiate a list variable to store the closing prices. We need these for RSI indicator calculations.
closes = []

#tracks to see if we have already made a purchase of this stock so we do not keep buying or selling.
already_bought = False

#Functions

#print account info
def accountInfo ():
    account = client.get_account()

    balances = account['balances']
    print("Current Account Balances")
    print(balances)

#define a function for ordering
def order (side, quantity, symbol, order_type=ORDER_TYPE_MARKET):
    try:
        print("Sending Order")
        order = client.create_order(symbol=symbol,
                                # side = SIDE_BUY, #constant from enums import above to indicate buy
                                side=side,
                                type=order_type, #constant from enums import to indicate market order
                                quantity=quantity)
        print(order)
    except Exception as e:
        print(e)
        return False
    
    return True


#define the websocket functions to handle the websocket events

def on_open (ws) :
    print('Opened Connection')

def on_close (ws) :
    print('Closed Connection')

#This will be the most important event handler as wel will manipulate the data received in here
#perform checks on it using TA-Lib such as RSI or EMA checks and then execute trades based off 
#the checks performed.
def on_message(ws, message) :
    # print('Received Message')

    global closes

    #takes the message and loads it using the python json library to make it easier to work with. 
    json_message = json.loads(message)
    # pprint.pprint(json_message)

    #since we are using RSI for a simple test we want to capture the close of each candlestick that is closed. 

    #capture the entire candlstick message including timestamp, open, close, low, high, final candlestick data flag
    candle = json_message['k']
    

    #capture the variable that indicates whether this is the final candlestick data or not. 
    is_candle_closed = candle['x']

    #capture the closing price of the candle
    close_price = candle['c']

    if is_candle_closed:
        # pprint.pprint(candle)
        print("candle closed at {}".format(close_price))

        #append the closing price to a list called closes
        #in order for numpy to work on it we need to cast it to a float
        closes.append(float(close_price))

        print("list of closing prices")
        print(closes)

        #need to make sure that the closes list has more data than the periods we are checking for the RSI
        #exp if you have rsi_period of 14 you need 15 data points in the closes list
        if len(closes) > rsi_period:
            #convert the list into a numpy list in order for talib to perform calculations on
            np_closes = numpy.array(closes)

            #talib docs indicate an RSI function which needs (close, period)
            rsi = talib.RSI(np_closes, rsi_period)
            print("all rsis calculated so far")
            print(rsi)
            #this will be used to make trading decision
            last_rsi = rsi[-1]
            print("current rsi is {}".format(last_rsi))
            print(last_rsi)

            #now we want to see if the RSI is over or under the RSI boundaries
            if last_rsi > rsi_overbought:
                #check to see if we already sold this stock and if not then sell
                if already_bought:    
                    print("SELL")
                    order_sold = order (SIDE_SELL, trade_quantity, trade_symbol)
                    if order_sold:
                        already_bought = False
                    
                else:
                    print("We already got out of the stock no action needed")
            if last_rsi < rsi_oversold:
                #check to see if we already own this stock and if not then buy
                if already_bought:
                    print("It is oversold but we already bought")
                else:
                    print("Buy")
                    order_bought = order (SIDE_BUY, trade_quantity, trade_symbol)
                    if order_bought:
                        already_bought = True


#initiate the socket and store it in the variable
ws = websocket.WebSocketApp(
    socket2,
    on_message=on_message,
    on_close=on_close,
    on_open=on_open)

#provides me more detail about errors occuring with the websocket
# websocket.enableTrace(True)
ws.run_forever(ping_interval=300)