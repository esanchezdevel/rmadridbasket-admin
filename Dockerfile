FROM openjdk:8-jdk-alpine

#install system dependencies
RUN apk update && apk add vim curl

RUN mkdir rmadridbasket-admin

WORKDIR rmadridbasket-admin

#deploy rmadrid-basket-es-admin project
COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/rmadridbasket-admin/app.jar"]
