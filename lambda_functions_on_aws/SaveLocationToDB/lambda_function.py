import boto3
import json
from decimal import Decimal

dynamodb = boto3.client('dynamodb')
table = 'Locations'

def lambda_handler(event, context):

    id_time = str(event.get('time'))
    url = str(event.get('url'))
    description = str(event.get('description'))
    lat = Decimal(str(event.get('lat')))
    lng = Decimal(str(event.get('lng')))

    # {table name: {table type: param}}
    response = dynamodb.put_item(TableName=table, Item={
        "id": {"S": id_time}, "lat": {"S": str(lat)}, "lng": {"S": str(lng)}, "description": {"S": description}, "url": {"S": url}
    })

    return {
        "response": json.dumps(response)
    }
