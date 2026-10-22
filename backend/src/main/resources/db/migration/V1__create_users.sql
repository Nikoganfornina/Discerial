
CREATE TABLE IF NOT EXISTS users (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(), --postgre genera automaticamente un uuid random para cada usuario
  email VARCHAR(254) NOT NULL,
  nickname VARCHAR(30) NOT NULL ,
  password_hash TEXT NOT NULL,
  create_at TIMESTAMPTZ NOT NULL DEFAULT NOW() ,
  update_at TIMESTAMPTZ NOT NULL DEFAULT NOW() ,
  email_verified_at TIMESTAMPTZ ,
  status VARCHAR(20) NOT NULL DEFAULT 'PENDING_VERIFICATION',

  -- estados definidos, no existen mas estados de los que haya aqui
  CONSTRAINT users_status_check
    CHECK(
      status IN (
          'PENDING_VERIFICATION',
          'ACTIVE',
          'SUSPENDED'
      )
    ),

  CONSTRAINT users_nickname_length_check
    CHECK (LENGTH(TRIM(nickname)) BETWEEN 3 AND 30)

);

-- evita correos duplicados e ignora mayusculas
CREATE UNIQUE INDEX users_email_unique
  ON users (LOWER(email));
-- evita nicknames duplicados e ignora mayusculas
CREATE UNIQUE INDEX users_nickname_unique
  ON users (LOWER(nickname));


