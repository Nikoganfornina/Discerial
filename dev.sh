#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"

if ! command -v docker >/dev/null 2>&1; then
  echo "ERROR: Docker no está instalado. Instala Docker Desktop o Colima." >&2
  exit 1
fi
if ! docker info >/dev/null 2>&1; then
  echo "ERROR: Docker no está iniciado. Abre Docker Desktop o inicia Colima." >&2
  exit 1
fi

if [[ ! -f .env ]]; then
  if ! command -v openssl >/dev/null 2>&1; then
    echo "ERROR: OpenSSL es necesario para generar la contraseña de PostgreSQL." >&2
    exit 1
  fi
  {
    printf 'POSTGRES_USER=discerial\n'
    printf 'POSTGRES_DB=discerial\n'
    printf 'POSTGRES_PASSWORD=%s\n' "$(openssl rand -hex 24)"
    printf 'APP_PORT=3100\n'
    printf 'DB_PORT=5433\n'
  } > .env
  chmod 600 .env
  echo "Configuración local .env generada (excluida de Git)."
fi

echo "Levantando Discerial + PostgreSQL locales..."
docker compose -f compose.dev.yml up --build -d
app_port="$(sed -n 's/^APP_PORT=//p' .env | head -1)"
app_port="${app_port:-3100}"

if ! command -v curl >/dev/null 2>&1; then
  echo "Docker iniciado. Abre http://localhost:${app_port} (curl no disponible para comprobarlo)."
  exit 0
fi

echo "Esperando a que Next.js y PostgreSQL respondan..."
ready=false
for _ in $(seq 1 45); do
  if curl -fsS --max-time 2 "http://127.0.0.1:${app_port}/api/health" >/dev/null 2>&1; then
    ready=true
    break
  fi
  sleep 1
done

if [[ "$ready" != "true" ]]; then
  echo "ERROR: Los contenedores han arrancado, pero la app no responde correctamente." >&2
  docker compose -f compose.dev.yml logs --tail 25 app db >&2
  exit 1
fi

echo
echo "Discerial listo en: http://localhost:${app_port}"
echo "PostgreSQL: conectado, con datos locales persistentes."
echo "Logs: docker compose -f compose.dev.yml logs -f app"
echo "Detener: ./stop.sh"
