# ggroupid: aartifactid

aartifactid project

## 0. Requirements
- Docker
- docker-compose

## 1. Build and test
To build the app, which compiles the source code and generates the target directory for each module, run:
```bash
make build
```
To execute ONLY unit tests:
```bash
make test
```
To execute ONLY integration tests: 
```bash
make verify
```
This step will stop the app execution (if running), start all required Docker containers, and stop them all after running the integration tests.

To clean the build artifacts and remove `target` directories, run:
```bash
make clean
```
## 2. Run
To run the app:
```bash
make run
```
To run the app with a custom profile, such as `dev`, use:
```bash
make run dev
```
Replace `dev` with your desired profile.
Profiles allow you to run the application with different configurations, such as for development, testing, or production settings.
You can define your application profiles in `code/aartifactid-boot/src/main/resources/application.yml`.

Show logs of your current app execution:
```bash
make logs
```

To start needed infrastructure containers only:
```bash
make infra
```
This might be helpful to debug along your IDE execution.

To stop the app execution and all other required containers run:
```bash
make stop
```
