#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
docker compose -f compose.dev.yml down
echo "Contenedores detenidos. Los datos de PostgreSQL se conservan."
