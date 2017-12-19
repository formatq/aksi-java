# build stage
FROM maven:3-jdk-8-alpine AS build-env

#copy src
WORKDIR /app

ADD . /app

RUN mvn clean install

#final stage

FROM openjdk:8-jre
WORKDIR /aksi

COPY --from=build-env  /root/.m2/repository/ru/formatq/telegram/aksi/1.0-SNAPSHOT/aksi-1.0-SNAPSHOT.jar /aksi/app.jar

CMD ["java", "-jar", "/aksi/app.jar", "/aksi/application.properties"]



