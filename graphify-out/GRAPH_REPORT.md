# Graph Report - 1.21.1  (2026-08-31)

## Corpus Check
- 56 files · ~114,488 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 385 nodes · 675 edges · 26 communities (25 shown, 1 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 6 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `d4af8057`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- CustomPacketPayload
- ContainerCosArmor
- InventoryCosArmor
- NeoForgeArmorCosmetic.java
- CAStacksBase
- InventoryManager
- GuiCosArmorButton
- GuiCosArmorInventory
- PlayerRenderHandler.java
- GuiHandler.java
- PayloadOpenCosArmorInventory
- CurseForge — Variables del proyecto
- Flujo de trabajo — Armor Cosmetic (NeoForge)
- MixinPlayerRenderer.java
- InventoryManagerClient
- PayloadSyncHiddenFlags
- Armor Cosmetic
- gradlew
- [0.0.0-beta.1] - 2026-08-31
- ModConstants.java
- PayloadSyncCosArmor

## God Nodes (most connected - your core abstractions)
1. `InventoryCosArmor` - 44 edges
2. `ContainerCosArmor` - 36 edges
3. `InventoryManager` - 24 edges
4. `CAStacksBase` - 20 edges
5. `GuiCosArmorInventory` - 19 edges
6. `CurseForge — Variables del proyecto` - 13 edges
7. `NeoForgeArmorCosmetic` - 12 edges
8. `Flujo de trabajo — Armor Cosmetic (NeoForge)` - 11 edges
9. `PayloadOpenCosArmorInventory` - 10 edges
10. `PayloadSyncCosArmor` - 10 edges

## Surprising Connections (you probably didn't know these)
- `handleGuiInitPost()` --calls--> `PayloadOpenCosArmorInventory`  [INFERRED]
  src/main/java/com/skd/armorcosmetic/impl/client/GuiHandler.java → src/main/java/com/skd/armorcosmetic/impl/network/payload/PayloadOpenCosArmorInventory.java
- `handleGuiInitPost()` --calls--> `PayloadOpenNormalInventory`  [INFERRED]
  src/main/java/com/skd/armorcosmetic/impl/client/GuiHandler.java → src/main/java/com/skd/armorcosmetic/impl/network/payload/PayloadOpenNormalInventory.java
- `InventoryCosArmor` --inherits--> `CAStacksBase`  [EXTRACTED]
  src/main/java/com/skd/armorcosmetic/impl/inventory/InventoryCosArmor.java → src/main/java/com/skd/armorcosmetic/api/inventory/CAStacksBase.java
- `InventoryManagerClient` --inherits--> `InventoryManager`  [EXTRACTED]
  src/main/java/com/skd/armorcosmetic/impl/client/InventoryManagerClient.java → src/main/java/com/skd/armorcosmetic/impl/InventoryManager.java
- `InventoryManager` --references--> `InventoryCosArmor`  [EXTRACTED]
  src/main/java/com/skd/armorcosmetic/impl/InventoryManager.java → src/main/java/com/skd/armorcosmetic/impl/inventory/InventoryCosArmor.java

## Import Cycles
- None detected.

## Communities (26 total, 1 thin omitted)

### Community 0 - "CustomPacketPayload"
Cohesion: 0.11
Nodes (18): CustomPacketPayload, ArmorCosmetic, Logger, FriendlyByteBuf, Override, StreamCodec, Type, PayloadOpenNormalInventory (+10 more)

### Community 1 - "ContainerCosArmor"
Cohesion: 0.10
Nodes (22): CraftingContainer, CraftingInput, CraftingRecipe, Level, RecipeBookMenu, RecipeBookType, RecipeHolder, ResultContainer (+14 more)

### Community 2 - "InventoryCosArmor"
Cohesion: 0.12
Nodes (12): Container, FunctionalInterface, MenuProvider, ContentsChangeListener, HiddenFlagsChangeListener, InventoryCosArmor, AbstractContainerMenu, Component (+4 more)

### Community 3 - "NeoForgeArmorCosmetic.java"
Cohesion: 0.11
Nodes (16): BooleanValue, DeferredHolder, DeferredRegister, FMLClientSetupEvent, FMLCommonSetupEvent, IEventBus, IntValue, Mod (+8 more)

### Community 4 - "CAStacksBase"
Cohesion: 0.12
Nodes (10): CompoundTag, Event, ICancellableEvent, ItemStackHandler, Provider, CosArmorAPI, CosArmorDeathDrops, Player (+2 more)

### Community 5 - "InventoryManager"
Cohesion: 0.12
Nodes (10): LivingDropsEvent, Nonnull, PlayerLoggedInEvent, PlayerLoggedOutEvent, RegisterCommandsEvent, SaveToFile, ServerStoppingEvent, InventoryManager (+2 more)

### Community 6 - "GuiCosArmorButton"
Cohesion: 0.11
Nodes (13): Button, GuiCosArmorButton, Component, GuiGraphics, OnPress, Override, GuiCosArmorToggleButton, Component (+5 more)

### Community 7 - "GuiCosArmorInventory"
Cohesion: 0.18
Nodes (9): EffectRenderingInventoryScreen, RecipeBookComponent, RecipeUpdateListener, GuiCosArmorInventory, Component, GuiGraphics, Inventory, Override (+1 more)

### Community 8 - "PlayerRenderHandler.java"
Cohesion: 0.15
Nodes (16): EquipmentSlot, Inventory, Player, PlayerInventoryHelper, handleLoggedOut(), AbstractClientPlayer, LoggingOut, Override (+8 more)

### Community 9 - "GuiHandler.java"
Cohesion: 0.16
Nodes (11): Field, InventoryScreen, Post, InventoryScreenAccess, GuiHandler, INSTANCE, handleGuiDrawPre(), handleGuiInitPost() (+3 more)

### Community 10 - "PayloadOpenCosArmorInventory"
Cohesion: 0.17
Nodes (11): KeyMapping, handleClientTick(), Pre, KeyHandler, INSTANCE, registerKeyMappings(), FriendlyByteBuf, Override (+3 more)

### Community 11 - "CurseForge — Variables del proyecto"
Cohesion: 0.13
Nodes (14): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, IDs de `gameVersions` para 1.21.1, Parámetros del upload, Proyecto (+6 more)

### Community 12 - "Flujo de trabajo — Armor Cosmetic (NeoForge)"
Cohesion: 0.17
Nodes (11): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Armor Cosmetic (NeoForge), Flujo por tarea, Idioma (+3 more)

### Community 13 - "MixinPlayerRenderer.java"
Cohesion: 0.44
Nodes (7): CallbackInfo, Inject, Mixin, MultiBufferSource, PoseStack, AbstractClientPlayer, MixinPlayerRenderer

### Community 14 - "InventoryManagerClient"
Cohesion: 0.31
Nodes (5): InventoryManagerClient, Inventory, LoadingCache, LoggingOut, Override

### Community 15 - "PayloadSyncHiddenFlags"
Cohesion: 0.33
Nodes (5): FriendlyByteBuf, Override, StreamCodec, Type, PayloadSyncHiddenFlags

### Community 16 - "Armor Cosmetic"
Cohesion: 0.40
Nodes (4): Armor Cosmetic, Build, Features, Requirements

### Community 17 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 18 - "[0.0.0-beta.1] - 2026-08-31"
Cohesion: 0.40
Nodes (4): [0.0.0-beta.1] - 2026-08-31, [0.0.0-beta.2] - 2026-08-31, Added, Fixed

### Community 24 - "PayloadSyncCosArmor"
Cohesion: 0.31
Nodes (6): RegistryFriendlyByteBuf, ItemStack, Override, StreamCodec, Type, PayloadSyncCosArmor

## Knowledge Gaps
- **31 isolated node(s):** `INSTANCE`, `INSTANCE`, `INSTANCE`, `ModConstants`, `Fixed` (+26 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **1 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `InventoryCosArmor` connect `InventoryCosArmor` to `ContainerCosArmor`, `CAStacksBase`, `InventoryManager`, `InventoryManagerClient`, `PayloadSyncHiddenFlags`, `PayloadSyncCosArmor`?**
  _High betweenness centrality (0.180) - this node is a cross-community bridge._
- **Why does `ContainerCosArmor` connect `ContainerCosArmor` to `NeoForgeArmorCosmetic.java`, `InventoryManager`, `InventoryManagerClient`, `GuiCosArmorInventory`?**
  _High betweenness centrality (0.171) - this node is a cross-community bridge._
- **Why does `CAStacksBase` connect `CAStacksBase` to `InventoryCosArmor`?**
  _High betweenness centrality (0.081) - this node is a cross-community bridge._
- **What connects `INSTANCE`, `INSTANCE`, `INSTANCE` to the rest of the system?**
  _31 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `CustomPacketPayload` be split into smaller, more focused modules?**
  _Cohesion score 0.10967741935483871 - nodes in this community are weakly interconnected._
- **Should `ContainerCosArmor` be split into smaller, more focused modules?**
  _Cohesion score 0.10121951219512196 - nodes in this community are weakly interconnected._
- **Should `InventoryCosArmor` be split into smaller, more focused modules?**
  _Cohesion score 0.11587301587301588 - nodes in this community are weakly interconnected._