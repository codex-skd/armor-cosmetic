package com.skd.armorcosmetic;

import com.skd.armorcosmetic.impl.ModConfigs;
import net.neoforged.fml.ModContainer;

public class Config {

    public static void registerConfigs(ModContainer container) {
        ModConfigs.registerConfigs(container);
    }
}
