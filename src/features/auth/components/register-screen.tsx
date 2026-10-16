"use client";

import { useState, type FormEvent } from "react";
import Link from "next/link";
import styles from "@/styles/auth.module.css";

export function RegisterScreen() {
  const [message, setMessage] = useState("");

  function handleRegister(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    const data = new FormData(event.currentTarget);
    const password = String(data.get("password") ?? "");
    const confirm = String(data.get("confirmPassword") ?? "");

    if (password !== confirm) {
      setMessage("Las contraseñas no coinciden.");
      return;
    }

    setMessage("El registro todavía no está implementado.");
  }

  return (
    <main className={styles.page}>
      <section className={styles.card}>
        <p className={styles.eyebrow}>DISCERIAL</p>
        <h1>Crear cuenta</h1>
        <p>Comienza tu viaje por el conocimiento.</p>

        <form onSubmit={handleRegister} className={styles.form}>
          <label htmlFor="username">Nombre de usuario</label>
          <input
            id="username"
            name="username"
            autoComplete="username"
            placeholder="Tu nombre de usuario"
            required
          />

          <label htmlFor="registerEmail">Correo electrónico</label>
          <input
            id="registerEmail"
            name="email"
            type="email"
            autoComplete="email"
            placeholder="correo@ejemplo.com"
            required
          />

          <label htmlFor="registerPassword">Contraseña</label>
          <input
            id="registerPassword"
            name="password"
            type="password"
            autoComplete="new-password"
            minLength={8}
            required
          />

          <label htmlFor="confirmPassword">Repite la contraseña</label>
          <input
            id="confirmPassword"
            name="confirmPassword"
            type="password"
            autoComplete="new-password"
            minLength={8}
            required
          />

          <button type="submit" className={styles.button}>
            Crear cuenta
          </button>
        </form>

        <p className={styles.notice} role="status">
          {message}
        </p>

        <p className={styles.footer}>
          ¿Ya tienes cuenta? <Link href="/login">Inicia sesión</Link>
        </p>
      </section>
    </main>
  );
}
