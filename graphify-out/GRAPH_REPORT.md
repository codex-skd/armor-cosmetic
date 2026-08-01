# Graph Report - 26.1.2  (2026-08-02)

## Corpus Check
- 56 files · ~117,174 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 441 nodes · 749 edges · 28 communities (27 shown, 1 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 9 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `1ffd244d`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- Armor Cosmetic - Changelog
- InventoryManager
- CustomPacketPayload
- ContainerCosArmor
- GuiCosArmorInventory
- InventoryCosArmor
- PlayerRenderHandler.java
- GuiCosArmorButton
- CAStacksBase
- ArmorCosmetic.java
- GuiHandler.java
- PayloadOpenCosArmorInventory
- CurseForge — Variables del proyecto
- ModConfigs.java
- Flujo de trabajo — Armor Cosmetic (NeoForge)
- InventoryManagerClient
- Armor Cosmetic
- ArmorCosmeticClient.java
- CLAUDE.md — armor_cosmetic (26.1.2)
- v1.0.19 — Workflow Restructure
- v1.0.20 — Tombstone Compatibility: Real Armor Captured
- gradlew
- v1.0.18 — Per-Slot Toggle Fix: Hide Real Armor, Show Skin

## God Nodes (most connected - your core abstractions)
1. `InventoryCosArmor` - 45 edges
2. `ContainerCosArmor` - 27 edges
3. `InventoryManager` - 26 edges
4. `CAStacksBase` - 21 edges
5. `GuiCosArmorInventory` - 17 edges
6. `Armor Cosmetic - Changelog` - 17 edges
7. `ArmorCosmetic` - 14 edges
8. `CurseForge — Variables del proyecto` - 13 edges
9. `Flujo de trabajo — Armor Cosmetic (NeoForge)` - 11 edges
10. `PayloadOpenCosArmorInventory` - 10 edges

## Surprising Connections (you probably didn't know these)
- `handleGuiInitPost()` --calls--> `PayloadOpenCosArmorInventory`  [INFERRED]
  src/main/java/com/skd/armorcosmetic/impl/client/GuiHandler.java → src/main/java/com/skd/armorcosmetic/impl/network/payload/PayloadOpenCosArmorInventory.java
- `handleGuiInitPost()` --calls--> `PayloadOpenNormalInventory`  [INFERRED]
  src/main/java/com/skd/armorcosmetic/impl/client/GuiHandler.java → src/main/java/com/skd/armorcosmetic/impl/network/payload/PayloadOpenNormalInventory.java
- `ArmorCosmetic` --references--> `ContainerCosArmor`  [EXTRACTED]
  src/main/java/com/skd/armorcosmetic/ArmorCosmetic.java → src/main/java/com/skd/armorcosmetic/impl/inventory/ContainerCosArmor.java
- `InventoryCosArmor` --inherits--> `CAStacksBase`  [EXTRACTED]
  src/main/java/com/skd/armorcosmetic/impl/inventory/InventoryCosArmor.java → src/main/java/com/skd/armorcosmetic/api/inventory/CAStacksBase.java
- `InventoryManagerClient` --inherits--> `InventoryManager`  [EXTRACTED]
  src/main/java/com/skd/armorcosmetic/impl/client/InventoryManagerClient.java → src/main/java/com/skd/armorcosmetic/impl/InventoryManager.java

## Import Cycles
- None detected.

## Communities (28 total, 1 thin omitted)

### Community 0 - "Armor Cosmetic - Changelog"
Cohesion: 0.05
Nodes (37): [1.0.0] - 2026-07-06, [1.0.10] - 2026-07-09, [1.0.11] - 2026-07-09, [1.0.15] - 2026-07-10, [1.0.16] - 2026-07-10, [1.0.17] - 2026-07-10, [1.0.18] - 2026-07-11, [1.0.19] - 2026-07-14 (+29 more)

### Community 1 - "InventoryManager"
Cohesion: 0.09
Nodes (14): CommandContext, CommandSourceStack, FunctionalInterface, LivingDropsEvent, PlayerLoggedInEvent, PlayerLoggedOutEvent, RegisterCommandsEvent, SaveToFile (+6 more)

### Community 2 - "CustomPacketPayload"
Cohesion: 0.09
Nodes (22): CustomPacketPayload, RegistryFriendlyByteBuf, FriendlyByteBuf, Override, StreamCodec, Type, PayloadOpenNormalInventory, FriendlyByteBuf (+14 more)

### Community 3 - "ContainerCosArmor"
Cohesion: 0.13
Nodes (19): AbstractCraftingMenu, CraftingContainer, CraftingRecipe, RecipeBookType, RecipeHolder, ResultContainer, ServerLevel, Slot (+11 more)

### Community 4 - "GuiCosArmorInventory"
Cohesion: 0.12
Nodes (15): AbstractRecipeBookScreen, EffectsInInventory, MouseButtonEvent, ScreenPosition, GuiCosArmorInventory, Component, GuiGraphicsExtractor, Identifier (+7 more)

### Community 5 - "InventoryCosArmor"
Cohesion: 0.15
Nodes (11): Container, MenuProvider, ContentsChangeListener, HiddenFlagsChangeListener, InventoryCosArmor, AbstractContainerMenu, Component, Inventory (+3 more)

### Community 6 - "PlayerRenderHandler.java"
Cohesion: 0.12
Nodes (21): AbstractClientPlayer, Avatar, CallbackInfo, Inject, Mixin, EquipmentSlot, Inventory, Player (+13 more)

### Community 7 - "GuiCosArmorButton"
Cohesion: 0.11
Nodes (14): Button, GuiCosArmorButton, Component, GuiGraphicsExtractor, Identifier, OnPress, Override, GuiCosArmorToggleButton (+6 more)

### Community 8 - "CAStacksBase"
Cohesion: 0.11
Nodes (11): CompoundTag, Event, ICancellableEvent, ItemStackHandler, Provider, CosArmorAPI, CosArmorDeathDrops, Player (+3 more)

### Community 9 - "ArmorCosmetic.java"
Cohesion: 0.12
Nodes (16): DeferredHolder, DeferredRegister, FMLClientSetupEvent, FMLCommonSetupEvent, IEventBus, PayloadRegistrar, RegisterKeyMappingsEvent, RegisterPayloadHandlersEvent (+8 more)

### Community 10 - "GuiHandler.java"
Cohesion: 0.15
Nodes (11): Field, InventoryScreen, Post, GuiHandler, INSTANCE, handleGuiDrawPre(), handleGuiInitPost(), Pre (+3 more)

### Community 11 - "PayloadOpenCosArmorInventory"
Cohesion: 0.17
Nodes (11): KeyMapping, handleClientTick(), Pre, KeyHandler, INSTANCE, registerKeyMappings(), FriendlyByteBuf, Override (+3 more)

### Community 12 - "CurseForge — Variables del proyecto"
Cohesion: 0.13
Nodes (14): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, IDs de `gameVersions` para 26.1.2, Parámetros del upload, Proyecto (+6 more)

### Community 13 - "ModConfigs.java"
Cohesion: 0.23
Nodes (8): BooleanValue, Builder, IntValue, ModConfigSpec, Config, ModContainer, ModContainer, ModConfigs

### Community 14 - "Flujo de trabajo — Armor Cosmetic (NeoForge)"
Cohesion: 0.17
Nodes (11): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Armor Cosmetic (NeoForge), Flujo por tarea, Idioma (+3 more)

### Community 15 - "InventoryManagerClient"
Cohesion: 0.31
Nodes (5): InventoryManagerClient, Inventory, LoadingCache, LoggingOut, Override

### Community 16 - "Armor Cosmetic"
Cohesion: 0.40
Nodes (4): Armor Cosmetic, Build, Features, Requirements

### Community 17 - "ArmorCosmeticClient.java"
Cohesion: 0.60
Nodes (3): ArmorCosmeticClient, Mod, ModContainer

### Community 18 - "CLAUDE.md — armor_cosmetic (26.1.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — armor_cosmetic (26.1.2), Prioridad de instrucciones, Workflow del mod

### Community 19 - "v1.0.19 — Workflow Restructure"
Cohesion: 0.50
Nodes (3): 📝 Notes, 🛠️ Technical Changes, v1.0.19 — Workflow Restructure

### Community 20 - "v1.0.20 — Tombstone Compatibility: Real Armor Captured"
Cohesion: 0.50
Nodes (3): 🐛 Fix, 🛠️ Technical Changes, v1.0.20 — Tombstone Compatibility: Real Armor Captured

### Community 21 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **55 isolated node(s):** `INSTANCE`, `INSTANCE`, `INSTANCE`, `Workflow del mod`, `Prioridad de instrucciones` (+50 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **1 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `InventoryCosArmor` connect `InventoryCosArmor` to `InventoryManager`, `CustomPacketPayload`, `ContainerCosArmor`, `GuiCosArmorInventory`, `CAStacksBase`, `ArmorCosmetic.java`, `InventoryManagerClient`?**
  _High betweenness centrality (0.206) - this node is a cross-community bridge._
- **Why does `ContainerCosArmor` connect `ContainerCosArmor` to `ArmorCosmetic.java`, `GuiCosArmorInventory`, `InventoryManager`, `InventoryManagerClient`?**
  _High betweenness centrality (0.115) - this node is a cross-community bridge._
- **Why does `CAStacksBase` connect `CAStacksBase` to `InventoryCosArmor`?**
  _High betweenness centrality (0.070) - this node is a cross-community bridge._
- **What connects `INSTANCE`, `INSTANCE`, `INSTANCE` to the rest of the system?**
  _55 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Armor Cosmetic - Changelog` be split into smaller, more focused modules?**
  _Cohesion score 0.05263157894736842 - nodes in this community are weakly interconnected._
- **Should `InventoryManager` be split into smaller, more focused modules?**
  _Cohesion score 0.09246088193456614 - nodes in this community are weakly interconnected._
- **Should `CustomPacketPayload` be split into smaller, more focused modules?**
  _Cohesion score 0.09388335704125178 - nodes in this community are weakly interconnected._