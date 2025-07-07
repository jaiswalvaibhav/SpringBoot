# Docker documentation

Dockerfile: https://docs.docker.com/reference/dockerfile/

# Spring Boot Docker Demo

A comprehensive guide for containerizing and deploying Spring Boot applications using Docker.

## 1. Dockerfile Configuration

```dockerfile
FROM eclipse-temurin:17

#Metadata
LABEL mantainer="vaibhavjaiswal248@gmail.com"

WORKDIR /app

#Copy jar file from existing location to destination
COPY target/docker-0.0.1-SNAPSHOT.jar /app/springboot-docker-test.jar

#Entrypoint to start jar file from
ENTRYPOINT ["java", "-jar", "springboot-docker-test.jar"]

#Note: The .jar file name that copy in /app directory and the entry point name of .jar should match
```

**Important Note:** The .jar file name that you copy in the /app directory and the entry point name of .jar should match.

## 2. Building Docker Images

### 2.1 Build with Latest Tag
```bash
# Run below command to build dockerfile to an image with tag: latest
docker build -t springboot-docker-demo .
```

### 2.2 Build with Custom Tag
```bash
# Run below command to build dockerfile to an image with a custom tag
docker build -t springboot-docker-demo:0.1.RELEASE .
```

**Note:** The name of .jar file and docker image need not be same because .jar is the file the application runs from.

## 3. Running Docker Containers

### 3.1 Basic Container Run
```bash
# Run docker image:
docker run -p 8090:8082 springboot-docker-demo:0.1.RELEASE
```

**Port Mapping Explanation:**
- In the above command, `-p` maps the port of springboot application to docker host or OS machine host's port: `-p <host_port_local>:<springboot_container_port>`
- By default, processes inside a container run in an isolated network namespace
- Even if the app is running and listening inside the container, the host can't access it unless you explicitly expose/map the ports

### 3.2 Check Running Containers
```bash
# To check running containers
docker ps
```

### 3.3 Background (Detached) Mode
```bash
# To run docker container in a detached mode i.e. in the background
docker run -p 8090:8082 -d springboot-docker-demo:0.1.RELEASE
```

## 4. Container Log Management

After running containers in detached mode, you can access logs in two ways:

```bash
# Then we can get logs in 2 ways:
# 1) Logs till now: docker logs c1e0
docker logs c1e0

# 2) Tail the live logs using -f: docker logs -f c1e0
docker logs -f c1e0
```

**Note:** `c1e0` is the docker initial 4 chars of the container id. Press Ctrl+C to exit the logs. This time it won't stop the container but just exit.

### 4.1 Verify Container Status
```bash
# We can verify using ps (process status):
docker ps
```

**Example Output:**
```
CONTAINER ID   IMAGE                                COMMAND                  CREATED         STATUS         PORTS                                         NAMES
c1e029c06d18   springboot-docker-demo:0.1.RELEASE   "java -jar springboo…"   2 minutes ago   Up 2 minutes   0.0.0.0:8090->8082/tcp, [::]:8090->8082/tcp   frosty_engelbart
```

### 4.2 Stop Container
```bash
# Now to stop the docker container use initial 4 chars of the container id
docker stop c1e0
```

## 5. Running Multiple Docker Container Instances

You can run multiple docker container instances of local docker image with different names:

### 5.1 Without Explicit Name (Random Name)
```bash
# 1) without giving any explicit name to the container:
# Docker creates a random name of the app
docker run -p 8090:8082 -d springboot-docker-demo:0.1.RELEASE
```

### 5.2 With Custom Container Name
```bash
# 2) with a custom name of the docker container
docker run --name my-app -p 8091:8082 -d vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
```

### 5.3 Check Container Names
```bash
# 3) Check the names of the container
docker ps
```

**Example Output:**
```
CONTAINER ID   IMAGE                                                  COMMAND                  CREATED              STATUS              PORTS                                         NAMES
e4e349ae92aa   vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE   "java -jar springboo…"   3 seconds ago        Up 2 seconds        0.0.0.0:8091->8082/tcp, [::]:8091->8082/tcp   my-app
bea7ecc13b4f   vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE   "java -jar springboo…"   About a minute ago   Up About a minute   0.0.0.0:8090->8082/tcp, [::]:8090->8082/tcp   modest_davinci
```

## 6. Pushing Docker Image to Docker Hub

### 6.1 Update Tag with Docker Hub ID
```bash
# 1) Update the tag with <dockerhub id>/<docker-image>:<tag>
docker tag springboot-docker-demo:0.1.RELEASE vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
```

### 6.2 Push the Image
```bash
# 2) Push the image:
docker push vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
```

