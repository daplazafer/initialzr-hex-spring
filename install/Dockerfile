ARG DOCKER_IMAGE_MAVEN
ARG DOCKER_IMAGE_JAVA
ARG PROFILE

FROM ${DOCKER_IMAGE_MAVEN} as builder
COPY code/ /code/
WORKDIR /code
RUN mvn clean package -DskipTests=true

FROM $DOCKER_IMAGE_JAVA
COPY --from=builder /code/aartifactid-boot/target/*.jar /usr/aartifactid/aartifactid.jar
WORKDIR /usr/aartifactid
CMD java -jar -Dspring.profiles.active=$PROFILE aartifactid.jar