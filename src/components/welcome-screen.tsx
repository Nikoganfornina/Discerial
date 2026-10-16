"use client";

import Image from "next/image";
import { useEffect, useRef, useState } from "react";
import Link from "next/link";

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
      <section className="hero" id="inicio" aria-labelledby="site-title">
        <div className="hero-tint" aria-hidden="true" />

        <header className="topbar">
          <a className="wordmark" href="#inicio" aria-label="Discerial, inicio">
            <span className="mark-icon" aria-hidden="true">◈</span>
            Discerial
          </a>
          <a className="topbar-link" href="#categorias">
            Explorar <span aria-hidden="true">↗</span>
          </a>
        </header>

        <div className="hero-content">
          <p className="hero-eyebrow">
            <span className="line" aria-hidden="true" />
            SCIENTIA LOCUM NON OCCUPAT
            <span className="line" aria-hidden="true" />
          </p>

          <div className="emblem-card">
            <Image
              src="/legacy/logo.png"
              width={500}
              height={500}
              priority
              alt="Escudo original de Discerial, con motivos griegos, una columna dorada y el lema Scientia locum non occupat"
              className="emblem-image"
            />
          </div>

          <h1 id="site-title" className="visually-hidden">Discerial</h1>
          <p className="hero-description">
            Una nueva era para descubrir, aprender y competir.
          </p>

          <div className="hero-actions">
            <Link href="/login" className="primary-action">
              Comenzar <span aria-hidden="true">↗</span>
            </Link>

            <a className="secondary-action" href="#categorias">
              Descubrir el juego <span aria-hidden="true">↓</span>
            </a>
          </div>
          <p className="hero-motto">EL SABER NO OCUPA LUGAR</p>
        </div>

        <div className="hero-bottom">
          <span>CONOCIMIENTO · HISTORIA · DESAFÍO</span>
          <a href="#categorias" aria-label="Bajar para descubrir las categorías">
            DESLIZA PARA EXPLORAR <span aria-hidden="true">↓</span>
          </a>
          <span>EST. MMXXV</span>
        </div>
      </section>

      <section className="categories" id="categorias" aria-labelledby="categories-title">
        <div className="section-inner">
          <p className="section-kicker">LUDERE · DISCERE · COGNOSCERE</p>
          <h2 id="categories-title">El conocimiento no tiene límites.</h2>
          <p className="section-description">
            Cinco disciplinas. Infinitas preguntas. Una misma curiosidad.
            Redescubre los temas que dieron vida a Discerial.
          </p>

          <div className="category-grid">
            {categories.map((category) => (
              <button
                key={category.name}
                type="button"
                className="category-card"
                onClick={() => setPreview(category.name)}
                aria-label={category.name + " — vista previa de categoría"}
              >
                <Image
                  className="category-image"
                  src={category.image}
                  fill
                  sizes="(max-width: 640px) 100vw, (max-width: 1000px) 45vw, 20vw"
                  alt=""
                />
                <span className="category-shade" aria-hidden="true" />
                <span className="category-number">{category.index} / V</span>
                <span className="category-text">
                  <strong>{category.name}</strong>
                  <small>{category.subtitle}</small>
                </span>
                <span className="category-arrow" aria-hidden="true">↗</span>
              </button>
            ))}
          </div>

          <div className="section-footer">
            <p>UNA NUEVA ETAPA COMIENZA</p>
            <p>Discerial · Un proyecto de NG & Bernet</p>
          </div>
        </div>
      </section>

      {preview && (
        <div className="modal-backdrop" onMouseDown={() => setPreview(null)}>
          <div
            className="modal"
            role="dialog"
            aria-modal="true"
            aria-labelledby="modal-title"
            aria-describedby="modal-desc"
            onMouseDown={(event) => event.stopPropagation()}
          >
            <span className="modal-ornament" aria-hidden="true">✦</span>
            <p className="modal-kicker">NOVA AETAS</p>
            <h2 id="modal-title">
              {preview === "Discerial" ? "El regreso de Discerial" : preview}
            </h2>
            <p id="modal-desc">
              Estamos reconstruyendo Discerial para la web. La portada y las
              categorías son una vista previa: las cuentas y las partidas
              todavía no están disponibles.
            </p>
            <button type="button" className="modal-close" onClick={() => setPreview(null)} ref={closeButtonRef}>
              Entendido
            </button>
          </div>
        </div>
      )}
    </main>
  );
}
