# JUnit Mockito Practice Project

This project is a simple Java application demonstrating the use of JUnit 5 and Mockito for unit testing. It includes a basic User model, a UserRepository implementation using an `ArrayList`, a UserService that interacts with the repository, and a UserController (using Spring-like annotations for structure, though not a runnable web app without a framework).

The primary focus of this project is the `UserServiceTest` class, which showcases various Mockito annotations (`@Mock`, `@InjectMocks`, `@Spy`) and methods (`when`, `verify`, argument matchers) for testing the `UserService` in isolation by mocking its `UserRepository` dependency.

## Project Structure

![image](https://github.com/user-attachments/assets/9d3dda38-0c7e-40a7-afc8-bc785c28f618)

* `src/main/java`: Contains the main application source code.

* `src/test/java`: Contains the test source code.

* `build.gradle`: The Gradle build script.

* `README.md`: This file.

## Prerequisites

* Java Development Kit (JDK)  17

* Gradle (or you can use the Gradle wrapper included with the project)

## Building the Project

To build the project, navigate to the project root directory in your terminal and run:

./gradlew build
This command will compile the Java code, run the tests, and package the application (though for this simple project, the main output is the compiled classes and test results).

## Running Tests

You can run the unit tests specifically using the following command:

./gradlew test
This will execute all tests located in the `src/test/java` directory. Test reports will be generated in the `build/reports/tests/test/` directory.

## Exploring the Code

* **`com.example.model.User`**: A simple POJO representing a user.

* **`com.example.repository.UserRepository`**: Interface for user data access.

* **`com.example.repository.UserRepositoryImpl`**: An in-memory implementation of `UserRepository` using `ArrayList`.

* **`com.example.service.UserService`**: Business logic for user operations, depending on `UserRepository`.
* **`com.example.controller.UserController`**: A sample controller demonstrating how a web layer might use the `UserService`.

* **`com.example.service.UserServiceTest`**: Contains unit tests for `UserService` using JUnit 5 and Mockito. This is where you can see Mockito annotations and methods in action.

Feel free to modify the code, add more tests, or expand the functionality for your practice!
