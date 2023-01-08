# Quarkus Demo

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## 1. Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
$ ./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## 2. Running the application inside docker container
### 2.1 Run the application with JVM mode

#### Build docker images
```shell script
$ make build-jvm
```
#### Run docker container
```shell script
$ make run-jvm
```
### 2.2 Run application with Native mode
#### Build docker images
```shell script
$ make build-native
```
#### Run docker container
```shell script
$ make run-native
```