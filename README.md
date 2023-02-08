# Quarkus Demo
##Prerequisites
OS: Ubuntu or Window Subsystem

Tools: Docker, Make

## 1. Running the application in DEV mode
### 1.1 Build DEV docker images
```shell script
$ make build-dev-images
```
### 1.1 Start DEV container
```shell script
$ make run-dev
```
### 1.2 Execute DEV code
```shell script
$ make exec-dev
```
### 1.3 Development
- Use port 5005 to debug application

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## 2. Running the application with deployment
### 2.1 Run the application with JVM mode
#### 2.1.1 Build JVM docker images
```shell script
$ make build-jvm-images
```
#### 2.1.2 Run JVM docker container
```shell script
$ make run-jvm
```
### 2.2 Run application with Native mode
#### 2.2.1 Build Native docker images
```shell script
$ make build-native-images
```
#### 2.2.2 Run Native docker container
```shell script
$ make run-native
```
### 2.3 Run Spring Boot application
#### 2.3.1 Build Spring Boot application
```shell script
$ make build-spring-boot-images
```
#### 2.3.2 Run Spring Boot docker container
```shell script
$ make run-spring-boot
```
### 2.4 Build all images
```shell script
$ make build-all-images
```