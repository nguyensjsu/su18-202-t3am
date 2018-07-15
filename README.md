# su18-202-t3am
Welcome to the su18-202-t3am wiki!

## Members
- Sy Le
- Kevin Lai
- Hyunwook Shin
- Lin Cheng

## Important Links
### Sprint board in google sheet
Please update your task here....
- https://docs.google.com/spreadsheets/d/1pfwGInHH9siD7P3Q7gQIALwi6-xfxB6CuSd0tBX_e3c/edit?usp=sharing

### Github link...
- https://github.com/nguyensjsu/su18-202-t3am

### How to set up project locally
- https://docs.google.com/document/d/1YKTlsn7VnxUjwjOXPiQigHKz-usWgbWOH0NESBkKrKk/edit?usp=sharing

### EC2 Demo Server
- http://ec2-34-192-241-153.compute-1.amazonaws.com:8202/api/v1/cards?uid=1



## Sample Curl / Demo
### Get Cards by UserId
#### Curl Sample
```
curl -X GET \
  'http://localhost:8202/api/v1/cards?uid=2c60158e-d432-4b78-a300-360cc6fa7260'
```

#### Response
```
[
  {
    "number": "123456789",
    "code": "321",
    "balance": 20,
    "date_added": 1524958728440,
    "uid": "2c60158e-d432-4b78-a300-360cc6fa7260"
  }
]
```

### Create User
#### Curl Sample
```
curl -X POST \
  http://localhost:8202/api/v1/signup \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
  "email": "syle1@gmail.com",
  "full_name": "Sy Le",
  "password": "password"
}'
```

#### Response
```
{
    "number": "123456789",
    "code": "321",
    "balance": 20,
    "date_added": 1524958728440,
    "uid": "2c60158e-d432-4b78-a300-360cc6fa7260"
}
```


## API Contract
### Task Breakdown
#### Core business logic jar
Business logics will be bundled as a jar to be used inside the rest api app (either Spring Boot or Jersey)

#### Back End
API App to have the following api's:

##### GET /api/v1/authentication
input: ?user_id=syle
output: nothing, but will create the new user if the user_id is brandh new

##### GET /api/v1/user_info return the information related to the user by user_id
input: ?user_id=syle
output:
{
user_id: "syle",
balance: 20
}

##### GET /api/v1/transactions list all the transactions (card reloads vs purchase history) by user_id
timestamp is epoch unix timestamp
input: ?user_id=syle
output:[
{type: 'reload', balance: 20.00, timestamp: 1234},
{type: 'purchase', balance: -5, timestamp: 4567}
]

##### POST /api/v1/reload add a new card to the list
input: user_id, balance, card_id, card_code
output: no output (HTTP Response code - some success code)

##### POST /api/v1/purchase add a new purchase transaction to the list
input: user_id, balance, note
output: no output (HTTP Response code - some success code)


#### Front End
Front end - will implement both web and android, and have the following components:

a. login flow (simply ask for first name and last name), will use this for authentication
b. landing page to show your user_id and current balance
c. transaction list
d. add new card (reload) (refer to the above api for input)
e. add new purchase (refer to the above api for input)






## Sprint Updates
### 07/15/2018
- Initial Code complete, Server is now Up and Running
- Created 2 AWS EC instances for prod and dev
  - PROD: ec2-18-209-62-254.compute-1.amazonaws.com
  - DEV: ec2-34-192-241-153.compute-1.amazonaws.com
- Created AWS RDS for data store
- Functional getCards API
- Functional signUp API
- Basic plumbing works for POJO Objects such as `Purchase`, `UserProfile`, `Card`
- Created SQL Backup file for new deployment `sql_backup`

### 07/14/2018
- Team meetup, draft out dependencies, task breakdown and intial work


## TODO's
### Infra Works
- [X] Set up AWS Dynamo DB (for NoSQL) or RDS to persist data. 2 instances: 1 for prod and 1 for dev.
- [X] Set up the pipeline for webapp (AWS Beanstalk) includes the domain and basic hello world api.
- [ ] [Nice to have]. Set up CI/CD pipeline for the above webapp/noSQL in the github repo...

### API Work
- [X] User Signup (Sy)
- [ ] User Signin (Sy)
- [ ] User Signout (Sy)
- [ ] Get User Info by UserID (Sy)
- [X] Create Card / Reload (Lin)
- [ ] Create Purchase / Payment (Hyunwook)
- [X] Get All Transactions (Cards & Purchase) by uid (Hyunwook) 
  - [X] Get Cards by uid (Lin) 
  - [X] Get Purchase by uid (Kevin)


### Testings / Misc
- [ ] Unit Tests
- [ ] Integration
- [ ] UML Diagrams Documentation
- [ ] Project Report



### Desired Extra Credit Sections
- [X] Implement a "real" iOS or Android Mobile App calling the REST API
- [ ] Deploy API to AWS in an Auto Scaled EC2 Cluster with Load Balancer
- [ ] Deploy API to AWS as Docker Containers in Amazon Containers
- [ ] Deploy API to AWS as Docker Containers in Amazon EKS
- [X] Implement Web "Front-End" Deployed to Heroku for Starbucks Payment Card management
