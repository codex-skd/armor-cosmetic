# CurseForge — Publicación

## Variables del proyecto

| Variable | Valor | Ámbito |
|----------|-------|--------|
| `curseforge_project_id` | `1600093` | Por proyecto |
| `curseforge_upload_token` | (token personal) | Por cuenta (compartido entre proyectos) |
| `mod_id` | `armor_cosmetic` | Por proyecto |
| `minecraft_version` | `26.1.2` | Por proyecto |
| `framework` | `neoforge` | Por proyecto |
| `java_version` | `25` | Por proyecto |
| `environment` | `Client`, `Server` | Por proyecto |

## API Token

El token se genera en https://authors-old.curseforge.com/account/api-tokens

**Una sola cuenta, un solo token** — el mismo token sirve para todos los proyectos del autor.

Autenticación: cabecera `X-Api-Token` o query param `token`.

## Parámetros del upload

| Campo | Valor | Notas |
|-------|-------|-------|
| `displayName` | `Armor Cosmetic (1.0.19)` | Nombre visible, NO el nombre del archivo |
| `changelog` | Contenido del release notes | Siempre en formato Markdown |
| `changelogType` | `markdown` | Obligatorio para formato correcto |
| `releaseType` | `release` o `beta` | Según el tipo |
| `gameVersionNames` | `["Client", "Server", "26.1.2", "NeoForge"]` | Minecraft + entorno + modloader |

## Subir archivo (JAR)

```
POST https://minecraft.curseforge.com/api/projects/{curseforge_project_id}/upload-file
```

Multipart form-data con campos:
- `metadata` — JSON con changelog, gameVersions, releaseType...
- `file` — el JAR

## Actualizar metadatos de archivo

```
POST https://minecraft.curseforge.com/api/projects/{curseforge_project_id}/update-file
```

## Descripción del proyecto

No hay endpoint API para actualizar la descripción. Se edita manualmente desde la web de CurseForge pegando el contenido de `project_description.md`.

## Release notes

Las release notes se suben como `changelog` en el campo `metadata` al hacer upload del JAR. Se almacenan en `docs/curseforge/versions/<version>.md`.

## Flujo

1. Incrementar versión en `gradle.properties`
2. `./gradlew clean build`
3. Crear release notes en `docs/curseforge/versions/<version>.md`
4. Actualizar `CHANGELOG.md`
5. Commit + tag (`<mc-version>-<framework>-<version>`)
6. Subir JAR a CurseForge vía API
7. Actualizar descripción en web si cambia
