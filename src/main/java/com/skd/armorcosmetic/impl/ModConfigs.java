package com.skd.armorcosmetic.impl;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfigs {

    public static ModConfigSpec.BooleanValue CosArmorGuiButton_Hidden;
    public static ModConfigSpec.IntValue CosArmorGuiButton_Left;
    public static ModConfigSpec.IntValue CosArmorGuiButton_Top;
    public static ModConfigSpec.BooleanValue CosArmorToggleButton_Hidden;
    public static ModConfigSpec.IntValue CosArmorToggleButton_Left;
    public static ModConfigSpec.IntValue CosArmorToggleButton_Top;
    public static ModConfigSpec.BooleanValue CosArmorCreativeGuiButton_Hidden;
    public static ModConfigSpec.IntValue CosArmorCreativeGuiButton_Left;
    public static ModConfigSpec.IntValue CosArmorCreativeGuiButton_Top;
    public static ModConfigSpec.BooleanValue CosArmorKeepThroughDeath;
    public static ModConfigSpec.BooleanValue CosArmorDisableRecipeBook;
    public static ModConfigSpec.BooleanValue CosArmorDisableCosHatCommand;

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static {
        CosArmorGuiButton_Hidden = BUILDER
                .comment("Hide the cosmetic armor button on the normal inventory screen")
                .define("CosArmorGuiButton_Hidden", false);
        CosArmorGuiButton_Left = BUILDER
                .comment("Left offset for the cosmetic armor button on the normal inventory screen")
                .defineInRange("CosArmorGuiButton_Left", 76, -1000, 1000);
        CosArmorGuiButton_Top = BUILDER
                .comment("Top offset for the cosmetic armor button on the normal inventory screen")
                .defineInRange("CosArmorGuiButton_Top", 7, -1000, 1000);
        CosArmorToggleButton_Hidden = BUILDER
                .comment("Hide the toggle button")
                .define("CosArmorToggleButton_Hidden", false);
        CosArmorToggleButton_Left = BUILDER
                .comment("Left offset for the toggle button")
                .defineInRange("CosArmorToggleButton_Left", 152, -1000, 1000);
        CosArmorToggleButton_Top = BUILDER
                .comment("Top offset for the toggle button")
                .defineInRange("CosArmorToggleButton_Top", 17, -1000, 1000);
        CosArmorCreativeGuiButton_Hidden = BUILDER
                .comment("Hide the cosmetic armor button on the creative inventory screen")
                .define("CosArmorCreativeGuiButton_Hidden", false);
        CosArmorCreativeGuiButton_Left = BUILDER
                .comment("Left offset for the cosmetic armor button on the creative inventory screen")
                .defineInRange("CosArmorCreativeGuiButton_Left", 127, -1000, 1000);
        CosArmorCreativeGuiButton_Top = BUILDER
                .comment("Top offset for the cosmetic armor button on the creative inventory screen")
                .defineInRange("CosArmorCreativeGuiButton_Top", 7, -1000, 1000);
        CosArmorKeepThroughDeath = BUILDER
                .comment("Keep cosmetic armor through death")
                .define("CosArmorKeepThroughDeath", false);
        CosArmorDisableRecipeBook = BUILDER
                .comment("Disable the recipe book button on the cosmetic armor inventory screen")
                .define("CosArmorDisableRecipeBook", false);
        CosArmorDisableCosHatCommand = BUILDER
                .comment("Disable the /coshat command")
                .define("CosArmorDisableCosHatCommand", false);
    }

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static void registerConfigs(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, SPEC);
    }
}
