DOCKER_IMAGE_MAVEN=$(shell grep DOCKER_IMAGE_MAVEN .env | cut -d '=' -f2)
DOCKER_IMAGE_JAVA=$(shell grep DOCKER_IMAGE_JAVA .env | cut -d '=' -f2)
SERVER_PORT=$(shell grep SERVER_PORT .env | cut -d '=' -f2)
LOCAL_DIR=$(shell pwd)
MAVEN_CACHE_DIR=$(HOME)/.m2
DOCKER_DIR=/app
PROFILE=default
ifneq (,$(word 2, $(MAKECMDGOALS)))
  PROFILE := $(word 2, $(MAKECMDGOALS))
  $(eval $(PROFILE):;@:)
endif
IMAGE_NAME=my-hex-spring-app
ifneq (,$(word 2, $(MAKECMDGOALS)))
  IMAGE_NAME := $(word 2, $(MAKECMDGOALS))
  $(eval $(IMAGE_NAME):;@:)
endif

build:
	@docker run --rm -v $(LOCAL_DIR):$(DOCKER_DIR) -w $(DOCKER_DIR)/code -v $(MAVEN_CACHE_DIR):/root/.m2 -it $(DOCKER_IMAGE_MAVEN) \
		mvn clean install -DskipTests=true

run:
	@docker run -p $(SERVER_PORT):$(SERVER_PORT) -v $(LOCAL_DIR):$(DOCKER_DIR) -w $(DOCKER_DIR) -it $(DOCKER_IMAGE_JAVA) \
 		java -jar \
 		-Dspring.profiles.active=$(PROFILE) \
 		-Dserver.port=$(SERVER_PORT) \
 		code/boot/target/*.jar

clean:
	@docker run --rm -v $(LOCAL_DIR):$(DOCKER_DIR) -w $(DOCKER_DIR)/code -v $(MAVEN_CACHE_DIR):/root/.m2 -it $(DOCKER_IMAGE_MAVEN) \
		mvn clean

image:
	@docker build --rm \
		--build-arg DOCKER_IMAGE_MAVEN=$(DOCKER_IMAGE_MAVEN) \
        --build-arg DOCKER_IMAGE_JAVA=$(DOCKER_IMAGE_JAVA) \
        --build-arg SERVER_PORT=$(SERVER_PORT) \
        --build-arg PROFILE=$(PROFILE) \
        -t $(IMAGE_NAME) .

.PHONY: build run clean image