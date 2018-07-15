# 
# Hyunwook Shin
#
VERSION = 1.0

clean:
	mvn clean

install: clean
	mvn install

run: install
	java -cp starbucks2-service/target/starbucks2-service-$(VERSION).jar RestService
