# Repository Guidelines

## Project Structure & Module Organization

This is a Spring Boot 3.5 REST API for notes, using Java 17, Maven, JPA, PostgreSQL, and Flyway.

- `src/main/java/com/notes/notes_example/` contains application code.
- `controller/` exposes REST endpoints under `/api`.
- `dto/` holds request and response payload classes.
- `model/` contains JPA entities such as `Note`.
- `repository/` contains Spring Data repositories.
- `mapper/` converts between DTOs and entities.
- `exception/` contains API exception types and global handling.
- `src/main/resources/db/migration/` contains Flyway migrations named like `V1__Create_Notes_Table.sql`.
- `src/test/java/` contains JUnit/Spring Boot tests.

Do not edit generated build output in `target/`.

## Build, Test, and Development Commands

- `docker-compose up -d` starts the local PostgreSQL database on port `5432`.
- `./mvnw spring-boot:run` runs the API locally.
- `./mvnw test` runs the test suite.
- `./mvnw test -Dtest=NotesExampleApplicationTests` runs one test class.
- `./mvnw clean package` compiles, tests, and builds the application artifact.

Run the database before starting the app or integration-style tests that load the Spring context.

## Coding Style & Naming Conventions

Use standard Java conventions: 4-space indentation, `PascalCase` classes, `camelCase` methods and fields, and package names under `com.notes.notes_example`. Keep controllers thin; put mapping logic in `mapper/`, persistence access in `repository/`, and reusable API errors in `exception/`.

Prefer constructor injection for Spring components. Keep imports explicit when practical; avoid broad `java.util.*` imports in new code. Validate incoming request DTOs with Jakarta Validation annotations and `@Valid`.

## Testing Guidelines

Tests use JUnit 5 through `spring-boot-starter-test`. Name test classes with the `*Tests` suffix and keep them under the matching package in `src/test/java`. Add focused tests for controller behavior, repository queries, validation, and exception handling when changing those areas.

Before submitting changes, run `./mvnw test`. Use `./mvnw clean package` for broader verification before releases or larger refactors.

## Commit & Pull Request Guidelines

This checkout does not include Git history, so no existing commit convention can be inferred. Use concise imperative commits such as `Add note category filter` or `Fix note validation response`.

Pull requests should include a short summary, testing performed, linked issues when applicable, and example API requests/responses for endpoint changes. Include screenshots only when a change affects generated documentation or visible UI.

## Security & Configuration Tips

Local database credentials live in `application.properties` and `docker-compose.yml` for development only. Do not commit production secrets. Add database changes as new Flyway migrations instead of editing already-applied migration files.
