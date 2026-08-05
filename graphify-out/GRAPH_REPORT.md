# Graph Report - 26.2  (2026-08-06)

## Corpus Check
- 58 files · ~115,822 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 402 nodes · 473 edges · 101 communities (30 shown, 71 thin omitted)
- Extraction: 98% EXTRACTED · 2% INFERRED · 0% AMBIGUOUS · INFERRED: 9 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `48ab2455`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- InventoryManager
- GuiHandler.java
- ContainerCosArmor
- GuiCosArmorInventory
- InventoryCosArmor
- PlayerRenderHandler.java
- CAStacksBase
- CustomPacketPayload
- ArmorCosmetic.java
- PayloadOpenCosArmorInventory
- ModConfigs.java
- InventoryScreen
- PayloadSyncCosArmor
- InventoryManagerClient
- ArmorCosmeticClient.java
- gradlew
- build.gradle
- settings.gradle
- CLAUDE.md — armor_cosmetic (26.2)
- gradlew
- Player
- ModContainer
- Component
- GuiGraphicsExtractor
- Identifier
- OnPress
- Override
- Component
- GuiGraphicsExtractor
- Identifier
- Inventory
- Override
- Component
- GuiGraphicsExtractor
- OnPress
- Override
- Pre
- RegisterMenuScreensEvent
- Inventory
- LoadingCache
- LoggingOut
- Override
- Pre
- EquipmentSlot
- Inventory
- Player
- AvatarRenderState
- LoggingOut
- Override
- AbstractContainerMenu
- Container
- EquipmentSlot
- Inventory
- ItemStack
- Override
- Player
- AbstractContainerMenu
- Component
- Inventory
- ItemStack
- Override
- Player
- Inventory
- LoadingCache
- Logger
- MenuType
- FriendlyByteBuf
- Override
- StreamCodec
- Type
- FriendlyByteBuf
- Override
- StreamCodec
- Type
- FriendlyByteBuf
- Override
- StreamCodec
- Type
- FriendlyByteBuf
- Override
- StreamCodec
- Type
- ItemStack
- Override
- StreamCodec
- Type
- FriendlyByteBuf
- Override
- StreamCodec
- Type
- AvatarRenderState

## God Nodes (most connected - your core abstractions)
1. `InventoryManager` - 24 edges
2. `InventoryCosArmor` - 24 edges
3. `CAStacksBase` - 21 edges
4. `GuiCosArmorInventory` - 15 edges
5. `ContainerCosArmor` - 15 edges
6. `ArmorCosmetic` - 14 edges
7. `CurseForge — Variables del proyecto` - 13 edges
8. `Flujo de trabajo — Armor Cosmetic (NeoForge)` - 11 edges
9. `GuiCosArmorButton` - 8 edges
10. `CosArmorDeathDrops` - 7 edges

## Surprising Connections (you probably didn't know these)
- `handleClientTick()` --calls--> `PayloadOpenCosArmorInventory`  [INFERRED]
  src/main/java/com/skd/armorcosmetic/impl/client/KeyHandler.java → src/main/java/com/skd/armorcosmetic/impl/network/payload/PayloadOpenCosArmorInventory.java
- `handleGuiInitPost()` --calls--> `PayloadOpenCosArmorInventory`  [INFERRED]
  src/main/java/com/skd/armorcosmetic/impl/client/GuiHandler.java → src/main/java/com/skd/armorcosmetic/impl/network/payload/PayloadOpenCosArmorInventory.java
- `handleGuiInitPost()` --calls--> `PayloadOpenNormalInventory`  [INFERRED]
  src/main/java/com/skd/armorcosmetic/impl/client/GuiHandler.java → src/main/java/com/skd/armorcosmetic/impl/network/payload/PayloadOpenNormalInventory.java
- `ArmorCosmetic` --references--> `ContainerCosArmor`  [EXTRACTED]
  src/main/java/com/skd/armorcosmetic/ArmorCosmetic.java → src/main/java/com/skd/armorcosmetic/impl/inventory/ContainerCosArmor.java
- `InventoryCosArmor` --inherits--> `CAStacksBase`  [EXTRACTED]
  src/main/java/com/skd/armorcosmetic/impl/inventory/InventoryCosArmor.java → src/main/java/com/skd/armorcosmetic/api/inventory/CAStacksBase.java

## Import Cycles
- None detected.

## Communities (101 total, 71 thin omitted)

### Community 0 - "InventoryManager"
Cohesion: 0.08
Nodes (13): CommandContext, CommandSourceStack, FunctionalInterface, InventoryManagerClient, ContentsChangeListener, HiddenFlagsChangeListener, InventoryManager, LivingDropsEvent (+5 more)

### Community 1 - "GuiHandler.java"
Cohesion: 0.13
Nodes (5): Button, GuiCosArmorButton, GuiCosArmorToggleButton, ICreativeInvWidget, IShiftingWidget

### Community 2 - "ContainerCosArmor"
Cohesion: 0.12
Nodes (12): AbstractCraftingMenu, Container, CraftingContainer, CraftingRecipe, registerMenuScreens(), ContainerCosArmor, ModObjects, RecipeBookType (+4 more)

