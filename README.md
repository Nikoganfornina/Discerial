# Discerial

**Discerial** es un juego de preguntas sobre Historia, Filosofía, Literatura y Biología, desarrollado originalmente como proyecto de fin de grado por **NG & Bernet**. Esta es su **nueva versión web**, con una interfaz inspirada en el diseño grecorromano original.

> **Estado actual:** portada responsive y previsualización de categorías. Todavía no se han implementado usuarios, preguntas jugables ni multijugador.

## Tecnologías

| Capa | Tecnología |
| --- | --- |
| Frontend | Next.js 16, React 19, TypeScript y CSS |
| Servidor | Next.js (Node.js 22) y rutas API |
| Base de datos | PostgreSQL 17 |
| Desarrollo y contenedores | Docker Compose |
| Control de versiones y CI | GitHub y GitHub Actions |

Los **dos contenedores** (`app` y `db`) funcionan independientemente en cada ordenador. **No es necesario instalar Node.js ni PostgreSQL** en el sistema anfitrión para desarrollar con Docker.

## Inicio rápido (Mac, Linux o Windows con WSL)

**Requisitos:** Git, Docker (Docker Desktop, Docker Engine o Colima) y el motor Docker en ejecución. En Windows se recomienda WSL2 con integración de Docker Desktop.

Clona el proyecto **una sola vez**:

```bash
git clone https://github.com/Nikoganfornina/Discerial.git
cd Discerial
```

Cada vez que quieras trabajar, **un único comando**:

```bash
./dev.sh
```

Abre **http://localhost:3100**.

La primera ejecución tarda más: descarga y construye las imágenes, instala las dependencias dentro del contenedor, genera un archivo `.env` local con contraseña aleatoria y prepara PostgreSQL con las cinco categorías. El comando comprueba que la aplicación y la base de datos responden antes de indicar que están listas.

Los cambios en `src/` se ven con **recarga automática**. No tienes que reconstruir Docker por cada modificación de React o CSS.

Para parar los contenedores **sin borrar tus datos**:

```bash
./stop.sh
```

### Comandos útiles

```bash
# Estado de los contenedores
docker compose -f compose.dev.yml ps

# Logs en tiempo real
docker compose -f compose.dev.yml logs -f app

# Estado de la base de datos
curl http://localhost:3100/api/health

# Volver a construir si cambian dependencias
./dev.sh
```

La aplicación escucha solo en **127.0.0.1:3100** y PostgreSQL en **127.0.0.1:5433**. Si esos puertos están ocupados, cambia `APP_PORT` y `DB_PORT` en tu `.env` local.

**No ejecutes `docker compose -f compose.dev.yml down -v`** salvo que quieras eliminar tu base de datos local. `./stop.sh` no borra volúmenes.

## Trabajar entre dos desarrolladores

Ambos clonamos **este mismo repositorio**, pero cada uno tiene su **propia base de datos local**. Los datos de prueba no se sincronizan mediante Git: lo que compartimos son el código y los scripts versionados que crean o actualizan la estructura.

Para una funcionalidad nueva:

```bash
git switch main
git pull --ff-only origin main
git switch -c feat/nombre-funcionalidad
# Realiza tus cambios...
git add .
git commit -m "feat: descripción del cambio"
git push -u origin feat/nombre-funcionalidad
```

Después, abre una **Pull Request** hacia `main` para que el otro revise y apruebe los cambios. Antes de empezar otra tarea, actualiza tu `main` con `git pull --ff-only`.

Las Pull Requests y los cambios a `main` lanzan automáticamente las comprobaciones de **compilación, TypeScript y ESLint** mediante GitHub Actions.

**Seguridad:** nunca subas `.env`, contraseñas, `node_modules` o datos privados. Estos archivos están excluidos del repositorio.

## Estructura

```text
Discerial/
├── src/app/                 # Páginas y rutas API (Next.js)
├── src/components/          # Componentes React
├── public/legacy/           # Imágenes del juego original
├── db/init/                 # Datos iniciales para BD vacía
├── .github/workflows/ci.yml # Comprobaciones automáticas
├── compose.dev.yml          # Docker para desarrollo local
├── compose.yml              # Plantilla para futuro despliegue
├── Dockerfile               # Etapas de desarrollo y producción
├── dev.sh                   # Arrancar todo en local
└── stop.sh                  # Detener sin perder datos
```

El esquema inicial se crea mediante `db/init/001_categorias.sql`. PostgreSQL ejecuta ese script **solo la primera vez** que crea una base de datos vacía; no sustituye a un sistema de migraciones. Cuando agreguemos usuarios, preguntas y partidas usaremos migraciones versionadas.

## Próximos pasos

- Modelo de datos, migraciones y panel de administración de preguntas.
- Cuentas de usuario con contraseñas protegidas, roles y sesiones separadas.
- Modo individual con puntuación, temporizador y estadísticas.
- Partidas online por salas con lógica de juego validada en el servidor.
- Despliegue al servidor privado con Docker y Cloudflare Tunnel, **más adelante**.

## Origen

Esta versión sustituye el contenido del antiguo repositorio JavaFX. Se conserva su **historial Git** para poder recuperar el proyecto original; los archivos que se muestran ahora en `main` pertenecen exclusivamente a la versión web. El desarrollo actual **se realiza solo en localhost**, sin servidores externos ni base de datos remota.

Los gráficos reutilizados proceden del proyecto original. Antes de publicar el juego, verificar las licencias de todos los recursos visuales y de audio.
