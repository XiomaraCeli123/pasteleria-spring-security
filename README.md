# Pastelería con Spring Security

Hice este proyecto para practicar Spring Security. 
La idea es sencilla: una página web de una pastelería donde cualquiera puede ver el inicio y la sección de contacto, pero para ver los pasteles hay que crear una cuenta e iniciar sesión.

La pastelería es casi una excusa. Lo que quería era probar la seguridad de verdad, con usuarios guardados en una base de datos y no con un usuario fijo de prueba.

## Qué hace

- Permite registrarse con nombre, apellido, correo, teléfono, dirección y contraseña.
- Al registrarse valida que el correo sea correcto y no esté repetido, que la contraseña tenga al menos 6 caracteres y que coincida con su confirmación.
- Guarda las contraseñas cifradas con BCrypt, nunca en texto plano.
- Inicia sesión con el formulario de login que trae Spring Security.
- Si alguien intenta entrar a la página de pasteles sin haber iniciado sesión, lo manda al login.

## Cómo quedó la seguridad

Todo está configurado en `SecurityConfig.java`. Son públicas la página de inicio, el contacto, el registro, el login, el cierre de sesión y los archivos estáticos (CSS, JS e imágenes). Cualquier otra ruta exige estar autenticado.

Para saber quién es quién, `AppUserService` busca al usuario por su correo en MySQL y le entrega sus datos a Spring Security. Cada persona que se registra recibe el rol **Cliente**. Al iniciar o cerrar sesión, la aplicación vuelve a la página principal.

## Con qué la hice

Java 17, Spring Boot 3.3, Spring Security, Spring Data JPA, Thymeleaf, MySQL y Maven.

## Cómo probarla en tu computadora

Necesitas Java 17, Maven (o usar el `mvnw` que ya viene en el proyecto) y MySQL encendido.

1. Descarga el proyecto:

   ```bash
   git clone https://github.com/XiomaraCeli123/pasteleria-spring-security.git
   cd pasteleria-spring-security
   ```

2. Crea la base de datos vacía en MySQL:

   ```sql
   CREATE DATABASE proyecto;
   ```

3. Mira `src/main/resources/application.properties`. Está configurado para MySQL en el puerto 3306, usuario `root` y sin contraseña. Si el tuyo es distinto, cámbialo ahí.

4. Ejecuta la aplicación:

   ```bash
   .\mvnw.cmd spring-boot:run     # Windows
   ./mvnw spring-boot:run         # Linux o macOS
   ```

5. Abre http://localhost:8080

Las tablas se crean solas la primera vez. Para ver la seguridad en acción, intenta abrir la página de pasteles sin cuenta, después regístrate, inicia sesión y vuelve a intentarlo.

## Lo que aprendí

- Cómo Spring Security decide qué rutas son públicas y cuáles no con `permitAll()` y `authenticated()`.
- Por qué las contraseñas se guardan cifradas y cómo se hace con BCrypt.
- Cómo conectar el login con una base de datos propia usando `UserDetailsService`.
- Cómo validar un formulario de registro con Bean Validation.

## Autora

Damaris Xiomara Quispe Celi · [GitHub](https://github.com/XiomaraCeli123) · [LinkedIn](https://linkedin.com/in/damaris-xiomara-quispe-celi-229743292)
