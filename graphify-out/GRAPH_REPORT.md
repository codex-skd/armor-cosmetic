# Graph Report - .  (2026-07-28)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 353 nodes · 672 edges · 18 communities
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 9 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `46e8e92e`
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

## God Nodes (most connected - your core abstractions)
1. `InventoryCosArmor` - 45 edges
2. `ContainerCosArmor` - 27 edges
3. `InventoryManager` - 26 edges
4. `CAStacksBase` - 21 edges
5. `GuiCosArmorInventory` - 17 edges
6. `ArmorCosmetic` - 14 edges
7. `PayloadOpenCosArmorInventory` - 10 edges
8. `PayloadSetSkinArmor` - 10 edges
9. `PayloadSyncCosArmor` - 10 edges
10. `GuiCosArmorButton` - 9 edges

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

## Communities (18 total, 0 thin omitted)

### Community 0 - "InventoryManager"
Cohesion: 0.09
Nodes (14): CommandContext, CommandSourceStack, FunctionalInterface, LivingDropsEvent, PlayerLoggedInEvent, PlayerLoggedOutEvent, RegisterCommandsEvent, SaveToFile (+6 more)

### Community 1 - "GuiHandler.java"
Cohesion: 0.08
Nodes (20): Button, GuiCosArmorButton, Component, GuiGraphicsExtractor, Identifier, OnPress, Override, GuiCosArmorToggleButton (+12 more)

### Community 2 - "ContainerCosArmor"
Cohesion: 0.13
Nodes (19): AbstractCraftingMenu, CraftingContainer, CraftingRecipe, RecipeBookType, RecipeHolder, ResultContainer, ServerLevel, Slot (+11 more)

### Community 3 - "GuiCosArmorInventory"
Cohesion: 0.12
Nodes (15): AbstractRecipeBookScreen, EffectsInInventory, MouseButtonEvent, ScreenPosition, GuiCosArmorInventory, Component, GuiGraphicsExtractor, Identifier (+7 more)

### Community 4 - "InventoryCosArmor"
Cohesion: 0.15
Nodes (11): Container, MenuProvider, ContentsChangeListener, HiddenFlagsChangeListener, InventoryCosArmor, AbstractContainerMenu, Component, Inventory (+3 more)

### Community 5 - "PlayerRenderHandler.java"
Cohesion: 0.12
Nodes (21): AbstractClientPlayer, Avatar, CallbackInfo, Inject, Mixin, EquipmentSlot, Inventory, Player (+13 more)

### Community 6 - "CAStacksBase"
Cohesion: 0.11
Nodes (11): CompoundTag, Event, ICancellableEvent, ItemStackHandler, Provider, CosArmorAPI, CosArmorDeathDrops, Player (+3 more)

### Community 7 - "CustomPacketPayload"
Cohesion: 0.13
Nodes (16): CustomPacketPayload, FriendlyByteBuf, Override, StreamCodec, Type, PayloadOpenNormalInventory, FriendlyByteBuf, Override (+8 more)

### Community 8 - "ArmorCosmetic.java"
Cohesion: 0.12
Nodes (16): DeferredHolder, DeferredRegister, FMLClientSetupEvent, FMLCommonSetupEvent, IEventBus, PayloadRegistrar, RegisterKeyMappingsEvent, RegisterPayloadHandlersEvent (+8 more)

### Community 9 - "PayloadOpenCosArmorInventory"
Cohesion: 0.17
Nodes (11): KeyMapping, handleClientTick(), Pre, KeyHandler, INSTANCE, registerKeyMappings(), FriendlyByteBuf, Override (+3 more)

### Community 10 - "ModConfigs.java"
Cohesion: 0.23
Nodes (8): BooleanValue, Builder, IntValue, ModConfigSpec, Config, ModContainer, ModContainer, ModConfigs

### Community 11 - "InventoryScreen"
Cohesion: 0.29
Nodes (5): Field, InventoryScreen, Post, handleGuiInitPost(), InventoryScreenAccess

### Community 12 - "PayloadSyncCosArmor"
Cohesion: 0.31
Nodes (6): RegistryFriendlyByteBuf, ItemStack, Override, StreamCodec, Type, PayloadSyncCosArmor

### Community 13 - "InventoryManagerClient"
Cohesion: 0.31
Nodes (5): InventoryManagerClient, Inventory, LoadingCache, LoggingOut, Override

### Community 14 - "ArmorCosmeticClient.java"
Cohesion: 0.60
Nodes (3): ArmorCosmeticClient, Mod, ModContainer

### Community 15 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **3 isolated node(s):** `INSTANCE`, `INSTANCE`, `INSTANCE`
  These have ≤1 connection - possible missing edges or undocumented components.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `InventoryCosArmor` connect `InventoryCosArmor` to `InventoryManager`, `ContainerCosArmor`, `GuiCosArmorInventory`, `CAStacksBase`, `CustomPacketPayload`, `ArmorCosmetic.java`, `PayloadSyncCosArmor`, `InventoryManagerClient`?**
  _High betweenness centrality (0.322) - this node is a cross-community bridge._
- **Why does `ContainerCosArmor` connect `ContainerCosArmor` to `ArmorCosmetic.java`, `InventoryManager`, `GuiCosArmorInventory`, `InventoryManagerClient`?**
  _High betweenness centrality (0.180) - this node is a cross-community bridge._
- **Why does `CAStacksBase` connect `CAStacksBase` to `InventoryCosArmor`?**
  _High betweenness centrality (0.110) - this node is a cross-community bridge._
- **What connects `INSTANCE`, `INSTANCE`, `INSTANCE` to the rest of the system?**
  _3 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `InventoryManager` be split into smaller, more focused modules?**
  _Cohesion score 0.09246088193456614 - nodes in this community are weakly interconnected._
- **Should `GuiHandler.java` be split into smaller, more focused modules?**
  _Cohesion score 0.08095238095238096 - nodes in this community are weakly interconnected._
- **Should `ContainerCosArmor` be split into smaller, more focused modules?**
  _Cohesion score 0.13257575757575757 - nodes in this community are weakly interconnected._