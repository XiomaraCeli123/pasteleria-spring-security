# Pastelería con Spring Security

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6-6DB33F?logo=springsecurity&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?logo=thymeleaf&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)

Proyecto para practicar autenticación con Spring Security. Es una página de pastelería en la que cualquiera ve la portada y el contacto, pero para entrar al resto hay que registrarse e iniciar sesión.

## Cómo se ve

**Portada sin sesión iniciada**



**Registro de usuario**



**Página principal con la sesión iniciada**



## Qué hace

- Registro con validaciones: correo válido y no repetido, contraseña de mínimo 6 caracteres y confirmación.
- Contraseñas cifradas con BCrypt.
- Inicio de sesión con el formulario de Spring Security y usuarios guardados en MySQL.
- Rutas protegidas: sin sesión, el acceso redirige al login.

## Seguridad

| Rutas | Acceso |
|---|---|
| Inicio, contacto, registro, login y archivos estáticos | Público |
| Todo lo demás | Solo usuarios autenticados |

La configuración está en `SecurityConfig.java`, y `AppUserService` carga cada usuario por su correo desde la base de datos. Quien se registra recibe el rol `Cliente`.

## Cómo ejecutarlo

Requisitos: Java 17 y MySQL encendido.

1. Clona el repositorio:

   ```bash
   git clone https://github.com/XiomaraCeli123/pasteleria-spring-security.git
   cd pasteleria-spring-security
   ```

2. Crea la base de datos:

   ```sql
   CREATE DATABASE proyecto;
   ```

3. Revisa la conexión en `src/main/resources/application.properties` (MySQL en el puerto 3306, usuario `root`, sin contraseña).

4. Ejecuta el proyecto:

   ```bash
   .\mvnw.cmd spring-boot:run      # Windows
   ./mvnw spring-boot:run          # Linux o macOS
   ```

5. Abre http://localhost:8080

Las tablas se crean solas la primera vez.

## Autora

Damaris Xiomara Quispe Celi · [GitHub](https://github.com/XiomaraCeli123) · [LinkedIn](https://linkedin.com/in/damaris-xiomara-quispe-celi-229743292)
