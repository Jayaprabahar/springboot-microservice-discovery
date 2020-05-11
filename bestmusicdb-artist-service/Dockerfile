FROM openjdk:11-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} artist-service.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/artist-service.jar"]