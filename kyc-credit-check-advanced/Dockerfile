FROM openjdk:11
COPY target/kyc-credit-check-advanced.jar kyc-credit-check-advanced.jar
ENV JAVA OPTS=""
ENTRYPOINT exec java -jar kyc-credit-check-advanced.jar --debug