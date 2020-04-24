FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/demosb-0.0.1-SNAPSHOT.jar
COPY authserver.jks .
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]