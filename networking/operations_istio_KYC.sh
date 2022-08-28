minikube delete
minikube stop
minikube start --driver=docker
docker login
minikube docker-env
istioctl install --set profile=demo -y