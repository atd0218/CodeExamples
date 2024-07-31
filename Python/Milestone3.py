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

#Variable Definitions
tableName = 'Phase3-auto-LSITest7'
secondTableName = 'Phase3-autoTest1'
thirdTableName = 'Phase3-On-Demand'
fourthTablename = 'Phase3V1GlobalTable5'
fifthTablename = 'Phase3V2GlobalTable5'
sixthTablename = 'Phase3V1GlobalTable5'
seventhTablename = 'Phase3V1GlobalTable5'
tableArn = f'arn:aws:dynamodb:us-east-1:699043355965:table/{fifthTablename}'
client = boto3.client('dynamodb')
client2 = boto3.client('lambda')
autoscale = boto3.client('application-autoscaling')
pp = pprint.PrettyPrinter(indent=0)
functionName = 'Milestone2StreamToNewTable'

#Create a table with one LSI and 2 partition keys to store 15GB to each partition key.
def CreateTableOne():
    response = client.create_table(
    AttributeDefinitions=[
        {
            'AttributeName': 'partition_key',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'sort_key',
            'AttributeType': 'N'
        },
        {
            'AttributeName': 'some_data',
            'AttributeType': 'S'
        }
    ],
    TableName= tableName,
    KeySchema=[
        {
            'AttributeName': 'partition_key',
            'KeyType': 'HASH'
        },
        {
            'AttributeName': 'sort_key',
            'KeyType': 'RANGE'
        }
    ],
    LocalSecondaryIndexes=[
        {
            'IndexName': 'TestIndex',
            'KeySchema': [
                {
                    'AttributeName': 'partition_key',
                    'KeyType': 'HASH'
                },
                {
                    'AttributeName': 'some_data',
                    'KeyType': 'RANGE'
                }
            ],
            'Projection': {
                'ProjectionType': 'ALL'
            }
        },
    ],
    BillingMode='PROVISIONED',
    ProvisionedThroughput={
        'ReadCapacityUnits': 1000,
        'WriteCapacityUnits': 1000
    },
    SSESpecification={
        'Enabled': False
    }
)
    print(f"{tableName} Table Creation in Progress!")

#Need logic to see when table status is Active so I can add autoscaling to the table
def tableOneCreated():
    start_time = time.time()
    while True:
        response = client.describe_table(
        TableName= tableName
)
        if response['Table']['TableStatus'] == "ACTIVE":
            end_time = time.time()
            total_time = end_time - start_time
            print(f"Table Created Total Time Was: {total_time:.4f} seconds.\n")
            break
        elif response['Table']['TableStatus'] == "CREATING":
            # interval_time = time.time()
            # time_since = interval_time - start_time
            # print(f"Current Import Status: {response['ImportTableDescription']['ImportStatus']}.")
            # print(f"\n Import has been running for: {time_since:.4f} seconds.")
            time.sleep(10)
        # elif response['ImportTableDescription']['ImportStatus'] == "FAILED":
        #     end_time = time.time()
        #     total_time = end_time - start_time
        #     print(f"Import Completed Total Time Was: {total_time:.4f} seconds.")
        #     break

#Turn on Application Autoscaling for the table
def enableAutoscaling():
	#Read capacity
	autoscale.register_scalable_target(ServiceNamespace='dynamodb',
	                                            ResourceId=f'table/{tableName}',
	                                            ScalableDimension='dynamodb:table:ReadCapacityUnits',
	                                            MinCapacity=1000,
	                                            MaxCapacity=3000)
	#Write capacity
	autoscale.register_scalable_target(ServiceNamespace='dynamodb',
	                                            ResourceId=f'table/{tableName}',
	                                            ScalableDimension='dynamodb:table:WriteCapacityUnits',
	                                            MinCapacity=1000,
	                                            MaxCapacity=3000)

	percent_of_use_to_aim_for = 50.0
	scale_out_cooldown_in_seconds = 30
	scale_in_cooldown_in_seconds = 30
	autoscale.put_scaling_policy(ServiceNamespace='dynamodb',
	                                    ResourceId=f'table/{tableName}',
	                                    PolicyType='TargetTrackingScaling',
	                                    PolicyName='ScaleDynamoDBReadCapacityUtilization',
	                                    ScalableDimension='dynamodb:table:ReadCapacityUnits',
	                                    TargetTrackingScalingPolicyConfiguration={
	                                      'TargetValue': percent_of_use_to_aim_for,
	                                      'PredefinedMetricSpecification': {
	                                        'PredefinedMetricType': 'DynamoDBReadCapacityUtilization'
	                                      },
	                                      'ScaleOutCooldown': scale_out_cooldown_in_seconds,
	                                      'ScaleInCooldown': scale_in_cooldown_in_seconds
	                                    })
	autoscale.put_scaling_policy(ServiceNamespace='dynamodb',
	                                    ResourceId=f'table/{tableName}',
	                                    PolicyType='TargetTrackingScaling',
	                                    PolicyName='ScaleDynamoDBWriteCapacityUtilization',
	                                    ScalableDimension='dynamodb:table:WriteCapacityUnits',
	                                    TargetTrackingScalingPolicyConfiguration={
	                                      'TargetValue': percent_of_use_to_aim_for,
	                                      'PredefinedMetricSpecification': {
	                                        'PredefinedMetricType': 'DynamoDBWriteCapacityUtilization'
	                                      },
	                                      'ScaleOutCooldown': scale_out_cooldown_in_seconds,
	                                      'ScaleInCooldown': scale_in_cooldown_in_seconds
	                                    })
	print(f"Autoscaling enabled for table {tableName}!")

#Write Data to the first table partition

