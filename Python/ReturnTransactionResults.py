import csv
import urllib3
import concurrent.futures
import json
import requests
import pprint
import time
from datetime import datetime
from tqdm import tqdm, trange
api_url_base = 'https://loanpro.simnang.com/api/public/api/1/odata.svc/Loans'
source_file = 'loans4scripts.csv'
output_file = 'APIRespnse.csv'
http = urllib3.PoolManager()
headers = {
            'Content-Type': 'application/json',
            'Autopal-Instance-Id': '5200776',
            'Authorization': 'Bearer e273f929e835ad5047a4b93e9ce3dea6eb43b099'
        }

#initiate the CSV reader to open and read the csv file
csvReader = csv.reader(open(source_file, newline = ''), delimiter = ',')
#skip the first line in the CSV file the header
next(csvReader, None)

#def get_transactions(loanid):
    #response = http.request('GET', f"{api_url_base}({loanid})", headers = headers)
    #pprint.ppprint(json.loads(respnse.content))
    #json_return = json.dumps(response)
    #json_response = json.dumps(json_response)
    #print(json_return)

def get_transactions(row):
    loanid = row[0]
    url = ("https://loanpro.simnang.com/api/public/api/1/odata.svc/Loans("+ loanid +")?$expand=LoanSettings,LoanSetup,EscrowCalculators,Transactions,Autopays,StatusArchive,Payments%26nopaging=true")
    response = requests.get(url, headers = headers)
    print(response.content)
    #pprint.pprint(json.loads(response.content))




futures_list = []
with concurrent.futures.ThreadPoolExecutor(max_workers=5) as executor:
    for row in csvReader:
        futures = executor.submit(get_transactions, row)
        futures_list.append(futures)

    barSettings = {
        'total': len(futures_list),
        'desc': 'Script Completion Progress',
        'leave': True,
        'colour': 'green'
    }

    for i in tqdm(concurrent.futures.as_completed(futures_list), **barSettings):
        pass
