# 1. Usamos la imagen oficial de Java 21 (la versión que vi en tus logs)
FROM eclipse-temurin:21-jdk-alpine

# 2. Creamos una carpeta de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiamos el archivo compilado (.jar) de tu proyecto al contenedor
# (El asterisco ayuda a que no importe el nombre exacto de la versión)
COPY target/*.jar app.jar

# 4. Exponemos el puerto (esto es simbólico, el puerto real lo da el YAML)
EXPOSE 8080

# 5. El comando que ejecutará Docker al encender el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]