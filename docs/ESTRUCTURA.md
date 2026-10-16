# Discerial — Guía de estructura y normas de desarrollo

> **Objetivo:** localizar cada archivo rápidamente y mantener la base ordenada mientras dos personas desarrollan el juego. Las carpetas preparadas para el futuro no implementan ninguna funcionalidad.

## Regla fundamental

**Una responsabilidad, una ubicación.** Las rutas de Next.js están en `src/app/`; el código de cada funcionalidad está en `src/features/`; los estilos están en `src/styles/`; el código verdaderamente compartido está en `src/components/`, `src/lib/` y otras carpetas transversales.

No es necesario llenar todas las carpetas desde el primer día. Los archivos `.gitkeep` solo existen para que Git conserve las carpetas vacías; elimínalos al incorporar el primer archivo real.

## Árbol de carpetas

```text
Discerial/
├── src/
│   ├── app/                      Rutas Next.js: page.tsx, layout.tsx y route.ts
│   │   ├── api/health/route.ts    Endpoint de salud de servidor y BD
│   │   ├── login/page.tsx         URL /login
│   │   ├── register/page.tsx      URL /register
│   │   ├── main/page.tsx          URL /main
│   │   ├── page.tsx               URL / (portada)
│   │   ├── layout.tsx             Layout raíz, importa estilos globales
│   │   └── icon.png               Favicon de la web
│   ├── features/                 Código por funcionalidades
│   │   ├── landing/components/   Portada, categorías promocionales y modal
│   │   ├── landing/data/         Datos de presentación de la portada, si crecen
│   │   ├── auth/components/      Formularios de login y registro
│   │   ├── auth/lib/             Futuras validaciones o lógica de autenticación
│   │   ├── dashboard/components/ Pantalla principal del jugador
│   │   ├── dashboard/data/       Datos locales de presentación del dashboard
│   │   ├── categories/           Futuras categorías del juego
│   │   ├── questions/            Futuras preguntas y respuestas
│   │   ├── game/                 Futuro motor y vistas de partidas
│   │   ├── rewards/              Futura progresión, niveles y recompensas
│   │   ├── multiplayer/          Futuras salas y partidas entre usuarios
│   │   └── admin/                Futuro panel de administración
│   ├── styles/                   Todos los CSS del proyecto
│   │   ├── tokens.css            Variables compartidas de color y tipografía
│   │   ├── globals.css           Reset y reglas comunes de accesibilidad
│   │   ├── landing.module.css    Estilos exclusivos de la portada
│   │   ├── auth.module.css       Estilos de login y registro
│   │   └── dashboard.module.css  Estilos del panel principal
│   ├── components/
│   │   ├── ui/                   Componentes reutilizables (Button, Modal...)
│   │   └── layout/               Header/Footer reutilizables entre pantallas
│   ├── lib/
│   │   ├── db/                   Cliente de BD y consultas compartidas del servidor
│   │   ├── validators/           Validadores transversales
│   │   └── utils/                Utilidades genéricas sin lógica específica
│   ├── hooks/                    Hooks reutilizables de React, si hacen falta
│   ├── types/                    Tipos e interfaces compartidos
│   └── constants/                Constantes transversales (no secretos)
├── db/
│   ├── init/                     Script inicial para crear una BD nueva
│   └── migrations/               Reservado para futuras migraciones versionadas
├── public/legacy/                Recursos gráficos originales (URLs /legacy/...)
├── docs/                         Documentación técnica, decisiones y planificación
├── .github/workflows/            Automatizaciones CI
├── compose.dev.yml               Docker de desarrollo
├── compose.yml                   Plantilla de despliegue aún no probada
├── Dockerfile                     Imagen de la aplicación
├── dev.sh y stop.sh               Arranque/parada de Docker en Mac, Linux y Bash
└── package.json                  Dependencias y comandos npm
```

## ¿Qué debe ir en cada carpeta?

### `src/app/`: solo las rutas

Next.js necesita archivos especiales llamados `page.tsx` (páginas), `layout.tsx` (envoltorios) y `route.ts` (endpoints HTTP). **No hay que renombrarlos ni moverlos fuera de `app`.** Una página aquí importa el componente que se implementa en `features`:

```tsx
import { LoginScreen } from "@/features/auth/components/login-screen";

export default function LoginPage() {
  return <LoginScreen />;
}
```

Los endpoints en `app/api/` recibirán las peticiones, validarán la entrada y delegarán en lógica de servidor; no deben contener CSS ni JSX. Ningún código de navegador debe acceder directamente a la base de datos.

### `src/features/`: código propio de cada ámbito

Las pantallas específicas van en `features/<nombre>/components/`; la lógica y los datos del ámbito pueden ir en `lib/` o `data/` **solo cuando hagan falta**. Ejemplo: las reglas de puntuación pertenecen a `features/game/`, no a una utilidad genérica. Nunca muevas aquí algo que Next.js exija tener en `app/`.

Las carpetas `categories`, `questions`, `game`, `rewards`, `multiplayer` y `admin` **son reservas**, no implementaciones ni compromisos de arquitectura.

### `src/styles/`: el CSS centralizado, pero no mezclado

