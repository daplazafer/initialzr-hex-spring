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
	fi; \
	if [ -z "$(word 2, $(NEWARGS))" ]; then \
		echo "Error: 'ArtifactId' not specified"; \
		exit 1; \
	fi; \
	if [ -z "$(word 3, $(NEWARGS))" ]; then \
		echo "Error: Repository path not specified"; \
		exit 1; \
	fi; \
	echo "Copying files to $(word 3, $(NEWARGS))..."; \
	mkdir -p "$(word 3, $(NEWARGS))"; \
	cp -r install/* "$(word 3, $(NEWARGS))" || true; \
	find install/ -type f -name ".*" -exec cp {} "$(word 3, $(NEWARGS))/" \;; \
	echo "Renaming project files..."; \
	find "$(word 3, $(NEWARGS))" -type f -exec sed -i 's/ggroupid/$(word 1, $(NEWARGS))/g' {} \; ; \
	find "$(word 3, $(NEWARGS))" -type f -exec sed -i 's/aartifactid/$(word 2, $(NEWARGS))/g' {} \; ; \
	echo "Renaming directories..."; \
	find "$(word 3, $(NEWARGS))" -depth -type d | while read -r dir; do \
		base_dir=$$(dirname "$$dir"); \
		base_name=$$(basename "$$dir"); \
		if echo "$$base_name" | grep -q "ggroupid"; then \
			new_name=$$(echo "$$base_name" | sed 's/ggroupid/$(word 1, $(NEWARGS))/g'); \
			mv "$$dir" "$$base_dir/$$new_name"; \
		fi; \
		if echo "$$base_name" | grep -q "aartifactid"; then \
			new_name=$$(echo "$$base_name" | sed 's/aartifactid/$(word 2, $(NEWARGS))/g'); \
			mv "$$dir" "$$base_dir/$$new_name"; \
		fi; \
	done
	@echo "Copying .gitignore"; \
	cat install/.gitignore > "$(word 3, $(NEWARGS))/.gitignore"; \
	echo "Done!"; \

.PHONY: install