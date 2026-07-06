package com.skd.armorcosmetic.impl.client.gui;

import com.skd.armorcosmetic.ArmorCosmetic;
import com.skd.armorcosmetic.impl.ModConfigs;
import com.skd.armorcosmetic.impl.ModObjects;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import com.skd.armorcosmetic.impl.network.payload.PayloadSetSkinArmor;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class GuiCosArmorInventory extends AbstractRecipeBookScreen<ContainerCosArmor> {

    public static final Identifier TEXTURE = ArmorCosmetic.id("textures/gui/cosarmorinventory.png");

    private final EffectsInInventory effects;
    private final boolean recipeBookDisabled;
    public float oldMouseX;
    public float oldMouseY;
    private boolean useMousePos;
    private boolean buttonClicked;

    public GuiCosArmorInventory(ContainerCosArmor menu, Inventory playerInventory, Component title) {
        super(menu, new CraftingRecipeBookComponent(menu), playerInventory, title);
        this.effects = new EffectsInInventory(this);
        this.titleLabelX = 97;
        this.recipeBookDisabled = ModConfigs.CosArmorDisableRecipeBook.get();
    }

    @Override
    protected void init() {
        super.init();
        try {
            java.lang.reflect.Field field = AbstractRecipeBookScreen.class.getDeclaredField("recipeBookComponent");
            field.setAccessible(true);
            RecipeBookComponent comp = (RecipeBookComponent) field.get(this);
            if (comp != null) {
                comp.setVisible(false);
            }
        } catch (Exception ignored) {
        }
        if (menu instanceof ContainerCosArmor container) {
            InventoryCosArmor cosInv = getCosInventory(container);
            if (cosInv != null) {
                for (int i = 0; i < 4; i++) {
                    int invSlot = 3 - i;
                    boolean isSkin = cosInv.isSkinArmor(invSlot);
                    addRenderableWidget(new GuiCosArmorToggleButton(
                            leftPos + ModConfigs.SkinArmorToggle_Left.get() + i * ModConfigs.SkinArmorToggle_Spacing.get(),
                            topPos + ModConfigs.SkinArmorToggle_Top.get(),
                            ModConfigs.SkinArmorToggle_Width.get(),
                            ModConfigs.SkinArmorToggle_Height.get(),
                            Component.empty(),
                            isSkin ? 1 : 0,
                            null, null,
                        btn -> {
                            if (btn instanceof GuiCosArmorToggleButton toggleBtn) {
                                toggleBtn.state = toggleBtn.state == 1 ? 0 : 1;
                                cosInv.setSkinArmor(invSlot, toggleBtn.state == 1);
                                ClientPacketDistributor.sendToServer(new PayloadSetSkinArmor(invSlot, toggleBtn.state == 1));
                            }
                        }));
                }
            }
        }
    }

    private InventoryCosArmor getCosInventory(ContainerCosArmor container) {
        for (int i = 49; i >= 46; i--) {
            if (container.getSlot(i).container instanceof InventoryCosArmor inv) {
                return inv;
            }
        }
        return null;
    }

    @Override
    protected ScreenPosition getRecipeBookButtonPosition() {
        return new ScreenPosition(leftPos + 133, topPos + 62);
    }

    @Override
    protected void onRecipeBookButtonClick() {
        if (recipeBookDisabled) return;
        this.minecraft.setScreen(new net.minecraft.client.gui.screens.inventory.InventoryScreen(this.minecraft.player));
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.text(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        graphics.text(this.font, Component.translatable("cos.gui.cosmeticslots"), leftPos + 104, topPos + 46, 4210752, false);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
        if (useMousePos) {
            oldMouseX = mouseX;
            oldMouseY = mouseY;
        }
    }

    @Override
    public boolean showsActiveEffects() {
        return true;
    }

    @Override
    protected boolean isBiggerResultSlot() {
        return true;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        int x = leftPos;
        int y = topPos;
        graphics.fill(0, 0, width, height, 0xC0101010);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0.0F, 0.0F, imageWidth, imageHeight, 256, 256);
        effects.extractRenderState(graphics, mouseX, mouseY);
    }

    @Override
    public void removed() {
        super.removed();
        InventoryCosArmor cosInv = menu instanceof ContainerCosArmor c ? getCosInventory(c) : null;
        if (cosInv != null) {
            for (int i = 0; i < 4; i++) {
                ClientPacketDistributor.sendToServer(new PayloadSetSkinArmor(i, cosInv.isSkinArmor(i)));
            }
        }
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        buttonClicked = false;
        return super.mouseReleased(event);
    }
}