1. **`tokens.css`**: variables CSS para paleta, tipografías y futuros tamaños comunes. Definir una variable aquí una sola vez y reutilizarla mediante `var(--olive)`, etc.
2. **`globals.css`**: `html`, `body`, reset básico, accesibilidad. No poner selectores de pantallas como `.hero` o `.card` aquí.
3. **`*.module.css`**: CSS de una pantalla o conjunto estrechamente relacionado. Los selectores tienen ámbito local y usan `camelCase` para poder escribir `styles.marblePanel` sin corchetes.

Ejemplo:

```tsx
import styles from "@/styles/dashboard.module.css";

export function MainScreen() {
  return <main className={styles.main}>...</main>;
}
```

Evitar estilos inline cuando se puedan expresar con clases. Agrupar estilos por componente y dejar sus `@media` próximos, legibles. No duplicar colores fijos cuando ya exista un token apropiado. Si un estilo es verdaderamente compartido por varias pantallas, convertirlo en componente compartido o añadir un módulo CSS dedicado; no volver a `globals.css` por comodidad.

### `src/components/`, `src/lib/`, `src/types/` y `src/hooks/`

Solo material reutilizado **por más de una funcionalidad**. No usar estas carpetas para esconder componentes de una sola pantalla. Los accesos a BD y secretos van exclusivamente en código de servidor. No crear hooks, tipos o abstracciones hasta que exista un caso de uso concreto.

### `db/`, `public/`, `docs/`

- `db/init/` tiene el SQL que PostgreSQL ejecuta **solo la primera vez** que crea el volumen. `db/migrations/` está reservado: **todavía no tenemos herramienta de migraciones**.
- `public/` guarda archivos estáticos accesibles desde el navegador: `public/legacy/logo.png` se usa como `/legacy/logo.png`. No guardar secretos.
- `docs/` guarda arquitectura, decisiones, guías de colaboración y futuros diagramas.

## Convenciones obligatorias

- **Archivos/carpetas**: `kebab-case` (`login-screen.tsx`, `auth.module.css`).
- **Componentes y tipos**: `PascalCase` (`LoginScreen`, `UserProfile`).
- **Variables y funciones**: `camelCase` (`handleLogin`, `currentUser`).
- **Clases en CSS Modules**: `camelCase` (`.primaryAction`, `.marblePanel`).
- **TypeScript**: `strict: true`; no usar `any` sin motivo y sin justificarlo.
- **Imports**: usar alias `@/` cuando importe claridad. No crear dependencias circulares.
- **Estados y efectos**: `"use client"` solo en componentes que necesiten interactividad del navegador. Las páginas y componentes estáticos pueden quedarse en el servidor.
- **Botones y enlaces**: `<button>` para acciones o enviar formularios; `<Link>` para navegar.
- **Comentarios**: explicar por qué se hace algo, no repetir el código.
- **Commits**: cambios pequeños, mensajes en español y revisión antes de fusionar.
- **Nuevas funcionalidades**: decidir primero sus responsabilidades y ubicación; no crear arquitectura vacía sin necesidad.

## Advertencias actuales

- **Login/registro son demostraciones visuales**. El enlace de login conduce a `/main` sin validar una cuenta; no es autenticación real ni una zona protegida.
- El `GET /api/health` responde con 503 cuando PostgreSQL no está configurado, lo que es normal si se trabaja sin Docker.
- La base de datos inicial solo tiene categorías. No se ha añadido lógica de partidas, administración, usuarios ni recompensas en esta reorganización.

## Cómo trabajar y comprobar cambios

Sin Docker (solo interfaz, requiere Node.js instalado):

```powershell
npm ci
npm run dev
```

Web: `http://localhost:3100`. La base de datos no está disponible en este modo a menos que la configures aparte.

Con Docker (requiere motor Docker funcionando, en Git Bash/WSL/Mac/Linux):

```bash
./dev.sh
./stop.sh
```

Para mantener el formato coherente en IntelliJ y en los dos ordenadores, usamos **EditorConfig y Prettier**. Los archivos de configuración son `.editorconfig` y `.prettierrc.json`.

```bash
npm run format          # Aplica formato al codigo y documentacion
npm run format:check    # Comprueba que nadie haya desordenado el formato
```

La comprobación de formato también se ejecuta en GitHub Actions.

Antes de cada commit o Pull Request:

```bash
npm run format:check
npm run typecheck
npm run lint
npm run build
```

Después de mover una pantalla o una hoja CSS, probar `/`, `/login`, `/register` y `/main`, incluyendo móvil. No sustituir UI real por maqueta ni cambiar su comportamiento durante una refactorización estructural.

## Dónde empezar al añadir código

- **Pantalla nueva**: `src/app/ruta/page.tsx` y `src/features/<funcion>/components/pantalla.tsx`.
- **Estilo de pantalla**: `src/styles/<funcion>.module.css`.
- **Acción HTTP**: `src/app/api/<recurso>/route.ts` y lógica de servidor en su funcionalidad.
- **Componente compartido**: `src/components/ui/` o `src/components/layout/`, solo si realmente se reutiliza.
- **Dato permanente**: diseñar el esquema y las migraciones antes de asumir que basta con un archivo SQL de inicio.

> Mantener la simplicidad: una organización predecible sirve para que ambos programadores entiendan el proyecto, no para añadir capas artificiales.
