# Minimal Web App To Serve CMPE202 Starbucks App...
Deploy to heroku app using node js to serve static html




## Heroku Deployments
### Run locally
#### Approach 1
This uses serve

```
npm i --save serve
node_modules/serve/bin/serve --port $PORT webapp
```


#### Approach 2
This uses plain vanilla node js, clean and no deps...

##### Choose your app
```
heroku git:remote -a your_app_here
```


##### Set ENV
```
heroku config:set API_HOST="http://localhost:8202"
heroku config

```

```
node index.js
```
