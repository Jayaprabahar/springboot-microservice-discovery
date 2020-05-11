FROM openjdk:11-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} discovery-service.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","/discovery-service.jar"]