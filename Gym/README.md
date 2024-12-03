# Gym Management System

## Overview

This project is a Java-based Gym Management System designed to manage gym activities efficiently. The project was developed as part of an Object-Oriented Programming (OOP) course in university, applying advanced OOP principles, such as abstraction, encapsulation, inheritance, and polymorphism.

The system allows management of gym clients, sessions, instructors, and other essential operations, showcasing a modular and extensible design.

---

## Features

- **Client Management**:
  - Manage client data using the `Person` and `Client` classes.
- **Session Management**:
  - Handle gym sessions through the `Sessions` and `SessionType` classes.
- **Gym Administration**:
  - Includes classes for gym management (`Gym`, `gymSecretary`, and `Instructor`).
- **Gender Handling**:
  - Utilizes the `Gender` enumeration to handle gender-related logic.
- **Error Handling**:
  - Includes custom exception handling for specific scenarios.
- **Forum System**:
  - Manage forum types with the `ForumType` class.

---

## Project Structure

- **Main Class**:
  - `Main.java`: Entry point for the program.
- **Packages**:
  - `gym.customers`:
    - `Person.java`: Base class for individuals.
    - `Client.java`: Represents gym clients.
  - `gym.management`:
    - `Gym.java`: Core gym class.
    - `gymSecretary.java`: Handles gym administrative tasks.
    - `Instructor.java`: Represents gym instructors.
    - `Sessions.java`: Manages gym sessions.
    - `SessionType.java`: Enumerates session types.
  - `gym.Exception`:
    - Handles custom exceptions.
  - `gym.Gender`:
    - Enumerates genders for individuals.
- **Other Files**:
  - `.gitignore`: Specifies ignored files for Git version control.
  - `.idea`: IntelliJ IDEA project configuration files.

---

## Requirements

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Development Environment**: IntelliJ IDEA or any preferred Java IDE.
- **Dependencies**: None (uses built-in Java libraries).

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/<username>/Gym.git
   cd Gym
   ```

2. Open the project in your Java IDE.

3. Run the `Main.java` file to start the application.

---

## Acknowledgments

This project was developed as part of an OOP course at Ariel University. Special thanks to the course instructors and peers for their support.
