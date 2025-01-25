[![wakatime](https://wakatime.com/badge/user/1573cfda-f106-4c1b-87c2-64cdbd982d7a/project/84adda27-6be1-47f5-b442-8e4bfa979525.svg)](https://wakatime.com/badge/user/1573cfda-f106-4c1b-87c2-64cdbd982d7a/project/84adda27-6be1-47f5-b442-8e4bfa979525)

# Movies Platform

🎥 A full-stack application for managing and browsing movies, with secure JWT authentication and role-based access control. Built with Spring Boot and Angular.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Database Setup](#database-setup)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Security](#security)
- [Logging](#logging)
- [Notes](#notes)
- [Screenshots](#screenshots)
  

## Features

- 🔐 **Secure JWT Authentication**: Protect user data with JSON Web Tokens.
- 👥 **Role-Based Access Control (Admin/User)**: Differentiate access levels for admins and users.
- 🎬 **Movie Search and Management**: Easily search, add, and manage movies.
- 📋 **Batch Operations for Movies**: Add or delete multiple movies at once.
- 📱 **Responsive UI**: Works seamlessly on all devices.
- 🔍 **Advanced Search Capabilities**: Filter movies by title, genre, and more.
- 📊 **Pagination Support**: Navigate through large datasets with ease.

## Tech Stack

### Backend
- [Java 17](https://openjdk.org/projects/jdk/17/)
- [Spring Boot 3.x](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT Authentication](https://jwt.io/)
- [MySQL Database](https://www.mysql.com/)
- [Maven](https://maven.apache.org/)
- [Lombok](https://projectlombok.org/)
- [SLF4J Logging](http://www.slf4j.org/)

### Frontend
- [Angular 16](https://angular.io/)
- [Angular Material](https://material.angular.io/)
- [TypeScript](https://www.typescriptlang.org/)
- [RxJS](https://rxjs.dev/)
- [Angular JWT](https://github.com/auth0/angular2-jwt)
- [SCSS](https://sass-lang.com/)

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- MySQL
- Node.js and npm
- Angular CLI

### Database Setup
1. Create a MySQL database named `MoviesPlatform`.
2. Update the database configuration in `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/MoviesPlatform
    username: root
    password: yourpassword
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### Backend Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/ahmedSherif-eng/movies-platform.git
   ```
2. Navigate to the project directory:
   ```bash
   cd movies-platform
   ```
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. The backend will start on `http://localhost:8080`.

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd MoviesFront
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Run the application:
   ```bash
   ng serve
   ```
4. The frontend will be available at `http://localhost:4200`.


## Security

- **JWT-based Authentication**: Tokens are generated upon successful login and validated for each request.
- **Role-Based Authorization**: Admins have access to all endpoints, while users have limited access.
- **Secure Password Handling**: Passwords are hashed using BCrypt before being stored in the database.
- **CORS Configuration**: Cross-Origin Resource Sharing is configured to allow requests from the frontend.

## Logging

Application logs are available at:
- Console output
- `logs/application.log`

## Notes

- The frontend application must be running for complete functionality.
- Configure the database connection before starting the application.
- Default admin and user credentials are configured in the database.
- The application uses the OMDB API for movie data.
- To contribute, fork the repository and submit a pull request.
- Report issues [here](https://github.com/ahmedSherif-eng/MoviesPlatform/issues).

## Screenshots

### Public

![image](https://github.com/user-attachments/assets/c5bc6e3a-fadd-453b-937c-a781604ca441)
![image](https://github.com/user-attachments/assets/c090c127-9395-4190-ae67-ec36eb23024f)

### Admin

![image](https://github.com/user-attachments/assets/c52b0fca-eecb-4d14-852e-97c87ac702d5)
![image](https://github.com/user-attachments/assets/3fe622a5-9f8c-461f-b2ea-ac97a26860dc)
![image](https://github.com/user-attachments/assets/451632df-8f0a-40f3-b376-af7c088db22f)

### User
![image](https://github.com/user-attachments/assets/e0046c87-c79a-41ad-af0e-c2ac1ab246cc)
![image](https://github.com/user-attachments/assets/57e4b6fa-23a7-4ae4-8725-67f37b575770)
