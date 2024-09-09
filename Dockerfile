# Use an official Maven image to build the project
FROM maven:3.6.3-openjdk-17 AS build

# Set the working directory
WORKDIR /app

#copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source files and build the project
COPY src ./src
RUN mvn clean package -DskipTests

# Use an official Openjdk image to build the project
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR files from the build stage
COPY --from=build /app/target/crick-fever-0.0.1-SNAPSHOT.jar .

# Expose port
EXPOSE 8080

# Specify the command to run the project
ENTRYPOINT ["java", "-jar", "/app/target/crick-fever-0.0.1-SNAPSHOT.jar"]