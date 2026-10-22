# Discerial

Discerial es un juego web de preguntas basado en un proyecto JavaFX original de NG & Bernet. Se está reconstruyendo para la web: por ahora tenemos una portada, pantallas de acceso de demostración y la base técnica del nuevo backend Java.

## Tecnologías

| Parte                    | Tecnología                                    | Ubicación                                |
| ------------------------ | --------------------------------------------- | ---------------------------------------- |
| Interfaz                 | Next.js 16, React 19, TypeScript, CSS Modules | src/                                     |
| Backend                  | Java 21, Spring Boot 4.1, Spring Web MVC      | backend/src/main/java/                   |
| Persistencia             | Spring Data JPA, Hibernate, PostgreSQL 17     | backend/.../user/, Docker                |
| Esquema de base de datos | Flyway                                        | backend/src/main/resources/db/migration/ |
| Contenedores             | Docker Compose                                | compose.dev.yml                          |

**Aún no existe registro/login real ni partidas.** El formulario de registro actual es una maqueta; el backend Java no expone contraseñas ni permite dar de alta usuarios todavía. La autenticación se implementará con Spring Security en próximos pasos.

## Inicio rápido con Docker

Requisitos: Docker Desktop o Docker Engine iniciado, Git y un terminal Bash (macOS, Linux, Git Bash o WSL). No hace falta instalar Java ni Node.js en el sistema operativo.

    git clone https://github.com/Nikoganfornina/Discerial.git
    cd Discerial
    ./dev.sh

Se crean o reutilizan tres servicios: frontend, backend y base de datos.

- Web: http://localhost:3100
- API Java: http://localhost:8080/api/health
- API desde la web: http://localhost:3100/api/health
- PostgreSQL local: 127.0.0.1:5433

La primera ejecución puede tardar varios minutos porque Maven descarga dependencias Java. El script genera un archivo .env local con credenciales aleatorias si falta; ese archivo no se debe subir a Git.

Para detener sin borrar tus datos:

    ./stop.sh

No ejecutes docker compose down -v sin querer eliminar la base de datos. La información de PostgreSQL permanece en un volumen Docker.

## Trabajar sin Docker en los servidores de desarrollo

Para modificar solo la interfaz (Node.js 22):

    npm ci
    npm run dev

El frontend funciona en http://localhost:3100. Las rutas /api/\* se redirigen a http://localhost:8080 de forma predeterminada; si Java no está arrancado, esas peticiones fallarán, aunque las pantallas se pueden diseñar.

Para ejecutar **Java fuera de Docker** necesitas JDK 21 y PostgreSQL disponible:

    cd backend
    ./mvnw spring-boot:run

Configura SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME y SPRING_DATASOURCE_PASSWORD para conectar con tu BD local. En Windows usa mvnw.cmd en lugar de ./mvnw.

La opción más sencilla para trabajar en Java sin instalar JDK 21 es usar ./dev.sh, y modificar las clases de backend/ con IntelliJ. El contenedor de desarrollo recompila al arrancar: para cambios en Java reinicia el servicio backend con docker compose -f compose.dev.yml restart backend.

## Cómo se comunica la aplicación

1. El navegador solicita /api/health en el mismo origen que Next.js.
2. Next.js reenvía /api/\* al servicio interno backend:8080.
3. El controlador Spring Boot ejecuta SELECT 1 en PostgreSQL y devuelve JSON.
4. Toda la lógica sensible futura (registro, puntuación, partidas, permisos) residirá en Java.

Next.js no debe gestionar contraseñas, permisos de jugadores ni accesos directos a PostgreSQL. Se ha retirado Better Auth porque la autenticación futura será Java/Spring Security.

## Organización

- src/app: rutas Next.js; src/features: pantallas por funcionalidad; src/styles: CSS centralizado y modular.
- backend/src/main/java/es/discerial/backend/health: primer controlador REST.
- backend/src/main/java/es/discerial/backend/user: entidad User, estado y repositorio JPA.
- backend/src/main/resources/db/migration: migraciones Flyway versionadas (V1 conserva el esquema users que ya diseñaste).
- backend/src/test: pruebas del backend.
- docs/ESTRUCTURA.md: normas de arquitectura y organización.
- backend/README.md: guía práctica para continuar programando Java.

Flyway puede incorporar bases locales antiguas con tablas preexistentes sin eliminarlas, mediante baseline versión 0. **No cambies la migración V1 después de compartirla:** los cambios posteriores requieren V2, V3, etc. Si ya tienes un esquema de usuarios diferente, revísalo antes de aplicar migraciones.

## Comprobaciones

Frontend:

    npm ci
    npm run format:check
    npm run typecheck
    npm run lint
    npm run build

Backend (Java 21):

    cd backend
    ./mvnw test
    ./mvnw package

Antes de fusionar, comprobar las cuatro rutas /, /login, /register y /main, y que /api/health responde desde Java.

## Trabajo en equipo y Git

Cada desarrollador tiene su propia base de datos local. Versionamos las migraciones, no los datos ni las contraseñas.

    git switch main
    git pull --ff-only origin main
    git switch -c feat/nombre-cambio
    # Programa y prueba
    git add .
    git commit -m "feat: describe el cambio en español"
    git push -u origin feat/nombre-cambio

Abre una Pull Request hacia main. Nunca subas .env ni credenciales.

El backend inicial y la plantilla de producción no constituyen un despliegue público listo para usuarios: faltan autenticación, autorización, validaciones y endurecimiento de seguridad. Los recursos visuales heredados requieren comprobar sus licencias antes de publicar el juego.
