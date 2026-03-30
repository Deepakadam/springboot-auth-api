<<<<<<< HEAD
# 🔐 Auth API - Spring Boot

A secure authentication system built using Spring Boot and MySQL with JWT-based authentication.

---

## 🚀 Features

- User Registration
- User Login
- JWT Token Generation
- Protected API (Authorization)
- Input Validation
- Global Exception Handling
- RESTful API Design

---

## 🛠️ Tech Stack

- JWT (jjwt)
- Maven

## Project Structure

```text
src/main/java/com/auth/authapi
|- controller     # REST endpoints
|- service        # Business logic
|- repository     # Database access layer
|- entity         # JPA entities
|- util           # JWT helper logic
```

## API Endpoints

| Method | Endpoint       | Description |
|--------|----------------|-------------|
| GET    | /users         | Get all users |
| POST   | /users         | Create a new user |
| GET    | /users/{id}    | Get user by id |
| PUT    | /users/{id}    | Update user |
| DELETE | /users/{id}    | Delete user |
| POST   | /register      | Register a new user |
| POST   | /login         | Login and get JWT token |
| GET    | /profile       | Access profile using Bearer token |

## Request and Response Samples

### Register

```http
POST /register
Content-Type: application/json

{
  "username": "deepak",
  "password": "pass123"
}
```

Possible response:

```text
User registered successfully
```

### Login

```http
POST /login
Content-Type: application/json

{
  "username": "deepak",
  "password": "pass123"
}
```

Possible response:

```text
<jwt-token-string>
```

### Profile

```http
GET /profile
Authorization: Bearer <jwt-token-string>
```

Possible response:

```text
Welcome deepak
```

## Local Setup

### 1. Configure MySQL

Update `src/main/resources/application.properties` with your DB credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/authdb
spring.datasource.username=root
spring.datasource.password=root123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 2. Run the Application

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Mac/Linux:

```bash
./mvnw spring-boot:run
```

Default server URL:

```text
http://localhost:8080
```

## cURL Quick Test

```bash
curl -X POST http://localhost:8080/register -H "Content-Type: application/json" -d '{"username":"deepak","password":"pass123"}'
curl -X POST http://localhost:8080/login -H "Content-Type: application/json" -d '{"username":"deepak","password":"pass123"}'
curl -X GET http://localhost:8080/profile -H "Authorization: Bearer <TOKEN>"
```

## Notes

- Passwords are currently stored as plain text for learning/demo purposes.
- JWT secret is hardcoded in the utility class.
- For production use, add password hashing, centralized exception handling, and token validation filters.

## Author

Deepak Adam

## Screenshots

### Login (JWT Token)

![Login (JWT Token)](Screenshots/Login%20(JWT%20Token).png)

### Protected API

![Protected API](Screenshots/Protected%20API.png)
