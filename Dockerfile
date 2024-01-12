
FROM openjdk:17-jdk-alpine


WORKDIR /app


COPY target/pactovagasinternas-0.0.1-SNAPSHOT.jar pactovagasinternas.jar


CMD ["java", "-jar", "pactovagasinternas.jar"]
