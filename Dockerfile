# TWO-STAGE BUILD DOCKER FILE

ARG BUILD_IMAGE=fxdom/maven-openjdk-21:3.9.6
ARG RUNTIME_IMAGE=fxdom/maven-openjdk-21:3.9.6

#############################################################################################
###                Stage where Docker is pulling all maven dependencies                   ###
#############################################################################################
FROM ${BUILD_IMAGE} AS dependencies

COPY pom.xml ./

RUN mvn -B dependency:go-offline
#############################################################################################


#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM dependencies AS build

ARG DOCKER_HOST=tcp://host.docker.internal:2375

COPY src ./src

RUN mvn -B clean package -Dmaven.test.skip=true
#############################################################################################


#############################################################################################
### Stage where Docker is running a java process to run a service built in previous stage ###
#############################################################################################
FROM ${RUNTIME_IMAGE}
ARG JAR_FILE=target/TestTask-0.0.1-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} app.jar
RUN echo "#!/bin/bash" >> entrypoint.sh & \
echo "sleep \$ENTRY_DELAY" >> entrypoint.sh & \
echo "java -jar app.jar" >> entrypoint.sh & \
chmod +x entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
#############################################################################################
