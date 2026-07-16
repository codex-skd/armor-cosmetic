# CurseForge — Publicación

## Variables del proyecto

| Variable | Valor |
|----------|-------|
| `curseforge_project_id` | `1600093` |
| `mod_id` | `armor_cosmetic` |
| `minecraft_version` | `26.1.2` |
| `framework` | `neoforge` |

## API Token

Generar en https://authors-old.curseforge.com/account/api-tokens

Autenticación: cabecera `X-Api-Token` o query param `token`.

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