### Community 3 - "GuiCosArmorInventory"
Cohesion: 0.16
Nodes (5): AbstractRecipeBookScreen, EffectsInInventory, GuiCosArmorInventory, MouseButtonEvent, ScreenPosition

### Community 4 - "InventoryCosArmor"
Cohesion: 0.10
Nodes (6): ContentsChangeListener, HiddenFlagsChangeListener, InventoryCosArmor, ModPayloads, MenuProvider, PayloadRegistrar

### Community 5 - "PlayerRenderHandler.java"
Cohesion: 0.50
Nodes (5): Avatar, CallbackInfo, MixinPlayerRenderer, Inject, Mixin

### Community 6 - "CAStacksBase"
Cohesion: 0.12
Nodes (10): CompoundTag, Event, CosArmorAPI, CosArmorDeathDrops, ICancellableEvent, ItemStackHandler, Provider, CAStacksBase (+2 more)

### Community 7 - "CustomPacketPayload"
Cohesion: 0.10
Nodes (8): CustomPacketPayload, PayloadOpenCosArmorInventory, PayloadOpenNormalInventory, PayloadSetHiddenFlags, PayloadSetSkinArmor, PayloadSyncCosArmor, PayloadSyncHiddenFlags, RegistryFriendlyByteBuf

### Community 8 - "ArmorCosmetic.java"
Cohesion: 0.14
Nodes (14): DeferredHolder, DeferredRegister, FMLCommonSetupEvent, IEventBus, Mod, RegisterKeyMappingsEvent, RegisterPayloadHandlersEvent, ArmorCosmetic (+6 more)

### Community 9 - "PayloadOpenCosArmorInventory"
Cohesion: 0.33
Nodes (5): handleClientTick(), KeyHandler, INSTANCE, registerKeyMappings(), KeyMapping

### Community 10 - "ModConfigs.java"
Cohesion: 0.24
Nodes (7): BooleanValue, Builder, Config, IntValue, ModConfigSpec, ModContainer, ModConfigs

### Community 11 - "InventoryScreen"
Cohesion: 0.18
Nodes (7): Field, GuiHandler, INSTANCE, handleGuiInitPost(), InventoryScreenAccess, InventoryScreen, Post

### Community 12 - "PayloadSyncCosArmor"
Cohesion: 0.50
Nodes (3): [1.0.5] - 2026-08-05, Change, [

### Community 13 - "InventoryManagerClient"
Cohesion: 0.13
Nodes (14): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, IDs de `gameVersions` para 26.2, Parámetros del upload, Proyecto (+6 more)

### Community 14 - "ArmorCosmeticClient.java"
Cohesion: 0.53
Nodes (4): EventBusSubscriber, ArmorCosmeticClient, FMLClientSetupEvent, SubscribeEvent

### Community 15 - "gradlew"
Cohesion: 0.20
Nodes (7): AbstractClientPlayer, PlayerInventoryHelper, onExtractPlayerRenderState(), onFinishPlayerRenderState(), PlayerRenderHandler, INSTANCE, restoreItems()

### Community 16 - "build.gradle"
Cohesion: 0.17
Nodes (11): Buenas prácticas, Commits (Conventional Commits), Convenciones de nomenclatura, Específico del mod, Estructura del proyecto, Flujo de trabajo — Armor Cosmetic (NeoForge), Flujo por tarea, Idioma (+3 more)

### Community 17 - "settings.gradle"
Cohesion: 0.40
Nodes (4): Armor Cosmetic, Build, Features, Requirements

### Community 18 - "CLAUDE.md — armor_cosmetic (26.2)"
Cohesion: 0.50
Nodes (3): CLAUDE.md — armor_cosmetic (26.2), Prioridad de instrucciones, Workflow del mod

### Community 19 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **32 isolated node(s):** `INSTANCE`, `INSTANCE`, `INSTANCE`, `Workflow del mod`, `Prioridad de instrucciones` (+27 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **71 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `ContainerCosArmor` connect `ContainerCosArmor` to `ArmorCosmetic.java`?**
  _High betweenness centrality (0.119) - this node is a cross-community bridge._
- **Why does `InventoryCosArmor` connect `InventoryCosArmor` to `ContainerCosArmor`, `CAStacksBase`?**
  _High betweenness centrality (0.076) - this node is a cross-community bridge._
- **Why does `CAStacksBase` connect `CAStacksBase` to `InventoryCosArmor`?**
  _High betweenness centrality (0.059) - this node is a cross-community bridge._
- **What connects `INSTANCE`, `INSTANCE`, `INSTANCE` to the rest of the system?**
  _32 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `InventoryManager` be split into smaller, more focused modules?**
  _Cohesion score 0.08013937282229965 - nodes in this community are weakly interconnected._
- **Should `GuiHandler.java` be split into smaller, more focused modules?**
  _Cohesion score 0.1286549707602339 - nodes in this community are weakly interconnected._
- **Should `ContainerCosArmor` be split into smaller, more focused modules?**
  _Cohesion score 0.12333333333333334 - nodes in this community are weakly interconnected._