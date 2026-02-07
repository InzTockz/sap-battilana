FROM eclipse-temurin:21-jdk

ARG JAR_FILE=target/sap-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} sap_battilana.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "sap_battilana.jar"]