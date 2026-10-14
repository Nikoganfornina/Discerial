# Discerial — Nueva versión web

Discerial es el juego de preguntas de NG & Bernet, reconstruido para web con **Next.js 16, React 19 y TypeScript**, conservando los recursos originales y su estética grecorromana.

## ¿Qué hay ahora?

- Portada responsive (escritorio y móvil).
- Cinco categorías visuales y avisos de «próximamente».
- PostgreSQL 17 en Docker con las cinco categorías sembradas inicialmente.
- Endpoint `GET /api/health` para comprobar que la aplicación ve la base de datos.
- **Todavía no hay login ni partidas.** El motor de juego se implementará por fases.

## Arranque rápido: un comando después de clonar

Requisitos: **Git** y **Docker** instalado e iniciado (Docker Desktop en Windows/Mac, o Docker Engine/Colima). No hace falta instalar Node ni PostgreSQL por separado.

```bash
# Cuando exista el nuevo repositorio GitHub:
git clone URL_DEL_REPOSITORIO
cd Discerial-web

# Mac, Linux o Windows usando WSL:
./dev.sh
```

Abrir **http://localhost:3100**. El primer arranque descargará las imágenes de Docker, instalará dependencias y creará una BD local.

El script crea automáticamente `.env` con una contraseña aleatoria si aún no existe. Nunca subas `.env` a Git.

**Para detener sin perder la base de datos:**

```bash
./stop.sh
```

Comandos útiles:

```bash
docker compose -f compose.dev.yml ps
docker compose -f compose.dev.yml logs -f app
docker compose -f compose.dev.yml logs -f db
curl http://localhost:3100/api/health
```

Al editar `src/`, Next.js actualiza automáticamente la web. Los dos contenedores son `app` y `db`. Los volúmenes Docker conservan la BD, las dependencias de Node y la caché de Next.js.

La BD escucha solo en **127.0.0.1:5433** de cada ordenador, y la web en **127.0.0.1:3100**. Para utilizar otros puertos, edita `APP_PORT` y `DB_PORT` en tu `.env`.

**No ejecutes** `docker compose -f compose.dev.yml down -v` a menos que quieras perder tus datos locales.

## Flujo Git de dos desarrolladores

Cada persona clona el **mismo repositorio GitHub**, crea su propia rama y trabaja en local:

```bash
git switch main
git pull --ff-only origin main
git switch -c feat/login
# editar archivos
git add .
git commit -m "feat: pantalla de login"
git push -u origin feat/login
```

Desde GitHub se abre una **Pull Request** para revisar e incorporar los cambios a `main`. Después, ambos hacen `git pull` para actualizar su copia local.

Se comparte **el código y los cambios del esquema** mediante Git, pero **no los datos reales de PostgreSQL**. Cada uno tiene su propio volumen local.

En esta iteración se usa un script SQL de arranque (`db/init/001_categorias.sql`) que PostgreSQL solo ejecuta al crear una base de datos vacía. **No es un sistema de migraciones**: cuando empecemos el modelo de usuarios, preguntas y partidas, incorporaremos migraciones versionadas (por ejemplo, Drizzle).

## Arquitectura

```
localhost:3100 --> Next.js (React + TypeScript)
                         |
                         | DATABASE_URL (red interna)
                         v
                     PostgreSQL 17
                       volumen local
```

- `compose.dev.yml`: 2 contenedores para trabajo local, con código montado y actualización automática.
- `compose.yml`: plantilla de despliegue, todavía no desplegada ni probada como producción.
- `Dockerfile`: etapas separadas para desarrollo y producción.
- `dev.sh`: inicia el entorno local y genera `.env` con credenciales únicas.
- `stop.sh`: detiene ambos contenedores sin borrar datos.
- `db/init/`: SQL de primera inicialización de una base vacía.
- `src/`: aplicación web.
- `public/legacy/`: recursos originales usados en el rediseño.

**Importante:** la base de producción futura será independiente de las bases locales de desarrollo. No exponer PostgreSQL a Internet. Cloudflare Tunnel se configurará solo cuando queramos desplegar la web al servidor.

## Estado del repositorio

Esta carpeta es un repositorio Git local en la rama `main`. **No tiene remote de GitHub configurado aún.** Habrá que crear un nuevo repositorio de GitHub (idealmente privado al inicio) y hacer el primer `git push`. No reutilizar el Git del proyecto JavaFX sin decidirlo.

Los assets originales se conservan para respetar la estética. Antes de una publicación pública, comprobar derechos sobre imágenes, vídeos y música del proyecto anterior.
