import boto3
import pprint
import time

#Variable Definitions
tableName = 'MileStone1Table'
client = boto3.client('dynamodb')
pp = pprint.PrettyPrinter(indent=0)

#Import the table with data from the GDELT s3 files
def importTable():
    response = client.import_table(
        S3BucketSource={
            'S3BucketOwner': '699043355965',
            'S3Bucket': 'uashtodasmemilestone1bucket',
            'S3KeyPrefix': 'M1'
        },
        InputFormat='CSV',
        InputFormatOptions={
            'Csv': {
                'Delimiter': '\t',
                'HeaderList': [
                    'GlobalEventID',
                    'Day',
                    'MonthYear',
                    'year',
                    'FractionDate',
                    'Actor1Code',
                    'Actor1Name',
                    'Actor1CountryCode',
                    'Actor1KnownGroupCode',
                    'Actor1EthnicCode',
                    'Actor1Religion1Code',
                    'Actor1Religion2Code',
                    'Actor1Type1Code',
                    'Actor1Type2Code',
                    'Actor1Type3Code',
                    'Actor2Code',
                    'Actor2Name',
                    'Actor2CountryCode',
                    'Actor2KnownGroupCode',
                    'Actor2EthnicCode',
                    'Actor2Religion1Code',
                    'Actor2Religion2Code',
                    'Actor2Type1Code',
                    'Actor2Type2Code',
                    'Actor1Type3Code',
                    'IsRootEvent',
                    'EventCode',
                    'EventBaseCode',
                    'EventRootCode',
                    'QuadClass',
                    'GoldsteinScale',
                    'NumMentions',
                    'NumSources',
                    'NumArticles',
                    'AvgTone',
                    'Actor1Geo_Type',
                    'Actor1Geo_Fullname',
                    'Actor1Geo_CountryCode',
                    'Actor1Geo_ADM1Code',
                    'Actor1Geo_Lat',
                    'Actor1Geo_Long',
                    'Actor1Geo_FeatureID',
                    'Actor2Geo_Type',
                    'Actor2Geo_Fullname',
                    'Actor2Geo_CountryCode',
                    'Actor2Geo_ADM1Code',
                    'Actor2Geo_Lat',
                    'Actor2Geo_Long',
                    'Actor2Geo_FeatureID',
                    'ActionGeo_Type',
                    'ActionGeo_Fullname',
                    'ActionGeo_CountryCode',
                    'ActionGeo_ADM1Code',
                    'ActionGeo_Lat',
                    'ActionGeo_Long',
                    'ActionGeo_FeatureID',
                    'DATEADDED'
                ]
            }
        },
        InputCompressionType='NONE',
        TableCreationParameters={
            'TableName': tableName,
            'AttributeDefinitions': [
                {
                    'AttributeName': 'GlobalEventID',
                    'AttributeType': 'S'
                },
                {
                    'AttributeName': 'Day',
                    'AttributeType': 'S'
                },
                {
                    'AttributeName': 'MonthYear',
                    'AttributeType': 'S'
                },
                {
                    'AttributeName': 'year',
                    'AttributeType': 'S'
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
            'KeySchema': [
                {
                    'AttributeName': 'GlobalEventID',
                    'KeyType': 'HASH'
                },
            ],
            'BillingMode': 'PAY_PER_REQUEST',
            'SSESpecification': {
                'Enabled': False,
            },
            'GlobalSecondaryIndexes': [
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
                    }
                }
            ]
        }
    )
    global ImpArn 
    ImpArn = response['ImportTableDescription']['ImportArn']

#Need logic to wait until the import has completed before moving to the next functions for queries. 
def importComplete():
    start_time = time.time()
    print("Import in progress! You will be notified upon import completion/failure.")
    while True:
        response = client.describe_import(
        ImportArn= ImpArn
)
        if response['ImportTableDescription']['ImportStatus'] == "COMPLETED":
            end_time = time.time()
            total_time = end_time - start_time
            print(f"Import Completed Total Time Was: {total_time:.4f} seconds.")
            break
        elif response['ImportTableDescription']['ImportStatus'] == "IN_PROGRESS":
            # interval_time = time.time()
            # time_since = interval_time - start_time
            # print(f"Current Import Status: {response['ImportTableDescription']['ImportStatus']}.")
            # print(f"\n Import has been running for: {time_since:.4f} seconds.")
            time.sleep(600)
        elif response['ImportTableDescription']['ImportStatus'] == "FAILED":
            end_time = time.time()
            total_time = end_time - start_time
            print(f"Import Completed Total Time Was: {total_time:.4f} seconds.")
            break





