FROM openjdk:11
VOLUME /tmp
COPY target/kyc-aggregator-mgt.jar kyc-aggregator-mgt.jar
ENV JAVA OPTS=""
ENTRYPOINT exec java -jar kyc-aggregator-mgt.jar --debug