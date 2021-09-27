FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/backend-desapp-api-0.0.1-SNAPSHOT.war
ADD ${JAR_FILE} app.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war", "--server.port=${PORT:8080}"]