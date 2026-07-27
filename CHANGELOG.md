# Armor Cosmetic - Changelog

## [1.0.22] - 2026-07-22

### Cambios
- **Auditoría de proyecto**: eliminado `TEMPLATE_LICENSE.txt`, movidos templates a `src/main/resources/templates/`, añadido `temp/` al `.gitignore`.
- **WORKFLOW actualizado**: renombrado a `WORKFLOW_ARMOR_COSMETIC_26-1-2.md` siguiendo el formato del genérico v1.0.0.
- **Logo**: renombrado a `armor_cosmetic.png` según convención `<mod_id>.png`.
- **build.gradle**: actualizada ruta de templates a `src/main/resources/templates/`.

## [1.0.21] - 2026-07-16

### Arreglos
- Reintento de subida a CurseForge con changelog en markdown correctamente formateado.

## [1.0.20] - 2026-07-16

### Arreglos
- **Armadura real perdida al morir con Tombstone**: ahora la armadura real se añade a `LivingDropsEvent` junto a la cosmética. Tombstone captura ambas en la tumba.
- Al morir, la armadura real se vacía del jugador y se mete en `event.getDrops()`, garantizando que Tombstone la capture.

## [1.0.19] - 2026-07-14

### Cambios
- Reestructurada rama `main` (solo commit inicial) → desarrollo en `minecraft/<mc-version>/neoforge-<neo-version>/production`.
- Formato de tags actualizado a `<mc-version>-neoforge-<version>` (ej: `26.1.2-neoforge-1.0.18`).
- JAR renombrado a `<mod_id>-<minecraft_version>-<framework>-<mod_version>.jar`.
- WORKFLOW.md actualizado con ramas, convención de JAR y política de idiomas.

## [1.0.18] - 2026-07-11

### Arreglos
- Los toggles individuales (S/A) ahora ocultan la **armadura real** en lugar de la cosmética.
  - Toggle ON (verde/S) = oculta la armadura real → se ve la piel (o cosmética si equipada).
  - Toggle OFF (gris/A) = comportamiento normal (armadura real visible, cosmética encima).
  - Ahora puedes llevar armadura funcional puesta mientras muestras solo tu skin o armadura cosmética.

## [1.0.17] - 2026-07-10

### Arreglos
- Actualizados los 11 archivos de idioma: el botón "Back to Normal" ahora pone "Close" / "Cerrar".
- Tooltip actualizado: "Close Cosmetic Inventory" / "Cerrar inventario cosmético".

## [1.0.16] - 2026-07-10

### Arreglos
- Añadido `META-INF/mods.toml` junto a `neoforge.mods.toml` para que CurseForge detecte el mod como NeoForge.
- Corregido `pack.mcmeta` (tenía una variable de template sin expandir).
- Actualizadas las traducciones al inglés y español del botón/tooltip.

## [1.0.15] - 2026-07-10

### Arreglos
- **Bloqueo del inventario**: al cerrar el inventario cosmético ahora vuelve correctamente al control normal del inventario.
- **Armadura real perdida al morir**: corregido. La armadura real ahora se suelta correctamente en lugar de desaparecer.
- **Compatibilidad con Corail Tombstone**: los objetos cosméticos ahora son capturados por la tumba al morir.
- **Crash (NullPointerException) al morir**: el contenedor se cierra correctamente mediante `doCloseContainer()`.

## [1.0.11] - 2026-07-09

### Cambios
- Eliminada la opción de configuración `keepThroughDeath`. La armadura cosmética siempre se suelta al morir.

## [1.0.10] - 2026-07-09

### Changed
- Split config into COMMON + CLIENT sides for proper server death handling.

## [1.0.9] - 2026-07-08

### Changed
- Updated chestplate icon.

## [1.0.8] - 2026-07-08

### Added
- Tooltips on all buttons.
- Full translations in 11 languages (en, es, de, fr, ja, pt, ru, zh, ko, tr, cs).

## [1.0.7] - 2026-07-08

### Changed
- Chestplate icon that scales to button size.

## [1.0.6] - 2026-07-08

### Changed
- Icon scales to fit button size.
- Default button size 16x16 to fit icon.

## [1.0.1] - 2026-07-06

### Changed
- Updated default config values for better out-of-box experience
- Toggle button hidden by default, creative button hidden by default
- Recipe book and /coshat command disabled by default
- Skin armor toggle buttons repositioned and resized
- Cosmetic armor button now 10x10 (compact, like original mod)
- Removed unused icon size configuration options
- Buttons render text labels only (no sprites/icons)

## [1.0.0] - 2026-07-06

### First Stable Release
- Initial release of ArmorCosmetic for NeoForge 26.1.2.76 (Minecraft 26.1.2)
- Replica of CosmeticArmorReworked by zlainsama, ported by Stalking Dragons

### Features
- Dual armor system: wear one set for stats, another for display
- Cosmetic armor inventory GUI with 2x2 crafting grid
- Global toggle button to enable/disable cosmetic armor rendering (useful for PvP)
- Per-slot skin armor toggle buttons with configurable position and size
- Cosmetic armor data persists across sessions (per-player NBT files)
- Death drop handling for cosmetic armor (configurable)
- Hidden flags API for addon mods
- Commands: /clearcosarmor, /coshat
- Full translations in 11 languages (en, es, de, fr, ja, pt, ru, zh, ko, tr, cs)
- Fully configurable GUI button positions, sizes, and visibility
- Equipment type filtering per cosmetic armor slot
- Client-server sync for multiplayer compatibility

### Known Issues
- Player model not displayed in cosmetic inventory screen
- Item tooltip may show incorrect armor stats while in cosmetic view
- Recipe book may remain open when switching to cosmetic inventory

### Credits
- Original mod: CosmeticArmorReworked by zlainsama, dmillerw
- Ported to NeoForge 26.1.2.76 by Stalking Dragons
- Inspired by baubles (azanor)