**Important Note:** What happens when you docker tag step?
- Docker simply adds another reference (tag) to the same image ID (can check Image ID using `docker images`)
- It does not duplicate the layers
- Both names point to the same image in the local image store so twice the storage is not used

## 7. Pull Docker Image from Docker Hub

### 7.1 Pull Latest Tag
```bash
# 1) Pull latest tag (if available)
docker pull mysql
# or
docker pull mysql:latest
```

### 7.2 Pull Specific Tag
```bash
# 2) Pull a given tag:
docker pull vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
```

### 7.3 List Images After Pulling
```bash
# List images after pulling
docker images
```

## 8. Run Docker Image in Docker Container

### 8.1 Run Container in Background (Random Name)
```bash
# 1) Run container in background (docker gives random name to the app):
docker run -p 8091:8082 -d vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
```

### 8.2 Run Container with Custom Name
```bash
# 2) Run container with custom app name in background:
# Run springboot app in docker container:
docker run --name my-app -p 8091:8082 -d vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
```

## 9. MySQL Database Container Example

### 9.1 Run MySQL in Docker Container
```bash
# Example of mysql db running in docker container:
# Run mysql in docker container:
docker run -p 3307:3306 --name mysqldocker \
  -e MYSQL_ROOT_PASSWORD=Mysql@123 \
  -e MYSQL_DATABASE=employee_db \
  -e MYSQL_USER=vaibhav \
  -e MYSQL_PASSWORD=vaibhav \
  -d mysql:latest
```

### 9.2 Check MySQL Container Logs
```bash
# Check logs of mysql running container
docker logs -f mysqldocker
```

### 9.3 Access MySQL Container
```bash
# To check if mysql db is running in docker container, execute the container app:
docker exec -it mysqldocker bash
```

**Note:** In the above command, `-i` means interactive and `-t` gives terminal.

### 9.4 Login to MySQL
```bash
# Run the command to login after bash opens:
mysql -u root -p
# Enter the password given above: Mysql@123
```

### 9.5 Verify Database Creation
```bash
# mysql db terminal opens
# list the dbs to check if "employee_db" is created from above command:
mysql> show databases;
```

**Expected Output:**
```
+--------------------+
| Database           |
+--------------------+
| employee_db        |
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
5 rows in set (0.026 sec)
```

## 10. Remove or Delete Docker Images

### 10.1 By Name and Tag
```bash
# 1) By name + tag:
docker rmi vaibhavjaiswal248/springboot-docker-demo:0.1.RELEASE
```

### 10.2 By Image ID
```bash
# 2) By image id:
docker rmi abc123456789
```

## 11. Remove Built Container Apps

**Important Note:** Containers are stopped but the state's metadata is stored locally. It can be started from the same state again later with the container id.

### 11.1 Get All Stopped Containers
```bash
# Get all the stopped containers
docker ps -a
```

### 11.2 Remove Specific Stopped Container
```bash
# Remove a particular stopped container:
docker rm <container-id>
# or
docker rm <app-name>
```

### 11.3 Remove All Stopped Containers
```bash
# Remove all the stopped containers (not the running ones):
docker rm $(docker ps -aq)
```

**Note:** This command removes all stopped containers but not the running ones.

## 12. Best Practices

1. **Port Management**: Always ensure host ports aren't already in use before mapping
2. **Container Naming**: Use meaningful names for containers, especially in production environments
3. **Resource Management**: Regularly clean up unused containers and images to save disk space
4. **Environment Variables**: Use environment variables for configuration instead of hardcoding values
5. **Image Tagging**: Use specific version tags instead of 'latest' for production deployments

## 13. Troubleshooting

- **Port Conflicts**: If you get port binding errors, check if the host port is already in use
- **Container Access**: Verify that port mappings are correct and containers are running
- **Resource Issues**: Check available disk space and memory if containers fail to start
- **Network Issues**: Ensure Docker daemon is running and network connectivity is available

# Terminal, SSH, CLI and Shell
| Term         | What it is                                                          |
| ------------ | ------------------------------------------------------------------- |
| **Terminal** | The app you use to type commands (the window).                      |
| **Shell**    | The program that runs inside the terminal to process your commands. |
| **bash**     | A specific type of shell.                                           |
| **CLI**      | The whole concept of interacting via text commands.                 |
| **SSH**      | A way to open a CLI session on a remote computer securely.          |


| You open… | It gives you… | which runs…       |
| --------- | ------------- | ----------------- |
| Terminal  | CLI window    | Shell (like bash) |
| SSH       | Remote CLI    | Remote shell      |
