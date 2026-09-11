## [1.0.1] - 2026-09-11

### Fixed

- Compatibilidad con Corail Tombstone: al morir con Tombstone instalado, la armadura cosmética ya no se suelta al suelo ni se transfiere a la tumba; se conserva en sus ranuras cosméticas tras revivir. Antes acababa equipada como armadura principal y las ranuras cosméticas quedaban vacías.

## [1.0.0] - 2026-08-31

### Added
- Initial stable release
- Default config values updated for better UI positioning (CosArmorGuiButton_Left=75, CosArmorGuiButton_Top=50, CosArmorToggleButton_Left=77, CosArmorToggleButton_Top=45)
- Recipe book disabled by default in cosmetic inventory
- Toggle button positioned above main button
- Config files organized in `config/armor_cosmetic/` folder (client.toml, common.toml)
- Full Configured mod integration for hot-reload config editing
- Fixed tooltip translations (English/Spanish)
- ASM dependency conflict resolution (forced to 9.10.1)

### Changed
- Bumped version from 0.0.0-beta.3 to 1.0.0

## [0.0.0-beta.2] - 2026-08-31

### Fixed

- **CurseForge gameVersions**: corregido ID de MC `16498` (26.2) → `11779` (1.21.1) para etiquetado correcto en la plataforma.

## [0.0.0-beta.1] - 2026-08-31

### Added

- Port inicial a Minecraft 1.21.1 + NeoForge 21.1.249 (NeoGradle 7.0.145 + Parchment 2024.11.17) basado en CosmeticArmorReworked 1.21.5-neoforge.
- Sistema dual de armadura, GUI cosmética, toggles por slot y global, comandos `/clearcosarmor` y `/coshat`, compatibilidad con tombstones.
- Workflow `docs/WORKFLOW_ARMOR_COSMETIC_1-21-1.md` para rama `minecraft/1.21.1/neoforge-21.1.249/production`.
