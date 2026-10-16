"use client";

import Image from "next/image";
import { useEffect, useRef, useState } from "react";
import Link from "next/link";
import styles from "@/styles/landing.module.css";

const categories = [
  {
    name: "Historia",
    subtitle: "Los acontecimientos que cambiaron el mundo",
    image: "/legacy/categorias/Historia.jpg",
    index: "I",
  },
  {
    name: "Filosofía",
    subtitle: "Pensar, cuestionar y comprender",
    image: "/legacy/categorias/Filosofia.jpg",
    index: "II",
  },
  {
    name: "Literatura",
    subtitle: "Las palabras que perduran",
    image: "/legacy/categorias/Literatura.jpg",
    index: "III",
  },
  {
    name: "Biología",
    subtitle: "El fascinante mundo de la vida",
    image: "/legacy/categorias/Biologia.jpg",
    index: "IV",
  },
  {
    name: "Mixta",
    subtitle: "Un desafío entre todas las disciplinas",
    image: "/legacy/categorias/Mix.jpg",
    index: "V",
  },
] as const;

export function WelcomeScreen() {
  const [preview, setPreview] = useState<string | null>(null);
  const closeButtonRef = useRef<HTMLButtonElement>(null);

  useEffect(() => {
    if (preview !== null) {
      closeButtonRef.current?.focus();
    }

    function onEscape(event: KeyboardEvent) {
      if (event.key === "Escape") setPreview(null);
    }

    window.addEventListener("keydown", onEscape);
    return () => window.removeEventListener("keydown", onEscape);
  }, [preview]);

  return (
    <main>
      <section className={styles.hero} id="inicio" aria-labelledby="site-title">
        <div className={styles.heroTint} aria-hidden="true" />

        <header className={styles.topbar}>
          <a className={styles.wordmark} href="#inicio" aria-label="Discerial, inicio">
            <span className={styles.markIcon} aria-hidden="true">
              ◈
            </span>
            Discerial
          </a>
          <a className={styles.topbarLink} href="#categorias">
            Explorar <span aria-hidden="true">↗</span>
          </a>
        </header>

        <div className={styles.heroContent}>
          <p className={styles.heroEyebrow}>
            <span className={styles.line} aria-hidden="true" />
            SCIENTIA LOCUM NON OCCUPAT
            <span className={styles.line} aria-hidden="true" />
          </p>

          <div className={styles.emblemCard}>
            <Image
              src="/legacy/logo.png"
              width={500}
              height={500}
              priority
              alt="Escudo original de Discerial, con motivos griegos, una columna dorada y el lema Scientia locum non occupat"
              className={styles.emblemImage}
            />
          </div>

          <h1 id="site-title" className="visually-hidden">
            Discerial
          </h1>
          <p className={styles.heroDescription}>
            Una nueva era para descubrir, aprender y competir.
          </p>

          <div className={styles.heroActions}>
            <Link href="/login" className={styles.primaryAction}>
              Comenzar <span aria-hidden="true">↗</span>
            </Link>

            <a className={styles.secondaryAction} href="#categorias">
              Descubrir el juego <span aria-hidden="true">↓</span>
            </a>
          </div>
          <p className={styles.heroMotto}>EL SABER NO OCUPA LUGAR</p>
        </div>

        <div className={styles.heroBottom}>
          <span>CONOCIMIENTO · HISTORIA · DESAFÍO</span>
          <a href="#categorias" aria-label="Bajar para descubrir las categorías">
            DESLIZA PARA EXPLORAR <span aria-hidden="true">↓</span>
          </a>
          <span>EST. MMXXV</span>
        </div>
      </section>

      <section className={styles.categories} id="categorias" aria-labelledby="categories-title">
        <div className={styles.sectionInner}>
          <p className={styles.sectionKicker}>LUDERE · DISCERE · COGNOSCERE</p>
          <h2 id="categories-title">El conocimiento no tiene límites.</h2>
          <p className={styles.sectionDescription}>
            Cinco disciplinas. Infinitas preguntas. Una misma curiosidad. Redescubre los temas que
            dieron vida a Discerial.
          </p>

          <div className={styles.categoryGrid}>
            {categories.map((category) => (
              <button
                key={category.name}
                type="button"
                className={styles.categoryCard}
                onClick={() => setPreview(category.name)}
                aria-label={category.name + " — vista previa de categoría"}
              >
                <Image
                  className={styles.categoryImage}
                  src={category.image}
                  fill
                  sizes="(max-width: 640px) 100vw, (max-width: 1000px) 45vw, 20vw"
                  alt=""
                />
                <span className={styles.categoryShade} aria-hidden="true" />
                <span className={styles.categoryNumber}>{category.index} / V</span>
                <span className={styles.categoryText}>
                  <strong>{category.name}</strong>
                  <small>{category.subtitle}</small>
                </span>
                <span className={styles.categoryArrow} aria-hidden="true">
                  ↗
                </span>
              </button>
            ))}
          </div>

          <div className={styles.sectionFooter}>
            <p>UNA NUEVA ETAPA COMIENZA</p>
            <p>Discerial · Un proyecto de NG & Bernet</p>
          </div>
        </div>
      </section>

      {preview && (
        <div className={styles.modalBackdrop} onMouseDown={() => setPreview(null)}>
          <div
            className={styles.modal}
            role="dialog"
            aria-modal="true"
            aria-labelledby="modal-title"
            aria-describedby="modal-desc"
            onMouseDown={(event) => event.stopPropagation()}
          >
            <span className={styles.modalOrnament} aria-hidden="true">
              ✦
            </span>
            <p className={styles.modalKicker}>NOVA AETAS</p>
            <h2 id="modal-title">
              {preview === "Discerial" ? "El regreso de Discerial" : preview}
            </h2>
            <p id="modal-desc">
              Estamos reconstruyendo Discerial para la web. La portada y las categorías son una
              vista previa: las cuentas y las partidas todavía no están disponibles.
            </p>
            <button
              type="button"
              className={styles.modalClose}
              onClick={() => setPreview(null)}
              ref={closeButtonRef}
            >
              Entendido
            </button>
          </div>
        </div>
      )}
    </main>
  );
}
