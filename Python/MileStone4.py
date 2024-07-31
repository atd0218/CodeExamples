import boto3
import botocore
import pprint
import time
import pandas as pd
from decimal import Decimal
import string
import json
import amazondax
from boto3.dynamodb.conditions import Key
from datetime import datetime

#variables
client = boto3.client("dynamodb")
tableName = 'Table2Test21'
tableName2 = 'Table2Test21Restore'
backupName = 'testBackup2'
GTName = 'Phase3V2GlobalTable5'

#Create DynamoDB On-Demand Backup and wait for it to complete
def createBackup():
	response = client.create_backup(
    TableName=tableName,
    BackupName=backupName
)
	global backupStatus
	backupStatus = response['BackupDetails']['BackupArn']

	start_time = time.time()
	while True:
		response2 = client.describe_backup(
		BackupArn=backupStatus
			)

		if response2['BackupDescription']['BackupDetails']['BackupStatus'] == "AVAILABLE":
			end_time = time.time()
			total_time = end_time - start_time
			print(f"Backup has Completed! It took: {total_time:.4f} seconds.\n")
			break
		elif response2['BackupDescription']['BackupDetails']['BackupStatus'] == "CREATING":
	        # interval_time = time.time()
	        # time_since = interval_time - start_time
	        # print(f"Current Import Status: {response['ImportTableDescription']['ImportStatus']}.")
	        # print(f"\n Import has been running for: {time_since:.4f} seconds.")
			time.sleep(10)

#Restore the backup once it has completed. 
def restoreBackup():
	response = client.restore_table_from_backup(
    TargetTableName=tableName2,
    BackupArn=backupStatus,
    BillingModeOverride='PAY_PER_REQUEST',
    SSESpecificationOverride={
        'Enabled': False
    }
)
	print(f"Backup is now restoring for table: {tableName2}")


#Use Transaction APIs on Global Tables

def transactWriteItem():

	print(f"Starting Transact Writes for table: {GTName}!")

	response = client.transact_write_items(
    TransactItems=[
        {
            'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test',
                        
                    },
                    'sort_key':{
                    	'N': '123'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test1',
                        
                    },
                    'sort_key':{
                    	'N': '1231'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test11',
                        
                    },
                    'sort_key':{
                    	'N': '12311'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test111',
                        
                    },
                    'sort_key':{
                    	'N': '123111'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test2',
                        
                    },
                    'sort_key':{
                    	'N': '1232'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test22',
                        
                    },
                    'sort_key':{
                    	'N': '12322'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test222',
                        
                    },
                    'sort_key':{
                    	'N': '123222'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test3',
                        
                    },
                    'sort_key':{
                    	'N': '1233'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test33',
                        
                    },
                    'sort_key':{
                    	'N': '12333'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test333',
                        
                    },
                    'sort_key':{
                    	'N': '123333'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test4',
                        
                    },
                    'sort_key':{
                    	'N': '1234'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test44',
                        
                    },
                    'sort_key':{
                    	'N': '12344'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test444',
                        
                    },
                    'sort_key':{
                    	'N': '123444'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test5',
                        
                    },
                    'sort_key':{
                    	'N': '1235'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test55',
                        
                    },
                    'sort_key':{
                    	'N': '12355'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test555',
                        
                    },
                    'sort_key':{
                    	'N': '123555'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test66',
                        
                    },
                    'sort_key':{
                    	'N': '123667'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test77',
                        
                    },
                    'sort_key':{
                    	'N': '12377'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test8',
                        
                    },
                    'sort_key':{
                    	'N': '1238'
                    }
                },
                'TableName': GTName
            },
             'Put': {
                'Item': {
                    'partition_key': {
                        'S': 'test882',
                        
                    },
                    'sort_key':{
                    	'N': '12388'
                    }
                },
                'TableName': GTName
            }
      	},
    ]
)


if __name__ == '__main__':
	# backup_create = createBackup()
	# backup_restore = restoreBackup()
	transact_write = transactWriteItem()
