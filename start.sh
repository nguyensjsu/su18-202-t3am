echo "Build Project" && \
pushd starbucks2-common && \
mvn install && \
popd && \
pushd starbucks2-domain && \
mvn install && \
popd && \
pushd starbucks2-service && \
mvn install && \
popd && \
java -cp starbucks2-service/target/starbucks2-service-1.0.jar RestService && \
echo "Server Started"
