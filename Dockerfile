# Use lightweight JDK base image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Only copy the built JAR file
COPY target/*.jar app.jar

EXPOSE 9898

ENTRYPOINT ["java", "-jar", "app.jar"]
