# Formula1API

Formula1API is a RESTful API built with Spring Boot that manages Formula 1 data, including drivers, teams, and races. It uses MySQL for persistent storage and supports database migrations with **Flyway**. The project is containerized with Docker and provides endpoints for CRUD operations on all main entities.

## Features

- Manage Formula 1 drivers, teams, and races
- RESTful API endpoints for CRUD operations
- Data validation and error handling
- MySQL as the main database (H2 for testing)
- Database migrations with Flyway
- Spring Security (basic setup)
- API documentation with Swagger (OpenAPI)
- Docker and Docker Compose support for easy setup

## Endpoints

- `/api/f1/drivers` - Manage drivers
- `/api/f1/teams` - Manage teams
- `/api/f1/races` - Manage races

## Technologies Used

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security
- Flyway
- MySQL (main), H2 (test)
- Lombok
- Docker & Docker Compose
- Swagger/OpenAPI

## Getting Started

### Prerequisites

- Java 21+
- Docker & Docker Compose (for containerized setup)
- Gradle (wrapper included)

### Running Locally (without Docker)

1. **Configure Database**

   Update `src/main/resources/application.properties` if you want to use a local MySQL instance, or use the default Docker Compose setup.

2. **Build and Run**

   ```bash
   ./gradlew build
   ./gradlew bootRun
   ```

   The API will be available at `http://localhost:8080`.

   > Note:
   >
   > If you open the API URL in your browser, it will ask you to log in with the default credentials:
   >
   > - **Username**: `admin`
   > - **Password**: `adminpass`

### Running with Docker Compose

1. **Start Services**

   ```bash
   docker compose up --build
   ```

   - MySQL will be available on port 3306.
   - The API will be available at `http://localhost:8000`.

2. **API Documentation**

   Visit `http://localhost:8000/swagger-ui.html` for interactive API docs.

   > Note:
   >
   > If you open the API URL in your browser, it will ask you to log in with the default credentials:
   >
   > - **Username**: `admin`
   > - **Password**: `adminpass`

### Running Tests

```bash
./gradlew test
```

## Project Structure

- `src/main/java/com/example/formula1api/` - Main source code
- `src/main/resources/db/migration/` - Flyway migration scripts
- `src/test/` - Test sources and data loader

## Database Migrations

Flyway is used for managing schema changes. Migration scripts are in `src/main/resources/db/migration/`.

## Example Entities

- **Team**: name, fullName, drivers
- **Driver**: name, team, races
- **Race**: name, date, participants
