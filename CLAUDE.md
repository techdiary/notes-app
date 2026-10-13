# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Start PostgreSQL database (required before running the app)
docker-compose up -d

# Build the project
./mvnw clean package

# Run the application
./mvnw spring-boot:run

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=NotesExampleApplicationTests
```

## Architecture

Spring Boot 3.5.x REST API with PostgreSQL backend using JPA/Hibernate.

**Package structure:** `com.notes.notes_example`
- `controller/` - REST endpoints under `/api`
- `model/` - JPA entities
- `repository/` - Spring Data JPA repositories

**Database:** PostgreSQL on localhost:5432, database `testdb`. Schema auto-updated by Hibernate (`ddl-auto=update`).

## API Endpoints

All endpoints prefixed with `/api`:
- `GET /notes` - List notes with pagination (`page`, `size` params), optional `title` filter
- `GET /notes/{id}` - Get single note
- `POST /notes` - Create note
- `PUT /notes/{id}` - Update note
- `DELETE /notes/{id}` - Delete note
- `DELETE /notes` - Delete all notes
