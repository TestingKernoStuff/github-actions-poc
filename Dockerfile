# Use the official Gradle image to build the app
FROM gradle:8.7-jdk11 AS build

# Copy the project files into the Docker image
COPY --chown=gradle:gradle . /home/gradle/project

# Set the working directory
WORKDIR /home/gradle/project

# Build the application
RUN gradle build --no-daemon

# Use the official OpenJDK image for the runtime
FROM openjdk:11-jre-slim

# Copy the built application into the Docker image
COPY --from=build /home/gradle/project/build/libs/*.jar /app/app.jar

# Specify the entrypoint for the Docker container
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
