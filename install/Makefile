DOCKER_IMAGE_MAVEN=$(shell grep DOCKER_IMAGE_MAVEN .env | cut -d '=' -f2)
DOCKER_DIR=/ggroupid
ifneq (,$(word 2, $(MAKECMDGOALS)))
  PROFILE := $(word 2, $(MAKECMDGOALS))
  $(eval $(PROFILE):;@:)
endif
COMPOSE_APP = docker-compose-standalone.yml
COMPOSE_INFRA = docker-compose.yml

build:
	@docker run --rm -v $(shell pwd):$(DOCKER_DIR) -w $(DOCKER_DIR)/code -v $(HOME)/.m2:/root/.m2 -it $(DOCKER_IMAGE_MAVEN) \
		mvn clean install -DskipTests=true

test:
	@docker run --rm -v $(shell pwd):$(DOCKER_DIR) -w $(DOCKER_DIR)/code -v $(HOME)/.m2:/root/.m2 -it $(DOCKER_IMAGE_MAVEN) \
    	mvn test

verify:
	@make stop > /dev/null 2>&1
	@docker-compose up -d
	@docker run --network="host" --rm -v $(shell pwd):$(DOCKER_DIR) -w $(DOCKER_DIR)/code -v $(HOME)/.m2:/root/.m2 -it $(DOCKER_IMAGE_MAVEN) \
    	mvn verify -Pintegration-tests
	@make stop > /dev/null 2>&1

clean:
	@docker run --rm -v $(shell pwd):$(DOCKER_DIR) -w $(DOCKER_DIR)/code -v $(HOME)/.m2:/root/.m2 -it $(DOCKER_IMAGE_MAVEN) \
		mvn clean

run:
	@make stop > /dev/null 2>&1
	@PROFILE=$(PROFILE) docker-compose -f $(COMPOSE_APP) -f $(COMPOSE_INFRA) up --build -d

logs:
	@docker-compose -f $(COMPOSE_APP) logs -f

infra:
	@docker-compose up -d

stop:
	@docker-compose -f $(COMPOSE_APP) -f $(COMPOSE_INFRA) down

.PHONY: build test verify clean run logs infra stop