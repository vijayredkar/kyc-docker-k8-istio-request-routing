cd  /c/Vijay/Java/projects/kyc-k8-docker-istio/kyc-aggregator-mgt
echo "------------------docker rmi--------------------------"
docker rmi kyc-aggregator-mgt:latest
echo "------------------docker rmi--------------------------"
docker rmi -f kyc-aggregator-mgt:latest | docker rmi -f vijayredkar/kyc-aggregator-mgt:latest
echo "------------------mvn clean install--------------------------"
mvn clean install
echo "------------------docker build kyc-aggregator-mgt--------------------------"
docker build -t kyc-aggregator-mgt -f Dockerfile .
echo "------------------docker image ls--------------------------"
docker image ls
echo "------------------docker tag--------------------------"
docker tag  kyc-aggregator-mgt vijayredkar/kyc-aggregator-mgt:latest
echo "------------------docker push--------------------------"
docker push vijayredkar/kyc-aggregator-mgt

