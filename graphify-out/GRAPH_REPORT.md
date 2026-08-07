# Graph Report - 26.2  (2026-08-07)

## Corpus Check
- 67 files · ~117,932 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 426 nodes · 529 edges · 100 communities (35 shown, 65 thin omitted)
- Extraction: 98% EXTRACTED · 2% INFERRED · 0% AMBIGUOUS · INFERRED: 11 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `c65d34ce`
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
- Override
- Component
- GuiGraphicsExtractor
- OnPress
- Override
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
1. `InventoryCosArmor` - 26 edges
2. `InventoryManager` - 24 edges
3. `CAStacksBase` - 21 edges
4. `GuiCosArmorInventory` - 19 edges
5. `ContainerCosArmor` - 19 edges
6. `ArmorCosmetic` - 14 edges
7. `CurseForge — Variables del proyecto` - 13 edges
8. `Flujo de trabajo — Armor Cosmetic (NeoForge)` - 11 edges
9. `GuiCosArmorButton` - 8 edges
10. `CosArmorDeathDrops` - 7 edges

## Surprising Connections (you probably didn't know these)
- `handleClientTick()` --calls--> `PayloadOpenCosArmorInventory`  [INFERRED]
  src/main/java/com/skd/armorcosmetic/impl/client/KeyHandler.java → src/main/java/com/skd/armorcosmetic/impl/network/payload/PayloadOpenCosArmorInventory.java
- `ArmorCosmetic` --references--> `ContainerCosArmor`  [EXTRACTED]
  src/main/java/com/skd/armorcosmetic/ArmorCosmetic.java → src/main/java/com/skd/armorcosmetic/impl/inventory/ContainerCosArmor.java
- `InventoryCosArmor` --inherits--> `CAStacksBase`  [EXTRACTED]
  src/main/java/com/skd/armorcosmetic/impl/inventory/InventoryCosArmor.java → src/main/java/com/skd/armorcosmetic/api/inventory/CAStacksBase.java
- `handleGuiInitPost()` --calls--> `PayloadOpenCosArmorInventory`  [INFERRED]
  src/main/java/com/skd/armorcosmetic/impl/client/GuiHandler.java → src/main/java/com/skd/armorcosmetic/impl/network/payload/PayloadOpenCosArmorInventory.java
- `handleGuiInitPost()` --calls--> `PayloadOpenNormalInventory`  [INFERRED]
  src/main/java/com/skd/armorcosmetic/impl/client/GuiHandler.java → src/main/java/com/skd/armorcosmetic/impl/network/payload/PayloadOpenNormalInventory.java

## Import Cycles
- None detected.

## Communities (100 total, 65 thin omitted)

### Community 0 - "InventoryManager"
Cohesion: 0.08
Nodes (13): CommandContext, CommandSourceStack, FunctionalInterface, InventoryManagerClient, ContentsChangeListener, HiddenFlagsChangeListener, InventoryManager, LivingDropsEvent (+5 more)

### Community 1 - "GuiHandler.java"
Cohesion: 0.13
Nodes (5): Button, GuiCosArmorButton, GuiCosArmorToggleButton, ICreativeInvWidget, IShiftingWidget

### Community 2 - "ContainerCosArmor"
Cohesion: 0.13
Nodes (11): AbstractCraftingMenu, Container, CraftingContainer, CraftingRecipe, ContainerCosArmor, ModObjects, RecipeBookType, RecipeHolder (+3 more)

### Community 3 - "GuiCosArmorInventory"
Cohesion: 0.15
Nodes (14): AbstractRecipeBookScreen, Builder, EffectsInInventory, EntityRenderState, LivingEntity, MouseButtonEvent, ScreenPosition, GuiCosArmorInventory (+6 more)

### Community 4 - "InventoryCosArmor"
Cohesion: 0.14
Nodes (4): ContentsChangeListener, HiddenFlagsChangeListener, InventoryCosArmor, MenuProvider

### Community 5 - "PlayerRenderHandler.java"
Cohesion: 0.50
Nodes (5): Avatar, CallbackInfo, MixinPlayerRenderer, Inject, Mixin

