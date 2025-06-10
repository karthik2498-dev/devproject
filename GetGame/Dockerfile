# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy the pom.xml and download dependencies (leverages Docker cache)
COPY GetGame/pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY GetGame/src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# Stage 2: Run the application using a minimal JDK image
FROM openjdk:17-jdk-slim

# Copy the built JAR from stage 1
COPY --from=build /app/target/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 1155