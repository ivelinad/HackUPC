import json
import boto3

dynamodb = boto3.client('dynamodb')
table = 'Locations'

def lambda_handler(event, context):
    # get all the objects in the db
    response = dynamodb.scan(TableName=table)

    locations = []
    for loc in response.get("Items"):
        locations.append({
            "lat": loc.get("lat").get('S'),
            "lng": loc.get("lng").get('S'),
            "description": loc.get("description").get('S'),
            "url": loc.get("url").get('S')
        })

    return {
        "locations": locations
    }
