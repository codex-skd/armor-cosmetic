package com.skd.armorcosmetic.impl;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfigs {

    public static ModConfigSpec.BooleanValue CosArmorGuiButton_Hidden;
    public static ModConfigSpec.IntValue CosArmorGuiButton_Left;
    public static ModConfigSpec.IntValue CosArmorGuiButton_Top;
    public static ModConfigSpec.IntValue CosArmorGuiButton_Width;
    public static ModConfigSpec.IntValue CosArmorGuiButton_Height;
    public static ModConfigSpec.BooleanValue CosArmorToggleButton_Hidden;
    public static ModConfigSpec.IntValue CosArmorToggleButton_Left;
    public static ModConfigSpec.IntValue CosArmorToggleButton_Top;
    public static ModConfigSpec.IntValue CosArmorToggleButton_Width;
    public static ModConfigSpec.IntValue CosArmorToggleButton_Height;
    public static ModConfigSpec.BooleanValue CosArmorCreativeGuiButton_Hidden;
    public static ModConfigSpec.IntValue CosArmorCreativeGuiButton_Left;
    public static ModConfigSpec.IntValue CosArmorCreativeGuiButton_Top;
    public static ModConfigSpec.IntValue CosArmorCreativeGuiButton_Width;
    public static ModConfigSpec.IntValue CosArmorCreativeGuiButton_Height;
    public static ModConfigSpec.BooleanValue CosArmorDisableRecipeBook;
    public static ModConfigSpec.BooleanValue CosArmorDisableCosHatCommand;
    public static ModConfigSpec.IntValue SkinArmorToggle_Left;
    public static ModConfigSpec.IntValue SkinArmorToggle_Top;
    public static ModConfigSpec.IntValue SkinArmorToggle_Spacing;
    public static ModConfigSpec.IntValue SkinArmorToggle_Width;
    public static ModConfigSpec.IntValue SkinArmorToggle_Height;

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    static {
        CosArmorGuiButton_Hidden = BUILDER
                .comment("Hide the cosmetic armor button on the normal inventory screen")
                .define("CosArmorGuiButton_Hidden", false);
        CosArmorGuiButton_Left = BUILDER
                .comment("Left offset for the cosmetic armor button")
                .defineInRange("CosArmorGuiButton_Left", 76, -1000, 1000);
        CosArmorGuiButton_Top = BUILDER
                .comment("Top offset for the cosmetic armor button")
                .defineInRange("CosArmorGuiButton_Top", 7, -1000, 1000);
        CosArmorGuiButton_Width = BUILDER
                .comment("Width of the cosmetic armor button")
                .defineInRange("CosArmorGuiButton_Width", 10, 10, 200);
        CosArmorGuiButton_Height = BUILDER
                .comment("Height of the cosmetic armor button")
                .defineInRange("CosArmorGuiButton_Height", 10, 8, 40);
        CosArmorToggleButton_Hidden = BUILDER
                .comment("Hide the toggle button")
                .define("CosArmorToggleButton_Hidden", true);
        CosArmorToggleButton_Left = BUILDER
                .comment("Left offset for the toggle button")
                .defineInRange("CosArmorToggleButton_Left", 76, -1000, 1000);
        CosArmorToggleButton_Top = BUILDER
                .comment("Top offset for the toggle button")
                .defineInRange("CosArmorToggleButton_Top", 22, -1000, 1000);
        CosArmorToggleButton_Width = BUILDER
                .comment("Width of the toggle button")
                .defineInRange("CosArmorToggleButton_Width", 10, 6, 60);
        CosArmorToggleButton_Height = BUILDER
                .comment("Height of the toggle button")
                .defineInRange("CosArmorToggleButton_Height", 10, 6, 40);
        CosArmorCreativeGuiButton_Hidden = BUILDER
                .comment("Hide the cosmetic armor button on the creative inventory screen")
                .define("CosArmorCreativeGuiButton_Hidden", true);
        CosArmorCreativeGuiButton_Left = BUILDER
                .comment("Left offset for the cosmetic armor button on the creative inventory screen")
                .defineInRange("CosArmorCreativeGuiButton_Left", 127, -1000, 1000);
        CosArmorCreativeGuiButton_Top = BUILDER
                .comment("Top offset for the cosmetic armor button on the creative inventory screen")
                .defineInRange("CosArmorCreativeGuiButton_Top", 7, -1000, 1000);
        CosArmorCreativeGuiButton_Width = BUILDER
                .comment("Width of the creative inventory button")
                .defineInRange("CosArmorCreativeGuiButton_Width", 60, 10, 200);
        CosArmorCreativeGuiButton_Height = BUILDER
                .comment("Height of the creative inventory button")
                .defineInRange("CosArmorCreativeGuiButton_Height", 12, 8, 40);
        CosArmorDisableRecipeBook = BUILDER
                .comment("Disable the recipe book button on the cosmetic armor inventory screen")
                .define("CosArmorDisableRecipeBook", true);
        CosArmorDisableCosHatCommand = BUILDER
                .comment("Disable the /coshat command")
                .define("CosArmorDisableCosHatCommand", true);
        SkinArmorToggle_Left = BUILDER
                .comment("Left offset for skin armor toggle buttons (per slot)")
                .defineInRange("SkinArmorToggle_Left", 98, -1000, 1000);
        SkinArmorToggle_Top = BUILDER
                .comment("Top offset for skin armor toggle buttons (per slot)")
                .defineInRange("SkinArmorToggle_Top", 55, -1000, 1000);
        SkinArmorToggle_Spacing = BUILDER
                .comment("Horizontal spacing between skin armor toggle buttons")
                .defineInRange("SkinArmorToggle_Spacing", 18, 0, 100);
        SkinArmorToggle_Width = BUILDER
                .comment("Width of skin armor toggle buttons")
                .defineInRange("SkinArmorToggle_Width", 5, 4, 40);
        SkinArmorToggle_Height = BUILDER
                .comment("Height of skin armor toggle buttons")
                .defineInRange("SkinArmorToggle_Height", 5, 4, 40);
    }

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static void registerConfigs(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, SPEC);
    }
}