def writePartitionKey1(key_count, item_size, dyn_resource=None):
    print(f"Beginning Table Population for {tableName}!")
    """
    Writes test data to the demonstration table.

    :param key_count: The number of partition and sort keys to use to populate the
                      table. The total number of items is key_count * key_count.
    :param item_size: The size of non-key data for each test item.
    :param dyn_resource: Either a Boto3 or DAX resource.
    """
    if dyn_resource is None:
        dyn_resource = boto3.resource('dynamodb')

    table = dyn_resource.Table(tableName)
    some_data = 'X' * item_size
    some_data2 = 'Y' * item_size 
    some_data3 = 'Z' * item_size 
    some_data4 = 'A' * item_size 
    some_data5 = 'XYZA' * item_size 
    some_data6 = 'Ash' * item_size 
    some_data7 = 'X' * item_size
    some_data8 = 'Y' * item_size 
    some_data9 = 'Z' * item_size 
    some_data10 = 'A' * item_size 
    some_data11 = 'XYZA' * item_size 
    some_data12 = 'Ash' * item_size
    some_data13 = 'X' * item_size
    some_data14 = 'Y' * item_size 
    some_data15 = 'Z' * item_size 
    some_data16 = 'A' * item_size 
    some_data17 = 'XYZA' * item_size 
    some_data18 = 'Ash' * item_size
    some_data19 = 'X' * item_size
    some_data20 = 'Y' * item_size 
    some_data21 = 'Z' * item_size 
    some_data22 = 'A' * item_size 
    some_data23 = 'XYZA' * item_size 
    some_data24 = 'Ash' * item_size
    some_data25 = 'X' * item_size
    some_data26 = 'Y' * item_size 
    some_data27 = 'Z' * item_size 
    some_data28 = 'A' * item_size 
    some_data29 = 'XYZA' * item_size 
    some_data30 = 'Ash' * item_size
    some_data31 = 'X' * item_size
    some_data32 = 'Y' * item_size 
    some_data33 = 'Z' * item_size 
    some_data34 = 'A' * item_size 
    some_data35 = 'XYZA' * item_size 
    some_data36 = 'Ash' * item_size
    some_data37 = 'X' * item_size
    some_data38 = 'Y' * item_size 
    some_data39 = 'Z' * item_size 
    some_data40 = 'A' * item_size 
    some_data41 = 'XYZA' * item_size 
    some_data42 = 'Ash' * item_size

    with table.batch_writer() as batch:
	    for sort_key in range(1, key_count + 1):
	    	try:
		        batch.put_item(Item={
		            'partition_key': 'deiwo2',
		            'sort_key': sort_key,
		            'some_data': some_data,
		            'some_data2': some_data2,
		            'some_data3': some_data3,
		            'some_data4': some_data4,
		            'some_data5': some_data5,
		            'some_data6': some_data6,
		            'some_data7': some_data7,
		            'some_data8': some_data8,
		            'some_data9': some_data9,
		            'some_data10': some_data10,
		            'some_data11': some_data11,
		            'some_data12': some_data12,
		            'some_data13': some_data13,
		            'some_data14': some_data14,
		            'some_data15': some_data15,
		            'some_data16': some_data16,
		            'some_data17': some_data17,
		            'some_data18': some_data18,
		            'some_data19': some_data19,
		            'some_data20': some_data20,
		            'some_data21': some_data21,
		            'some_data22': some_data22,
		            'some_data23': some_data23,
		            'some_data24': some_data24,
		            'some_data25': some_data25,
		            'some_data26': some_data26,
		            'some_data27': some_data27,
		            'some_data28': some_data28,
		            'some_data29': some_data29,
		            'some_data30': some_data30,
		            'some_data31': some_data31,
		            'some_data32': some_data32,
		            'some_data33': some_data33,
		            'some_data34': some_data34,
		            'some_data35': some_data35,
		            'some_data36': some_data36,
		            'some_data37': some_data37,
		            'some_data38': some_data38,
		            'some_data39': some_data39,
		            'some_data40': some_data40,
		            'some_data41': some_data41,
		            'some_data42': some_data42
		        })
		        print(f"Put item ({sort_key}) succeeded.")
	    		
    		except Exception as error:
    			print(error)
    			continue

	    	except botocore.exceptions.ClientError as clientError:
	    		print(clientError)
	    		continue

#Write Data to the second table partition.

def writePartitionKey2(key_count, item_size, dyn_resource=None):
    print(f"Beginning Table Population for {tableName}!")
    """
    Writes test data to the demonstration table.

    :param key_count: The number of partition and sort keys to use to populate the
                      table. The total number of items is key_count * key_count.
    :param item_size: The size of non-key data for each test item.
    :param dyn_resource: Either a Boto3 or DAX resource.
    """
    if dyn_resource is None:
        dyn_resource = boto3.resource('dynamodb')

    table = dyn_resource.Table(tableName)
    some_data = 'X' * item_size
    some_data2 = 'Y' * item_size 
    some_data3 = 'Z' * item_size 
    some_data4 = 'A' * item_size 
    some_data5 = 'XYZA' * item_size 
    some_data6 = 'Ash' * item_size 
    some_data7 = 'X' * item_size
    some_data8 = 'Y' * item_size 
    some_data9 = 'Z' * item_size 
    some_data10 = 'A' * item_size 
    some_data11 = 'XYZA' * item_size 
    some_data12 = 'Ash' * item_size
    some_data13 = 'X' * item_size
    some_data14 = 'Y' * item_size 
    some_data15 = 'Z' * item_size 
    some_data16 = 'A' * item_size 
    some_data17 = 'XYZA' * item_size 
    some_data18 = 'Ash' * item_size
    some_data19 = 'X' * item_size
    some_data20 = 'Y' * item_size 
    some_data21 = 'Z' * item_size 
    some_data22 = 'A' * item_size 
    some_data23 = 'XYZA' * item_size 
    some_data24 = 'Ash' * item_size
    some_data25 = 'X' * item_size
    some_data26 = 'Y' * item_size 
    some_data27 = 'Z' * item_size 
    some_data28 = 'A' * item_size 
    some_data29 = 'XYZA' * item_size 
    some_data30 = 'Ash' * item_size
    some_data31 = 'X' * item_size
    some_data32 = 'Y' * item_size 
    some_data33 = 'Z' * item_size 
    some_data34 = 'A' * item_size 
    some_data35 = 'XYZA' * item_size 
    some_data36 = 'Ash' * item_size
    some_data37 = 'X' * item_size
    some_data38 = 'Y' * item_size 
    some_data39 = 'Z' * item_size 
    some_data40 = 'A' * item_size 
    some_data41 = 'XYZA' * item_size 
    some_data42 = 'Ash' * item_size 

    with table.batch_writer() as batch:
	    for sort_key in range(1, key_count + 1):
	    	try:
		        batch.put_item(Item={
		            'partition_key': 'askfjew',
		            'sort_key': sort_key,
		            'some_data': some_data,
		            'some_data2': some_data2,
		            'some_data3': some_data3,
		            'some_data4': some_data4,
		            'some_data5': some_data5,
		            'some_data6': some_data6,
		            'some_data7': some_data7,
		            'some_data8': some_data8,
		            'some_data9': some_data9,
		            'some_data10': some_data10,
		            'some_data11': some_data11,
		            'some_data12': some_data12,
		            'some_data13': some_data13,
		            'some_data14': some_data14,
		            'some_data15': some_data15,
		            'some_data16': some_data16,
		            'some_data17': some_data17,
		            'some_data18': some_data18,
		            'some_data19': some_data19,
		            'some_data20': some_data20,
		            'some_data21': some_data21,
		            'some_data22': some_data22,
		            'some_data23': some_data23,
		            'some_data24': some_data24,
		            'some_data25': some_data25,
		            'some_data26': some_data26,
		            'some_data27': some_data27,
		            'some_data28': some_data28,
		            'some_data29': some_data29,
		            'some_data30': some_data30,
		            'some_data31': some_data31,
		            'some_data32': some_data32,
		            'some_data33': some_data33,
		            'some_data34': some_data34,
		            'some_data35': some_data35,
		            'some_data36': some_data36,
		            'some_data37': some_data37,
		            'some_data38': some_data38,
		            'some_data39': some_data39,
		            'some_data40': some_data40,
		            'some_data41': some_data41,
		            'some_data42': some_data42
		        })
		        print(f"Put item ({sort_key}) succeeded.")
	    		
    		except Exception as error:
    			print(error)
    			continue

    		except botocore.exceptions.ClientError as clientError:
    			print(clientError)
    			continue

