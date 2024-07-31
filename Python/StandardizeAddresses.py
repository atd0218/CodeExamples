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

# see if keys exist
def keys_exist(json_return, *keys):
    for key in keys:
        try:
            json_return = json_return[key]
        except Exception as e:
            return False
    return True

#get Address ID    
def get_addressid(env, custid, headers):
    response = http.request('GET', f"https://{env}.simnang.com/api/public/api/1/odata.svc/Customers({custid})/PrimaryAddress", headers = headers)
    json_return = json.loads(response.data)

    if keys_exist(json_return, 'd', 'id'):
        return json_return['d']['id']
    else:
        return 'Result Not Found'

# get Mailing Address ID
def get_mailaddressid(env, custid, headers):
    response = http.request('GET', f"https://{env}.simnang.com/api/public/api/1/odata.svc/Customers({custid})/MailAddress", headers = headers)
    json_return = json.loads(response.data)

    if keys_exist(json_return, 'd', 'id'):
        return json_return['d']['id']
    else:
        return 'Result Not Found'

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


#function to standardize and verify the address
def standardize_address(row, env, head):
    custid = row[0]
    addressId = get_addressid(env, custid, head)
    mailaddressId = get_mailaddressid(env,custid, head)
    #standardize Primary Address 
    data = {
                "isStandardized": 1
           }
    encoded_data = json.dumps(data).encode('utf-8')
    response = http.request('POST', f"https://{env}.simnang.com/api/public/api/1/odata.svc/Address({addressId})/standardize", headers = head, body = encoded_data)
    #verify Primary Address
    data2 = {
                "verified": True
           }
    encoded_data2 = json.dumps(data2).encode('utf-8')
    response = http.request('POST', f"https://{env}.simnang.com/api/public/api/1/odata.svc/Address({addressId})/verify", headers = head, body = encoded_data2)
    #standardize Mailing Address 
    data3 = {
                "isStandardized": 1
           }
    encoded_data3 = json.dumps(data3).encode('utf-8')
    response = http.request('POST', f"https://{env}.simnang.com/api/public/api/1/odata.svc/Address({mailaddressId})/standardize", headers = head, body = encoded_data3)
    #verify Mailing Address
    data4 = {
                "verified": True
           }
    encoded_data4 = json.dumps(data4).encode('utf-8')
    response = http.request('POST', f"https://{env}.simnang.com/api/public/api/1/odata.svc/Address({mailaddressId})/verify", headers = head, body = encoded_data4)

#function to set threading and run through each row in the CSV
def start_threading(env, head):
    #initiate CSV Reader
    source_file = 'loans4scripts.csv'
    csvReader = csv.reader(open(source_file, newline = ''), delimiter = ',')
    next(csvReader, None)

    #initiate threading of the calls
    futures_list = []
    with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
        for row in csvReader:
            futures = executor.submit(standardize_address, row, env, head)
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
    start_threading(env, headers)

#calls the namespace of the program so we can force main to run
if __name__ == "__main__":
    main()



