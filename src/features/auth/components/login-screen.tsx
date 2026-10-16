"use client";

import { useState, type FormEvent } from "react";
import Link from "next/link";
import styles from "@/styles/auth.module.css";

export function LoginScreen() {
  const [message, setMessage] = useState("");

  function handleLogin(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setMessage("El inicio de sesión todavía no está implementado.");
  }

  return (
    <main className={styles.page}>
      <section className={styles.card}>
        <p className={styles.eyebrow}>DISCERIAL</p>
        <h1>Iniciar sesión</h1>
        <p>Bienvenido de nuevo al conocimiento.</p>

        <form onSubmit={handleLogin} className={styles.form}>
          <label htmlFor="email">Correo electrónico</label>
          <input
            id="email"
            name="email"
            type="email"
            autoComplete="email"
            placeholder="correo@ejemplo.com"
            required
          />

          <label htmlFor="password">Contraseña</label>
          <input
            id="password"
            name="password"
            type="password"
            autoComplete="current-password"
            placeholder="Tu contraseña"
            required
          />

          <Link href="/main" className={styles.primaryAction}>
            Iniciar Sesion <span aria-hidden="true">↗</span>
          </Link>
        </form>

        <p className={styles.notice} role="status">
          {message}
        </p>

        <p className={styles.footer}>
          ¿No tienes cuenta? <Link href="/register">Regístrate</Link>
        </p>
      </section>
    </main>
  );
}