#Create a table with no LSI and 2 partition keys to store 15GB to each partition key.
def CreateTableTwo():
    response = client.create_table(
    AttributeDefinitions=[
        {
            'AttributeName': 'partition_key',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'sort_key',
            'AttributeType': 'N'
        }
    ],
    TableName= secondTableName,
    KeySchema=[
        {
            'AttributeName': 'partition_key',
            'KeyType': 'HASH'
        },
        {
            'AttributeName': 'sort_key',
            'KeyType': 'RANGE'
        }
    ],
    BillingMode='PROVISIONED',
    ProvisionedThroughput={
        'ReadCapacityUnits': 1000,
        'WriteCapacityUnits': 1000
    },
    SSESpecification={
        'Enabled': False
    }
)
    print(f"{secondTableName} Table Creation in Progress!")

#Need logic to see when table status is Active so I can add autoscaling to the table
def tableTwoCreated():
    start_time = time.time()
    while True:
        response = client.describe_table(
        TableName= secondTableName
)
        if response['Table']['TableStatus'] == "ACTIVE":
            end_time = time.time()
            total_time = end_time - start_time
            print(f"Table Created Total Time Was: {total_time:.4f} seconds.\n")
            break
        elif response['Table']['TableStatus'] == "CREATING":
            # interval_time = time.time()
            # time_since = interval_time - start_time
            # print(f"Current Import Status: {response['ImportTableDescription']['ImportStatus']}.")
            # print(f"\n Import has been running for: {time_since:.4f} seconds.")
            time.sleep(10)
        # elif response['ImportTableDescription']['ImportStatus'] == "FAILED":
        #     end_time = time.time()
        #     total_time = end_time - start_time
        #     print(f"Import Completed Total Time Was: {total_time:.4f} seconds.")
        #     break

#Turn on Application Autoscaling for the table
def enableAutoscalingTableTwo():
	#Read capacity
	autoscale.register_scalable_target(ServiceNamespace='dynamodb',
	                                            ResourceId=f'table/{secondTableName}',
	                                            ScalableDimension='dynamodb:table:ReadCapacityUnits',
	                                            MinCapacity=1000,
	                                            MaxCapacity=3000)
	#Write capacity
	autoscale.register_scalable_target(ServiceNamespace='dynamodb',
	                                            ResourceId=f'table/{secondTableName}',
	                                            ScalableDimension='dynamodb:table:WriteCapacityUnits',
	                                            MinCapacity=1000,
	                                            MaxCapacity=3000)

	percent_of_use_to_aim_for = 50.0
	scale_out_cooldown_in_seconds = 30
	scale_in_cooldown_in_seconds = 30
	autoscale.put_scaling_policy(ServiceNamespace='dynamodb',
	                                    ResourceId=f'table/{secondTableName}',
	                                    PolicyType='TargetTrackingScaling',
	                                    PolicyName='ScaleDynamoDBReadCapacityUtilization',
	                                    ScalableDimension='dynamodb:table:ReadCapacityUnits',
	                                    TargetTrackingScalingPolicyConfiguration={
	                                      'TargetValue': percent_of_use_to_aim_for,
	                                      'PredefinedMetricSpecification': {
	                                        'PredefinedMetricType': 'DynamoDBReadCapacityUtilization'
	                                      },
	                                      'ScaleOutCooldown': scale_out_cooldown_in_seconds,
	                                      'ScaleInCooldown': scale_in_cooldown_in_seconds
	                                    })
	autoscale.put_scaling_policy(ServiceNamespace='dynamodb',
	                                    ResourceId=f'table/{secondTableName}',
	                                    PolicyType='TargetTrackingScaling',
	                                    PolicyName='ScaleDynamoDBWriteCapacityUtilization',
	                                    ScalableDimension='dynamodb:table:WriteCapacityUnits',
	                                    TargetTrackingScalingPolicyConfiguration={
	                                      'TargetValue': percent_of_use_to_aim_for,
	                                      'PredefinedMetricSpecification': {
	                                        'PredefinedMetricType': 'DynamoDBWriteCapacityUtilization'
	                                      },
	                                      'ScaleOutCooldown': scale_out_cooldown_in_seconds,
	                                      'ScaleInCooldown': scale_in_cooldown_in_seconds
	                                    })
	print(f"Autoscaling enabled for table {secondTableName}!")

#Write Data to the first table partition

