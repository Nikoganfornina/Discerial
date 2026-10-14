# Discerial — Nueva etapa web

Punto de partida para reconstruir Discerial, el juego original de **NG & Bernet**. El código de JavaFX original permanece intacto en `../Discerial-legacy`.

## Estado (primera iteración)

- [x] Portada responsive, manteniendo fondos, logotipo y categorías originales.
- [x] Vista previa de cinco disciplinas (sin partidas activas).
- [x] Endpoint `GET /api/health` que comprueba conexión real a PostgreSQL.
- [x] Dos contenedores Docker (aplicación web y base de datos PostgreSQL), almacenamiento persistente.
- [ ] Registro/login, preguntas, sesiones, estadísticas y administrador.
- [ ] Salas multijugador, WebSockets, cronómetro y validación de respuestas en servidor.

**Atención:** esta es una demo de la portada. Ni el inicio de sesión ni el juego están implementados todavía.

## Tecnologías

Next.js 16 + React 19 + TypeScript estricto. PostgreSQL 17. Una aplicación Node que sirve web y endpoints; en fases posteriores podrá servir conexiones de tiempo real sin añadir más contenedores inicialmente.

## Desarrollo local

```sh
npm ci
npm run dev
```

Abre http://localhost:3100. La portada funciona sin PostgreSQL. `/api/health` devuelve 503 si la base de datos no está configurada o disponible.

## Iniciar los dos Docker

El archivo `.env` local ya se genera con una contraseña aleatoria; no se incluye en Git.

Para configurarlo en un segundo ordenador o en el servidor:

```sh
cp .env.example .env
# Sustituye POSTGRES_PASSWORD por una clave fuerte
docker compose up -d --build
docker compose ps
curl http://127.0.0.1:3100/api/health
```

- Web: **http://127.0.0.1:3100** (mapeado al puerto 3000 del contenedor).
- PostgreSQL: solo escucha en el servidor en **127.0.0.1:5433**, nunca en una interfaz pública.
- Dentro de Docker, la app llega a la base de datos mediante el servicio `db:5432`.
- Los datos persisten en el volumen nombrado `discerial_pgdata`, independiente de actualizaciones de los contenedores.
- No ejecutar `docker compose down -v` salvo que quieras borrar la base de datos.

Cloudflare Tunnel (ya instalado en el servidor o incorporado en el futuro) apuntaría a `http://127.0.0.1:3100`. No se ha creado ningún túnel ni se ha realizado ningún despliegue en esta iteración.

## Dos desarrolladores, una base de datos

**No expongas 5432/5433 directamente a Internet.** Para conectar aplicaciones en desarrollo al PostgreSQL remoto, abre un túnel SSH **en cada ordenador**:

```sh
ssh -N -L 5433:127.0.0.1:5433 usuario@servidor
```

En el ordenador del desarrollador, crea `.env.local` con:

```dotenv
DATABASE_URL=postgresql://discerial:CONTRASENA_REMOTA@127.0.0.1:5433/discerial
```

Después ejecuta `npm run dev`. La aplicación desplegada no requiere túnel SSH para acceder a PostgreSQL porque usa la red interna de Docker.

**Recomendación:** al avanzar, usar una única base de datos de desarrollo compartida y otra de producción, evitando modificar las partidas reales durante las pruebas. Gestionaremos el esquema con migraciones, no con cambios manuales.

## Próximos hitos propuestos

1. Modelado de datos y migraciones: usuarios, preguntas, categorías y partidas.
2. Autenticación segura, perfiles y roles (administrador/jugador).
3. Modo individual completo con motor de partidas compartido.
4. Salas online y respuestas sincronizadas (WebSockets, lógica autoritativa en servidor).
5. Ranking, recompensas, estadísticas y despliegue público con Cloudflare Tunnel.

## Git

Este es un **repositorio Git local independiente** del JavaFX. No tiene repositorio remoto todavía: crear un nuevo GitHub y conectar ambos ordenadores requiere elegir un nombre y la visibilidad del repositorio.

Los recursos gráficos copiados provienen del Discerial original. Antes de publicar públicamente, verifica que tenéis derechos para redistribuir cada música, vídeo e imagen del juego antiguo (aquí solo hay imágenes de la portada y categorías).
