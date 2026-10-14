import { NextResponse } from "next/server";
import postgres from "postgres";

export const dynamic = "force-dynamic";

const databaseUrl = process.env.DATABASE_URL;
const sql = databaseUrl
  ? postgres(databaseUrl, {
      max: 2,
      connect_timeout: 3,
      idle_timeout: 10,
    })
  : null;

export async function GET() {
  if (!sql) {
    return NextResponse.json(
      { status: "degraded", database: "not_configured" },
      { status: 503 },
    );
  }

  try {
    await sql.unsafe("SELECT 1");
    return NextResponse.json({ status: "ok", database: "connected" });
  } catch {
    // Evitar filtrar host, usuario o credenciales en respuestas HTTP.
    return NextResponse.json(
      { status: "degraded", database: "unavailable" },
      { status: 503 },
    );
  }
}
