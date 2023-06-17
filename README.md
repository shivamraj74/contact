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
│               └── ApplicatioTests.java
└── pom.xml

- The controller package contains the ContactController class responsible for handling HTTP requests and responses.
- The dao package contains the Contact class representing the contact entity.
- The entities package contains the ContactRepository interface for interacting with the database.
- The services package contains the ContactService interface and its implementation class ContactServiceImpl for managing contact operations.
- The Application class in the root package is the entry point of the application.
- The application.yml file in the resources directory contains the database configuration.
