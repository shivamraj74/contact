# Use an official OpenJDK runtime as the base image
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/contact.jar .

# Expose the port on which the application will run
EXPOSE 8080

# Specify the command to run when the container starts
CMD ["java", "-jar", "contact.jar"]
