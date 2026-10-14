-- Esquema inicial para cada base de datos local.
-- Docker lo ejecuta al crear el volumen por primera vez.
CREATE TABLE IF NOT EXISTS categories (
  id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  slug TEXT NOT NULL UNIQUE,
  name TEXT NOT NULL
);

INSERT INTO categories (slug, name) VALUES
  ('historia', 'Historia'),
  ('filosofia', 'Filosofía'),
  ('literatura', 'Literatura'),
  ('biologia', 'Biología'),
  ('mixta', 'Mixta')
ON CONFLICT (slug) DO NOTHING;
