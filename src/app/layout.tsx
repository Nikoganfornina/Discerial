import type { Metadata } from "next";
import "@/styles/tokens.css";
import "@/styles/globals.css";

export const metadata: Metadata = {
  title: "Discerial — El saber no ocupa lugar",
  description:
    "Discerial: un juego de conocimiento inspirado en la historia y la cultura del mundo clásico. Una nueva etapa está en camino.",
};

export default function RootLayout({ children }: Readonly<{ children: React.ReactNode }>) {
  return (
    <html lang="es">
      <body>{children}</body>
    </html>
  );
}
