# ArmorCosmetic - Changelog

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
