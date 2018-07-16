# 
# Hyunwook Shin
#
VERSION = 1.0
DUMMY_PASSWD = _get_password_from_doc
NEW_PASSWD ?= $(DUMMY_PASSWD)

clean:
	mvn clean

populate:
	sed -i "s/value=\"$(DUMMY_PASSWD)\"/value=\"$(NEW_PASSWD)\"/g" starbucks2-domain/src/java/sql-maps-config.xml

unpopulate:
	sed -i "s/value=\"$(NEW_PASSWD)\"/value=\"$(DUMMY_PASSWD)\"/g" starbucks2-domain/src/java/sql-maps-config.xml

install: clean
	mvn install

run: install
	java -cp starbucks2-service/target/starbucks2-service-$(VERSION).jar RestService
