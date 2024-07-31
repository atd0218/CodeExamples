import csv
import urllib3
import concurrent.futures
import json
import time
import argparse
import sys
from datetime import datetime
from tqdm import tqdm, trange

#initiate http requests
http = urllib3.PoolManager()

#Function to decide what transaction status this will be updated to
#def transaction_status():
#    while True:
#        print('Enter 1 to change status to Settled Successfully')
#        print('Enter 2 to change status to Processing')
#        print('Enter 3 to change status to Failed')
#
#        choice = int(input('Enter Your Choice'))
#
#        if(choice == 1):
#            return "SETTLED SUCCESSFULLY"
#        elif(choice == 2):
#            return "PROCESSING"
#        elif(choice == 3):
#            return "FAILED"
#        else:
#            print("Invalid Choice")


#input validation for headers
def valid_headers(e, h):
    try:
        response = http.request('GET', f"https://{e}.simnang.com/api/processors/nacha", headers = h, timeout=3.0, retries=False)
    
        if response.status == 200:
            print('Headers are valid')
            return True 
        elif response.status == 409 or 404:
            print('Invalid URL Please try again')
            return False
        elif response.status == 401:
            print('Invalid Headers please try again')
            return False
    except urllib3.exceptions.ConnectTimeoutError:
        print('Invalid URL please try again')

#function to run the rules
def run_rules(row, env, head):
    tranID = row[0]
    status = row[1]
    Rcode = row[2]
    comments = row [3]
    results = " "
    results.append({"message": "Transaction Status Updated", "status": f"{status}", "reason_code": f"{Rcode}"})
    data = {
                "transaction": {
                    results
                             }
            }
    encoded_data = json.dumps(data).encode('utf-8')
    response = http.request('PUT', f"https://{env}.simnang.com/api/transactions/{tranID}", headers = head, body = encoded_data)

#function to set threading and run through each row in the CSV
def start_threading(env, head):
    #initiate CSV Reader
    source_file = 'TransactionUpdate.csv'
    csvReader = csv.reader(open(source_file, newline = ''), delimiter = ',')
    next(csvReader, None)

    #initiate threading of the calls
    futures_list = []
    with concurrent.futures.ThreadPoolExecutor(max_workers=6) as executor:
        for row in csvReader:
            futures = executor.submit(run_rules, row, env, head)
            futures_list.append(futures)

        barSettings = {
            'total': len(futures_list),
            'desc': 'Script Completion Progress',
            'leave': True,
            'colour': 'green'
        }

        for i in tqdm(concurrent.futures.as_completed(futures_list), **barSettings):
            pass

def main():
    #enter input to populate calls
    while True:
        env = input('Enter the Environment you will be running the call for: ').lower()
        secret = input('Enter the Secret for the tenant: ')
        token = input('Enter the Token for the tenant: ')

        headers = {
                    'Content-Type': 'application/json',
                    'Authorization': f'{token}',
                    'Secret': f'{secret}'
                }

        if valid_headers(env, headers):
            break

    #call initiate threading function
    while True:
        print("\nOkay! Here's what I've got:"
              + "\n=========================================="
              + "\nEnvironment: " + env 
             )
        confirm = input("\nIs this correct? y/n\n")
        if confirm == "y":
            print("\nWorking on it...")
            start_threading(env, headers)
            break
        elif confirm == "n":
            print("\nOops! Try setup again.")
            main()
            break
        else:
            print("\nInvalid selection, please try again.")

#calls the namespace of the program so we can force main to run
if __name__ == "__main__":
    main()