# Contact Identification Service

The Contact Identification Service is a web service built with Spring Boot that allows identifying and managing contacts based on their email and phone number. It provides an endpoint `/identify` to receive HTTP POST requests with a JSON payload and returns a response with the consolidated contact information.

## Prerequisites

Before running the application, make sure you have the following:

- Java Development Kit (JDK) 8 or higher installed
- MySQL database installed and running
- Maven or Gradle build tool installed (depending on your preference)

## Getting Started

### Configure the Database

- Open the `src/main/resources/application.yml` file.
- Replace the `your_database_name`, `your_username`, and `your_password` placeholders with your actual database details.

### Build the Project

Run the following command in the project root directory:
mvn clean install

### Run the Application

If you're using an IDE, you can run the `Application` class directly from your IDE.

Run the following command in the project root directory:
mvn spring-boot:run


The Contact Identification Service will start and listen on port 8080.

## Usage

To identify a contact, send an HTTP POST request to the `/identify` endpoint with the following JSON payload:

```json
{
  "email": "contact@example.com",
  "phoneNumber": "1234567890"
}
```


```json
{
  "contact": {
    "primaryContactId": 1,
    "emails": ["contact@example.com"],
    "phoneNumbers": ["1234567890"],
    "secondaryContactIds": [2, 3]
  }
}
```

- The primaryContactId field represents the ID of the primary contact.
- The emails field contains an array of email addresses associated with the contact.
- The phoneNumbers field contains an array of phone numbers associated with the contact.
- The secondaryContactIds field contains an array of contact IDs that are secondary to the primary contact.
 
## Project Structure

```
├── src
│   ├── main
│   │   ├── java/com/example/contact
│   │   │   ├── controller
│   │   │   │   └── ContactController.java
│   │   │   ├── dao
│   │   │   │   └── Contact.java
│   │   │   ├── entities
│   │   │   │   └── ContactRepository.java
│   │   │   ├── services
│   │   │   │   ├── ContactService.java
│   │   │   │   └── ContactServiceImpl.java
│   │   │   └── Application.java
│   │   └── resources
│   │       └── application.yml
│   └── test
│       └── java/com/example/contact
│           └── service
│               └── ApplicationTests.java
└── pom.xml
```

- The controller package contains the ContactController class responsible for handling HTTP requests and responses.
- The dao package contains the Contact class representing the contact entity.
- The entities package contains the ContactRepository interface for interacting with the database.
- The services package contains the ContactService interface and its implementation class ContactServiceImpl for managing contact operations.
- The Application class in the root package is the entry point of the application.
- The application.yml file in the resources directory contains the database configuration.

## Service Implementation

The service implementation is divided into two parts: the service interface and its implementation class.

### ContactService Interface

The ContactService interface defines the contract for managing contact operations. It declares methods for identifying and retrieving contact information.

### ContactServiceImpl Class

The ContactServiceImpl class is the implementation of the ContactService interface. It provides the logic for identifying and managing contacts.
Identification Logic

The identification logic is implemented in the identifyContact method. Here's a step-by-step explanation of the identification process:

- The method receives a ContactRequest object containing the email and phoneNumber fields from the incoming request.
- First, it checks if a contact with the given email or phoneNumber already exists in the database. It uses the ContactRepository to perform the database query.
- If a contact is found, it is considered the primary contact. The primary contact's ID, email, and phoneNumber are added to the response.
- Next, the method checks if any other contacts with the same email or phoneNumber exist in the database.
- If secondary contacts are found, their IDs are added to the response's secondaryContactIds array.
- Finally, the response is returned with the consolidated contact information.

## Database Operations

- The service uses the ContactRepository interface for interacting with the database. The repository interface extends the JpaRepository interface, which provides convenient methods for performing CRUD operations.
- The service implementation uses the repository to query and manipulate contact data in the database.

## To dockerize the Contact Identification Service and provide steps to run it, you can follow the instructions below:
## Dockerizing the Contact Identification Service

- Install Docker on your machine if you haven't already. Refer to the Docker documentation for installation instructions specific to your operating system.
- Open a terminal or command prompt and navigate to the root directory of the Contact Identification Service project.
- Create a Dockerfile in the project root directory with the following content:

```Dockerfile
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
```
- Save the Dockerfile.
- Build a Docker image using the Dockerfile. Run the following command in the terminal or command prompt:
```
docker build -t contact .
```
This command will build an image named contact using the Dockerfile.

Once the image is built, you can run a container based on the image using the following command:

```
docker run -p 8080:8080 contact
```

- This command starts a container based on the contact-identification-service image and maps port 8080 of the container to port 8080 of the host machine. Adjust the port mapping as per your requirements.
- The Contact Identification Service should now be running inside a Docker container. You can access it by opening a web browser and navigating to http://localhost:8080.





