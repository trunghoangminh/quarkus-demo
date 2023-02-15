SHELL:=/bin/bash

build-dev-images:
	docker build -f src/main/docker/Dockerfile.dev -t quarkus/quarkus-demo-dev .

build-jvm-images:
	./mvnw clean package -DskipTests; \
	docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-demo-jvm .

build-native-images:
	./mvnw clean package -Pnative -Dquarkus.native.container-build=true -DskipTests; \
	docker build -f src/main/docker/Dockerfile.native -t quarkus/quarkus-demo-native .

build-spring-boot-images:
	docker build -f spring-boot/Dockerfile -t spring/spring-boot-demo .

build-all-images: build-dev-images build-jvm-images build-native-images build-spring-boot-images build-spring-boot-images

run-dev:
	docker-compose up -d --force-recreate quarkus-demo-dev

exec-dev:
	docker exec -it -uroot quarkus-demo-dev bash -c 'cd /work && ./mvnw quarkus:dev -Dquarkus.http.host=0.0.0.0 -DdebugHost=0.0.0.0 -DskipTests';

run-jvm:
	docker-compose up -d --force-recreate quarkus-demo-jvm

run-native:
	docker-compose up -d --force-recreate quarkus-demo-native

run-spring-boot:
	docker-compose up -d --force-recreate spring-boot-demo

clean-containers:
	docker-compose down

clean-all: clean-containers
	sudo rm -rf data
