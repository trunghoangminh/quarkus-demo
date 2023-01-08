SHELL:=/bin/bash

build-jvm:
	./mvnw package -DskipTests; \
	docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-demo-jvm .

run-jvm:
	if [ "$( docker container inspect -f '{{.State.Running}}' mongodb )" == "false" ]; then \
		docker run --name mongodb -d mongo:5.0; \
	fi; \
	docker run -i --rm -p 8080:8080 quarkus/quarkus-demo-jvm

build-native:
	./mvnw package -Pnative -Dquarkus.native.container-build=true -DskipTests; \
	docker build -f src/main/docker/Dockerfile.native -t quarkus/quarkus-demo-native .

run-native:
	if [ "$( docker container inspect -f '{{.State.Running}}' mongodb )" == "false" ]; then \
		docker run --name mongodb -d mongo:5.0; \
	fi; \
	docker run -i --rm -p 8080:8080 quarkus/quarkus-demo-native
