ARG DOCKER_IMAGE_MAVEN
ARG DOCKER_IMAGE_JAVA
ARG SERVER_PORT
ARG PROFILE

FROM ${DOCKER_IMAGE_MAVEN} as builder
COPY code/ /code/
WORKDIR /code
RUN mvn clean package -DskipTests=true

FROM $DOCKER_IMAGE_JAVA
COPY --from=builder /code/boot/target/*.jar /usr/ggroupid/aartifactid.jar
WORKDIR /usr/ggroupid
EXPOSE $SERVER_PORT
CMD java -jar -Dspring.profiles.active=$PROFILE -Dserver.port=$SERVER_PORT aartifactid.jar