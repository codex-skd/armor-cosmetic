# Graph Report - 1.21.1  (2026-08-31)

## Corpus Check
- 49 files · ~85k words
- Verdict: port inicial a 1.21.1

## Summary
- Port de armor_cosmetic a Minecraft 1.21.1 + NeoForge 21.1.249
- Build: NeoGradle 7.0.145 + Parchment 2024.11.17 + Java 21
- Fuente base: CosmeticArmorReworked-1.21.5-neoforge adaptado
- Adaptaciones 1.21.1: RecipeBookMenu/CraftingMenu, EffectRenderingInventoryScreen, Pair<BlockAtlas, ResourceLocation> para slots, Mixin PlayerRenderer.render, CAStacksBase NBT 1.21.1 API

## Community Hubs
- InventoryManager
- ContainerCosArmor (RecipeBookMenu 2x2)
- InventoryCosArmor / CAStacksBase
- GuiCosArmorInventory (EffectRenderingInventoryScreen + RecipeBookComponent)
- PlayerRenderHandler + MixinPlayerRenderer (render PRE/POST)
- Payloads (6 tipos)
- NeoForgeArmorCosmetic (mod entry)

## Build Verification
- ./gradlew build SUCCESS (jar: armor_cosmetic-1.21.1-neoforge-21.1.249-0.0.0-beta.1.jar)
