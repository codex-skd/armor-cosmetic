# CurseForge — Variables del proyecto

> Las siguientes variables son leídas automáticamente por `../codex-docs/scripts/curseforge-upload.ps1`

project_id = 1600093
api_token = ee776b0a-ee95-4850-b554-06be02a8657f
game_versions = 9638, 9639, 16498, 10150
release_type = release
## Proyecto

| Variable | Valor |
|----------|-------|
| `curseforge_project_id` | `1600093` |
| `mod_id` | `armor_cosmetic` |
| `display_name` | `Armor Cosmetic` (separado, no junto) |

## Tokens

| API | Token | Uso |
|-----|-------|-----|
| Upload | `ee776b0a-ee95-4850-b554-06be02a8657f` | Subir archivos JAR |
| Core (GET) | `$2a$10$yGwryAfmRkS9ZJsJUDf5YOKZpOIsmHB8Fji2D8JVCKBSZEKYlwmaO` | Consultar datos del mod |

Autenticación Upload: cabecera `X-Api-Token`
Autenticación Core: cabecera `x-api-key`

## Versión actual

| Variable | Valor |
|----------|-------|
| `minecraft_version` | `26.2` |
| `framework` | `neoforge` |
| `java_version` | `25` |
| `environment` | `Client`, `Server` |

## Rama

```
minecraft/26.2/neoforge-26.2.0.45-beta/production
```

## Tag

Formato: `<mc-version>-<framework>-<version>`
Ejemplo: `26.2-neoforge-0.0.0-beta.1`

## Parámetros del upload

| Campo | Valor | Notas |
|-------|-------|-------|
| `displayName` | `Armor Cosmetic (1.0.2)` | Nombre visible: `display_name (version)` |
| `changelog` | HTML (no Markdown) | Ver estructura abajo |
| `changelogType` | `html` | Obligatorio para que se vea bien |
| `releaseType` | `release` o `beta` | Según el tipo de versión |
| `gameVersions` | `[9638, 9639, 10150, 16498]` | **IDs numéricos**, no nombres (la API devuelve 400 "Expected Integer but got String" si se envían strings como `"Client"`). Ver tabla de IDs abajo |

### IDs de `gameVersions` para 26.2

Obtenidos de `sortableGameVersions` de un archivo ya subido (`GET /v1/mods/1600093/files/<id>` con el token Core):

| Nombre | ID | gameVersionTypeId |
|--------|-----|--------|
| `Client` | `9638` | 75208 |
| `Server` | `9639` | 75208 |
| `NeoForge` | `10150` | 68441 |
| `26.2` | `16498` | 86297 |

> Ojo: `GET https://minecraft.curseforge.com/api/game/versions` (con `X-Api-Token`) devuelve **varias entradas duplicadas** con el mismo nombre `26.2` pero distinto `id`/`gameVersionTypeID` (p. ej. `16498`, `16500`). Solo una es la correcta (la que ya usan los archivos existentes) — verificarlo siempre contra un archivo ya publicado antes de asumir un ID.

## Estructura del changelog (HTML)

```html
<h2>v0.0.0-beta.1 - Port to Minecraft 26.2</h2>

<h3>Changes</h3>
<ul>
<li><strong>Port to 26.2</strong>: Adapted to Minecraft 26.2 / NeoForge 26.2.0.32-beta.</li>
<li><strong>API fixes</strong>: <code>setScreenAndShow</code>, <code>gui.screen()</code>.</li>
</ul>

<hr>

<p><strong>JAR</strong>: <code>armor_cosmetic-26.2-neoforge-0.0.0-beta.1.jar</code></p>
```

## Subir archivo (JAR)

**No usar `urllib.request` de Python** — el body multipart hecho a mano (concatenando bytes de texto UTF-8 con los bytes crudos del JAR) provoca un `500 An unhandled exception occurred` del lado de CurseForge por razones no diagnosticadas (probado en esta sesión, ver `codex-docs/scripts/curseforge-upload.ps1` para el motivo original de por qué se abandonó el enfoque manual en PowerShell). Usar PowerShell con `System.Net.Http.MultipartFormDataContent`, que sí funciona:

```powershell
Add-Type -AssemblyName System.Net.Http

$version = "1.0.2"
$changelog = [System.IO.File]::ReadAllText((Resolve-Path "docs/curseforge/versions/$version.md"))

$metadata = @{
    displayName   = "Armor Cosmetic ($version)"
    gameVersions  = @(9638, 9639, 10150, 16498)   # Client, Server, NeoForge, 26.2 — IDs, no nombres
    releaseType   = "release"
    changelogType = "html"
    changelog     = $changelog
}
$metadataJson = $metadata | ConvertTo-Json -Compress

$fileItem = Get-Item -Path (Resolve-Path "build/libs/armor_cosmetic-26.2-neoforge-$version.jar")
$fileBytes = [System.IO.File]::ReadAllBytes($fileItem.FullName)

$httpClient = New-Object System.Net.Http.HttpClient
$httpClient.DefaultRequestHeaders.Add('X-Api-Token', 'ee776b0a-ee95-4850-b554-06be02a8657f')

$multipart = New-Object System.Net.Http.MultipartFormDataContent
$multipart.Add((New-Object System.Net.Http.StringContent($metadataJson, [System.Text.Encoding]::UTF8, "application/json")), "metadata")

$fileContent = New-Object System.Net.Http.ByteArrayContent(,$fileBytes)
$fileContent.Headers.ContentType = [System.Net.Http.Headers.MediaTypeHeaderValue]::Parse("application/java-archive")
$multipart.Add($fileContent, "file", $fileItem.Name)

$response = $httpClient.PostAsync("https://minecraft.curseforge.com/api/projects/1600093/upload-file", $multipart).GetAwaiter().GetResult()
Write-Host $response.Content.ReadAsStringAsync().GetAwaiter().GetResult()
$httpClient.Dispose(); $multipart.Dispose()
```

## Verificar con GET

```bash
curl -s "https://api.curseforge.com/v1/mods/1600093/files/<FILE_ID>" \
  -H "x-api-key: $2a$10$yGwryAfmRkS9ZJsJUDf5YOKZpOIsmHB8Fji2D8JVCKBSZEKYlwmaO"
```

## Changelog

```bash
curl -s "https://api.curseforge.com/v1/mods/1600093/files/<FILE_ID>/changelog" \
  -H "x-api-key: $2a$10$yGwryAfmRkS9ZJsJUDf5YOKZpOIsmHB8Fji2D8JVCKBSZEKYlwmaO"
```

## Descripcion del proyecto

No hay endpoint API para actualizar la descripcion. Se edita manualmente desde la web de CurseForge pegando el HTML de `docs/curseforge/project_description.md`.

## Flujo completo

1. `./gradlew clean build`
2. Actualizar `docs/curseforge/versions/<version>.md` con HTML
3. Actualizar `CHANGELOG.md`
4. `git commit -m "fix: descripcion\n\nvX.Y.Z"` + `git push`
5. `git tag -a 26.2-neoforge-<version> -m "vX.Y.Z: descripcion"` + `git push origin <tag>`
6. Subir JAR a CurseForge con Python
7. Verificar con GET que el changelog se vea bien
8. Liberar manualmente desde la web si es necesario
