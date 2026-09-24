package com.skd.armorcosmetic.impl.client;

import java.util.UUID;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.skd.armorcosmetic.impl.InventoryManager;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.common.NeoForge;

public class InventoryManagerClient extends InventoryManager {

    protected final LoadingCache<UUID, InventoryCosArmor> ClientCache = CacheBuilder.newBuilder()
            .build(new CacheLoader<>() {
                @Override
                public InventoryCosArmor load(UUID uuid) {
                    return new InventoryCosArmor();
                }
            });

    @Override
    public ContainerCosArmor createContainerClient(int id, Inventory playerInventory) {
        Minecraft mc = Minecraft.getInstance();
        return new ContainerCosArmor(id, playerInventory, getCosArmorInventoryClient(mc.player.getUUID()), mc.player);
    }

    @Override
    public InventoryCosArmor getCosArmorInventoryClient(UUID uuid) {
        if (uuid == null) {
            return Dummy;
        }
        return ClientCache.getUnchecked(uuid);
    }

    private void handleLoggedOut(ClientPlayerNetworkEvent.LoggingOut event) {
        ClientCache.invalidateAll();
    }

    @Override
    public void registerEventsClient() {
        NeoForge.EVENT_BUS.addListener(this::handleLoggedOut);
    }
}
