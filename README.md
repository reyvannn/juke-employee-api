# Juke – Employee API

Employee Management REST API (Spring Boot, Java 21).  
Implements CRUD for employees, validation, exception handling, and Docker/Compose for easy local runs.

## Requirements
- Docker and Docker Compose

## Quick Start (Docker Compose)
This starts the API and a Postgres 17.6 database together.

```
docker compose up --build
```

### API Documentation (Swagger)
After starting the app, the API documentation is available at:

http://localhost:8080/swagger-ui.html

## API Endpoints
```
GET    /api/employees           -> list all employees
GET    /api/employees/{id}      -> get one employee
POST   /api/employees           -> create employee
PUT    /api/employees/{id}      -> update employee
DELETE /api/employees/{id}      -> delete employee
```

### Request Body Schema (POST & PUT)

The following JSON body structure is required for creating and updating an employee:

```json
{
  "name": "Jane Doe",               // @NotBlank
  "email": "jane@example.com",      // @NotBlank, @Email (must be unique)
  "position": "Software Engineer",  // @NotBlank
  "salary": 75000.00                // @Positive
}
```

## Run without Docker

1.
```
./mvnw spring-boot:run
```
2.
Set datasource in `application.properties` (or environment variables) to point at your local Postgres.
