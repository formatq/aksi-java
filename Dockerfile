# build stage
FROM maven:3-jdk-8-alpine AS build-env

#copy src
WORKDIR /app

ADD . /app

COPY application.properties /src/main/resources/

RUN mvn clean install

#final stage

FROM openjdk:8-jre
WORKDIR /aksi

COPY --from=build-env  /root/.m2/repository/ru/formatq/telegram/aksi/1.0-SNAPSHOT/aksi-1.0-SNAPSHOT.jar /aksi/app.jar

CMD java -Xmx64m -Xss1024k -jar /aksi/app.jar



