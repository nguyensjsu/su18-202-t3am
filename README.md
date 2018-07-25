# su18-202-t3am
Welcome to the su18-202-t3am wiki!

## Members
- Sy Le
- Kevin Lai
- Hyunwook Shin
- Lin Cheng

## Main Docs
### Team Project Report
https://github.com/nguyensjsu/su18-202-t3am/blob/master/CMPE%20202%20-%20T3am%20Group%20Project%20Report.pdf

### Team Presentation Slides
https://github.com/nguyensjsu/su18-202-t3am/blob/master/CMPE%20202%20-%20T3am%20Group%20Project%20Slidedeck.pdf

## Important Links
### Sprint board in google sheet
Please update your task here....
- https://docs.google.com/spreadsheets/d/1pfwGInHH9siD7P3Q7gQIALwi6-xfxB6CuSd0tBX_e3c/edit?usp=sharing

### Github link...
- https://github.com/nguyensjsu/su18-202-t3am

### How to set up project locally
- https://docs.google.com/document/d/1YKTlsn7VnxUjwjOXPiQigHKz-usWgbWOH0NESBkKrKk/edit?usp=sharing

Note: I added a maven plugin to run the server automatically using just maven

```
# from project root
mvn clean install

# start the sever
mvn exec:java -pl starbucks2-service
```

### Demo Envs
#### Staging Env
- api: http://ec2-34-192-241-153.compute-1.amazonaws.com:8202
- webapp: https://cmpe202-t3am-starbucks-webapp.herokuapp.com

### Prod Env
- api: TBD
- webapp: TBD


### Heroku Envs
- api : http://cmpe202-java-rest-api.herokuapp.com
- webapp : http://cmpe202-t3am-starbucks-webapp.herokuapp.com

### Jenkins Server
- https://ec2-18-222-125-85.us-east-2.compute.amazonaws.com/jenkins

## Sample Curl / Demo
### Sign up (Create User)
This API is used for NEW users to sign up for new account.

#### Curl Sample
```
curl -X POST \
  http://localhost:8202/api/v1/signup \
  -H 'accept: application/json' \
  -d '{
  "email": "syle1@gmail.com",
  "full_name": "Sy Le",
  "password": "password"
}'
```

#### Response
https://github.com/nguyensjsu/su18-202-t3am/blob/master/starbucks2-domain/src/main/java/model/UserProfile.java


### Sign in (Log in)
This API is used for EXSITING users to sign in with their old account.

#### Curl Sample
```
curl -X POST \
  http://localhost:8202/api/v1/signin \
  -H 'accept: application/json' \
  -d '{
  "email": "syle1@gmail.com",
  "password": "password"
}'
```

#### Response
https://github.com/nguyensjsu/su18-202-t3am/blob/master/starbucks2-domain/src/main/java/model/UserProfile.java



### Get Cards Reload by UserId
This API is used to get a list of cards reloaded by userId

#### Curl Sample
```
curl -X GET \
  'http://localhost:8202/api/v1/cards?uid=2c60158e-d432-4b78-a300-360cc6fa7260'
```

#### Response
https://github.com/nguyensjsu/su18-202-t3am/blob/master/starbucks2-domain/src/main/java/model/Card.java



### Reload Card by UserId
This API is used to add/reload a card for a user by UserId

#### Curl Sample
```
curl 'http://localhost:8080/api/v1/reload' \8081/no-referrer' \
    -H 'Connection: keep-alive' \
    -H 'DNT: 1' --data-binary '{"uid":"958bbc20-cda2-4f57-9fe6-e1ecb6e6e913","number":"111111111","code":"111","balance":"10"}'
```

#### Response
https://github.com/nguyensjsu/su18-202-t3am/blob/master/starbucks2-domain/src/main/java/model/Card.java




### Get Purchases by UserId
This API is used to get a list of previous purchases by UserID

#### Curl Sample
```
curl -X GET \
  'http://localhost:8202/api/v1/purchases?uid=2c60158e-d432-4b78-a300-360cc6fa7260'
```

#### Response
https://github.com/nguyensjsu/su18-202-t3am/blob/master/starbucks2-domain/src/main/java/model/Purchase.java




### Add Purchase by UserId
This API is used to add a purchase by UserID

#### Curl Sample
```
curl 'http://localhost:8080/api/v1/purchase' \
    -H 'Content-Type: application/json; charset=utf-8' \
    -H 'DNT: 1' --data-binary '{"uid":"958bbc20-cda2-4f57-9fe6-e1ecb6e6e913","balance":"3","note":"Iced Coffee"}'
```

#### Response
https://github.com/nguyensjsu/su18-202-t3am/blob/master/starbucks2-domain/src/main/java/model/Purchase.java


### Front End Flows/Components
Front end - will implement both web and android, and have the following components:

a. login flow (simply ask for first name and last name), will use this for authentication
b. landing page to show your user_id and current balance
c. transaction list
d. add new card (reload) (refer to the above api for input)
e. add new purchase (refer to the above api for input)






## Sprint Updates
### 07/21/2018 to 07/27/2018
- Team mainly works on project report

### 07/20/2018
- Done Front End and Deployed Front End App and API to Heroku
  - api : http://cmpe202-java-rest-api.herokuapp.com
  - webapp : http://cmpe202-t3am-starbucks-webapp.herokuapp.com
- Started and work on report

### 07/19/2018
- Complete last set of flow front end integration
- Start work on the deployment of front end heroku deployment
- Added Validation to API
- Wrap server response in ServerResponse
- Start work on project report
- Successfully run on AWS ECS Fargate

### 07/17/2018 to 07/18/2018
- Continue working on integrating Web FE in Add Purchase Flow
- Refactored and clean up code to make the Add Purchase API flow to work
- Clean up sql_backup file
- Docker container for the API later
- WIP - Continued Add mode unit tests

### 07/16/2018
- Completed API to sign in using `email` and `password`
- Deployed our API Back End to heroku: http://cmpe202-java-rest-api.herokuapp.com
- Continued BE and FE (webapp)integration work: authentication (signin / signup) flow
- Parametrized Database Connection Strings, App Host, App Port into environment for deployment.
- Fixed UserProfile Fetch API to return real data instead of mocks
- WIP - CI/CD pipeline with jenkins
- WIP - Plumbing up unit tests

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
- [X] Set up EC2 instances or ECS Fargate as API server.
- [X] Set up CI/CD pipeline for the above webapp/noSQL in the github repo...

### API Work
- [X] Server framework (Lin)
- [X] User Signup (Sy)
- [X] User Signin (Hyunwook)
- [X] User Signout (Hyunwook)
- [X] Get User Info by UserID (Sy)
- [X] Create Card / Reload (Lin)
- [X] Create Purchase / Payment (Hyunwook)
- [X] Get All Transactions (Cards & Purchase) by uid (Hyunwook)
  - [X] Get Cards by uid (Lin)
  - [X] Get Purchase by uid (Kevin)


### Testings / Misc
- [X] Unit Tests
- [X] Integration
- [X] UML Diagrams Documentation
- [X] Project Report



### Desired Extra Credit Sections
- [ ] Implement a "real" iOS or Android Mobile App calling the REST API
- [X] Implement Web "Front-End" Deployed to Heroku for Starbucks Payment Card management
- [X] Deploy API to AWS as Docker Containers in Amazon Containers
- [ ] Deploy API to AWS in an Auto Scaled EC2 Cluster with Load Balancer
- [ ] Deploy API to AWS as Docker Containers in Amazon EKS
