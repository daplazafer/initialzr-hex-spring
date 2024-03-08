DOCKER_IMAGE_MAVEN=$(shell grep DOCKER_IMAGE_MAVEN .env | cut -d '=' -f2)
DOCKER_DIR=/ggroupid
ifneq (,$(word 2, $(MAKECMDGOALS)))
  PROFILE := $(word 2, $(MAKECMDGOALS))
  $(eval $(PROFILE):;@:)
endif

build:
	@docker run --rm -v $(shell pwd):$(DOCKER_DIR) -w $(DOCKER_DIR)/code -v $(HOME)/.m2:/root/.m2 -it $(DOCKER_IMAGE_MAVEN) \
		mvn clean install -DskipTests=true

run:
	PROFILE=$(PROFILE) docker-compose up --build aartifactid

up:
	PROFILE=$(PROFILE) docker-compose up --build -d

down:
	docker-compose down

logs:
	@docker-compose logs -f aartifactid

.PHONY: up down