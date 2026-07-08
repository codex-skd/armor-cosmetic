package com.skd.armorcosmetic.impl;

import java.io.File;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.skd.armorcosmetic.api.event.CosArmorDeathDrops;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import com.skd.armorcosmetic.impl.network.payload.PayloadSyncCosArmor;
import com.skd.armorcosmetic.impl.network.payload.PayloadSyncHiddenFlags;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.LevelResource;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.SaveToFile;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class InventoryManager {

    protected static final InventoryCosArmor Dummy = new InventoryCosArmor();
    protected static final Random RANDOM = new Random();

    protected final LoadingCache<UUID, InventoryCosArmor> CommonCache = CacheBuilder.newBuilder()
            .build(new CacheLoader<>() {
                @Override
                public InventoryCosArmor load(UUID uuid) {
                    InventoryCosArmor inventory = new InventoryCosArmor();
                    loadInventory(uuid, inventory);
                    inventory.setUpdateListener((InventoryCosArmor.ContentsChangeListener) (inv, slot) -> onInventoryChanged(uuid, inv, slot));
                    inventory.setUpdateListener((InventoryCosArmor.HiddenFlagsChangeListener) (inv, modid, identifier) -> onHiddenFlagsChanged(uuid, inv, modid, identifier));
                    return inventory;
                }
            });

    public InventoryManager() {
    }

    public static boolean checkIdentifier(String modid, String identifier) {
        if (modid == null || modid.isEmpty() || identifier == null || identifier.isEmpty()) {
            return false;
        }
        return ModList.get().isLoaded(modid);
    }

    public ContainerCosArmor createContainerClient(int id, Inventory playerInventory) {
        throw new UnsupportedOperationException();
    }

    public InventoryCosArmor getCosArmorInventory(UUID uuid) {
        if (uuid == null) {
            return Dummy;
        }
        return CommonCache.getUnchecked(uuid);
    }

    public InventoryCosArmor getCosArmorInventoryClient(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    protected File getDataFile(UUID uuid) {
        return ServerLifecycleHooks.getCurrentServer()
                .getWorldPath(LevelResource.PLAYER_DATA_DIR).toFile()
                .toPath().resolve(uuid.toString() + ".cosarmor").toFile();
    }

    private void handlePlayerDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!event.getEntity().isEffectiveAi()) return;
        if (event.getEntity().level() instanceof net.minecraft.server.level.ServerLevel serverLevel
                && serverLevel.getGameRules().get(GameRules.KEEP_INVENTORY)) return;

        InventoryCosArmor inventory = getCosArmorInventory(event.getEntity().getUUID());
        CosArmorDeathDrops deathEvent = new CosArmorDeathDrops((Player) event.getEntity(), inventory);
        NeoForge.EVENT_BUS.post(deathEvent);
        if (deathEvent.isCanceled()) return;

        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i).copy();
            if (stack.isEmpty()) continue;

            float f1 = RANDOM.nextFloat() * 0.75F + 0.125F;
            float f2 = RANDOM.nextFloat() * 0.75F;
            float f3 = RANDOM.nextFloat() * 0.75F + 0.125F;

            while (!stack.isEmpty()) {
                ItemEntity itemEntity = new ItemEntity(
                        event.getEntity().level(),
                        event.getEntity().getX() + f1,
                        event.getEntity().getY() + f2,
                        event.getEntity().getZ() + f3,
                        stack.split(RANDOM.nextInt(21) + 10));
                itemEntity.setDeltaMovement(
                        RANDOM.nextGaussian() * 0.05,
                        RANDOM.nextGaussian() * 0.05 + 0.2,
                        RANDOM.nextGaussian() * 0.05);
                event.getDrops().add(itemEntity);
            }

            inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    private void handlePlayerLoggedIn(PlayerLoggedInEvent event) {
        CommonCache.invalidate(event.getEntity().getUUID());
        getCosArmorInventory(event.getEntity().getUUID());

        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            for (ServerPlayer otherPlayer : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
                if (otherPlayer == serverPlayer) continue;
                UUID otherUUID = otherPlayer.getUUID();
                InventoryCosArmor otherInventory = getCosArmorInventory(otherUUID);
                for (int i = 0; i < otherInventory.getSlots(); i++) {
                    PacketDistributor.sendToPlayer(serverPlayer,
                            new PayloadSyncCosArmor(otherUUID, otherInventory, i));
                }
                otherInventory.forEachHidden((modid, identifier) -> {
                    PacketDistributor.sendToPlayer(serverPlayer,
                            new PayloadSyncHiddenFlags(otherUUID, otherInventory, modid, identifier));
                });
            }
            InventoryCosArmor ownInventory = getCosArmorInventory(serverPlayer.getUUID());
            for (int i = 0; i < ownInventory.getSlots(); i++) {
                PacketDistributor.sendToPlayer(serverPlayer,
                        new PayloadSyncCosArmor(serverPlayer.getUUID(), ownInventory, i));
            }
            ownInventory.forEachHidden((modid, identifier) -> {
                PacketDistributor.sendToPlayer(serverPlayer,
                        new PayloadSyncHiddenFlags(serverPlayer.getUUID(), ownInventory, modid, identifier));
            });
        }
    }

    private void handlePlayerLoggedOut(PlayerLoggedOutEvent event) {
        UUID uuid = event.getEntity().getUUID();
        InventoryCosArmor inventory = CommonCache.getIfPresent(uuid);
        if (inventory != null) {
            saveInventory(uuid, inventory);
            CommonCache.invalidate(uuid);
        }
    }

    private void handleRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("clearcosarmor")
                        .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .executes(ctx -> clearCosArmorForSelf(ctx))
                        .then(Commands.argument("targets", EntityArgument.players())
                                .executes(ctx -> clearCosArmorForTargets(ctx))));

        boolean disableCosHat;
        try {
            disableCosHat = ModConfigs.CosArmorDisableCosHatCommand.get();
        } catch (Exception e) {
            disableCosHat = true;
        }
        if (!disableCosHat) {
            event.getDispatcher().register(
                    Commands.literal("coshat")
                            .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                            .executes(ctx -> setCosHat(ctx)));
        }
    }

    private int clearCosArmorForSelf(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        int count = 0;
        InventoryCosArmor inventory = getCosArmorInventory(player.getUUID());
        for (int i = 0; i < inventory.getSlots(); i++) {
            if (!inventory.getStackInSlot(i).isEmpty()) {
                count++;
                inventory.setStackInSlot(i, ItemStack.EMPTY);
            }
        }
        final int finalCount = count;
        ctx.getSource().sendSuccess(() -> Component.translatable("cos.command.clearcosarmor.success.single", finalCount, player.getDisplayName()), true);
        return finalCount;
    }

    private int clearCosArmorForTargets(CommandContext<CommandSourceStack> ctx) {
        Collection<ServerPlayer> players;
        try {
            players = EntityArgument.getPlayers(ctx, "targets");
        } catch (Exception e) {
            return 0;
        }
        int totalCount = 0;
        for (ServerPlayer player : players) {
            InventoryCosArmor inventory = getCosArmorInventory(player.getUUID());
            for (int i = 0; i < inventory.getSlots(); i++) {
                if (!inventory.getStackInSlot(i).isEmpty()) {
                    totalCount++;
                    inventory.setStackInSlot(i, ItemStack.EMPTY);
                }
            }
        }
        final int count = totalCount;
        ctx.getSource().sendSuccess(() -> Component.translatable("cos.command.clearcosarmor.success.multiple", count, players.size()), true);
        return totalCount;
    }

    private int setCosHat(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        InventoryCosArmor inventory = getCosArmorInventory(player.getUUID());
        ItemStack headSlot = inventory.getStackInSlot(3);
        if (!headSlot.isEmpty()) {
            inventory.setSkinArmor(3, !inventory.isSkinArmor(3));
            ctx.getSource().sendSuccess(() -> Component.literal("Cosmetic hat toggled: " + (inventory.isSkinArmor(3) ? "skin" : "item")), true);
        }
        return 1;
    }

    private void handleSaveToFile(SaveToFile event) {
        UUID uuid = UUID.fromString(event.getPlayerUUID());
        InventoryCosArmor inventory = CommonCache.getIfPresent(uuid);
        if (inventory != null) {
            saveInventory(uuid, inventory);
        }
    }

    private void handleServerStopping(ServerStoppingEvent event) {
        ModObjects.logger.debug("Server is stopping... try to save all still loaded CosmeticArmor data");
        CommonCache.asMap().forEach((uuid, inventory) -> saveInventory(uuid, inventory));
        CommonCache.invalidateAll();
    }

    protected void loadInventory(UUID uuid, InventoryCosArmor inventory) {
        if (inventory == Dummy) return;
        try {
            File file = getDataFile(uuid);
            if (file.exists()) {
                MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
                inventory.deserializeNBT(server.registryAccess(), NbtIo.read(file.toPath()));
            }
        } catch (Exception e) {
            ModObjects.logger.error("Failed to load CosmeticArmor data", e);
        }
    }

    protected void saveInventory(UUID uuid, InventoryCosArmor inventory) {
        try {
            File file = getDataFile(uuid);
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            NbtIo.write(inventory.serializeNBT(server.registryAccess()), file.toPath());
        } catch (Exception e) {
            ModObjects.logger.error("Failed to save CosmeticArmor data", e);
        }
    }

    protected void onHiddenFlagsChanged(UUID uuid, InventoryCosArmor inventory, String modid, String identifier) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server.isDedicatedServer()) {
            PacketDistributor.sendToAllPlayers(new PayloadSyncHiddenFlags(uuid, inventory, modid, identifier));
        } else {
            server.getPlayerList().getPlayers().forEach(player -> {
                player.connection.send(new PayloadSyncHiddenFlags(uuid, inventory, modid, identifier));
            });
        }
    }

    protected void onInventoryChanged(UUID uuid, InventoryCosArmor inventory, int slot) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server.isDedicatedServer()) {
            PacketDistributor.sendToAllPlayers(new PayloadSyncCosArmor(uuid, inventory, slot));
        } else {
            server.getPlayerList().getPlayers().forEach(player -> {
                player.connection.send(new PayloadSyncCosArmor(uuid, inventory, slot));
            });
        }
    }

    public void registerEvents() {
        NeoForge.EVENT_BUS.addListener(this::handlePlayerLoggedIn);
        NeoForge.EVENT_BUS.addListener(this::handlePlayerLoggedOut);
        NeoForge.EVENT_BUS.addListener(this::handleSaveToFile);
        NeoForge.EVENT_BUS.addListener(this::handlePlayerDrops);
        NeoForge.EVENT_BUS.addListener(this::handleRegisterCommands);
        NeoForge.EVENT_BUS.addListener(this::handleServerStopping);
    }

    public void registerEventsClient() {
    }

    // Helper inner interfaces
    @FunctionalInterface
    public interface ContentsChangeListener {
        void onInventoryChanged(InventoryCosArmor inventory, int slot);
    }

    @FunctionalInterface
    public interface HiddenFlagsChangeListener {
        void onHiddenFlagsChanged(InventoryCosArmor inventory, String modid, String identifier);
    }
}
