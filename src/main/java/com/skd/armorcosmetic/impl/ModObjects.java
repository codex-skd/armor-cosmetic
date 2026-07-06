package com.skd.armorcosmetic.impl;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.skd.armorcosmetic.ArmorCosmetic;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModObjects {

    public static final Logger logger = LogUtils.getLogger();
    public static final Object invMan;

    static {
        Object manager;
        try {
            Class<?> clientClass = Class.forName("com.skd.armorcosmetic.impl.client.InventoryManagerClient");
            manager = clientClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            manager = new com.skd.armorcosmetic.impl.InventoryManager();
        }
        invMan = manager;
    }

    public static MenuType<ContainerCosArmor> getTypeContainerCosArmor() {
        return ArmorCosmetic.typeContainerCosArmor.get();
    }
}
