import csv
import urllib3
import concurrent.futures
import json
import time
from datetime import datetime
from tqdm import tqdm, trange


# Environment will be either loanpro or a PSaaS client name
environment = 'loanpro' 
#Source file that will be past in containing loan id's
source_file = 'loans4scripts.csv'
#This is used to set the URL for the API Request
api_url_base = f'https://{environment}.simnang.com/api/public/api/1/odata.svc/CustomFields'
#initiating urllib3 to make HTTP requests
http = urllib3.PoolManager()

#Headers used for API calls
headers = {
            'Content-Type': 'application/json',
            'Autopal-Instance-Id': '5200075',
            'Authorization': 'Bearer e3a1b0c7e02615cd083d1b7d10499d2e4a2748e4'
        }
        
#initiate the CSV reader to open and read the csv file
csvReader = csv.reader(open(source_file, newline = ''), delimiter = ',')
#skip the first line in the CSV file the header
next(csvReader, None)




def enable_fields(row):
    fieldid = row[0]
    data = {
                "reportEnabled": "1",
                "__update": True
            }
#encode the data into utf-8        
    encoded_data = json.dumps(data).encode('utf-8')
#append to the url the field id and set the headers and body
    response = http.request('PUT', f"{api_url_base}({fieldid})", headers = headers, body = encoded_data)
    
#Create the futures_list array for the progress bar
futures_list = []
#sets the number of threads to be used while executing the for each loop
with concurrent.futures.ThreadPoolExecutor(max_workers=5) as executor:
    for row in csvReader: #for each row in the csv file
        futures = executor.submit(enable_fields, row) #run the enable rules function on that row
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