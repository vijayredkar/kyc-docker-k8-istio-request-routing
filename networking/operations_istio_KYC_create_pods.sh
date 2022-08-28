echo "----w/ istio injected ns basic--------"
kubectl create ns basic
kubectl label namespace basic istio-injection=enabled
kubectl apply -f /c/Vijay/Java/projects/kyc-k8-docker-istio/networking/operations_kyc-credit-check-basic-k8-istio-mtls.yml  -n basic
kubectl get pods -n basic -o wide
kubectl get services -n basic -o wide

echo "----w/ istio injected ns advanced--------"
kubectl create ns advanced
kubectl label namespace advanced istio-injection=enabled
kubectl apply -f /c/Vijay/Java/projects/kyc-k8-docker-istio/networking/operations_kyc-credit-check-advanced-k8-istio-mtls.yml  -n advanced
kubectl get pods -n advanced -o wide
kubectl get services -n advanced -o wide


echo "----w istio injected ns consumer--------"
kubectl create ns consumer
kubectl label namespace consumer istio-injection=enabled
kubectl create -f /c/Vijay/Java/projects/kyc-k8-docker-istio/networking/operations_kyc-aggregator-mgt-k8-istio-mtls.yml -n consumer
kubectl get pods -n consumer -o wide
kubectl get services -n consumer -o wide