#Get the all the data for any given year
def YearQuery():
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
        ':year': {'S': '1979'}
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
                ':year': {'S': '1979'}
            },  
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    print("Printing all data for a given year\n")
    pp.pprint(queryResponse['Items'])
    time.sleep(30)

#Get the all the data for any given globaleventid
def EventQuery():
    queryResponse = client.query(
    TableName= tableName,
    #IndexName='YearCountryIndex',
    Select='ALL_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    ExpressionAttributeNames={
        '#e': 'GlobalEventID'
    },
    KeyConditionExpression='#e = :GlobalEventID',
    ExpressionAttributeValues={
        ':GlobalEventID': {'S': '211860'}
    }
)
    while 'LastEvaluatedKey' in queryResponse:
        queryResponse = client.query(
            TableName= tableName,
            #IndexName='YearCountryIndex',
            Select='ALL_ATTRIBUTES',
            ConsistentRead=False,
            ScanIndexForward=True,
            ReturnConsumedCapacity='NONE',
            ExpressionAttributeNames={
                '#e': 'GlobalEventID'
            },
            KeyConditionExpression='#e = :GlobalEventID',
            ExpressionAttributeValues={
                ':GlobalEventID': {'S': '211860'}
            },
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    print("Printing all data for the given GlobalEventID\n")
    pp.pprint(queryResponse['Items'])

#Get all Count of total items in DDB
def Describetable():

    Describe = client.describe_table(
    TableName= tableName
)
    print("Printing Item Count for Table\n")
    pp.pprint(Describe['Table']['ItemCount'])
    time.sleep(30)

#Get all the events where actor1name='BARACK OBAMA' or any other actor1name
def BarackQuery():
    queryResponse = client.query(
    TableName= tableName,
    IndexName='ActorEventIndex',
    Select='SPECIFIC_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    ExpressionAttributeNames={
        '#a': 'Actor1Name'
    },
    KeyConditionExpression='#a = :Actor1Name',
    ExpressionAttributeValues={
        ':Actor1Name': {'S': 'BARACK OBAMA'}
    },
    ProjectionExpression='GlobalEventID'
)
    while 'LastEvaluatedKey' in queryResponse:
        queryResponse = client.query(
            TableName= tableName,
            IndexName='ActorEventIndex',
            Select='SPECIFIC_ATTRIBUTES',
            ConsistentRead=False,
            ScanIndexForward=True,
            ReturnConsumedCapacity='NONE',
            ExpressionAttributeNames={
                '#a': 'Actor1Name'
            },
            KeyConditionExpression='#a = :Actor1Name',
            ExpressionAttributeValues={
                ':Actor1Name': {'S': 'BARACK OBAMA'}
            },
            ProjectionExpression='GlobalEventID',
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    print("Printing all events where actor was BARACK OBAMA\n")
    pp.pprint(queryResponse['Items'])
    time.sleep(30)

#Give me Actor1CountryCode for any given Actor1Name
def CountryQuery():
    queryResponse = client.query(
    TableName= tableName,
    IndexName='ActorCountryIndex',
    Select='SPECIFIC_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    ExpressionAttributeNames={
        '#a': 'Actor1Name'
    },
    KeyConditionExpression='#a = :Actor1Name',
    ExpressionAttributeValues={
        ':Actor1Name': {'S': 'HANOVER'}
    },
    ProjectionExpression='Actor1CountryCode',
)
    while 'LastEvaluatedKey' in queryResponse:
        queryResponse = client.query(
            TableName= tableName,
            IndexName='ActorCountryIndex',
            Select='SPECIFIC_ATTRIBUTES',
            ConsistentRead=False,
            ScanIndexForward=True,
            ReturnConsumedCapacity='NONE',
            ExpressionAttributeNames={
                '#a': 'Actor1Name'
            },
            KeyConditionExpression='#a = :Actor1Name',
            ExpressionAttributeValues={
                ':Actor1Name': {'S': 'HANOVER'}
            },
            ProjectionExpression='Actor1CountryCode',
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    print("Printing Country Code for any Given Actor Name\n")
    pp.pprint(queryResponse['Items'])
    time.sleep(30)

#Get all the items for any particular year and country
def YearCountryQuery():
    queryResponse = client.query(
    TableName= tableName,
    IndexName='YearCountryIndex',
    Select='ALL_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    ExpressionAttributeNames={
    '#y': 'year',
    '#c': 'Actor1CountryCode'
    },
    KeyConditionExpression='(#y = :year) AND (#c = :country)',
    ExpressionAttributeValues={
        ':year': {'S': '1980'},
        ':country': {'S': 'AFG'}
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
            '#y': 'year',
            '#c': 'Actor1CountryCode'
            },
            KeyConditionExpression='(#y = :year) AND (#c = :country)',
            ExpressionAttributeValues={
                ':year': {'S': '1980'},
                ':country': {'S': 'AFG'}
            },
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    print("Printing Country Code for any Given Actor Name\n")
    pp.pprint(queryResponse['Items'])
    time.sleep(30)

#Give me all the events happened in second month of 1979 year
def MonthYearQuery():
    queryResponse = client.query(
    TableName= tableName,
    IndexName='MonthYearIndex',
    Select='SPECIFIC_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    ExpressionAttributeNames={
    '#m': 'MonthYear'
    },
    KeyConditionExpression='#m = :monthyear',
    ExpressionAttributeValues={
        ':monthyear': {'S': '197902'}
    },
    ProjectionExpression='GlobalEventID'
)
    while 'LastEvaluatedKey' in queryResponse:
        queryResponse = client.query(
            TableName= tableName,
            IndexName='MonthYearIndex',
            Select='SPECIFIC_ATTRIBUTES',
            ConsistentRead=False,
            ScanIndexForward=True,
            ReturnConsumedCapacity='NONE',
            ExpressionAttributeNames={
            '#m': 'MonthYear'
            },
            KeyConditionExpression='#m = :monthyear',
            ExpressionAttributeValues={
                ':monthyear': {'S': '197902'}
            },
            ProjectionExpression='GlobalEventID',
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    print("Printing all events that happened in the second month of the 1979 year\n")
    pp.pprint(queryResponse['Items'])
    time.sleep(30)

# Give me all the events happened on 19790217 day
def DayQuery():
    queryResponse = client.query(
    TableName= tableName,
    IndexName='DayIndex',
    Select='SPECIFIC_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    ExpressionAttributeNames={
    '#d': 'Day'
    },
    KeyConditionExpression='#d = :day',
    ExpressionAttributeValues={
        ':day': {'S': '19790217'}
    },
    ProjectionExpression='GlobalEventID'
)
    while 'LastEvaluatedKey' in queryResponse:
        queryResponse = client.query(
            TableName= tableName,
            IndexName='DayIndex',
            Select='SPECIFIC_ATTRIBUTES',
            ConsistentRead=False,
            ScanIndexForward=True,
            ReturnConsumedCapacity='NONE',
            ExpressionAttributeNames={
            '#d': 'Day'
            },
            KeyConditionExpression='#d = :day',
            ExpressionAttributeValues={
                ':day': {'S': '19790217'}
            },
            ProjectionExpression='GlobalEventID',
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    print("Printing all events that happened on the day 19790217\n")
    pp.pprint(queryResponse['Items'])
    time.sleep(30)

# Give me a list of all root events
def IsRootQuery():
    queryResponse = client.query(
    TableName= tableName,
    IndexName='IsrootIndex',
    Select='SPECIFIC_ATTRIBUTES',
    ConsistentRead=False,
    ScanIndexForward=True,
    ReturnConsumedCapacity='NONE',
    ExpressionAttributeNames={
    '#r': 'IsRootEvent'
    },
    KeyConditionExpression='#r = :isRoot',
    ExpressionAttributeValues={
        ':isRoot': {'S': '1'}
    },
    ProjectionExpression='GlobalEventID'
)
    while 'LastEvaluatedKey' in queryResponse:
        queryResponse = client.query(
            TableName= tableName,
            IndexName='IsrootIndex',
            Select='SPECIFIC_ATTRIBUTES',
            ConsistentRead=False,
            ScanIndexForward=True,
            ReturnConsumedCapacity='NONE',
            ExpressionAttributeNames={
            '#r': 'IsRootEvent'
            },
            KeyConditionExpression='#r = :isRoot',
            ExpressionAttributeValues={
                ':isRoot': {'S': '1'}
            },
            ProjectionExpression='GlobalEventID',
            ExclusiveStartKey=queryResponse['LastEvaluatedKey']
            )
    print("Printing a list of all root events\n")
    pp.pprint(queryResponse['Items'])

if __name__ == '__main__':
    # import_table = importTable()
    # import_complete = importComplete()
    event_query = EventQuery()
    year_query = YearQuery()
    item_count = Describetable()
    barack_query = BarackQuery()
    country_query = CountryQuery()
    year_country_query = YearCountryQuery()
    month_year_query = MonthYearQuery()
    day_query = DayQuery()
    is_root_query = IsRootQuery()