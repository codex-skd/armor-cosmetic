# ArmorCosmetic - Changelog

## [0.1.15-beta] - 2026-07-06

### Fixed
- Skin armor toggle no longer hides real equipped armor - skin mode now leaves real armor visible
- Recipe book button and component now properly hidden when config is enabled
- "Cosmetic" label repositioned and now visible above cosmetic armor slots

## [0.1.14-beta] - 2026-07-06

### Added
- "Cosmetic" label above armor slots in cosmetic inventory
- Per-slot skin armor toggles with configurable position, size, and spacing
- Full translations in 11 languages

### Fixed
- Per-slot skin armor toggles now correctly sync to server and persist
- Toggle buttons display in correct order (Head→Chest→Legs→Feet left to right)
- Recipe book hidden when opening cosmetic inventory
- Server-side bounds checking prevents crashes from malformed packets
- Dark background overlay renders in cosmetic inventory
- Buttons show text labels (S=skin, A=armor) instead of broken icons

## [0.1.12-beta] - 2026-07-06

### Added
- "Cosmetic" label above armor slots in cosmetic inventory
- Configurable position, spacing, and size for per-slot skin armor toggles
- Configurable icon sizes inside buttons (0 = auto-fit)
- Configurable button width and height for all buttons
- Button icons for cosmetic armor, normal inventory, and toggle states
- Full translations in 11 languages (en, es, de, fr, ja, pt, ru, zh, ko, tr, cs)
- Equipment type filtering per cosmetic armor slot

### Fixed
- Cosmetic armor no longer replaces or deletes real equipped armor
- Armor rendering now works correctly on player model
- Per-slot skin armor toggles persist and sync to server
- Dark background overlay renders properly in cosmetic inventory
- Toggle button moved away from crafting label
- Recipe book disable config no longer crashes the game

## [0.1.1-beta] - 2026-07-06

### Added
- Initial beta release of ArmorCosmetic for NeoForge 26.1.2.76 (Minecraft 26.1.2)
- Replica of CosmeticArmorReworked by zlainsama
- Dual armor system: wear one set for stats, another for display
- Cosmetic armor inventory GUI with 2x2 crafting grid
- Toggle button to enable/disable cosmetic armor rendering (useful for PvP)
- Skin armor mode: hide armor slots to show player skin
- `/clearcosarmor` command to clear cosmetic armor inventories
- `/coshat` command to toggle helmet as cosmetic/skin
- Persistent cosmetic armor data (survives relog, per-player NBT files)
- Death drop handling for cosmetic armor (configurable)
- Hidden flags API for addon mods to hide specific armor pieces
- Configurable GUI button positions and visibility
- Multi-language support: English (en_us) and Spanish (es_es)
- Client-server sync for multiplayer compatibility

### Credits
- Original mod: CosmeticArmorReworked by zlainsama, dmillerw
- Ported to NeoForge 26.1.2.76 by skd
- Inspired by baubles (azanor)
