curl -X POST \
  http://localhost:8202/api/v1/signup \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: f7e43cc0-5413-b270-a7b3-1682f2f1ca70' \
  -d '{
  "uid": 2,
  "balance": 22,
  "full_name": "Sy Le",
  "password": "password",
  "date_added": 1531684142357
}'
