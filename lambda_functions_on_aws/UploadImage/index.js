// this lambda function ended up not being used, instead the image is uploaded to s3 in the app

const AWS = require('aws-sdk');
var s3 = new AWS.S3();

exports.handler = (event, context, callback) => {
    let encodedImage = JSON.parse(event.body).encodedString;
    let decodedImage = Buffer.from(encodedImage, 'base64');

    var filePath = "location/" + event.queryStringParameters.timeStamp + ".jpg";

    var params = {
        "Body": decodedImage,
        "Bucket": "hackupc-images",
        "Key": filePath
    };

    s3.upload(params, function(err, data){
       if(err) {
           callback(err, null);
       } else {
            let response = {
                "statusCode": 200,
                "headers": {
                    "": ""
                },
                "body": JSON.stringify(data),
                "isBase64Encoded": false
            };
           callback(null, response);
        }
    });
}

