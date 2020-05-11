FROM openjdk:11-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} album-service.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","/album-service.jar"]