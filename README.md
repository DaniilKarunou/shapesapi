# ShapesAPI

**ShapesAPI** is a REST service for managing different shape types. It allows adding new shapes, storing their parameters, and retrieving them by type.

---

## Features

* Create shapes with custom parameters (e.g., radius for circle).
* Support for multiple shape types (`CIRCLE`, `SQUARE`, `RECTANGLE`, etc.).
* Validation of shape parameters.
* Automatic tracking of creation and update timestamps.
* REST API endpoints for creating and fetching shapes.
* Can run locally with **H2** (in-memory) or **PostgreSQL** (via Docker).
* CI with **GitHub Actions** and **SonarQube** analysis.

---

## Technologies

* Java 21, Spring Boot 3
* Hibernate / JPA
* Lombok
* PostgreSQL / H2 database
* Docker & Docker Compose
* GitHub Actions CI
* SonarQube for static code analysis

---

## Running Locally

### 1. Using H2 (in-memory)

```bash
./mvnw spring-boot:run
```

* Application will start at `http://localhost:8080`.
* No external database setup is required.
* Database will reset on each restart.

---

### 2. Using Docker Compose (PostgreSQL)

With Docker Compose, you can run **ShapesAPI** and PostgreSQL together:

```bash
docker-compose up --build
```

* ShapesAPI will be available at `http://localhost:8080`.
* PostgreSQL will run with the credentials defined in `docker-compose.yml`.

To stop the services:

```bash
docker-compose down
```

---

## API Endpoints

* **POST** `/api/v1/shapes` — create a new shape
* **GET** `/api/v1/shapes/{type}` — get all shapes of a given type

### Example Requests

```bash
curl -X POST http://localhost:8080/api/v1/shapes \
  -H "Content-Type: application/json" \
  -d '{"type":"CIRCLE","parameters":[5.0]}'
```

```bash
curl http://localhost:8080/api/v1/shapes/CIRCLE
```

---

## CI & Code Quality

* **GitHub Actions**: builds the project, runs tests, and checks code quality.
* **SonarQube**: integrated for static code analysis and code quality metrics.

---

## Notes

* Entities extend `BaseEntity` with `id`, `createdAt`, `updatedAt`.
* `@SuperBuilder` is used to allow building entities including inherited fields.
* H2 database is recommended for local testing; PostgreSQL for production environments.