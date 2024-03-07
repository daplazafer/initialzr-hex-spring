ARG DOCKER_IMAGE_MAVEN=maven:3.9.5-eclipse-temurin-17-alpine
ARG DOCKER_IMAGE_JAVA=eclipse-temurin:17-jre-alpine
ARG SERVER_PORT=8080

FROM ${DOCKER_IMAGE_MAVEN} as builder
COPY code/ /code/
WORKDIR /code
RUN mvn package -DskipTests=true

FROM $DOCKER_IMAGE_JAVA
COPY --from=builder /code/boot/target/*.jar /usr/app/app.jar
WORKDIR /usr/app
EXPOSE $PORT
CMD java -jar -Dserver.port=$SERVER_PORT app.jar