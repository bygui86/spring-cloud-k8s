# SpringCloud KubernetesConfig sample project

## topics
* spring boot
* spring cloud
* spring cloud kubernetes
* spring cloud kubernetes config
* kubernetes configmap

---

## instructions

1. Start minikube

	**[optional if testing locally]**

		minikube start
		eval $(minikube docker-env)

2. Build Docker image

		docker build -t spring-cloud-k8s-config:0.0.1 .

3. Check Docker image

		docker images | grep -i "spring-cloud-k8s-config"

4. Deploy ConfigMap

		kubectl create -f kube-resources/configmap.yaml

5. Check ConfigMap creation

		kubectl get cm
		kubectl describe cm spring-cloud-k8s-config

6. Deploy ServiceAccount, Roles and RoleBindings

		kubectl create -f kube-resources/svc-account.yaml
		kubectl create -f kube-resources/role.yaml
		kubectl create -f kube-resources/role-bind.yaml

7. Check ServiceAccount, Roles and RoleBindings creation

		kubectl get sa
		kubectl get sa spring-cloud-k8s-config -o yaml
		kubectl get roles
		kubectl get roles spring-cloud-k8s-config -o yaml
		kubectl get rolebindings
		kubectl get rolebindings spring-cloud-k8s-config -o yaml

8. Deploy application

		kubectl create -f kube-resources/deploy.yaml
		kubectl rollout status deploy/spring-cloud-k8s-config

9. Check application deployment

		kubectl get deploy
		kubectl get po -l app=spring-cloud-k8s-config -o jsonpath='{range .items[*]}{.metadata.name}{"\n"}{end}' | xargs kubectl logs -f

10. Expose application

		kubectl create -f kube-resources/svc.yaml

11. Check application exposure

		kubectl get svc -l app=spring-cloud-k8s-config

12. Create Ingress

	**[optional]**

		kubectl create -f kube-resources/ing.yaml

13. Check application exposure

	**[optional]**

		kubectl get ing -l app=spring-cloud-k8s-config

14. Test application

		curl $(minikube service spring-cloud-k8s-config --url)"/info"
			... or if you have httpie installed ...
		http GET $(minikube service spring-cloud-k8s-config --url)"/info"

15. Reloading ConfigMap

		kubectl edit configmap spring-cloud-k8s-config
	
	* modify the value of a property, no matter which one
	* save the modification
	* wait a some seconds (basing on the reload polling configured on the service, default are 15)

16. Test application after configurations refresh

		curl $(minikube service spring-cloud-k8s-config --url)"/info"
			... or if you have httpie installed ...
		http GET $(minikube service spring-cloud-k8s-config --url)"/info"

17. Remove everything

		[optional] kubectl delete ing spring-cloud-k8s-config

		kubectl delete svc spring-cloud-k8s-config
		kubectl delete deploy spring-cloud-k8s-config
		kubectl delete configmaps spring-cloud-k8s-config
		kubectl delete sa spring-cloud-k8s-config
		kubectl delete roles spring-cloud-k8s-config
		kubectl delete rolebindings spring-cloud-k8s-config
		docker image rm spring-cloud-k8s-config:0.0.1

---

## links
[DONE]
* https://github.com/spring-cloud/spring-cloud-kubernetes/tree/master/spring-cloud-kubernetes-examples/kubernetes-hello-world-example
* https://github.com/spring-cloud/spring-cloud-kubernetes/tree/master/spring-cloud-kubernetes-examples/kubernetes-reload-example
* https://github.com/spring-cloud/spring-cloud-kubernetes

[IN-PROGRESS]

[TODO]
* https://github.com/Salaboy/spring-cloud-k8s-boss
* https://github.com/Salaboy/spring-cloud-k8s-minion
* https://github.com/Salaboy/spring-cloud-k8s-gateway
* https://github.com/Salaboy/spring-cloud-kubernetes-examples
* https://github.com/fabric8io/kubeflix