### Community 6 - "CAStacksBase"
Cohesion: 0.11
Nodes (10): CompoundTag, Event, CosArmorAPI, CosArmorDeathDrops, ICancellableEvent, ItemStackHandler, Provider, CAStacksBase (+2 more)

### Community 7 - "CustomPacketPayload"
Cohesion: 0.09
Nodes (10): CustomPacketPayload, PayloadOpenCosArmorInventory, PayloadOpenNormalInventory, PayloadSetHiddenFlags, PayloadSetSkinArmor, PayloadSyncCosArmor, PayloadSyncHiddenFlags, Post (+2 more)

### Community 8 - "ArmorCosmetic.java"
Cohesion: 0.11
Nodes (16): DeferredHolder, DeferredRegister, FMLCommonSetupEvent, IEventBus, Mod, PayloadRegistrar, RegisterKeyMappingsEvent, RegisterPayloadHandlersEvent (+8 more)

### Community 9 - "PayloadOpenCosArmorInventory"
Cohesion: 0.33
Nodes (5): handleClientTick(), KeyHandler, INSTANCE, registerKeyMappings(), KeyMapping

### Community 10 - "ModConfigs.java"
Cohesion: 0.14
Nodes (12): BooleanValue, Config, IntValue, ModConfigSpec, GuiHandler, INSTANCE, handleGuiDrawPre(), Pre (+4 more)

### Community 11 - "InventoryScreen"
Cohesion: 0.33
Nodes (3): Field, InventoryScreenAccess, InventoryScreen

### Community 12 - "PayloadSyncCosArmor"
Cohesion: 0.11
Nodes (17): [1.0.10] - 2026-08-07, [1.0.5] - 2026-08-05, [1.0.6] - 2026-08-07, [1.0.7] - 2026-08-07, [1.0.8] - 2026-08-07, [1.0.9] - 2026-08-07, Change, Enhancement (+9 more)

### Community 13 - "InventoryManagerClient"
Cohesion: 0.13
Nodes (14): Changelog, CurseForge — Variables del proyecto, Descripcion del proyecto, Estructura del changelog (HTML), Flujo completo, IDs de `gameVersions` para 26.2, Parámetros del upload, Proyecto (+6 more)

### Community 14 - "ArmorCosmeticClient.java"
Cohesion: 0.53
Nodes (4): EventBusSubscriber, ArmorCosmeticClient, FMLClientSetupEvent, SubscribeEvent

### Community 15 - "gradlew"
Cohesion: 0.19
Nodes (6): PlayerInventoryHelper, onExtractPlayerRenderState(), onFinishPlayerRenderState(), PlayerRenderHandler, INSTANCE, restoreItems()

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
- **41 isolated node(s):** `INSTANCE`, `INSTANCE`, `INSTANCE`, `Workflow del mod`, `Prioridad de instrucciones` (+36 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **65 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `InventoryCosArmor` connect `InventoryCosArmor` to `ArmorCosmetic.java`, `ContainerCosArmor`, `GuiCosArmorInventory`, `CAStacksBase`?**
  _High betweenness centrality (0.102) - this node is a cross-community bridge._
- **Why does `CAStacksBase` connect `CAStacksBase` to `InventoryCosArmor`?**
  _High betweenness centrality (0.055) - this node is a cross-community bridge._
- **Why does `ContainerCosArmor` connect `ContainerCosArmor` to `ArmorCosmetic.java`, `GuiCosArmorInventory`?**
  _High betweenness centrality (0.049) - this node is a cross-community bridge._
- **What connects `INSTANCE`, `INSTANCE`, `INSTANCE` to the rest of the system?**
  _41 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `InventoryManager` be split into smaller, more focused modules?**
  _Cohesion score 0.08013937282229965 - nodes in this community are weakly interconnected._
- **Should `GuiHandler.java` be split into smaller, more focused modules?**
  _Cohesion score 0.1286549707602339 - nodes in this community are weakly interconnected._
- **Should `ContainerCosArmor` be split into smaller, more focused modules?**
  _Cohesion score 0.13043478260869565 - nodes in this community are weakly interconnected._