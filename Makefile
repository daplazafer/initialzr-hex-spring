ifeq (install,$(firstword $(MAKECMDGOALS)))
  FIRST_ARG := $(shell echo '$(word 2, $(MAKECMDGOALS))' | tr '[:upper:]' '[:lower:]')
  SECOND_ARG := $(shell echo '$(word 3, $(MAKECMDGOALS))' | tr '[:upper:]' '[:lower:]')
  REMAINING_ARGS := $(wordlist 4, $(words $(MAKECMDGOALS)), $(MAKECMDGOALS))
  NEWARGS := $(FIRST_ARG) $(SECOND_ARG) $(REMAINING_ARGS)
  $(eval $(NEWARGS):;@:)
endif

install:
	@if [ -z "$(word 1, $(NEWARGS))" ]; then \
		echo "Error: 'GroupId' not specified"; \
		exit 1; \
	fi
	@if [ -z "$(word 2, $(NEWARGS))" ]; then \
		echo "Error: 'ArtifactId' not specified"; \
		exit 1; \
	fi
	@if [ -z "$(word 3, $(NEWARGS))" ]; then \
		echo "Error: Repository path not specified"; \
		exit 1; \
	fi
	@{ \
	echo "Copying files to $(word 3, $(NEWARGS))"; \
	mkdir -p "$(word 3, $(NEWARGS))"; \
	cp -r install/* "$(word 3, $(NEWARGS))"; \
	echo "Renaming project files to $(word 1, $(NEWARGS)) and $(word 2, $(NEWARGS))"; \
	find "$(word 3, $(NEWARGS))" -type f -not -path '*/\.*' -exec sed -i 's/ggroupid/$(word 1, $(NEWARGS))/g' {} \; ; \
	find "$(word 3, $(NEWARGS))" -type f -not -path '*/\.*' -exec sed -i 's/aartifactid/$(word 2, $(NEWARGS))/g' {} \; ; \
	echo "Renaming project references to $(word 1, $(NEWARGS)) and $(word 2, $(NEWARGS))"; \
	find "$(word 3, $(NEWARGS))" -depth -type d -name 'ggroupid' -execdir mv {} "$(word 1, $(NEWARGS))" \; ; \
	find "$(word 3, $(NEWARGS))" -depth -type d -name 'aartifactid' -execdir mv {} "$(word 2, $(NEWARGS))" \; ; \
	echo "Done!"; \
	}

.PHONY: install