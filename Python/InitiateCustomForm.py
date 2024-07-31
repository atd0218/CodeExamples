import csv
import urllib3
import concurrent.futures
import json
import time
from datetime import datetime
from tqdm import tqdm, trange


# Environment will be either loanpro or a PSaaS client name
environment = 'axcessfinancial' 
#This is used to set the URL for the API Request
api_url_base = f'https://{environment}.simnang.com/api/public/api/1/custom.forms/export/PDF/Loan/'
#Source file that will be past in containing loan id's
source_file = 'loans4scripts.csv'
#initiating urllib3 to make HTTP requests
http = urllib3.PoolManager()

#Headers used for API calls
headers = {
            'Content-Type': 'application/json',
            'Autopal-Instance-Id': '5202101',
            'Authorization': 'Bearer 30a954605bc51afabf32824c838e6bbf06305727'
        }
        
#initiate the CSV reader to open and read the csv file
csvReader = csv.reader(open(source_file, newline = ''), delimiter = ',')
#skip the first line in the CSV file the header
next(csvReader, None)


def enable_forms(row):
    loanid = row[0]
    data = {
                "0": 32
           }
#encode the data into utf-8        
    encoded_data = json.dumps(data).encode('utf-8')
#append to the urs the loan id and set the headers and body
    response = http.request('POST', f"{api_url_base}{loanid}", headers = headers, body = encoded_data)

    time.sleep(1)

    data2 = {
                "0": 45
            }
#encode the data into utf-8        
    encoded_data2 = json.dumps(data2).encode('utf-8')
#append to the urs the loan id and set the headers and body
    response2 = http.request('POST', f"{api_url_base}{loanid}", headers = headers, body = encoded_data)
    
    time.sleep(1)

    data3 = {
                "0": 50
            }
#encode the data into utf-8        
    encoded_data3 = json.dumps(data3).encode('utf-8')
#append to the urs the loan id and set the headers and body
    response3 = http.request('POST', f"{api_url_base}{loanid}", headers = headers, body = encoded_data)
    
    time.sleep(1)

    data4 = {
                "0": 52
            }
#encode the data into utf-8        
    encoded_data4 = json.dumps(data4).encode('utf-8')
#append to the urs the loan id and set the headers and body
    response4 = http.request('POST', f"{api_url_base}{loanid}", headers = headers, body = encoded_data)

    time.sleep(1)

    data5 = {
                "0": 41
            }
#encode the data into utf-8        
    encoded_data5 = json.dumps(data5).encode('utf-8')
#append to the urs the loan id and set the headers and body
    response5 = http.request('POST', f"{api_url_base}{loanid}", headers = headers, body = encoded_data)

    time.sleep(1)

    data6 = {
                "0": 42
            }
#encode the data into utf-8        
    encoded_data6 = json.dumps(data6).encode('utf-8')
#append to the urs the loan id and set the headers and body
    response6 = http.request('POST', f"{api_url_base}{loanid}", headers = headers, body = encoded_data)
    
#Create the futures_list array for the progress bar
futures_list = []
#sets the number of threads to be used while executing the for each loop
with concurrent.futures.ThreadPoolExecutor(max_workers=5) as executor:
    for row in csvReader: #for each row in the csv file
        futures = executor.submit(enable_forms, row) #run the enable forms function on that row
        futures_list.append(futures) #add to the progress bar shown
#This is used to set the colors of the progress bar
    barSettings = {
        'total': len(futures_list),
        'desc': 'Script Completion Progress',
        'leave': True,
        'colour': 'green'
    }

    for i in tqdm(concurrent.futures.as_completed(futures_list), **barSettings):
        pass