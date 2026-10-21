# Backend Java de Discerial

## Qué hay implementado

- DiscerialBackendApplication.java: arranque de Spring Boot 4.1.1 y Java 21.
- health/HealthController.java: GET /api/health; verifica PostgreSQL.
- user/User.java: clase @Entity que representa la tabla users.
- user/UserStatus.java: estados válidos del usuario.
- user/UserRepository.java: interfaz JPA para acceder a users, sin SQL manual para lo básico.
- resources/application.yml: conexión a PostgreSQL, Flyway y comprobación del esquema.
- resources/db/migration/V1__create_users.sql: primer esquema que creaste en PostgreSQL, ahora con control de versiones.
- test/java/.../health/HealthControllerTest.java: pruebas unitarias de la API.

**Pendiente:** validación de registro, hash de contraseñas, Spring Security, DTOs, sesiones, correo de verificación y protección de las rutas. No está implementada la autenticación ni hay endpoints de usuarios.

## Cómo empezar a tocar Java

Empieza leyendo DiscerialBackendApplication, después HealthController, User y UserRepository. Compara las anotaciones @Entity, @Table y @Column con V1__create_users.sql.

Para un cambio mínimo de aprendizaje: añade un campo público no sensible a la respuesta del controlador de salud, ejecútalo y comprueba el JSON. Después escribe una prueba que verifique el resultado.

Para el registro real, el siguiente ejercicio será crear un DTO con jakarta.validation (correo, nickname y contraseña), un servicio Java que haga las validaciones y el hash seguro de la contraseña y un controlador que reciba el POST. Después incorporaremos Spring Security, límites de intentos, sesiones y verificación de correo. **No guardes nunca contraseñas en texto plano.**

## Ejecución y pruebas

Con Docker desde la raíz del repositorio:

    ./dev.sh
    docker compose -f compose.dev.yml logs -f backend

Fuera de Docker necesitas JDK 21 y base PostgreSQL configurada:

    ./mvnw spring-boot:run
    ./mvnw test

El puerto Java es 8080; la web Next.js se sirve en 3100. Toda la API se invoca desde la web mediante /api/*.

## Cambios de la base de datos

La versión V1 se ejecuta una sola vez por base de datos gracias a Flyway. Para evolucionar users crea V2__nombre_del_cambio.sql en la misma carpeta. No modifiques retrospectivamente V1 tras haberla aplicado.

Hibernate está configurado en ddl-auto: validate, no en create o update. Eso significa que Java comprueba la estructura, **pero no intenta reconstruir automáticamente la base de datos**.

La BD local del Mac tenía una tabla antigua categories. La configuración baseline-on-migrate / baseline-version 0 permite incorporarla a Flyway sin borrarla; el nuevo V1 crea users. Otros equipos con esquemas antiguos distintos deben revisarlos antes de actualizar.
