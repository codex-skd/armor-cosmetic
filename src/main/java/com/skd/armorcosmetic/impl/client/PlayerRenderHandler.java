package com.skd.armorcosmetic.impl.client;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.skd.armorcosmetic.impl.ModObjects;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.ArrayDeque;
import java.util.Deque;

public enum PlayerRenderHandler {

    INSTANCE;

    private static final EquipmentSlot[] SLOTS = new EquipmentSlot[]{EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD};

    public static boolean Disabled = false;

    private final LoadingCache<Object, Deque<Runnable>> cache = CacheBuilder.newBuilder().weakKeys().build(new CacheLoader<Object, Deque<Runnable>>() {
        @Override
        public Deque<Runnable> load(Object key) { return new ArrayDeque<>(); }
    });

    public void onPreRender(AbstractClientPlayer player) {
        Deque<Runnable> queue = cache.getUnchecked(player);
        restoreItems(queue);
        Inventory invPlayer = PlayerInventoryHelper.getPlayerInventory(player);
        for (EquipmentSlot slot : SLOTS) {
            PlayerInventoryHelper.getPlayerEquipmentSlotIndex(slot).ifPresent(index -> {
                ItemStack stack = invPlayer.getItem(index);
                queue.add(() -> invPlayer.setItem(index, stack));
            });
        }
        if (Disabled) return;
        InventoryCosArmor invCosArmor = ModObjects.invMan.getCosArmorInventoryClient(player.getUUID());
        for (int i = 0; i < SLOTS.length; i++) {
            if (invCosArmor.isSkinArmor(i))
                PlayerInventoryHelper.getPlayerEquipmentSlotIndex(SLOTS[i]).ifPresent(index -> invPlayer.setItem(index, ItemStack.EMPTY));
            else {
                ItemStack stack = invCosArmor.getStackInSlot(i);
                if (!stack.isEmpty())
                    PlayerInventoryHelper.getPlayerEquipmentSlotIndex(SLOTS[i]).ifPresent(index -> invPlayer.setItem(index, stack));
            }
        }
    }

    public void onPostRender(AbstractClientPlayer player) {
        restoreItems(cache.getUnchecked(player));
    }

    // compatibility shims for old 1.21.5 names
    public void onExtractPlayerRenderState(AbstractClientPlayer player, Object state, float partialTicks) { onPreRender(player); }
    public void onFinishPlayerRenderState(AbstractClientPlayer player, Object state, float partialTicks) { onPostRender(player); }

    private void handleLoggedOut(ClientPlayerNetworkEvent.LoggingOut event) { Disabled = false; }

    public void registerEvents() { NeoForge.EVENT_BUS.addListener(this::handleLoggedOut); }

    private void restoreItems(Deque<Runnable> queue) {
        Runnable r;
        while ((r = queue.poll()) != null) {
            try { r.run(); } catch (Throwable e) { ModObjects.logger.error("Failed in restoring client player items", e); }
        }
    }
}
