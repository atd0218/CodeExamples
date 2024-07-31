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

#Skip deleted or archived loans
def skip_loan(loanid, e, h):
    response = http.request("GET", f"https://{e}.simnang.com/api/public/api/1/odata.svc/Loans({loanid})", headers = h)
    json_return = json.loads(response.data)
    if json_return["d"]["archived"] == 1 or json_return["d"]["deleted"] == 1:
        return True
    else:
        return False


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

#prompt user to give type of rule they want to update
#def get_rule_type():
#    while True:
#        print('Enter 1 to update Loan Settings Rules')
 #       print('Enter 2 to update Credits Rules')
#        print('Enter 3 to update Past Due Reset Rules')
 #       print('Enter 4 to update Checklists Rules')
  #      print('Enter 5 to update Due Date Rules')
#        print('Enter 6 to update Stop Interest Rules')
#        print('Enter 7 to update Account Tools Rules')
#        print('Enter 8 to update Customer Tools Rules')
#        print('Enter 9 to update Autopay Rules')
#        print('Enter 10 to update Loan Setup Rules')
#        print('Enter 11 to update Bankruptcy Rules')

#        choice = int(input('Enter your Choice:'))

#        if (choice == 1):
#            return "RuleAppliedLoanSettings"

#        elif (choice == 2):
#            return "RuleAppliedChargeOff"
            

#        elif (choice == 3):
#            return "RuleAppliedAPDReset"
                    
#        elif (choice == 4):
#            return "RuleAppliedChecklists"
                         
#        elif (choice == 5):
#            return "RuleAppliedChangeDueDates"
             
#        elif (choice == 6):
#            return "RuleAppliedStopInterest"
             
#        elif (choice == 7):
#            return "RuleAppliedAccountTools"             

#        elif (choice == 8):
#            return "RuleAppliedCustomerTools"
             
#        elif (choice == 9):
#            return "RuleAppliedAutopay"
             
#        elif (choice == 10):
#            return "RuleAppliedLoanSetup"
             
#        elif (choice == 11):
#            return "RuleAppliedBankruptcy"
                         
#        else:
#            print('Invalid Choice')

#ask user if they want to enable or disable rules
def get_status_type():
    while True:
        print('Enter 1 to activate rules')
        print('Enter 2 to inactivate rules')

        choice = int(input('Enter your Choice:'))

        if (choice == 1):
            return "activate"
        else:
            return "inactivate"

#function to run the rules
def run_rules(row, env, head, st):
    loanid = row[0]
    if skip_loan(loanid, env, head):
        return 
    else:
        rules = row[1]
        results = []
        split_rules = rules.split('.')
        for rule in split_rules:
            if st == "activate":
                results.append({"__id": f"{rule}", "enabled": True, "__destroy": False})
            else:
                results.append({"__id": f"{rule}", "enabled": False, "__destroy": True})
        data = {
                    "id": f"{loanid}",
                    "RuleApplied": {
                        "results": results
                               },
                    "__id": f"{loanid}",
                    "__update": True
                }
        encoded_data = json.dumps(data).encode('utf-8')
        response = http.request('PUT', f"https://{env}.simnang.com/api/public/api/1/odata.svc/Loans({loanid})", headers = head, body = encoded_data)
#function to set threading and run through each row in the CSV
def start_threading(env, head, st):
    #initiate CSV Reader
    source_file = 'loans4scripts.csv'
    csvReader = csv.reader(open(source_file, newline = ''), delimiter = ',')
    next(csvReader, None)

    #initiate threading of the calls
    futures_list = []
    with concurrent.futures.ThreadPoolExecutor(max_workers=10
        ) as executor:
        for row in csvReader:
            futures = executor.submit(run_rules, row, env, head, st)
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

    # call function for rule type
    #rule_type = get_rule_type()

    # call function for status type
    status_type = get_status_type()

    #call initiate threading function
    start_threading(env, headers, status_type)

#calls the namespace of the program so we can force main to run
if __name__ == "__main__":
    main()



