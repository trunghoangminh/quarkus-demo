# Quarkus Demo

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

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