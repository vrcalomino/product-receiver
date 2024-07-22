# Usar una imagen base con Maven y JDK
FROM maven:3.9.6-amazoncorretto-17 AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .

# Descargar las dependencias
RUN mvn dependency:go-offline

# Copiar el resto de los archivos de la aplicación y construir el proyecto
COPY . .

# Ejecutar el comando de construcción de Maven
RUN mvn clean package -DskipTests

# Usar una imagen base más ligera para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

COPY .env .env

# Copiar el archivo JAR desde la imagen de construcción
COPY --from=build /app/target/product-0.0.1-SNAPSHOT.jar app_product_receiver.jar

# Exponer el puerto
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app_product_receiver.jar"]