def writePartitionKey1TableTwo(key_count, item_size, dyn_resource=None):
    print(f"Beginning Table Population for {secondTableName}!")
    """
    Writes test data to the demonstration table.

    :param key_count: The number of partition and sort keys to use to populate the
                      table. The total number of items is key_count * key_count.
    :param item_size: The size of non-key data for each test item.
    :param dyn_resource: Either a Boto3 or DAX resource.
    """
    if dyn_resource is None:
        dyn_resource = boto3.resource('dynamodb')

    table = dyn_resource.Table(secondTableName)
    some_data = 'X' * item_size
    some_data2 = 'Y' * item_size 
    some_data3 = 'Z' * item_size 
    some_data4 = 'A' * item_size 
    some_data5 = 'XYZA' * item_size 
    some_data6 = 'Ash' * item_size 
    some_data7 = 'X' * item_size
    some_data8 = 'Y' * item_size 
    some_data9 = 'Z' * item_size 
    some_data10 = 'A' * item_size 
    some_data11 = 'XYZA' * item_size 
    some_data12 = 'Ash' * item_size
    some_data13 = 'X' * item_size
    some_data14 = 'Y' * item_size 
    some_data15 = 'Z' * item_size 
    some_data16 = 'A' * item_size 
    some_data17 = 'XYZA' * item_size 
    some_data18 = 'Ash' * item_size
    some_data19 = 'X' * item_size
    some_data20 = 'Y' * item_size 
    some_data21 = 'Z' * item_size 
    some_data22 = 'A' * item_size 
    some_data23 = 'XYZA' * item_size 
    some_data24 = 'Ash' * item_size
    some_data25 = 'X' * item_size
    some_data26 = 'Y' * item_size 
    some_data27 = 'Z' * item_size 
    some_data28 = 'A' * item_size 
    some_data29 = 'XYZA' * item_size 
    some_data30 = 'Ash' * item_size
    some_data31 = 'X' * item_size
    some_data32 = 'Y' * item_size 
    some_data33 = 'Z' * item_size 
    some_data34 = 'A' * item_size 
    some_data35 = 'XYZA' * item_size 
    some_data36 = 'Ash' * item_size
    some_data37 = 'X' * item_size
    some_data38 = 'Y' * item_size 
    some_data39 = 'Z' * item_size 
    some_data40 = 'A' * item_size 
    some_data41 = 'XYZA' * item_size 
    some_data42 = 'Ash' * item_size

    with table.batch_writer() as batch:
	    for sort_key in range(1, key_count + 1):
	    	try:
		        batch.put_item(Item={
		            'partition_key': 'askfjew',
		            'sort_key': sort_key,
		            'some_data': some_data,
		            'some_data2': some_data2,
		            'some_data3': some_data3,
		            'some_data4': some_data4,
		            'some_data5': some_data5,
		            'some_data6': some_data6,
		            'some_data7': some_data7,
		            'some_data8': some_data8,
		            'some_data9': some_data9,
		            'some_data10': some_data10,
		            'some_data11': some_data11,
		            'some_data12': some_data12,
		            'some_data13': some_data13,
		            'some_data14': some_data14,
		            'some_data15': some_data15,
		            'some_data16': some_data16,
		            'some_data17': some_data17,
		            'some_data18': some_data18,
		            'some_data19': some_data19,
		            'some_data20': some_data20,
		            'some_data21': some_data21,
		            'some_data22': some_data22,
		            'some_data23': some_data23,
		            'some_data24': some_data24,
		            'some_data25': some_data25,
		            'some_data26': some_data26,
		            'some_data27': some_data27,
		            'some_data28': some_data28,
		            'some_data29': some_data29,
		            'some_data30': some_data30,
		            'some_data31': some_data31,
		            'some_data32': some_data32,
		            'some_data33': some_data33,
		            'some_data34': some_data34,
		            'some_data35': some_data35,
		            'some_data36': some_data36,
		            'some_data37': some_data37,
		            'some_data38': some_data38,
		            'some_data39': some_data39,
		            'some_data40': some_data40,
		            'some_data41': some_data41,
		            'some_data42': some_data42
		        })
		        print(f"Put item ({sort_key}) succeeded.")
	    		
    		except Exception as error:
    			print(error)
    			continue

    		except botocore.exceptions.ClientError as clientError:
    			print(clientError)
    			continue

#Write Data to the second table partition.

