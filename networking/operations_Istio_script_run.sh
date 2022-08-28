cd /c/Vijay/Java/projects/kyc-k8-docker-istio/networking

echo "---------------operations_istio_KYC.sh--------"
./operations_istio_KYC.sh




echo "                        "
echo "------------------Gateway IP - minikube ip--------------------------"
minikube ip



#echo "                        "
#echo "---------------Istio Port - kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}'--------"
#kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}'


echo "                        "
echo "------------------Istio Port - kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}'--------------------------"
kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}'



echo "                        "
echo "------------------Update kyc-aggregator-mgt application with  Gateway IP--------------------------"

echo "                        "
echo "------------------Update kyc-aggregator-mgt application with  Istio Port--------------------------"



echo "                        "
echo "############################ Please complete your config update and press ENTER to continue ########################"
read -p "Press Enter to continue" </dev/tty



echo "---------------operations_istio_KYC_Docker.sh--------"
./operations_istio_KYC_Docker.sh

echo "---------------operations_istio_KYC_create_pods.sh--------"
./operations_istio_KYC_create_pods.sh



echo "                                       "
echo "-------------kubectl get services----------"

echo "                                       "
echo "-------------kubectl get services -n basic -o wide----------"
kubectl get services -n basic -o wide

echo "                                       "
echo "-------------kubectl get services -n advanced -o wide----------"
kubectl get services -n advanced -o wide

echo "                                       "
echo "-------------kubectl get services -n consumer -o wide----------"
kubectl get services -n consumer -o wide



echo "                                       "
echo "-------------kubectl get pods----------"

echo "                                       "
echo "-------------kubectl get pods -n basic -o wide----------"
kubectl get pods -n basic -o wide

echo "                                       "
echo "-------------kubectl get pods -n advanced -o wide----------"
kubectl get pods -n advanced -o wide

echo "                                       "
echo "-------------kubectl get pods -n consumer -o wide----------"
kubectl get pods -n consumer -o wide



echo "                                       "
echo "               add gateway   "
echo "-------------kubectl apply -f kubectl apply -f ----------"
kubectl apply -f operations_kyc-istio-gateway.yml





echo "                                       "
echo "               apply rule - custom header end-user = basic then redirect to basic credit chk MS    "
echo "-------------kubectl apply -f operations_kyc-istio-virtualsvc-basic-routing-headers.yml----------"
kubectl apply -f operations_kyc-istio-virtualsvc-basic-routing-headers.yml

echo "                                       "
echo "               apply rule - custom header end-user = advanced then redirect to advanced credit chk MS    "
echo "-------------kubectl apply -f operations_kyc-istio-virtualsvc-advanced-routing-headers.yml----------"
kubectl apply -f operations_kyc-istio-virtualsvc-advanced-routing-headers.yml
 




echo "                                       "
echo "-------------kubectl apply -f operations_kyc-istio-destrule-basic.yaml----------"
kubectl apply -f operations_kyc-istio-destrule-basic.yaml
echo "                                       "
echo "-------------kubectl apply -f operations_kyc-istio-destrule-advanced.yaml----------"
kubectl apply -f operations_kyc-istio-destrule-advanced.yaml








#check logs
echo "                         "
echo "---------------NEW BASH terminal  -  check logs  --------"
echo "---------------kubectl logs kyc-aggregator-mgt-7f6ddc8bbd-bxqzz -n consumer--------"
echo "---------------kubectl logs pod/kyc-aggregator-mgt-7f6ddc8bbd-bxqzz -n consumer --all-containers--------"
echo "---------------kubectl logs pod/kyc-credit-check-basic-d87477769-5qgbb -n basic --all-containers--------"
echo "---------------kubectl logs pod/kyc-credit-check-advanced-799795b457-9s8g2 -n advanced --all-containers--------"






#testing
echo "                         "
echo "---------------POSTMAN cURL for testing - redirection to basic  --------"
echo "curl --location --request GET 'http://127.0.0.1:8080/kyc/v1/customer?type=headerRedirect&headerValue=basic&creditCheck=basic"

echo "                         "
echo "---------------POSTMAN cURL for testing - redirection to advanced  --------"
echo "curl --location --request GET 'http://127.0.0.1:8080/kyc/v1/customer?type=headerRedirect&headerValue=advanced&creditCheck=advanced"


echo "                         "
echo "---------------POSTMAN cURL for testing - redirection to advanced  --------"
echo "curl --location --request GET 'http://127.0.0.1:8080/kyc/v1/customer' --header 'Content-Type: application/json'"






#port fwding  - wait for 5 mins to allow all PODs to be fully up w/ Istio enabled
echo "                         "
echo "---------------NEW BASH terminal  - manually PORT FWD - wait until all PODs to be fully up w/ Istio enabled--------"
#echo "                        "
echo "############################ sleeping 600 seconds ############################"
sleep 600s

export POD_NAME=$(kubectl get pods -n consumer --no-headers -o custom-columns=":metadata.name" --selector app=kyc-aggregator-mgt)
kubectl port-forward $POD_NAME 8080:8080 -n consumer


















