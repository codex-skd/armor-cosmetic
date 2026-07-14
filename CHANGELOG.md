# ArmorCosmetic - Changelog

## [1.0.18] - 2026-07-11

### Fix
- Per-slot toggles (S/A) now hide the **real armor** instead of the cosmetic armor.
  - Toggle ON (green/S) = hides real armor → shows skin (or cosmetic if equipped).
  - Toggle OFF (gray/A) = normal behavior (real armor visible, cosmetic on top).
  - Now you can wear functional armor underneath while displaying only your skin or cosmetic armor.

## [1.0.17] - 2026-07-10

### Fixes
- Updated all 11 language files: "Back to Normal" button now reads "Close" / "Cerrar".
- Tooltip updated: "Close Cosmetic Inventory" / "Cerrar inventario cosmético".

## [1.0.16] - 2026-07-10

### Fixes
- Added `META-INF/mods.toml` alongside `neoforge.mods.toml` so CurseForge detects the mod as NeoForge.
- Fixed `pack.mcmeta` (had unexpanded template variable).
- Updated English and Spanish translations for button/tooltip.

## [1.0.15] - 2026-07-10

### Fixes
- **Inventory lock**: Closing the cosmetic inventory now properly returns to normal inventory control.
- **Real armor lost on death**: Fixed. Real armor now drops correctly instead of being discarded.
- **Corail Tombstone compatibility**: Cosmetic armor items are now captured by tombstone on death.
- **Crash (NullPointerException) on death**: Container is properly closed via `doCloseContainer()`.

## [1.0.11] - 2026-07-09

### Changed
- Removed `keepThroughDeath` config option. Cosmetic armor always drops on death.

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
- Replica of CosmeticArmorReworked by zlainsama, ported by skd

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
- Ported to NeoForge 26.1.2.76 by skd
- Inspired by baubles (azanor)
