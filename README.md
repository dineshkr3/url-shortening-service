# Url Shortening Service

This is docker compose based URL Shortning Service which generates short url for the given url provide through REST Api. It also provides api to redirect to the actual url by providing short url.

# Features

  - /generate : Generates Short URL

# Technologies
    - Spring Boot : Application framework
    - MongoDb : Database to store Urls and aliases
    - Ehcache: Caching the alias for the configured amount of time.
    - Docker-Compose: For deploying mongodb and Application container

## Build

#### Building for source
```sh
$ mvn clean install
```
#### Building docker Image
```sh
$ docker build -t url-shortening-service .
```

## Installation

#### Deploy
Install the Application Server and MongoDb on Docker

```sh
$ cd docker
$ docker-compose up -d

```
#### Deployment on Kubernetes
Once image is pushed to artifactory then we can use Helm Charts to deploy on to Kubernetes.

#### Undeploy
For production environments...

```sh
$ docker-compose stop
$ docker-compose rm
```

### Test

UrlShorteningServiceApplicationTest class contains the test case to validate working application.

### Swagger UI
http://localhost:8085/swagger-ui/index.html

Swagger UI can be accessed once deployed as mentioned above.
