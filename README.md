# swift-pacs008-builder
Swift PACS008 Builder

curl -X POST "http://localhost:8080/api/swift/gen-app-heder" \
     -H "Content-Type: application/json" \
     -d '{
           "fromBIC": "HDBCVNVXXXX",
           "toBIC": "CHASUS33XXX",
           "bizMsgId": "0012533570286",
           "msgDefId": "pacs.008.001.08",
           "bizService": "swift.cbprplus.02",
           "creationDate": "2025-02-07T19:13:11.00+07:00",
           "possibleDuplicate": false
         }'


curl -X POST "http://localhost:8080/api/swift/generate-header" \
     -H "Content-Type: application/json" \
     -d '{
           "fromBIC": "HDBCVNVXXXX",
           "toBIC": "CHASUS33XXX",
           "bizMsgId": "0012533570286",
           "msgDefId": "pacs.008.001.08",
           "bizService": "swift.cbprplus.02",
           "creationDate": "2025-02-07T19:13:11.00+07:00",
           "possibleDuplicate": false,
           "userReference": "FT_HD025923",
           "messageCreator": "Messenger",
           "messageContext": "Original",
           "messageNature": "Financial",
           "priority": "Normal",
           "isPossibleDuplicate": false,
           "isNotificationRequested": false,
           "service": "swift.finplus",
           "requestType": "pacs.008.001.08",
           "requestSubtype": "swift.cbprplus.02",
           "isSigningRequested": true,
           "isNRRequested": false
         }'
