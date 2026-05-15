# 🐾 Pet Management System - Microservices Architecture

> **Academic Note:** This project was developed as an academic assignment for the **Full Stack I** course. Its primary purpose is to demonstrate the design, implementation, and deployment of a microservices-oriented architecture using modern Java ecosystem technologies.

---

## 📖 Project Overview

This project is a backend system for Pet Management built upon a microservices architecture. The separation of concerns ensures that each component is scalable, maintainable, and independently deployable, communicating seamlessly to provide a comprehensive solution.

The system currently consists of three core microservices:

- **ms-notificaciones (Notifications):** Responsible for creating, persisting, and querying user-directed notifications.
- **ms-recordatorios (Reminders):** Manages the scheduling of future events and reminders (e.g., veterinary appointments, vaccination schedules).
- **ms-reportes (Reports):** Handles the generation and storage of domain-specific reports (e.g., lost pet alerts, adoption records).

---

## 🛠️ Tech Stack & Tooling

The project is built leveraging a robust, enterprise-grade technology stack:

- **Language:** Java 21
- **Core Framework:** Spring Boot (v4.0.5)
- **Persistence Layer:** Spring Data JPA, Hibernate
- **Database:** PostgreSQL
- **Additional Libraries:** Lombok, Spring Boot Actuator, Jakarta Validation
- **Build Tool & Dependency Management:** Maven
- **Local development:** PostgreSQL directo con DBeaver

---

## ⚙️ Prerequisites

To build and run this project in your local environment, ensure you have the following installed:

- [Java Development Kit (JDK) 21](https://jdk.java.net/21/)
- [Apache Maven](https://maven.apache.org/) or use the provided Maven Wrapper
- A running PostgreSQL server on `localhost:5432`
- [DBeaver](https://dbeaver.io/) or any SQL client to inspect las bases de datos

---

## 🚀 Setup and Execution

### 1. Database Configuration

Each microservice uses its own PostgreSQL database. Create the following databases on your local server before bootstrapping the applications:

- `notificacionesdb`
- `recordatoriosdb`
- `reportesdb`

The default credentials configured in the `application.properties` files are:

- **Username:** `postgres`
- **Password:** `admin123`

The services support environment variables for connection configuration:

- `DB_HOST` (default: `localhost`)
- `DB_PORT` (default: `5432`)
- `DB_USERNAME` (default: `postgres`)
- `DB_PASSWORD` (default: `admin123`)

*Note: For local development, `spring.jpa.hibernate.ddl-auto=update` is enabled; en un entorno de producción, es mejor usar migraciones explícitas y no depender de auto-actualizaciones de esquema.*

---

### 2. Running the Microservices

Navigate to each microservice's root directory and spin up the application using the Maven wrapper.

#### ▶️ ms-notificaciones
```bash
cd ms-notificaciones
./mvnw spring-boot:run
```

#### ▶️ ms-recordatorios
```bash
cd ms-recordatorios
./mvnw spring-boot:run
```

#### ▶️ ms-reportes
```bash
cd ms-reportes
./mvnw spring-boot:run
```

---

## 📡 Core API Endpoints

Each microservice exposes a RESTful API. Below are the primary endpoints for each domain:

### ms-notificaciones (`/api/notificaciones`)

- `POST /` : Creates a new notification. Payload requires `destinatario`, `mensaje`, and `tipo`.
- `GET /` : Retrieves all notifications (supports filtering by `destinatario` via query parameter).

---

### ms-recordatorios (`/api/recordatorios`)

- `POST /` : Schedules a new reminder. Payload requires `destinatario`, `mensaje`, and `fechaRecordatorio`.
- `GET /` : Retrieves all reminders (supports filtering by `destinatario` via query parameter).

---

### ms-reportes (`/api/reportes`)

- `POST /` : Generates a new report. Payload requires `tipoReporte`, `solicitante`, and `contenido`.
- `GET /` : Retrieves all reports (supports filtering by `tipoReporte` via query parameter).

---

## 🤝 Contribution & Context

This repository is strictly for educational purposes as part of a university assessment. If you are a team member contributing to this project, please adhere to the established coding conventions discussed in class and ensure you create descriptive feature branches before submitting a Pull Request.