def writePartitionKey2TableTwo(key_count, item_size, dyn_resource=None):
    print(f"Beginning Table Population for {secondTableName}!")
    """
    Writes test data to the demonstration table.

    :param key_count: The number of partition and sort keys to use to populate the
                      table. The total number of items is key_count * key_count.
    :param item_size: The size of non-key data for each test item.
    :param dyn_resource: Either a Boto3 or DAX resource.
    """
    if dyn_resource is None:
        dyn_resource = boto3.resource('dynamodb')

    table = dyn_resource.Table(secondTableName)
    some_data = 'X' * item_size
    some_data2 = 'Y' * item_size 
    some_data3 = 'Z' * item_size 
    some_data4 = 'A' * item_size 
    some_data5 = 'XYZA' * item_size 
    some_data6 = 'Ash' * item_size 
    some_data7 = 'X' * item_size
    some_data8 = 'Y' * item_size 
    some_data9 = 'Z' * item_size 
    some_data10 = 'A' * item_size 
    some_data11 = 'XYZA' * item_size 
    some_data12 = 'Ash' * item_size
    some_data13 = 'X' * item_size
    some_data14 = 'Y' * item_size 
    some_data15 = 'Z' * item_size 
    some_data16 = 'A' * item_size 
    some_data17 = 'XYZA' * item_size 
    some_data18 = 'Ash' * item_size
    some_data19 = 'X' * item_size
    some_data20 = 'Y' * item_size 
    some_data21 = 'Z' * item_size 
    some_data22 = 'A' * item_size 
    some_data23 = 'XYZA' * item_size 
    some_data24 = 'Ash' * item_size
    some_data25 = 'X' * item_size
    some_data26 = 'Y' * item_size 
    some_data27 = 'Z' * item_size 
    some_data28 = 'A' * item_size 
    some_data29 = 'XYZA' * item_size 
    some_data30 = 'Ash' * item_size
    some_data31 = 'X' * item_size
    some_data32 = 'Y' * item_size 
    some_data33 = 'Z' * item_size 
    some_data34 = 'A' * item_size 
    some_data35 = 'XYZA' * item_size 
    some_data36 = 'Ash' * item_size
    some_data37 = 'X' * item_size
    some_data38 = 'Y' * item_size 
    some_data39 = 'Z' * item_size 
    some_data40 = 'A' * item_size 
    some_data41 = 'XYZA' * item_size 
    some_data42 = 'Ash' * item_size 

    with table.batch_writer() as batch:
	    for sort_key in range(1, key_count + 1):
	    	try:
		        batch.put_item(Item={
		            'partition_key': 'deiwo2',
		            'sort_key': sort_key,
		            'some_data': some_data,
		            'some_data2': some_data2,
		            'some_data3': some_data3,
		            'some_data4': some_data4,
		            'some_data5': some_data5,
		            'some_data6': some_data6,
		            'some_data7': some_data7,
		            'some_data8': some_data8,
		            'some_data9': some_data9,
		            'some_data10': some_data10,
		            'some_data11': some_data11,
		            'some_data12': some_data12,
		            'some_data13': some_data13,
		            'some_data14': some_data14,
		            'some_data15': some_data15,
		            'some_data16': some_data16,
		            'some_data17': some_data17,
		            'some_data18': some_data18,
		            'some_data19': some_data19,
		            'some_data20': some_data20,
		            'some_data21': some_data21,
		            'some_data22': some_data22,
		            'some_data23': some_data23,
		            'some_data24': some_data24,
		            'some_data25': some_data25,
		            'some_data26': some_data26,
		            'some_data27': some_data27,
		            'some_data28': some_data28,
		            'some_data29': some_data29,
		            'some_data30': some_data30,
		            'some_data31': some_data31,
		            'some_data32': some_data32,
		            'some_data33': some_data33,
		            'some_data34': some_data34,
		            'some_data35': some_data35,
		            'some_data36': some_data36,
		            'some_data37': some_data37,
		            'some_data38': some_data38,
		            'some_data39': some_data39,
		            'some_data40': some_data40,
		            'some_data41': some_data41,
		            'some_data42': some_data42
		        })
		        print(f"Put item ({sort_key}) succeeded.")
	    		
    		except Exception as error:
    			print(error)
    			continue

    		except botocore.exceptions.ClientError as clientError:
    			print(clientError)
    			continue

#Create an on demand table with partition and sort keys
def CreateTableThree():
    response = client.create_table(
    AttributeDefinitions=[
        {
            'AttributeName': 'partition_key',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'sort_key',
            'AttributeType': 'N'
        }
    ],
    TableName= thirdTableName,
    KeySchema=[
        {
            'AttributeName': 'partition_key',
            'KeyType': 'HASH'
        },
        {
            'AttributeName': 'sort_key',
            'KeyType': 'RANGE'
        }
    ],
    BillingMode='PAY_PER_REQUEST',
    SSESpecification={
        'Enabled': False
    }
)
    print(f"{thirdTableName} Table Creation in Progress!")

#Need logic to see when table status is Active so I can add autoscaling to the table
def tableThreeCreated():
    start_time = time.time()
    while True:
        response = client.describe_table(
        TableName= thirdTableName
)
        if response['Table']['TableStatus'] == "ACTIVE":
            end_time = time.time()
            total_time = end_time - start_time
            print(f"Table Created Total Time Was: {total_time:.4f} seconds.\n")
            break
        elif response['Table']['TableStatus'] == "CREATING":
            # interval_time = time.time()
            # time_since = interval_time - start_time
            # print(f"Current Import Status: {response['ImportTableDescription']['ImportStatus']}.")
            # print(f"\n Import has been running for: {time_since:.4f} seconds.")
            time.sleep(10)
        # elif response['ImportTableDescription']['ImportStatus'] == "FAILED":
        #     end_time = time.time()
        #     total_time = end_time - start_time
        #     print(f"Import Completed Total Time Was: {total_time:.4f} seconds.")
        #     break

#Write Data to the first table partition

def writePartitionKey1TableThree(key_count, item_size, dyn_resource=None):
    print(f"Beginning Table Population for {thirdTableName}!")
    """
    Writes test data to the demonstration table.

    :param key_count: The number of partition and sort keys to use to populate the
                      table. The total number of items is key_count * key_count.
    :param item_size: The size of non-key data for each test item.
    :param dyn_resource: Either a Boto3 or DAX resource.
    """
    if dyn_resource is None:
        dyn_resource = boto3.resource('dynamodb')

    table = dyn_resource.Table(thirdTableName)
    some_data = 'X' * item_size
    some_data2 = 'Y' * item_size 
    some_data3 = 'Z' * item_size 
    some_data4 = 'A' * item_size 
    some_data5 = 'XYZA' * item_size 
    some_data6 = 'Ash' * item_size 
    some_data7 = 'X' * item_size
    some_data8 = 'Y' * item_size 
    some_data9 = 'Z' * item_size 
    some_data10 = 'A' * item_size 
    some_data11 = 'XYZA' * item_size 
    some_data12 = 'Ash' * item_size
    some_data13 = 'X' * item_size
    some_data14 = 'Y' * item_size 
    some_data15 = 'Z' * item_size 
    some_data16 = 'A' * item_size 
    some_data17 = 'XYZA' * item_size 
    some_data18 = 'Ash' * item_size
    some_data19 = 'X' * item_size
    some_data20 = 'Y' * item_size 
    some_data21 = 'Z' * item_size 
    some_data22 = 'A' * item_size 
    some_data23 = 'XYZA' * item_size 
    some_data24 = 'Ash' * item_size
    some_data25 = 'X' * item_size
    some_data26 = 'Y' * item_size 
    some_data27 = 'Z' * item_size 
    some_data28 = 'A' * item_size 
    some_data29 = 'XYZA' * item_size 
    some_data30 = 'Ash' * item_size
    some_data31 = 'X' * item_size
    some_data32 = 'Y' * item_size 
    some_data33 = 'Z' * item_size 
    some_data34 = 'A' * item_size 
    some_data35 = 'XYZA' * item_size 
    some_data36 = 'Ash' * item_size
    some_data37 = 'X' * item_size
    some_data38 = 'Y' * item_size 
    some_data39 = 'Z' * item_size 
    some_data40 = 'A' * item_size 
    some_data41 = 'XYZA' * item_size 
    some_data42 = 'Ash' * item_size

    with table.batch_writer() as batch:
	    for sort_key in range(1, key_count + 1):
	    	try:
		        batch.put_item(Item={
		            'partition_key': 'deiwo2',
		            'sort_key': sort_key,
		            'some_data': some_data,
		            'some_data2': some_data2,
		            'some_data3': some_data3,
		            'some_data4': some_data4,
		            'some_data5': some_data5,
		            'some_data6': some_data6,
		            'some_data7': some_data7,
		            'some_data8': some_data8,
		            'some_data9': some_data9,
		            'some_data10': some_data10,
		            'some_data11': some_data11,
		            'some_data12': some_data12,
		            'some_data13': some_data13,
		            'some_data14': some_data14,
		            'some_data15': some_data15,
		            'some_data16': some_data16,
		            'some_data17': some_data17,
		            'some_data18': some_data18,
		            'some_data19': some_data19,
		            'some_data20': some_data20,
		            'some_data21': some_data21,
		            'some_data22': some_data22,
		            'some_data23': some_data23,
		            'some_data24': some_data24,
		            'some_data25': some_data25,
		            'some_data26': some_data26,
		            'some_data27': some_data27,
		            'some_data28': some_data28,
		            'some_data29': some_data29,
		            'some_data30': some_data30,
		            'some_data31': some_data31,
		            'some_data32': some_data32,
		            'some_data33': some_data33,
		            'some_data34': some_data34,
		            'some_data35': some_data35,
		            'some_data36': some_data36,
		            'some_data37': some_data37,
		            'some_data38': some_data38,
		            'some_data39': some_data39,
		            'some_data40': some_data40,
		            'some_data41': some_data41,
		            'some_data42': some_data42
		        })
		        print(f"Put item ({sort_key}) succeeded.")
	    		
    		except Exception as error:
    			print(error)
    			continue

    		except botocore.exceptions.ClientError as clientError:
    			print(clientError)
    			continue

