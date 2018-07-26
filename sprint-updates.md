# Standups
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

