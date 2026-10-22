#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"

if ! command -v docker >/dev/null 2>&1 || ! docker info >/dev/null 2>&1; then
  echo "ERROR: instala e inicia Docker Desktop antes de continuar." >&2
  exit 1
fi

if [[ ! -f .env ]]; then
  if ! command -v openssl >/dev/null 2>&1; then
    echo "ERROR: necesitas openssl para crear la clave de PostgreSQL." >&2
    exit 1
  fi
  {
    printf 'POSTGRES_USER=discerial\n'
    printf 'POSTGRES_DB=discerial\n'
    printf 'POSTGRES_PASSWORD=%s\n' "$(openssl rand -hex 24)"
    printf 'APP_PORT=3100\n'
    printf 'BACKEND_PORT=8080\n'
    printf 'DB_PORT=5433\n'
  } > .env
  chmod 600 .env
  echo "Generado .env local (no se sube a Git)."
fi

echo "Iniciando Next.js + Java 21 + PostgreSQL (sin borrar volumenes)..."
docker compose -f compose.dev.yml up --build -d
app_port="$(sed -n 's/^APP_PORT=//p' .env | head -1)"
backend_port="$(sed -n 's/^BACKEND_PORT=//p' .env | head -1)"
app_port="${app_port:-3100}"
backend_port="${backend_port:-8080}"

if command -v curl >/dev/null 2>&1; then
  ready=false
  for _ in $(seq 1 90); do
    if curl -fsS --max-time 2 "http://127.0.0.1:${backend_port}/api/health" >/dev/null 2>&1 &&
       curl -fsS --max-time 2 "http://127.0.0.1:${app_port}/api/health" >/dev/null 2>&1; then
      ready=true
      break
    fi
    sleep 2
  done
  if [[ "$ready" != "true" ]]; then
    echo "El entorno todavia no esta listo. Ultimos logs:" >&2
    docker compose -f compose.dev.yml logs --tail 35 app backend db >&2
    exit 1
  fi
fi

echo "Web: http://localhost:${app_port}"
echo "API Java: http://localhost:${backend_port}/api/health"
echo "Base de datos local preservada. Detener: ./stop.sh"
