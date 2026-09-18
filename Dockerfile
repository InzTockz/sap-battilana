#1.
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offiline -B

COPY src ./src
RUN mvn clean package -DskipTests

#2.
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar sap_battilana.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "sap_battilana.jar"]