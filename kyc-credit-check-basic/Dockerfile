FROM openjdk:11
COPY target/kyc-credit-check-basic.jar kyc-credit-check-basic.jar
ENV JAVA OPTS=""
ENTRYPOINT exec java -jar kyc-credit-check-basic.jar --debug