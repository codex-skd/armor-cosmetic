# CurseForge — Variables del proyecto

## Proyecto

| Variable | Valor |
|----------|-------|
| `curseforge_project_id` | `1600093` |
| `mod_id` | `armor_cosmetic` |

## Cuenta (compartido entre proyectos)

| Variable | Valor |
|----------|-------|
| `curseforge_upload_token` | `ee776b0a-ee95-4850-b554-06be02a8657f` |

## Versión actual

| Variable | Valor |
|----------|-------|
| `minecraft_version` | `26.1.2` |
| `framework` | `neoforge` |
| `java_version` | `25` |
| `environment` | `Client`, `Server` |

## API Token

Generar/regenerar en https://authors-old.curseforge.com/account/api-tokens

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
- `metadata` — JSON con displayName, changelog, gameVersionNames, releaseType...
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
5. Commit + tag (`<mc-version>-neoforge-<version>`)
6. Preguntar al usuario si desea subir a CurseForge
7. Subir JAR a CurseForge vía API
8. Actualizar descripción en web si cambia
