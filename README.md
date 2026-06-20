# Sistema Distribuido de Gestión de Mascotas y Clínicas Veterinarias

Este proyecto consiste en una arquitectura robusta de microservicios desarrollada con **Java 21**, **Spring Boot 3.2.5** y **Spring Cloud 2023.0.1**. Implementa patrones de diseño como API Gateway, Config Server, Eureka Service Discovery y Circuit Breaker (Resilience4j) para asegurar tolerancia a fallos. Todo el proyecto aplica estrictamente el patrón **CSR (Controller - Service - Repository)** y principios de *Clean Code*.

## 👨‍💻 Equipo de Desarrollo (Estudiantes)
- [Tu Nombre y Apellido]
- [Nombre de Compañero/a 1]
- [Nombre de Compañero/a 2]

## 🏗️ Microservicios Implementados

El ecosistema cuenta con un total de **14 microservicios** desacoplados, que garantizan la separación de responsabilidades:

| Servicio | Puerto Local | Descripción |
| :--- | :--- | :--- |
| `ms-config-server` | `8888` | Servidor centralizado de configuración nativa. |
| `ms-eureka` | `8761` | Registry y Discovery Server de Netflix Eureka. |
| `apigateway` | `8080` | Puerta de entrada única (Spring Cloud Gateway) con filtro global JWT. |
| `ms-auth` | *Dinámico* | Servicio de Autenticación, generación de JWT y encriptación BCrypt. |
| `ms-usuario` | *Dinámico* | Gestión de usuarios (dueños de mascotas). |
| `ms-mascotas` | *Dinámico* | Gestión y registro de mascotas. |
| `ms-veterinaria` | *Dinámico* | Gestión de veterinarios. |
| `ms-citas` | *Dinámico* | Agenda y programación de citas médicas. |
| `ms-historial` | *Dinámico* | Gestión de historias clínicas de las mascotas. |
| `ms-vacunas` | *Dinámico* | Registro de vacunación. |
| `ms-notificaciones` | *Dinámico* | Servicio de envío de notificaciones. |
| `ms-recordatorios` | *Dinámico* | Generación de recordatorios de citas y vacunas. |
| `ms-reportes` | *Dinámico* | Generación de reportes administrativos. |
| `ms-facturacion` | *Dinámico* | (Si aplica) Generación de cobros. |

*Nota: Todos los microservicios de dominio (Usuarios, Mascotas, etc.) se inician en puertos dinámicos (`server.port=0`) y se comunican a través del Gateway.*

## 🛣️ Rutas Principales del API Gateway

El API Gateway es la única entrada expuesta al exterior (Puerto 8080).

| Funcionalidad | Ruta en el Gateway |
| :--- | :--- |
| **Login y JWT** | `POST http://localhost:8080/api/v1/auth/login` |
| **Usuarios** | `GET/POST http://localhost:8080/api/v1/usuarios` |
| **Mascotas** | `GET/POST http://localhost:8080/api/v1/mascotas` |

## 📖 Documentación Swagger / OpenAPI

La documentación interactiva de la API está integrada nativamente en los microservicios.
Puedes acceder a ella localmente (asegúrate de que el microservicio esté corriendo en el puerto respectivo o a través del Gateway):
- **Usuarios / Auth Swagger UI:** `http://localhost:<PUERTO_DEL_SERVICIO>/swagger-ui.html`
- *Los controladores y DTOs están completamente documentados con descripciones, ejemplos de request/response y códigos HTTP.*

## 🚀 Instrucciones de Ejecución

### Entorno Local (Desarrollo)

Para levantar el ecosistema localmente, asegúrate de tener **JDK 21** y **PostgreSQL** (con la base de datos `db_usuarios`, `db_mascotas`, etc. creadas según corresponda).

1. **Levantar el Config Server:**
   ```bash
   cd ms-config-server
   ./mvnw spring-boot:run
   ```
2. **Levantar Eureka Server:**
   ```bash
   cd ../ms-eureka
   ./mvnw spring-boot:run
   ```
3. **Levantar los Microservicios de Dominio:** (Ej. `ms-usuario`, `ms-auth`, `ms-mascotas`)
   ```bash
   cd ../ms-usuario
   ./mvnw spring-boot:run
   ```
4. **Levantar el API Gateway:** (Debe ser el último)
   ```bash
   cd ../apigateway
   ./mvnw spring-boot:run
   ```

### Despliegue Remoto (Railway / Render / Docker)

Para desplegar este ecosistema en la nube (ej. Railway):
1. Levantar un servicio de base de datos PostgreSQL en la plataforma de nube y obtener la URL JDBC (`postgresql://user:pass@host:port/dbname`).
2. Configurar la variable de entorno `SPRING_CONFIG_IMPORT=optional:configserver:<URL_DEL_CONFIG_SERVER_PUBLICO>` en cada microservicio desplegado.
3. Desplegar los servicios conectando cada repositorio a Railway/Render, utilizando el `Dockerfile` proporcionado en la raíz de cada microservicio o utilizando el `Buildpack` de Maven con Java 21.

## 🧪 Pruebas Unitarias

El proyecto cuenta con una cobertura robusta probando las reglas de negocio, con manejo de excepciones y validaciones. Los tests están implementados con **JUnit 5** y **Mockito**.
Para ejecutar los tests de un microservicio:
```bash
cd ms-usuario
./mvnw test
```
