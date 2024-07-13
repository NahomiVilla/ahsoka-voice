FROM maven:3.9-eclipse-temurin-21-alpine AS build

COPY . .

RUN mvn install -DskipTests

FROM openjdk:21-jdk

COPY --from=build target/ashokavoice-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]