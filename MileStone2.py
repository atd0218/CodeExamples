import boto3
import pprint
import time
import pandas as pd
from decimal import Decimal
import string
import json
import amazondax
from boto3.dynamodb.conditions import Key


#Variable Definitions
tableName = 'Table2Test21'
secondTableName = 'Phase2Test21'
thirdTableName = 'HotPartitionTable2'
client = boto3.client('dynamodb')
client2 = boto3.client('lambda')
pp = pprint.PrettyPrinter(indent=0)
functionName = 'Milestone2StreamToNewTable'

# Create the  to feed the stream data into
def firstCreateTable():
    response = client.create_table(
    AttributeDefinitions=[
        {
            'AttributeName': 'GlobalEventID',
            'AttributeType': 'N'
        },
        {
            'AttributeName': 'Day',
            'AttributeType': 'N'
        },
        {
            'AttributeName': 'MonthYear',
            'AttributeType': 'N'
        },
        {
            'AttributeName': 'year',
            'AttributeType': 'N'
        },
        {
            'AttributeName': 'Actor1Name',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'Actor1CountryCode',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'IsRootEvent',
            'AttributeType': 'S'
        }
    ],
    TableName= tableName,
    KeySchema=[
        {
            'AttributeName': 'GlobalEventID',
            'KeyType': 'HASH'
        },
    ],
    GlobalSecondaryIndexes=[
        {
            'IndexName': 'ActorCountryIndex',
            'KeySchema': [
                {
                    'AttributeName': 'Actor1Name',
                    'KeyType': 'HASH'
                },
                {
                    'AttributeName': 'Actor1CountryCode',
                    'KeyType': 'RANGE'
                }
            ],
            'Projection': {
                'ProjectionType': 'KEYS_ONLY',
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        },
        {
            'IndexName': 'ActorEventIndex',
            'KeySchema': [
                {
                    'AttributeName': 'Actor1Name',
                    'KeyType': 'HASH'
                },
                {
                    'AttributeName': 'GlobalEventID',
                    'KeyType': 'RANGE'
                }
            ],
            'Projection': {
                'ProjectionType': 'KEYS_ONLY',
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        },
        {
            'IndexName': 'DayIndex',
            'KeySchema': [
                {
                    'AttributeName': 'Day',
                    'KeyType': 'HASH'
                }
            ],
            'Projection': {
                'ProjectionType': 'INCLUDE',
                'NonKeyAttributes': [
                    'GlobalEventID',
                ]
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        },
        {
            'IndexName': 'IsrootIndex',
            'KeySchema': [
                {
                    'AttributeName': 'IsRootEvent',
                    'KeyType': 'HASH'
                }
            ],
            'Projection': {
                'ProjectionType': 'INCLUDE',
                'NonKeyAttributes': [
                    'GlobalEventID',
                ]
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        },
        {
            'IndexName': 'MonthYearIndex',
            'KeySchema': [
                {
                    'AttributeName': 'MonthYear',
                    'KeyType': 'HASH'
                }
            ],
            'Projection': {
                'ProjectionType': 'INCLUDE',
                'NonKeyAttributes': [
                    'GlobalEventID',
                ]
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        },
        {
            'IndexName': 'YearCountryIndex',
            'KeySchema': [
                {
                    'AttributeName': 'year',
                    'KeyType': 'HASH'
                },
                {
                    'AttributeName': 'Actor1CountryCode',
                    'KeyType': 'RANGE'
                }
            ],
            'Projection': {
                'ProjectionType': 'ALL'
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        }
    ],
    BillingMode='PROVISIONED',
    ProvisionedThroughput={
        'ReadCapacityUnits': 3000,
        'WriteCapacityUnits': 3000
    },
    SSESpecification={
        'Enabled': False
    }
)
    print(f"{tableName} Table Creation in Progress!")

#Need logic to see when table status is Active so I can go update the Stream.
def tableCreated():
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

#Enable Streams on the table once it has been created. 
def updateTable():
    response = client.update_table(
    TableName= tableName,
    StreamSpecification={
        'StreamEnabled': True,
        'StreamViewType': 'NEW_IMAGE'
    }
)
    print(f"Stream enabled on table {tableName}!\n")
    global StreamArn
    StreamArn = response['TableDescription']['LatestStreamArn']

# Create a second table named "Phase2" to feed the stream data into
def createTable():
    response = client.create_table(
    AttributeDefinitions=[
        {
            'AttributeName': 'GlobalEventID',
            'AttributeType': 'N'
        },
        {
            'AttributeName': 'Day',
            'AttributeType': 'N'
        },
        {
            'AttributeName': 'MonthYear',
            'AttributeType': 'N'
        },
        {
            'AttributeName': 'year',
            'AttributeType': 'N'
        },
        {
            'AttributeName': 'Actor1Name',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'Actor1CountryCode',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'IsRootEvent',
            'AttributeType': 'S'
        }
    ],
    TableName= secondTableName,
    KeySchema=[
        {
            'AttributeName': 'GlobalEventID',
            'KeyType': 'HASH'
        },
    ],
    GlobalSecondaryIndexes=[
        {
            'IndexName': 'ActorCountryIndex',
            'KeySchema': [
                {
                    'AttributeName': 'Actor1Name',
                    'KeyType': 'HASH'
                },
                {
                    'AttributeName': 'Actor1CountryCode',
                    'KeyType': 'RANGE'
                }
            ],
            'Projection': {
                'ProjectionType': 'KEYS_ONLY',
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        },
        {
            'IndexName': 'ActorEventIndex',
            'KeySchema': [
                {
                    'AttributeName': 'Actor1Name',
                    'KeyType': 'HASH'
                },
                {
                    'AttributeName': 'GlobalEventID',
                    'KeyType': 'RANGE'
                }
            ],
            'Projection': {
                'ProjectionType': 'KEYS_ONLY',
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        },
        {
            'IndexName': 'DayIndex',
            'KeySchema': [
                {
                    'AttributeName': 'Day',
                    'KeyType': 'HASH'
                }
            ],
            'Projection': {
                'ProjectionType': 'INCLUDE',
                'NonKeyAttributes': [
                    'GlobalEventID',
                ]
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        },
        {
            'IndexName': 'IsrootIndex',
            'KeySchema': [
                {
                    'AttributeName': 'IsRootEvent',
                    'KeyType': 'HASH'
                }
            ],
            'Projection': {
                'ProjectionType': 'INCLUDE',
                'NonKeyAttributes': [
                    'GlobalEventID',
                ]
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        },
        {
            'IndexName': 'MonthYearIndex',
            'KeySchema': [
                {
                    'AttributeName': 'MonthYear',
                    'KeyType': 'HASH'
                }
            ],
            'Projection': {
                'ProjectionType': 'INCLUDE',
                'NonKeyAttributes': [
                    'GlobalEventID',
                ]
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        },
        {
            'IndexName': 'YearCountryIndex',
            'KeySchema': [
                {
                    'AttributeName': 'year',
                    'KeyType': 'HASH'
                },
                {
                    'AttributeName': 'Actor1CountryCode',
                    'KeyType': 'RANGE'
                }
            ],
            'Projection': {
                'ProjectionType': 'ALL'
            },
            'ProvisionedThroughput': {
                'ReadCapacityUnits': 3000,
                'WriteCapacityUnits': 3000
            }
        }
    ],
    BillingMode='PROVISIONED',
    ProvisionedThroughput={
        'ReadCapacityUnits': 3000,
        'WriteCapacityUnits': 3000
    },
    SSESpecification={
        'Enabled': False
    }
)
    print(f"{secondTableName} Table Creation in Progress!")

#Need logic to see when table status is Active so I can proceed with table population. 
def secondTableCreated():
    start_time = time.time()
    while True:
        response = client.describe_table(
        TableName= secondTableName
)
        if response['Table']['TableStatus'] == "ACTIVE":
            end_time = time.time()
            total_time = end_time - start_time
            print(f"Second Table Created Total Time Was: {total_time:.4f} seconds.\n")
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

#Update Lambda Function to add Dynamodb Trigger
def addTrigger():
    response = client2.create_event_source_mapping(
    EventSourceArn= StreamArn,
    FunctionName= functionName,
    Enabled=True,
    BatchSize=100,
    FilterCriteria={
        "Filters": [
        {
            "Pattern": "{ \"dynamodb\" : { \"NewImage\" : { \"year\" : { \"N\" : [\"1992\"] } } } }"
        }
    ]
    },
    MaximumBatchingWindowInSeconds=200,
    ParallelizationFactor=10,
    StartingPosition='TRIM_HORIZON',
    BisectBatchOnFunctionError=True,
    MaximumRetryAttempts=10,
    TumblingWindowInSeconds=0,
    FunctionResponseTypes=[
        'ReportBatchItemFailures',
    ]
)
    print(f"Trigger added to the Lambda Function {functionName}\n")

def PopulateTable():
    print(f"Beginning Table Population for Table {tableName}!\n")
    #Create JSON -> Dics and List of Dics
    # mileStone_JSON = json.loads(
    # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21990Formatted.csv', dtype={'FractionDate': 'str', 'Actor1Code': 'str', 'Actor1Name': 'str', 'Actor1CountryCode': 'str', 'Actor1KnownGroupCode': 'str', 'Actor1EthnicCode': 'str', 'Actor1Religion1Code': 'str', 'Actor1Religion2Code': 'str', 'Actor1Type1Code': 'str', 'Actor1Type2Code': 'str', 'Actor1Geo_Type': 'str', 'Actor1Geo_Fullname': 'str', 'Actor1Geo_CountryCode': 'str', 'Actor1Geo_Long': 'str', 'IsRootEvent': 'str', 'AvgTone': 'float', 'Actor2Type2Code': 'str'}).to_json(orient='records'), parse_float=Decimal)
    # mileStone_JSON2 = json.loads(
    # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21991Formatted.csv', dtype={'FractionDate': 'str', 'Actor1Code': 'str', 'Actor1Name': 'str', 'Actor1CountryCode': 'str', 'Actor1KnownGroupCode': 'str', 'Actor1EthnicCode': 'str', 'Actor1Religion1Code': 'str', 'Actor1Religion2Code': 'str', 'Actor1Type1Code': 'str', 'Actor1Type2Code': 'str', 'Actor1Geo_Type': 'str', 'Actor1Geo_Fullname': 'str', 'Actor1Geo_CountryCode': 'str', 'Actor1Geo_Long': 'str', 'IsRootEvent': 'str', 'AvgTone': 'float', 'Actor2Type2Code': 'str'}).to_json(orient='records'), parse_float=Decimal)
    mileStone_JSON3 = json.loads(
    pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21992Formatted.csv', dtype={'FractionDate': 'str', 'Actor1Code': 'str', 'Actor1Name': 'str', 'Actor1CountryCode': 'str', 'Actor1KnownGroupCode': 'str', 'Actor1EthnicCode': 'str', 'Actor1Religion1Code': 'str', 'Actor1Religion2Code': 'str', 'Actor1Type1Code': 'str', 'Actor1Type2Code': 'str', 'Actor1Geo_Type': 'str', 'Actor1Geo_Fullname': 'str', 'Actor1Geo_CountryCode': 'str', 'Actor1Geo_Long': 'str', 'IsRootEvent': 'str', 'AvgTone': 'float', 'Actor2Type2Code': 'str'}).to_json(orient='records'), parse_float=Decimal)
    # mileStone_JSON4 = json.loads(
    # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21993Formatted.csv', dtype={'FractionDate': 'str', 'Actor1Code': 'str', 'Actor1Name': 'str', 'Actor1CountryCode': 'str', 'Actor1KnownGroupCode': 'str', 'Actor1EthnicCode': 'str', 'Actor1Religion1Code': 'str', 'Actor1Religion2Code': 'str', 'Actor1Type1Code': 'str', 'Actor1Type2Code': 'str', 'Actor1Geo_Type': 'str', 'Actor1Geo_Fullname': 'str', 'Actor1Geo_CountryCode': 'str', 'Actor1Geo_Long': 'str', 'IsRootEvent': 'str', 'AvgTone': 'float', 'Actor2Type2Code': 'str'}).to_json(orient='records'), parse_float=Decimal)
    # mileStone_JSON5 = json.loads(
    # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21994Formatted.csv', dtype={'FractionDate': 'str', 'Actor1Code': 'str', 'Actor1Name': 'str', 'Actor1CountryCode': 'str', 'Actor1KnownGroupCode': 'str', 'Actor1EthnicCode': 'str', 'Actor1Religion1Code': 'str', 'Actor1Religion2Code': 'str', 'Actor1Type1Code': 'str', 'Actor1Type2Code': 'str', 'Actor1Geo_Type': 'str', 'Actor1Geo_Fullname': 'str', 'Actor1Geo_CountryCode': 'str', 'Actor1Geo_Long': 'str', 'IsRootEvent': 'str', 'AvgTone': 'float', 'Actor2Type2Code': 'str'}).to_json(orient='records'), parse_float=Decimal)
    # mileStone_JSON6 = json.loads(
    # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21995Formatted.csv', dtype={'FractionDate': 'str', 'Actor1Code': 'str', 'Actor1Name': 'str', 'Actor1CountryCode': 'str', 'Actor1KnownGroupCode': 'str', 'Actor1EthnicCode': 'str', 'Actor1Religion1Code': 'str', 'Actor1Religion2Code': 'str', 'Actor1Type1Code': 'str', 'Actor1Type2Code': 'str', 'Actor1Geo_Type': 'str', 'Actor1Geo_Fullname': 'str', 'Actor1Geo_CountryCode': 'str', 'Actor1Geo_Long': 'str', 'IsRootEvent': 'str', 'AvgTone': 'float', 'Actor2Type2Code': 'str'}).to_json(orient='records'), parse_float=Decimal)

    # mileStone_JSON = json.loads(
    # pd.read_csv('/Users/uashtoda/Documents/SmallFormatTestWriteShard50k.csv', dtype={'FractionDate': 'str', 'Actor1Code': 'str', 'Actor1Name': 'str', 'Actor1CountryCode': 'str', 'Actor1KnownGroupCode': 'str', 'Actor1EthnicCode': 'str', 'Actor1Religion1Code': 'str', 'Actor1Religion2Code': 'str', 'Actor1Type1Code': 'str', 'Actor1Type2Code': 'str', 'Actor1Geo_Type': 'str', 'Actor1Geo_Fullname': 'str', 'Actor1Geo_CountryCode': 'str', 'Actor1Geo_Long': 'str', 'IsRootEvent': 'str', 'AvgTone': 'float'}).to_json(orient='records'), parse_float=Decimal)

    # mileStone_JSON2 = json.loads(
    # pd.read_csv('/Users/uashtoda/Documents/SmallFormatTest.csv', dtype={'FractionDate': 'str', 'Actor1Code': 'str', 'Actor1Name': 'str', 'Actor1CountryCode': 'str', 'Actor1KnownGroupCode': 'str', 'Actor1EthnicCode': 'str', 'Actor1Religion1Code': 'str', 'Actor1Religion2Code': 'str', 'Actor1Type1Code': 'str', 'Actor1Type2Code': 'str', 'Actor1Geo_Type': 'str', 'Actor1Geo_Fullname': 'str', 'Actor1Geo_CountryCode': 'str', 'Actor1Geo_Long': 'str', 'IsRootEvent': 'str', 'AvgTone': 'float'}).to_json(orient='records'), parse_float=Decimal)

    #Print test to see what the JSON output looks like
    #print(mileStone_JSON)
    tablename = tableName

    #Create a list of Dictionaries and their table name.

    lst_Dics = [
    # {'item': mileStone_JSON, 'table':tablename},
    # {'item': mileStone_JSON2, 'table':tablename},
    {'item': mileStone_JSON3, 'table':tablename}
    # {'item': mileStone_JSON4, 'table':tablename},
    # {'item': mileStone_JSON5, 'table':tablename},
    # {'item': mileStone_JSON6, 'table':tablename}
    ]

    #Populate DDB Table

    dynamodb = boto3.resource('dynamodb')

    def insertDynamoItem (tablename,item_lst):
        dynamoTable = dynamodb.Table(tablename)
        
        # for record in item_lst:
        #     dynamoTable.put_item(Item=record)

        with dynamoTable.batch_writer() as batch:
            for record in item_lst:
                try:
                    batch.put_item(Item=record)
                except Exception as error:
                    print (error)
                    continue

        
        print('Success')
        return True

    #Upload Content to DynamoDB

    for element in lst_Dics:
        insertDynamoItem(element['table'],element['item'])

# def PopulateTable2():
#     print(f"Beginning Table Population for Table {tableName}!\n")
#     #Create JSON -> Dics and List of Dics
#     # mileStone_JSON = json.loads(
#     # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21990Formatted.csv').to_json(orient='records'), parse_float=Decimal)
#     # mileStone_JSON2 = json.loads(
#     # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21991Formatted.csv').to_json(orient='records'), parse_float=Decimal)
#     # mileStone_JSON3 = json.loads(
#     # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21992Formatted.csv').to_json(orient='records'), parse_float=Decimal)
#     # mileStone_JSON4 = json.loads(
#     # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21993Formatted.csv').to_json(orient='records'), parse_float=Decimal)
#     # mileStone_JSON5 = json.loads(
#     # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21994Formatted.csv').to_json(orient='records'), parse_float=Decimal)
#     # mileStone_JSON6 = json.loads(
#     # pd.read_csv('/Users/uashtoda/Documents/Milestone2/M21995Formatted.csv').to_json(orient='records'), parse_float=Decimal)

#     mileStone_JSON = json.loads(
#     pd.read_csv('/Users/uashtoda/Documents/SmallFormatTestWriteShard50k.csv', dtype={'FractionDate': 'str', 'Actor1Code': 'str', 'Actor1Name': 'str', 'Actor1CountryCode': 'str', 'Actor1KnownGroupCode': 'str', 'Actor1EthnicCode': 'str', 'Actor1Religion1Code': 'str', 'Actor1Religion2Code': 'str', 'Actor1Type1Code': 'str', 'Actor1Type2Code': 'str', 'Actor1Geo_Type': 'str', 'Actor1Geo_Fullname': 'str', 'Actor1Geo_CountryCode': 'str', 'Actor1Geo_Long': 'str', 'IsRootEvent': 'str'}).to_json(orient='records'), parse_float=Decimal)

#     mileStone_JSON2 = json.loads(
#     pd.read_csv('/Users/uashtoda/Documents/SmallFormatTest.csv', dtype={'FractionDate': 'str', 'Actor1Code': 'str', 'Actor1Name': 'str', 'Actor1CountryCode': 'str', 'Actor1KnownGroupCode': 'str', 'Actor1EthnicCode': 'str', 'Actor1Religion1Code': 'str', 'Actor1Religion2Code': 'str', 'Actor1Type1Code': 'str', 'Actor1Type2Code': 'str', 'Actor1Geo_Type': 'str', 'Actor1Geo_Fullname': 'str', 'Actor1Geo_CountryCode': 'str', 'Actor1Geo_Long': 'str', 'IsRootEvent': 'str'}).to_json(orient='records'), parse_float=Decimal)

#     #Print test to see what the JSON output looks like
#     #print(mileStone_JSON)
#     tablename = tableName

#     #Create a list of Dictionaries and their table name.

#     lst_Dics = [{'item': mileStone_JSON2, 'table':tablename}
#     # {'item': mileStone_JSON3, 'table':tablename},
#     # {'item': mileStone_JSON4, 'table':tablename},
#     # {'item': mileStone_JSON5, 'table':tablename},
#     # {'item': mileStone_JSON6, 'table':tablename}
#     ]

#     #Populate DDB Table

#     dynamodb = boto3.resource('dynamodb')

#     def insertDynamoItem (tablename,item_lst):
#         dynamoTable = dynamodb.Table(tablename)
        
#         # for record in item_lst:
#         #     dynamoTable.put_item(Item=record)

#         with dynamoTable.batch_writer() as batch:
#             for record in item_lst:
#                 try:
#                     batch.put_item(Item=record)
#                 except:
#                     print (f"Can't Import this row.")
#                     continue

        
#         print('Success')
#         return True

#     #Upload Content to DynamoDB

#     for element in lst_Dics:
#         insertDynamoItem(element['table'],element['item'])

#DAX Query for all events recorded in the year 1992
def DAX1992YearQuery(endpoint=None):
    start_time = time.time()

    if endpoint is None:
        endpoint = boto3.resource('dynamodb')

    table = endpoint.Table(tableName)

    queryResponse = table.query(
    IndexName='YearCountryIndex',
    Select='ALL_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    # ExpressionAttributeNames={
    #     '#y': 'year'
    # },
    KeyConditionExpression=Key('#y').eq(1992),
    # ExpressionAttributeValues={
    #     ':year': {'N': '1992'}
    # }
)
    while 'LastEvaluatedKey' in queryResponse:

        if endpoint is None:
            endpoint = boto3.resource('dynamodb')

        table = endpoint.Table(tableName)

        queryResponse = table.query(
            TableName= tableName,
            IndexName='YearCountryIndex',
            Select='ALL_ATTRIBUTES',
            ConsistentRead=False,
            ScanIndexForward=True,
            ReturnConsumedCapacity='NONE',
            # ExpressionAttributeNames={
            #     '#y': 'year'
            # },
            KeyConditionExpression=Key('#y').eq(1992),
            # ExpressionAttributeValues={
            #     ':year': {'N': '1992'}
            # },  
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    end_time = time.time()
    total_time = end_time - start_time
    print(f"Printing all data for 1992 With DAX. The total time was {total_time:.4f} seconds\n")
    # pp.pprint(queryResponse['Items'])

# Get all the events for 1992 without DAX
def YearQuery1992():
    start_time = time.time()
    queryResponse = client.query(
    TableName= tableName,
    IndexName='YearCountryIndex',
    Select='ALL_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    ExpressionAttributeNames={
        '#y': 'year'
    },
    KeyConditionExpression='#y = :year',
    ExpressionAttributeValues={
        ':year': {'N': '1992'}
    }
)
    while 'LastEvaluatedKey' in queryResponse:
        queryResponse = client.query(
            TableName= tableName,
            IndexName='YearCountryIndex',
            Select='ALL_ATTRIBUTES',
            ConsistentRead=False,
            ScanIndexForward=True,
            ReturnConsumedCapacity='NONE',
            ExpressionAttributeNames={
                '#y': 'year'
            },
            KeyConditionExpression='#y = :year',
            ExpressionAttributeValues={
                ':year': {'N': '1992'}
            },  
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    end_time = time.time()
    total_time = end_time - start_time
    print(f"Printing all data for 1992 Witout Using DAX. The total time was {total_time:.4f} seconds\n")
    # pp.pprint(queryResponse['Items'])

#DAX Query for all events recorded in the year 1993 and 1994
def DAX1993And1994YearQuery(endpoint=None):
    start_time = time.time()

    if endpoint is None:
        endpoint = boto3.resource('dynamodb')

    table = endpoint.Table(tableName)

    queryResponse = table.query(
    IndexName='YearCountryIndex',
    Select='ALL_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    # ExpressionAttributeNames={
    #     '#y': 'year'
    # },
    KeyConditionExpression=Key('year').eq(1993) or Key('year').eq(1994),
    # ExpressionAttributeValues={
    #     ':year': {'N': '1992'}
    # }
)
    while 'LastEvaluatedKey' in queryResponse:

        if endpoint is None:
            endpoint = boto3.resource('dynamodb')

        table = endpoint.Table(tableName)

        queryResponse = table.query(
            TableName= tableName,
            IndexName='YearCountryIndex',
            Select='ALL_ATTRIBUTES',
            ConsistentRead=False,
            ScanIndexForward=True,
            ReturnConsumedCapacity='NONE',
            # ExpressionAttributeNames={
            #     '#y': 'year'
            # },
            KeyConditionExpression=Key('year').eq(1993) or Key('year').eq(1994),
            # ExpressionAttributeValues={
            #     ':year': {'N': '1992'}
            # },  
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    end_time = time.time()
    total_time = end_time - start_time
    print(f"Printing all data for 1993 and 1994 With DAX. The total time was {total_time:.4f} seconds\n")
    # pp.pprint(queryResponse['Items'])

# Get all the events for 1993 and 1994 without DAX (Would need to create a new index where year is the sort key in order to query for this)
def YearQuery1993And1994():
    start_time = time.time()
    queryResponse = client.query(
    TableName= tableName,
    IndexName='YearCountryIndex',
    Select='ALL_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    ExpressionAttributeNames={
        '#y': 'year'
    },
    KeyConditionExpression='#y = :year1 AND #y = :year2',
    ExpressionAttributeValues={
        ':year1': {'N': '1993'},
        ':year2': {'N': '1994'}
    }
)
    while 'LastEvaluatedKey' in queryResponse:
        queryResponse = client.query(
            TableName= tableName,
            IndexName='YearCountryIndex',
            Select='ALL_ATTRIBUTES',
            ConsistentRead=False,
            ScanIndexForward=True,
            ReturnConsumedCapacity='NONE',
            ExpressionAttributeNames={
                '#y': 'year'
            },
            KeyConditionExpression='#y = :year1 AND #y = :year2',
            ExpressionAttributeValues={
                ':year1': {'N': '1993'},
                ':year2': {'N': '1994'}
            }, 
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    end_time = time.time()
    total_time = end_time - start_time
    print(f"Printing all data for 1993 and 1994 Witout Using DAX. The total time was {total_time:.4f} seconds\n")
    # pp.pprint(queryResponse['Items'])

#Create a Seperate Table with a single partition to get partition throttling.
def thirdCreateTable():
    response = client.create_table(
    AttributeDefinitions=[
        {
            'AttributeName': 'partition_key',
            'AttributeType': 'S'
        }
    ],
    TableName= thirdTableName,
    KeySchema=[
        {
            'AttributeName': 'partition_key',
            'KeyType': 'HASH'
        },
    ],
    BillingMode='PROVISIONED',
    ProvisionedThroughput={
        'ReadCapacityUnits': 1,
        'WriteCapacityUnits': 1
    },
    SSESpecification={
        'Enabled': False
    }
)
    print(f"{thirdTableName} Table Creation in Progress!")

#Need logic to see when table status is Active so I can populate the table. 
def thirdTableCreated():
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

#Write Data to single paritition over and over until throttling occurs. 

def writeHotPartition(key_count, item_size, dyn_resource=None):
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

    for sort_key in range(1, key_count + 1):
        table.put_item(Item={
            'partition_key': '2839dkw2',
            'sort_key': sort_key,
            'some_data': some_data,
            'some_data2': some_data2,
            'some_data3': some_data3,
            'some_data4': some_data4,
            'some_data5': some_data5,
            'some_data6': some_data6
        })
        print(f"Put item ({sort_key}) succeeded.")



if __name__ == '__main__':
    #Table Creation for 2 tables

    # create_second_table = createTable()
    # second_table_created = secondTableCreated()
    # create_first_table = firstCreateTable()
    # table_created = tableCreated()

    #Add Stream and Lambda trigger

    # update_table = updateTable()
    # add_trigger = addTrigger()

    #Populate table

    # populate_table = PopulateTable()

    #Table Queries

    # with amazondax.AmazonDaxClient.resource(endpoint_url='dax://milestone2.dcmqig.dax-clusters.us-east-1.amazonaws.com') as dax:
    #     DAX1992YearQuery(endpoint=dax)
    # Dax_1992_query = DAX1992YearQuery()
    # year_1992_query = YearQuery1992()
    # year_1993_1994_query = YearQuery1993And1994()

    #Create a new table and write to one key until hot partition occurs
    third_table_create = thirdCreateTable()
    is_third_table_created = thirdTableCreated()
    write_key_count = 500000
    write_item_size = 20000
    hot_partition_write = writeHotPartition(write_key_count, write_item_size)