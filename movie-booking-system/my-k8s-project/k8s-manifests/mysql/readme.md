# Apply the PersistentVolumeClaim
kubectl apply -f mysql-pvc.yml

# Apply the MySQL Deployment
kubectl apply -f mysql-deployment.yml

# Apply the MySQL Service
kubectl apply -f mysql-service.yml

# Verifying the deployment

# 1: Check Pod Status: Run the following command to verify the MySQL pod is running:

kubectl get pods

# 2: Check Service Status: Run the following command to verify the MySQL service is correctly exposed: 
kubectl get svc

# 3: Port Forwarding (Optional): If you want to access MySQL from your local machine, use port-forwarding:

kubectl port-forward svc/mysql-service 3307:3307

# 4: Connecting to MySQL: Use any MySQL client to connect to MySQL:

mysql -h 127.0.0.1 -P 3307 -u root -p

mysql -h 127.0.0.1 -P 3307 -u root -p


