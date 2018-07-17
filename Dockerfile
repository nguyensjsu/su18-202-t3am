FROM java
EXPOSE 8202
COPY . /opt/t3am
CMD nohup java -cp /opt/t3am/starbucks2-service-1.0.jar RestService &