#Write Data to the second table partition.

def writePartitionKey2TableThree(key_count, item_size, dyn_resource=None):
    print(f"Beginning Table Population for {thirdTableName}!")
    """
    Writes test data to the demonstration table.

    :param key_count: The number of partition and sort keys to use to populate the
                      table. The total number of items is key_count * key_count.
    :param item_size: The size of non-key data for each test item.
    :param dyn_resource: Either a Boto3 or DAX resource.
    """
    if dyn_resource is None:
        dyn_resource = boto3.resource('dynamodb')

    table = dyn_resource.Table(thirdTableName)
    some_data = 'X' * item_size
    some_data2 = 'Y' * item_size 
    some_data3 = 'Z' * item_size 
    some_data4 = 'A' * item_size 
    some_data5 = 'XYZA' * item_size 
    some_data6 = 'Ash' * item_size 
    some_data7 = 'X' * item_size
    some_data8 = 'Y' * item_size 
    some_data9 = 'Z' * item_size 
    some_data10 = 'A' * item_size 
    some_data11 = 'XYZA' * item_size 
    some_data12 = 'Ash' * item_size
    some_data13 = 'X' * item_size
    some_data14 = 'Y' * item_size 
    some_data15 = 'Z' * item_size 
    some_data16 = 'A' * item_size 
    some_data17 = 'XYZA' * item_size 
    some_data18 = 'Ash' * item_size
    some_data19 = 'X' * item_size
    some_data20 = 'Y' * item_size 
    some_data21 = 'Z' * item_size 
    some_data22 = 'A' * item_size 
    some_data23 = 'XYZA' * item_size 
    some_data24 = 'Ash' * item_size
    some_data25 = 'X' * item_size
    some_data26 = 'Y' * item_size 
    some_data27 = 'Z' * item_size 
    some_data28 = 'A' * item_size 
    some_data29 = 'XYZA' * item_size 
    some_data30 = 'Ash' * item_size
    some_data31 = 'X' * item_size
    some_data32 = 'Y' * item_size 
    some_data33 = 'Z' * item_size 
    some_data34 = 'A' * item_size 
    some_data35 = 'XYZA' * item_size 
    some_data36 = 'Ash' * item_size
    some_data37 = 'X' * item_size
    some_data38 = 'Y' * item_size 
    some_data39 = 'Z' * item_size 
    some_data40 = 'A' * item_size 
    some_data41 = 'XYZA' * item_size 
    some_data42 = 'Ash' * item_size 

    with table.batch_writer() as batch:
	    for sort_key in range(1, key_count + 1):
	    	try:
		        batch.put_item(Item={
		            'partition_key': 'askfjew',
		            'sort_key': sort_key,
		            'some_data': some_data,
		            'some_data2': some_data2,
		            'some_data3': some_data3,
		            'some_data4': some_data4,
		            'some_data5': some_data5,
		            'some_data6': some_data6,
		            'some_data7': some_data7,
		            'some_data8': some_data8,
		            'some_data9': some_data9,
		            'some_data10': some_data10,
		            'some_data11': some_data11,
		            'some_data12': some_data12,
		            'some_data13': some_data13,
		            'some_data14': some_data14,
		            'some_data15': some_data15,
		            'some_data16': some_data16,
		            'some_data17': some_data17,
		            'some_data18': some_data18,
		            'some_data19': some_data19,
		            'some_data20': some_data20,
		            'some_data21': some_data21,
		            'some_data22': some_data22,
		            'some_data23': some_data23,
		            'some_data24': some_data24,
		            'some_data25': some_data25,
		            'some_data26': some_data26,
		            'some_data27': some_data27,
		            'some_data28': some_data28,
		            'some_data29': some_data29,
		            'some_data30': some_data30,
		            'some_data31': some_data31,
		            'some_data32': some_data32,
		            'some_data33': some_data33,
		            'some_data34': some_data34,
		            'some_data35': some_data35,
		            'some_data36': some_data36,
		            'some_data37': some_data37,
		            'some_data38': some_data38,
		            'some_data39': some_data39,
		            'some_data40': some_data40,
		            'some_data41': some_data41,
		            'some_data42': some_data42
		        })
		        print(f"Put item ({sort_key}) succeeded.")
	    		
    		except Exception as error:
    			print(error)
    			continue

    		except botocore.exceptions.ClientError as clientError:
    			print(clientError)
    			continue


