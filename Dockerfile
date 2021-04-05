#FROM alpine:3.13.2
FROM openjdk:8-jdk-alpine

#install system dependencies
RUN apk update && apk add vim curl

#deploy rmadrid-basket-es-admin project
COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
