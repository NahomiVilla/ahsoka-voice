FROM maven:3.8.5-openjdk-21 AS build

COPY . .

RUN mvn install -DskipTests

FROM openjdk:17-alpine

COPY --from=build target/ashokavoice-0.0.1-SNAPSHOT.jar java-app.jar

ENTRYPOINT ["java","-jar","java-app.jar"]