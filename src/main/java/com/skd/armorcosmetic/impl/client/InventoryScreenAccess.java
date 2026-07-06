package com.skd.armorcosmetic.impl.client;

import java.lang.reflect.Field;

import net.minecraft.client.gui.screens.inventory.InventoryScreen;

public class InventoryScreenAccess {

    private static final Field fXMouse;
    private static final Field fYMouse;

    static {
        fXMouse = findField(InventoryScreen.class, "xMouse", "field_2797");
        fYMouse = findField(InventoryScreen.class, "yMouse", "field_2798");
    }

    public static float getXMouse(InventoryScreen screen) {
        try {
            return fXMouse.getFloat(screen);
        } catch (Exception e) {
            return 0;
        }
    }

    public static float getYMouse(InventoryScreen screen) {
        try {
            return fYMouse.getFloat(screen);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void setXMouse(InventoryScreen screen, float value) {
        try {
            fXMouse.setFloat(screen, value);
        } catch (Exception ignored) {
        }
    }

    public static void setYMouse(InventoryScreen screen, float value) {
        try {
            fYMouse.setFloat(screen, value);
        } catch (Exception ignored) {
        }
    }

    private static Field findField(Class<?> clazz, String... names) {
        for (String name : names) {
            try {
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException ignored) {
            }
        }
        throw new RuntimeException("Field not found in " + clazz.getName());
    }
}
