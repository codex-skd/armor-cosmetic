package com.skd.armorcosmetic.impl.client.gui;

import com.skd.armorcosmetic.ArmorCosmetic;
import com.skd.armorcosmetic.impl.ModConfigs;
import com.skd.armorcosmetic.impl.ModObjects;
import com.skd.armorcosmetic.impl.inventory.ContainerCosArmor;
import com.skd.armorcosmetic.impl.inventory.InventoryCosArmor;
import com.skd.armorcosmetic.impl.network.payload.PayloadSetSkinArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class GuiCosArmorInventory extends EffectRenderingInventoryScreen<ContainerCosArmor> implements RecipeUpdateListener {

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ArmorCosmetic.MODID, "textures/gui/cosarmorinventory.png");
    private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
    public float oldMouseX;
    public float oldMouseY;
    private boolean useMousePos;
    private boolean buttonClicked;
    private boolean widthTooNarrow;

    public GuiCosArmorInventory(ContainerCosArmor container, Inventory invPlayer, Component displayName) {
        super(container, invPlayer, Component.translatable("container.crafting"));
        titleLabelX = 97;
    }

    @Override
    protected void init() {
        super.init();
        widthTooNarrow = width < 379;
        recipeBookComponent.init(width, height, minecraft, widthTooNarrow, menu);
        leftPos = recipeBookComponent.updateScreenPosition(width, imageWidth);
        if (!ModConfigs.CosArmorDisableRecipeBook.get()) {
            addRenderableWidget(new ImageButton(leftPos + 5, height / 2 - 49, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, b -> {
                recipeBookComponent.toggleVisibility();
                leftPos = recipeBookComponent.updateScreenPosition(width, imageWidth);
                b.setPosition(leftPos + 5, height / 2 - 49);
            }));
        }
        addWidget(recipeBookComponent);
        InventoryCosArmor invCosArmor = ModObjects.invMan.getCosArmorInventoryClient(minecraft.player.getUUID());
        for (int i = 0; i < 4; i++) {
            int j = 3 - i;
            addRenderableWidget(new GuiCosArmorToggleButton(leftPos + 97 + 18 * i, topPos + 61, 5, 5, Component.empty(), invCosArmor.isSkinArmor(j) ? 1 : 0, button -> {
                InventoryCosArmor inv = ModObjects.invMan.getCosArmorInventoryClient(minecraft.player.getUUID());
                inv.setSkinArmor(j, !inv.isSkinArmor(j));
                ((GuiCosArmorToggleButton) button).state = inv.isSkinArmor(j) ? 1 : 0;
                PacketDistributor.sendToServer(new PayloadSetSkinArmor(j, inv.isSkinArmor(j)));
            }));
        }
        smoothTransition();
    }

    @Override
    public void containerTick() {
        super.containerTick();
        recipeBookComponent.tick();
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(font, title, titleLabelX, titleLabelY, 4210752, false);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (recipeBookComponent.isVisible() && widthTooNarrow) {
            renderBackground(graphics, mouseX, mouseY, partialTick);
            recipeBookComponent.render(graphics, mouseX, mouseY, partialTick);
        } else {
            super.render(graphics, mouseX, mouseY, partialTick);
            recipeBookComponent.render(graphics, mouseX, mouseY, partialTick);
            recipeBookComponent.renderGhostRecipe(graphics, leftPos, topPos, false, partialTick);
        }
        renderTooltip(graphics, mouseX, mouseY);
        oldMouseX = (float) mouseX;
        oldMouseY = (float) mouseY;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int i = leftPos;
        int j = topPos;
        graphics.blit(TEXTURE, i, j, 0, 0, imageWidth, imageHeight, 256, 256);
        if (useMousePos) {
            oldMouseX = (float) mouseX;
            oldMouseY = (float) mouseY;
            useMousePos = false;
        }
        InventoryScreen.renderEntityInInventoryFollowsMouse(graphics, i + 26, j + 8, i + 75, j + 78, 30, 0.0625F, oldMouseX, oldMouseY, minecraft.player);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (recipeBookComponent.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        return widthTooNarrow && recipeBookComponent.isVisible() ? true : super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (buttonClicked) {
            buttonClicked = false;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int left, int top, int button) {
        boolean flag = mouseX < left || mouseY < top || mouseX >= left + imageWidth || mouseY >= top + imageHeight;
        return flag && recipeBookComponent.hasClickedOutside(mouseX, mouseY, left, top, imageWidth, imageHeight, button);
    }

    @Override
    protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
        return !widthTooNarrow || !recipeBookComponent.isVisible() ? super.isHovering(x, y, width, height, mouseX, mouseY) : false;
    }

    @Override
    public void recipesUpdated() {
        recipeBookComponent.recipesUpdated();
    }

    @Override
    public RecipeBookComponent getRecipeBookComponent() {
        return recipeBookComponent;
    }

    private void smoothTransition() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen instanceof InventoryScreen) {
            oldMouseX = InventoryScreenAccess.getXMouse((InventoryScreen) mc.screen);
            oldMouseY = InventoryScreenAccess.getYMouse((InventoryScreen) mc.screen);
        } else if (mc.screen instanceof CreativeModeInventoryScreen) {
            useMousePos = true;
        }
    }
}
