import csv
import urllib3
import concurrent.futures
import json
import time
from datetime import datetime
from tqdm import tqdm, trange


# Environment will be either loanpro or a PSaaS client name
environment = 'loanpro' 
#This is used to set the URL for the API Request
api_url_base = f'https://{environment}.simnang.com/api/public/api/1/odata.svc/Loans'
#Source file that will be past in containing loan id's
source_file = 'loans4scripts.csv'
#initiating urllib3 to make HTTP requests
http = urllib3.PoolManager()

#Headers used for API calls
headers = {
            'Content-Type': 'application/json',
            'Autopal-Instance-Id': '5201555',
            'Authorization': 'Bearer cebd13e109be717da105c1129d63424add2a2966'
        }
        
#initiate the CSV reader to open and read the csv file
csvReader = csv.reader(open(source_file, newline = ''), delimiter = ',')
#skip the first line in the CSV file the header
next(csvReader, None)


def enable_rules(row):
    loanid = row[0]
    portId = row[1]
    results = []
    split_rules = portId.split('.')
    for Id in split_rules:
        results.append({"__id": Id, 
                        "__destroy": True})
    data = {
                "Portfolios": {
                    "results": results
                },
                "__update": True,
                "__id": loanid
            }
#encode the data into utf-8        
    encoded_data = json.dumps(data).encode('utf-8')
#append to the urs the loan id and set the headers and body
    response = http.request('PUT', f"{api_url_base}({loanid})", headers = headers, body = encoded_data)
    
#Create the futures_list array for the progress bar
futures_list = []
#sets the number of threads to be used while executing the for each loop
with concurrent.futures.ThreadPoolExecutor(max_workers=4) as executor:
    for row in csvReader: #for each row in the csv file
        futures = executor.submit(enable_rules, row) #run the enable rules function on that row
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