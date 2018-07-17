# 
# Hyunwook Shin
#
VERSION = 1.0
DUMMY_PASSWD = _your_db_password
NEW_PASSWD ?= $(DUMMY_PASSWD)

clean:
	mvn clean
	$(MAKE) -C jenkins clean

populate:
	sed -i "s/value=\"$(DUMMY_PASSWD)\"/value=\"$(NEW_PASSWD)\"/g" starbucks2-domain/src/java/sql-maps-config.xml

unpopulate:
	sed -i "s/value=\"$(NEW_PASSWD)\"/value=\"$(DUMMY_PASSWD)\"/g" starbucks2-domain/src/java/sql-maps-config.xml

install: clean
	mvn install

test-junit: install
	mvn test

run: install
	java -cp starbucks2-service/target/starbucks2-service-$(VERSION).jar RestService

test: test-junit
	$(MAKE) -C jenkins test
