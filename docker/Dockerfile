FROM eclipse-temurin:17

#Metadata
LABEL mantainer="vaibhavjaiswal248@gmail.com"

WORKDIR /app

#Copy jar file from existing location to destination
COPY target/docker-0.0.1-SNAPSHOT.jar /app/springboot-docker-test.jar

#Entrypoint to start jar file from
ENTRYPOINT ["java", "-jar", "springboot-docker-test.jar"]

#Note: The .jar file name that copy in /app directory and the entry point name of .jar should match

#Run below command to build dockerfile to an image with tag: latest
# docker build -t springboot-docker-demo .
#Run below command to build dockerfile to an image with a custom tag
# docker build -t springboot-docker-demo:0.1.RELEASE .
#Note: The name of .jar file and docker image need not be same coz .jar is the file the application runs from

#Run docker image:
# docker run -p 8090:8082 springboot-docker-demo:0.1.RELEASE
#Note: In above command, -p maps the port of springboot application to docker host or OS machine host's port: -p <host_port_local>:<springboot_container_port>
#By default, processes inside a container run in an isolated network namespace.
#Even if the app is running and listening inside the container, the host can’t access it unless you explicitly expose/map the ports.

#To check running containers
# docker ps

#To run docker container in a detached mode i.e. in the background
# docker run -p 8090:8082 -d springboot-docker-demo:0.1.RELEASE

# Then we can get logs in 2 ways:
# 1) Logs till now: docker logs c1e0
# 2) Tail the live logs using -f: docker logs -f c1e0
# c1e0 is the docker initial 4 chars of the container id
# press ctrl+C to exit the logs. This time it wont stop the container but just exit.

# We can verify using ps (process status):
# docker ps
# CONTAINER ID   IMAGE                                COMMAND                  CREATED         STATUS         PORTS                                         NAMES
# c1e029c06d18   springboot-docker-demo:0.1.RELEASE   "java -jar springboo…"   2 minutes ago   Up 2 minutes   0.0.0.0:8090->8082/tcp, [::]:8090->8082/tcp   frosty_engelbart

# Now to stop the docker container use initial 4 chars of the container id
# docker stop c1e0

#Running multiple docker container instance of local docker image with different name:
# 1) without giving any explicit name to the container:
# Docker creates a random name of the app
# docker run -p 8090:8082 -d springboot-docker-demo:0.1.RELEASE
# 2) with a custom name of the docker container
# docker run --name my-app -p 8091:8082 -d vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
# 3) Check the names of the container
# docker ps
# CONTAINER ID   IMAGE                                                  COMMAND                  CREATED              STATUS              PORTS                                         NAMES
# e4e349ae92aa   vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE   "java -jar springboo…"   3 seconds ago        Up 2 seconds        0.0.0.0:8091->8082/tcp, [::]:8091->8082/tcp   my-app
# bea7ecc13b4f   vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE   "java -jar springboo…"   About a minute ago   Up About a minute   0.0.0.0:8090->8082/tcp, [::]:8090->8082/tcp   modest_davinci

#Pushing docker image to docker hub:
# 1) Update the tag with <dockerhub id>/<docker-image>:<tag>
# docker tag springboot-docker-demo:0.1.RELEASE vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
# 2) Push the image:
# docker push vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE

#Note: What happens when you docker tag step?
# Docker simply adds another reference (tag) to the same image ID (can check Image ID using docker images)
# It does not duplicate the layers.
# Both names point to the same image in the local image store so twice the storage is not used

#Pull docker image from dockerhub
# 1) Pull latest tag (if available)
# docker pull mysql or docker pull mysql:latest
# 2) Pull a given tag:
# docker pull vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
# List images after pulling
# docker images

#Run docker image in docker container
# 1) Run container in background (docker gives random name to the app):
# docker run -p 8091:8082 -d vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
# 2) Run container with custom app name in background:
# Run springboot app in docker container : docker run --name my-app -p 8091:8082 -d vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE

#Example of my sql db running in docker conatiner:
# Run mysql in docker container: docker run -p 3307:3306 --name mysqldocker -e MYSQL_ROOT_PASSWORD=Mysql@123 -e MYSQL_DATABASE=employee_db -e MYSQL_USER=vaibhav -e MYSQL_PASSWORD=vaibhav -d mysql:latest
# Check logs of mysql running container docker logs -f mysqldocker
# To check if mysql db is running in docker container, execute the container app:
# docker exec -it mysqldocker bash
# In the above command, -i means interctive and -t gives terminal
# Run the command to login after bash opens: mysql -u root -p
# Enter the password given above: Mysql@123
# mysql db terminal opens
# list the dbs to check if "employee_db" is created from above command: mysql> show databases;
#    +--------------------+
#    | Database           |
#    +--------------------+
#    | employee_db        |
#    | information_schema |
#    | mysql              |
#    | performance_schema |
#    | sys                |
#    +--------------------+
#    5 rows in set (0.026 sec)

#Remove or delete a local docker image:
# 1) By name + tag:
# docker rmi vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
# 2) By image id:
# docker rmi abc123456789

#Remove built container apps
# Container are stopped but the state's meta data is stored in local. It can be started from the same state again later with the container id. To delete all the stopped containers use:
# Get all the stopped containers
# docker ps -a
# Remove a particular stopped container:
# docker rm <container-id> or docker rm <app-name>
# Remove all the stopped containers (not the running ones):
# docker rm $(docker ps -aq)
