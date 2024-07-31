import csv
import urllib3
import concurrent.futures
import json
import time
from datetime import datetime
from tqdm import tqdm, trange
api_url_base = 'https://loanpro.simnang.com/api/public/api/1/odata.svc/Loans'
source_file = 'loans4scripts.csv'
http = urllib3.PoolManager()
headers = {
            'Content-Type': 'application/json',
            'Autopal-Instance-Id': '5201555',
            'Authorization': 'Bearer cebd13e109be717da105c1129d63424add2a2966'
        }
        
#initiate the CSV reader to open and read the csv file
csvReader = csv.reader(open(source_file, newline = ''), delimiter = ',')
#skip the first line in the CSV file the header
next(csvReader, None)

def keys_exist(json_return, *keys):
    for key in keys:
        try:
            json_return = json_return[key]
        except Exception as e:
            return False
    return True
    
def get_setupid(loanid):
    response = http.request('GET', f"{api_url_base}({loanid})", headers = headers)
    json_return = json.loads(response.data)

    if keys_exist(json_return, 'd', 'setupId'):
        return json_return['d']['setupId']
    else:
        return 'Result Not Found'
        
def inactivate_loan(row):
    loanId = row[0]
    setupid = get_setupid(loanId)
    data = {
                "LoanSetup": {
                    "active": 0,
                    "__id": setupid,
                    "__update": True
                }
            }
    encoded_data = json.dumps(data).encode('utf-8')
    if setupid != 'Result Not Found':
        response = http.request('PUT', f"{api_url_base}({loanId})", headers = headers, body = encoded_data)
    else:
        return setupid
        
    data2 = {
                
            "LoanSetup": {
                "interestApplication": "loan.interestApplication.betweenTransactions",
                "firstPeriodDays": "loan.firstPeriodDays.actual",
                "daysInYear": "loan.daysInYear.actual",
                "__id": setupid,
                "__update": True
            }
    }
    encoded_data2 = json.dumps(data2).encode('utf-8')
    response2 = http.request('PUT', f"{api_url_base}({loanId})", headers = headers, body = encoded_data2)
    
    data3 = {
         "__ignoreWarnings": True   
    }
    encoded_data3 = json.dumps(data3).encode('utf-8')
    response3 = http.request('POST', f"https://loanpro.simnang.com/api/public/api/1/Loans({loanId})/AutoPal.Activate()", headers = headers, body = encoded_data3)

futures_list = []
with concurrent.futures.ThreadPoolExecutor(max_workers=5) as executor:
    for row in csvReader:
        futures = executor.submit(inactivate_loan, row)
        futures_list.append(futures)

    barSettings = {
        'total': len(futures_list),
        'desc': 'Script Completion Progress',
        'leave': True,
        'colour': 'green'
    }

    for i in tqdm(concurrent.futures.as_completed(futures_list), **barSettings):
        pass