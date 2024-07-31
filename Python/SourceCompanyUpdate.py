#Write a script that will read in custom field data for source companies and then update the source companies
#need to have it recognize if a field in the csv file is blank and ignore it

import csv
import urllib3
import concurrent.futures
import json
import time
import argparse
import sys
from itertools import filterfalse
from itertools import chain
from datetime import datetime
from tqdm import tqdm, trange

#initiate http requests
http = urllib3.PoolManager()

#input validation for headers
def valid_headers(e, h):
    try:
        response = http.request('GET', f"https://{e}.simnang.com/api/public/api/1/odata.svc/Loans", headers = h, timeout=3.0, retries=False)
    
        if response.status == 200:
            print('Headers are valid')
            return True 
        elif response.status == 409:
            print('Invalid Instance ID Please try again')
            return False
        elif response.status == 401:
            print('Invalid Headers please try again')
            return False
    except urllib3.exceptions.ConnectTimeoutError:
        print('Invalid URL please try again')

    # Figure out how to manually set a timeout of like 5 seconds, if the response doesn't


#function to run the rules
def run_rules(row, env, head):
    scid = row[0]
    cfid = row[1]
    cfvalue = row[2]
    results = []
    results.append({"customFieldId": cfid, "customFieldValue": cfvalue})
    data = {
                "CustomFieldValues": {
                    "results": results
                           }
            }
    encoded_data = json.dumps(data).encode('utf-8')
    response = http.request('PUT', f"https://{env}.simnang.com/api/public/api/1/odata.svc/SourceCompanies({scid})", headers = head, body = encoded_data)

#function to set threading and run through each row in the CSV
def start_threading(env, head):
    #initiate CSV Reader
    source_file = 'sourceCompanyFields.csv'
    csvReader = csv.reader(open(source_file, newline = ''), delimiter = ',')
    next(csvReader, None)
    #filter out any blank lines in the CSV file then skip the first line
    #filt_csv = itertools.filterfalse(lambda x: x != ('\n'), csvReader)
    #csvReaderFilt = csv.reader(filt_csv)
    #next(csvReaderFilt, None)

    #initiate threading of the calls while ignoring any blank data in the csv
    futures_list = []
    with concurrent.futures.ThreadPoolExecutor(max_workers=6) as executor:
        for row in csvReader:
            if any(x.strip() for x in row):
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
        inId = input('Enter the Autopal-Instance-Id for the tenant: ')
        token = input('Enter the Authorization token for the tenant: ').lower()

        headers = {
                    'Content-Type': 'application/json',
                    'Autopal-Instance-Id': f'{inId}',
                    'Authorization': f'Bearer {token}'
                }

        if valid_headers(env, headers):
            break

    #call initiate threading function
    #start_threading(env, headers)

    while True:
        print("\nOkay! Here's what I've got:"
              + "\n=========================================="
              + "\nEnvironment: " + env 
              + "\nTenant: " + headers["Autopal-Instance-Id"])
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