#Create an on demand table to turn into a V1 Global Table which requires table creation in all regions. 
def createV1GlobalTable():
    response = client.create_table(
    AttributeDefinitions=[
        {
            'AttributeName': 'partition_key',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'sort_key',
            'AttributeType': 'N'
        }
    ],
    TableName= fourthTablename,
    KeySchema=[
        {
            'AttributeName': 'partition_key',
            'KeyType': 'HASH'
        },
        {
            'AttributeName': 'sort_key',
            'KeyType': 'RANGE'
        }
    ],
    BillingMode='PAY_PER_REQUEST',
    SSESpecification={
        'Enabled': False
    }
)
    print(f"{fourthTablename} Table Creation in Progress!")

#Need logic to see when table status is Active so I can add a replica afterwards
def tableFourCreated():
	start_time = time.time()
	while True:
		response = client.describe_table(
			TableName= fourthTablename
)
		if response['Table']['TableStatus'] == "ACTIVE":
			end_time = time.time()
			total_time = end_time - start_time
			print(f"Table Created Total Time Was: {total_time:.4f} seconds.\n")
			break
		elif response['Table']['TableStatus'] == "CREATING":
			time.sleep(10)

#Enable Streams on the table once it has been created. 
def updateTablev11():
    response = client.update_table(
    TableName= fourthTablename,
    StreamSpecification={
        'StreamEnabled': True,
        'StreamViewType': 'NEW_AND_OLD_IMAGES'
    }
)
    print(f"Stream enabled on table {fourthTablename}!\n")
    global StreamArn
    StreamArn = response['TableDescription']['LatestStreamArn']


# Create table in second region
def createV1GlobalTable2():
	v1client1 = boto3.client('dynamodb', region_name='us-west-1')
	response = v1client1.create_table(
    AttributeDefinitions=[
        {
            'AttributeName': 'partition_key',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'sort_key',
            'AttributeType': 'N'
        }
    ],
    TableName= sixthTablename,
    KeySchema=[
        {
            'AttributeName': 'partition_key',
            'KeyType': 'HASH'
        },
        {
            'AttributeName': 'sort_key',
            'KeyType': 'RANGE'
        }
    ],
    BillingMode='PAY_PER_REQUEST',
    SSESpecification={
        'Enabled': False
    }
)
	print(f"{sixthTablename} Table Creation in Progress!")


#Need logic to see when table status is Active so I can add a replica afterwards
def tablesixCreated():
    start_time = time.time()
    v1client1 = boto3.client('dynamodb', region_name='us-west-1')
    while True:
        response = v1client1.describe_table(
        TableName= sixthTablename
)
        if response['Table']['TableStatus'] == "ACTIVE":
            end_time = time.time()
            total_time = end_time - start_time
            print(f"Table Created Total Time Was: {total_time:.4f} seconds.\n")
            break
        elif response['Table']['TableStatus'] == "CREATING":
            # interval_time = time.time()
            # time_since = interval_time - start_time
            # print(f"Current Import Status: {response['ImportTableDescription']['ImportStatus']}.")
            # print(f"\n Import has been running for: {time_since:.4f} seconds.")
            time.sleep(10)
        # elif response['ImportTableDescription']['ImportStatus'] == "FAILED":
        #     end_time = time.time()
        #     total_time = end_time - start_time
        #     print(f"Import Completed Total Time Was: {total_time:.4f} seconds."
        #     break\

#Enable Streams on the table once it has been created. 
def updateTablev12():
	v1client1 = boto3.client('dynamodb', region_name='us-west-1')
	response = v1client1.update_table(
    TableName= sixthTablename,
    StreamSpecification={
        'StreamEnabled': True,
        'StreamViewType': 'NEW_AND_OLD_IMAGES'
    }
)
	print(f"Stream enabled on table {sixthTablename}!\n")
	global StreamArn
	StreamArn = response['TableDescription']['LatestStreamArn']

#Create the same table in the third region. 
def createV1GlobalTable3():
	v1client2 = boto3.client('dynamodb', region_name='us-west-2')
	response = v1client2.create_table(
    AttributeDefinitions=[
        {
            'AttributeName': 'partition_key',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'sort_key',
            'AttributeType': 'N'
        }
    ],
    TableName= seventhTablename,
    KeySchema=[
        {
            'AttributeName': 'partition_key',
            'KeyType': 'HASH'
        },
        {
            'AttributeName': 'sort_key',
            'KeyType': 'RANGE'
        }
    ],
    BillingMode='PAY_PER_REQUEST',
    SSESpecification={
        'Enabled': False
    }
)
	print(f"{seventhTablename} Table Creation in Progress!")

#Need logic to see when table status is Active so I can add a replica afterwards
def tablesevenCreated():
    start_time = time.time()
    v1client2 = boto3.client('dynamodb', region_name='us-west-2')
    while True:
        response = v1client2.describe_table(
        TableName= seventhTablename
)
        if response['Table']['TableStatus'] == "ACTIVE":
            end_time = time.time()
            total_time = end_time - start_time
            print(f"Table Created Total Time Was: {total_time:.4f} seconds.\n")
            break
        elif response['Table']['TableStatus'] == "CREATING":
            # interval_time = time.time()
            # time_since = interval_time - start_time
            # print(f"Current Import Status: {response['ImportTableDescription']['ImportStatus']}.")
            # print(f"\n Import has been running for: {time_since:.4f} seconds.")
            time.sleep(10)
        # elif response['ImportTableDescription']['ImportStatus'] == "FAILED":
        #     end_time = time.time()
        #     total_time = end_time - start_time
        #     print(f"Import Completed Total Time Was: {total_time:.4f} seconds.")
        #     break

#Enable Streams on the table once it has been created. 
def updateTablev13():
	v1client2 = boto3.client('dynamodb', region_name='us-west-2')
	response = v1client2.update_table(
    TableName= seventhTablename,
    StreamSpecification={
        'StreamEnabled': True,
        'StreamViewType': 'NEW_AND_OLD_IMAGES'
    }
)
	print(f"Stream enabled on table {seventhTablename}!\n")
	global StreamArn
	StreamArn = response['TableDescription']['LatestStreamArn']
	time.sleep(10)

