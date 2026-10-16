import Link from "next/link";
import styles from "@/styles/dashboard.module.css";

export function MainScreen() {
  return (
    <div className={styles.page}>
      <header className={styles.header}>
        <Link href="/" className={styles.logo}>
          Discerial
        </Link>

        <nav className={styles.navigation}>
          <span className={styles.active}>Tabula</span>
          <Link href="/login">Volver al acceso</Link>
        </nav>
      </header>

      <main className={styles.main}>
        <section className={styles.marblePanel} aria-label="Panel principal de Discerial" />
      </main>

      <footer className={styles.footer}>SCIENTIA LOCUM NON OCCUPAT</footer>
    </div>
  );
}
