package com.skd.armorcosmetic.api;

import com.skd.armorcosmetic.api.inventory.CAStacksBase;
import com.skd.armorcosmetic.impl.ModObjects;

import java.util.UUID;

public class CosArmorAPI {

    /**
     * @param uuid the UniqueID of a player (Use {@link net.minecraft.world.entity.player.Player#getUUID() Player.getUUID()})
     * @return associated {@link CAStacksBase CAStacks} for the input uuid
     */
    public static CAStacksBase getCAStacks(UUID uuid) {
        return ModObjects.invMan.getCosArmorInventory(uuid);
    }

    /**
     * @param uuid the UniqueID of a player (Use {@link net.minecraft.world.entity.player.Player#getUUID() Player.getUUID()})
     * @return associated {@link CAStacksBase CAStacks} for the input uuid on the Client
     * @throws UnsupportedOperationException if called in a DedicatedServer
     */
    public static CAStacksBase getCAStacksClient(UUID uuid) {
        return ModObjects.invMan.getCosArmorInventoryClient(uuid);
    }

}