#Add a region to the V1 Global Table that was created above
def addRegionToV1GT():
	response = client.create_global_table(
    GlobalTableName=fourthTablename,
    ReplicationGroup=[
        {
            'RegionName': 'us-west-2'
        },
    ]
)

	response = client.create_global_table(
    GlobalTableName=fourthTablename,
    ReplicationGroup=[
        {
            'RegionName': 'us-west-1'
        },
    ]
)


#Create an on demand table to turn into a V2 Global Table
def createV2GlobalTable():
    response = client.create_table(
    AttributeDefinitions=[
        {
            'AttributeName': 'partition_key',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'sort_key',
            'AttributeType': 'N'
        }
    ],
    TableName= fifthTablename,
    KeySchema=[
        {
            'AttributeName': 'partition_key',
            'KeyType': 'HASH'
        },
        {
            'AttributeName': 'sort_key',
            'KeyType': 'RANGE'
        }
    ],
    BillingMode='PAY_PER_REQUEST',
    SSESpecification={
        'Enabled': False
    }
)
    print(f"{fifthTablename} Table Creation in Progress!")

#Need logic to see when table status is Active so I can add a replica afterwards
def tableFiveCreated():
    start_time = time.time()
    while True:
        response = client.describe_table(
        TableName= fifthTablename
)
        if response['Table']['TableStatus'] == "ACTIVE":
            end_time = time.time()
            total_time = end_time - start_time
            print(f"Table Created Total Time Was: {total_time:.4f} seconds.\n")
            break
        elif response['Table']['TableStatus'] == "CREATING":
            # interval_time = time.time()
            # time_since = interval_time - start_time
            # print(f"Current Import Status: {response['ImportTableDescription']['ImportStatus']}.")
            # print(f"\n Import has been running for: {time_since:.4f} seconds.")
            time.sleep(10)
        # elif response['ImportTableDescription']['ImportStatus'] == "FAILED":
        #     end_time = time.time()
        #     total_time = end_time - start_time
        #     print(f"Import Completed Total Time Was: {total_time:.4f} seconds.")
        #     break

#Enable Streams on the table once it has been created. 
def updateTablev2():
	response = client.update_table(
    TableName= fifthTablename,
    StreamSpecification={
        'StreamEnabled': True,
        'StreamViewType': 'NEW_AND_OLD_IMAGES'
    }
)
	print(f"Stream enabled on table {fifthTablename}!\n")
	global StreamArn
	StreamArn = response['TableDescription']['LatestStreamArn']
	time.sleep(10)

#Add a region to the V2 Global Table that was created above.

def addRegiontoV2GT():
	response = client.update_table(
	    TableName=fifthTablename,
	    ReplicaUpdates=[
	        {
	            'Create': {
	                'RegionName': 'us-west-1'
	            }
	        }
	    ]
	)

	start_time = time.time()
	while True:
		response = client.describe_table(
        TableName= fifthTablename
)
		if response['Table']['TableStatus'] == "ACTIVE":
			end_time = time.time()
			total_time = end_time - start_time
			print(f"Table Finished Updating: {total_time:.4f} seconds.\n")
			break
		elif response['Table']['TableStatus'] == "UPDATING":
			time.sleep(10)


	response = client.update_table(
	    TableName=fifthTablename,
	    ReplicaUpdates=[
	        {
	            'Create': {
	                'RegionName': 'us-west-2'
	            }
	        }
	    ]
	)
	print(f"Replicas have been added to table {fifthTablename}!")


#Enabled PITR for table to be able to export it

def enablePITR():
	response = client.update_continuous_backups(
	    TableName=fifthTablename,
	    PointInTimeRecoverySpecification={
	        'PointInTimeRecoveryEnabled': True
	    }
	)


#Export a Previous Table to to S3

def exportTableToS3():
	response = client.export_table_to_point_in_time(
	    TableArn=tableArn,
	    ClientToken='uuiedkwoweiruwold',
	    S3BucketOwner= '699043355965',
	    S3Bucket= 'uashtodasmemilestone1bucket',
	    ExportFormat='DYNAMODB_JSON'
	)



if __name__ == '__main__':

	#Variables
	write_key_count = 120000
	write_item_size = 3000


    #Create a new table and write 30GB of data to it using 2 partition keys and 1 LSI.
    # first_table_created = CreateTableOne()
    # is_first_table_created = tableOneCreated()
    # enable_autoscaling = enableAutoscaling()
    # parition_key_one_write = writePartitionKey1(write_key_count, write_item_size)
    # partition_key_two_write = writePartitionKey2(write_key_count, write_item_size)

    # #Create a new table and write 30GB of data to it using 2 partition keys and No LSI.
	# second_table_created = CreateTableTwo()
	# is_second_table_created = tableTwoCreated()
	# enable_autoscaling_table_two = enableAutoscalingTableTwo()
	# parition_key_one_write_table_two = writePartitionKey1TableTwo(write_key_count, write_item_size)
	# partition_key_two_write_table_two = writePartitionKey2TableTwo(write_key_count, write_item_size)

    # #Create a new On-Demand table and write 30GB of data to it using 2 partition keys.
	third_table_created = CreateTableThree()
	is_third_table_created = tableThreeCreated()
	parition_key_one_write_table_three = writePartitionKey1TableThree(write_key_count, write_item_size)
	partition_key_two_write_table_three = writePartitionKey2TableThree(write_key_count, write_item_size)

    #Create an on demand table to turn into a V1 Global Table
    # v1_global_table = createV1GlobalTable()
    # v1_table_created = tableFourCreated()
    # v1_stream_enabled = updateTablev11()
    # v1_global_table2 = createV1GlobalTable2()
    # v1_table_created2 = tablesixCreated()
    # v1_stream_enabled2 = updateTablev12()
    # v1_global_table3 = createV1GlobalTable3()
    # v1_table_created3 = tablesevenCreated()
    # v1_stream_enabled3 = updateTablev13()
    # v1_add_replicas = addRegionToV1GT()
    # v2_global_table = createV2GlobalTable()
    # v2_table_created = tableFiveCreated() 
    # v2_stream_enabled = updateTablev2()
    # v2_add_replicas = addRegiontoV2GT()

    #Export Table to S3
    # enable_pitr = enablePITR()
    # export_to_s3 = exportTableToS3()
