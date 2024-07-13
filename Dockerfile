FROM maven:3.9.6-jdk-21 AS build

COPY . .

RUN mvn install -DskipTests

FROM openjdk:21.0.1-jdk

COPY --from=build target/ashokavoice-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]