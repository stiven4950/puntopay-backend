# PuntoPay - Backend

## Descripción del Proyecto

PuntoPay es una API desarrollada en **Spring Boot** que se encarga de gestionar las transacciones de recargas móviles, compra de pines y operaciones bancarias consumiendo los servicios de **Puntored**.

Esta API maneja la autenticación, validaciones de transacciones y almacenamiento de datos en una base de datos MySQL.

## Tecnologías Utilizadas

- **Java 17** - Lenguaje de programación.
- **Spring Boot 3.4.3** - Framework para el desarrollo del backend.
- **Spring Data JPA** - Manejo de persistencia de datos.
- **Spring Security** - Seguridad y autenticación.
- **Spring Validation** - Validaciones en el backend.
- **Spring Cloud OpenFeign** - Cliente HTTP declarativo para la comunicación con la API de Puntored.
- **Spring Cloud Netflix Eureka Client** - Para la comunicación con un servidor de descubrimiento.
- **JWT con Auth0 (JSON Web Token)** - Manejo de autenticación.
- **MySQL** - Base de datos relacional.
- **Lombok** - Reducción de boilerplate en Java.

## Requisitos Previos

Antes de ejecutar el backend, asegúrate de tener:

- **JDK 17** o superior.
- **Maven** instalado y configurado.
- **MySQL** como base de datos.
- **Spring Boot 3.4.3**.

## Instalación y Ejecución

### 1. Clonar el repositorio
```sh
git clone https://github.com/stiven4950/puntopay-backend.git
cd puntopay-backend
```

### 2. Configurar la base de datos
Crea una base de datos en MySQL y asegúrete de configurar el archivo `application.properties` con las credenciales correctas:
```properties
spring.application.name=api-transaccional
server.port=3000
server.servlet.context-path=/puntored-transactional/api

spring.datasource.url=jdbc:mysql://localhost:3306/api-transactional?createDatabaseIfNotExist=true
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
logging.level.org.springframework.security.web.*=debug

puntored.endpoint=https://services.preprodcxr.co/puntored/api/v1
puntored.usuarioHost=usuario_host
puntored.claveHost=clave_host
puntored.comercio=el_comercio
puntored.puntoVenta=punto_de_venta
puntored.claveTransaccional=tu_clave
puntored.terminal=la_terminal

eureka.client.enabled=false
```

### 3. Construir y ejecutar la API

Compila y ejecuta el backend con:
```sh
mvn clean install
mvn spring-boot:run
```
La API estará disponible en `http://localhost:3000/`.

## Contacto

Desarrollado por **Omar Stiven Rivera Rocha**  
**Ingeniero de Software**

