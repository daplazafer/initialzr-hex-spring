DOCKER_IMAGE_MAVEN=maven:3.9.5-eclipse-temurin-17-alpine
DOCKER_IMAGE_JAVA=eclipse-temurin:17-jre-alpine
SERVER_PORT=8080

LOCAL_DIR=$(shell pwd)
DOCKER_DIR=/app

ifeq (rename,$(firstword $(MAKECMDGOALS)))
  NEWARGS := $(wordlist 2, $(words $(MAKECMDGOALS)), $(MAKECMDGOALS))
  NEWARGS := $(shell echo '$(NEWARGS)' | tr '[:upper:]' '[:lower:]')
  $(eval $(NEWARGS):;@:)
endif

rename:
	@if [ -z "$(word 1, $(NEWARGS))" ]; then \
		echo "Error: 'GroupId' not specified"; \
		exit 1; \
	fi
	@if [ -z "$(word 2, $(NEWARGS))" ]; then \
		echo "Error: 'ArtifactId' not specified"; \
		exit 1; \
	fi
	@echo "Renaming to $(word 1, $(NEWARGS)) and $(word 2, $(NEWARGS))"
	@find code -type f -not -path '*/\.*' -exec sed -i 's/ggroupid/$(word 1, $(NEWARGS))/g' {} +
	@find code -type f -not -path '*/\.*' -exec sed -i 's/aartifactid/$(word 2, $(NEWARGS))/g' {} +
	@find code -depth -type d -name 'ggroupid' -execdir mv {} $(word 1, $(NEWARGS)) \;
	@find code -depth -type d -name 'aartifactid' -execdir mv {} $(word 2, $(NEWARGS)) \;

test:
	@docker run --rm -v $(LOCAL_DIR):$(DOCKER_DIR) -w $(DOCKER_DIR)/code -it $(DOCKER_IMAGE_MAVEN) mvn verify

build:
	@docker run --rm -v $(LOCAL_DIR):$(DOCKER_DIR) -w $(DOCKER_DIR)/code -it $(DOCKER_IMAGE_MAVEN) mvn clean package -DskipTests=true

run:
	@docker run --rm -p $(SERVER_PORT):$(SERVER_PORT) -v $(LOCAL_DIR):$(DOCKER_DIR) -w $(DOCKER_DIR) -it $(DOCKER_IMAGE_JAVA) java -jar -Dserver.port=$(SERVER_PORT) code/boot/target/*.jar

clean:
	@find code/ -type d -name 'target' -exec rm -rf {} +


.PHONY: rename test build run clean