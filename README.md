# pet-mgmt-microservices

Backend de gestion de mascotas construido con Java, Spring Boot y Maven. El
repositorio contiene microservicios independientes; cada servicio se compila
desde su propia carpeta con el Maven Wrapper incluido.

## Microservicios

| Servicio | Puerto | Base de datos | Descripcion |
| --- | ---: | --- | --- |
| `ms-eureka` | `8761` | No aplica | Registro de servicios Eureka |
| `ms-auth` | `8081` | No aplica | Login y emision de JWT |
| `ms-usuario` | `8082` | PostgreSQL | Usuarios |
| `ms-mascotas` | `8083` | PostgreSQL | Mascotas |
| `ms-veterinaria` | `8084` | PostgreSQL | Veterinarios |
| `ms-citas` | `8085` | PostgreSQL | Citas |
| `ms-historial` | `8086` | PostgreSQL | Historial medico |
| `ms-vacunas` | `8087` | PostgreSQL | Vacunas |
| `ms-notificaciones` | `8088` | PostgreSQL | Notificaciones |
| `ms-recordatorios` | `8089` | PostgreSQL | Recordatorios |
| `ms-reportes` | `8090` | PostgreSQL | Reportes |

## Requisitos

- JDK 21.
- PostgreSQL para los servicios con persistencia.
- Bash en Linux.

No se requiere Docker ni herramientas de Node.

## Configuracion local

Cada servicio define sus propiedades en `src/main/resources/application.properties`.
Las credenciales por defecto son para desarrollo local y se pueden cambiar con
variables de entorno.

Variables comunes:

| Variable | Uso |
| --- | --- |
| `SERVER_PORT` | Puerto HTTP del servicio |
| `DB_URL` | URL JDBC completa de PostgreSQL |
| `DB_USER` | Usuario de base de datos |
| `DB_PASSWORD` | Contrasena de base de datos |
| `DDL_AUTO` | Valor de `spring.jpa.hibernate.ddl-auto` |
| `JPA_SHOW_SQL` | Muestra SQL generado por Hibernate |

Bases locales esperadas por defecto:

| Servicio | Base de datos |
| --- | --- |
| `ms-usuario` | `db_usuarios` |
| `ms-mascotas` | `db_mascotas` |
| `ms-veterinaria` | `db_veterinaria` |
| `ms-citas` | `db_citas` |
| `ms-historial` | `db_historial` |
| `ms-vacunas` | `db_vacunas` |
| `ms-notificaciones` | `db_notificaciones` |
| `ms-recordatorios` | `db_recordatorios` |
| `ms-reportes` | `db_reportes` |

Variables especificas de `ms-auth`:

| Variable | Uso |
| --- | --- |
| `JWT_SECRET` | Clave para firmar tokens JWT |
| `JWT_EXPIRATION_MS` | Duracion del token en milisegundos |
| `USUARIOS_SERVICE_URL` | URL base de `ms-usuario` |

## Ejecucion

Desde la raiz del repositorio, entra al servicio que quieras ejecutar:

```bash
cd ms-eureka
./mvnw spring-boot:run
```

Para ejecutar otro servicio, vuelve a la raiz y repite el mismo patron:

```bash
cd ../ms-usuario
./mvnw spring-boot:run
```

## Pruebas

Cada modulo se prueba por separado:

```bash
cd ms-recordatorios
./mvnw test
```

Los tests usan H2 cuando el servicio requiere base de datos y deshabilitan
Eureka, por lo que no dependen de PostgreSQL ni del registry local.

## Endpoints principales

Los endpoints existentes siguen esta organizacion general:

| Servicio | Ruta base |
| --- | --- |
| `ms-auth` | `/api/v1/auth` |
| `ms-usuario` | `/api/v1/usuarios` |
| `ms-mascotas` | `/api/v1/mascotas` |
| `ms-veterinaria` | `/api/v1/veterinarios` |
| `ms-citas` | `/api/v1/citas` |
| `ms-historial` | `/api/v1/historial` |
| `ms-vacunas` | `/api/v1/vacunas` |
| `ms-notificaciones` | `/api/notificaciones` |
| `ms-recordatorios` | `/api/recordatorios` |
| `ms-reportes` | `/api/reportes` |

## Notas de desarrollo

- No subir carpetas `target/`, dependencias cacheadas ni configuracion local del IDE.
- Mantener cada microservicio con su propio `pom.xml` y wrapper Maven.
- Evitar dependencias que no se usen en el codigo del servicio.
