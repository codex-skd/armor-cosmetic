package com.skd.armorcosmetic.impl.inventory;

import com.skd.armorcosmetic.impl.ModObjects;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

public class ContainerCosArmor extends RecipeBookMenu<CraftingInput, CraftingRecipe> {

    private static final Map<EquipmentSlot, ResourceLocation> TEXTURE_EMPTY_SLOTS = Map.of(EquipmentSlot.FEET, InventoryMenu.EMPTY_ARMOR_SLOT_BOOTS, EquipmentSlot.LEGS, InventoryMenu.EMPTY_ARMOR_SLOT_LEGGINGS, EquipmentSlot.CHEST, InventoryMenu.EMPTY_ARMOR_SLOT_CHESTPLATE, EquipmentSlot.HEAD, InventoryMenu.EMPTY_ARMOR_SLOT_HELMET);
    private static final EquipmentSlot[] SLOT_IDS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 2, 2);
    private final ResultContainer resultSlots = new ResultContainer();
    private final Player player;

    public ContainerCosArmor(int containerId, Inventory invPlayer) {
        this(invPlayer, ModObjects.invMan.getCosArmorInventory(invPlayer.player.getUUID()), invPlayer.player, containerId);
    }

    public ContainerCosArmor(Inventory invPlayer, InventoryCosArmor invCosArmor, Player player, int windowId) {
        super(ModObjects.getTypeContainerCosArmor(), windowId);
        this.player = player;

        this.addSlot(new ResultSlot(player, craftSlots, resultSlots, 0, 154, 28));

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                addSlot(new Slot(craftSlots, j + i * 2, 98 + j * 18, 18 + i * 18));
            }
        }

        for (int k = 0; k < 4; ++k) {
            final EquipmentSlot equipmentslottype = SLOT_IDS[k];
            addSlot(new Slot(invPlayer, 39 - k, 8, 8 + k * 18) {
                @Override
                public void setByPlayer(ItemStack pNewStack, ItemStack pOldStack) {
                    player.onEquipItem(equipmentslottype, pOldStack, pNewStack);
                    super.setByPlayer(pNewStack, pOldStack);
                }
                @Override
                public boolean mayPickup(Player playerIn) {
                    ItemStack itemstack = getItem();
                    return (itemstack.isEmpty() || playerIn.isCreative() || !EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) && super.mayPickup(playerIn);
                }
                @Override
                @Nullable
                @OnlyIn(Dist.CLIENT)
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, TEXTURE_EMPTY_SLOTS.get(equipmentslottype));
                }
                @Override
                public int getMaxStackSize() { return 1; }
                @Override
                public boolean mayPlace(ItemStack stack) { return stack.canEquip(equipmentslottype, player); }
            });
        }

        for (int k = 0; k < 3; k++) {
            for (int i1 = 0; i1 < 9; i1++) {
                addSlot(new Slot(invPlayer, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }
        for (int l = 0; l < 9; l++) {
            addSlot(new Slot(invPlayer, l, 8 + l * 18, 142));
        }

        addSlot(new Slot(invPlayer, 40, 77, 62) {
            @Override
            public void setByPlayer(ItemStack pNewStack, ItemStack pOldStack) {
                player.onEquipItem(EquipmentSlot.OFFHAND, pOldStack, pNewStack);
                super.setByPlayer(pNewStack, pOldStack);
            }
            @Override
            @Nullable
            @OnlyIn(Dist.CLIENT)
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() { return Pair.of(InventoryMenu.BLOCK_ATLAS, InventoryMenu.EMPTY_ARMOR_SLOT_SHIELD); }
        });

        for (int i = 0; i < 4; i++) {
            final EquipmentSlot equipmentslottype = SLOT_IDS[i];
            addSlot(new Slot(invCosArmor, 3 - i, 98 + i * 18, 62) {
                @Override
                @Nullable
                @OnlyIn(Dist.CLIENT)
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() { return Pair.of(InventoryMenu.BLOCK_ATLAS, TEXTURE_EMPTY_SLOTS.get(equipmentslottype)); }
                @Override
                public int getMaxStackSize() { return 1; }
                @Override
                public boolean mayPlace(ItemStack stack) { return stack.canEquip(equipmentslottype, player); }
            });
        }
    }

    private static void slotChangedCraftingGrid(AbstractContainerMenu pMenu, Level pLevel, Player pPlayer, CraftingContainer pCraftSlots, ResultContainer pResultSlots, @Nullable RecipeHolder<CraftingRecipe> pRecipe) {
        if (!pLevel.isClientSide) {
            CraftingInput craftinginput = pCraftSlots.asCraftInput();
            ServerPlayer serverplayer = (ServerPlayer) pPlayer;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<RecipeHolder<CraftingRecipe>> optional = pLevel.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftinginput, pLevel, pRecipe);
            if (optional.isPresent()) {
                RecipeHolder<CraftingRecipe> recipeholder = optional.get();
                CraftingRecipe craftingrecipe = recipeholder.value();
                if (pResultSlots.setRecipeUsed(pLevel, serverplayer, recipeholder)) {
                    ItemStack itemstack1 = craftingrecipe.assemble(craftinginput, pLevel.registryAccess());
                    if (itemstack1.isItemEnabled(pLevel.enabledFeatures())) {
                        itemstack = itemstack1;
                    }
                }
            }
            pResultSlots.setItem(0, itemstack);
            pMenu.setRemoteSlot(0, itemstack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(pMenu.containerId, pMenu.incrementStateId(), 0, itemstack));
        }
    }

    @Override
    public void slotsChanged(Container inventoryIn) {
        slotChangedCraftingGrid(this, player.level(), player, craftSlots, resultSlots, null);
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        resultSlots.clearContent();
        if (!playerIn.level().isClientSide)
            clearContainer(playerIn, craftSlots);
    }

    @Override
    public boolean stillValid(Player playerIn) { return true; }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            EquipmentSlot desiredSlot = player.getEquipmentSlotForItem(stack);
            if (slotIndex == 0) {
                if (!moveItemStackTo(stack1, 9, 45, true)) return ItemStack.EMPTY;
                slot.onQuickCraft(stack1, stack);
            } else if (slotIndex >= 1 && slotIndex < 5) {
                if (!moveItemStackTo(stack1, 9, 45, false)) return ItemStack.EMPTY;
            } else if (slotIndex >= 5 && slotIndex < 9) {
                if (!moveItemStackTo(stack1, 9, 45, false)) return ItemStack.EMPTY;
            } else if (slotIndex >= 46 && slotIndex < 50) {
                if (!moveItemStackTo(stack1, 9, 45, false)) return ItemStack.EMPTY;
            } else if (desiredSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && !slots.get(8 - desiredSlot.getIndex()).hasItem()) {
                int j = 8 - desiredSlot.getIndex();
                if (!moveItemStackTo(stack1, j, j + 1, false)) return ItemStack.EMPTY;
            } else if (desiredSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && !slots.get(49 - desiredSlot.getIndex()).hasItem()) {
                int j = 49 - desiredSlot.getIndex();
                if (!moveItemStackTo(stack1, j, j + 1, false)) return ItemStack.EMPTY;
            } else if (slotIndex >= 9 && slotIndex < 36) {
                if (!moveItemStackTo(stack1, 36, 45, false)) return ItemStack.EMPTY;
            } else if (slotIndex >= 36 && slotIndex < 45) {
                if (!moveItemStackTo(stack1, 9, 36, false)) return ItemStack.EMPTY;
            } else if (!moveItemStackTo(stack1, 9, 45, false)) {
                return ItemStack.EMPTY;
            }
            if (stack1.isEmpty()) slot.set(ItemStack.EMPTY); else slot.setChanged();
            if (stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, stack1);
            if (slotIndex == 0) player.drop(stack1, false);
        }
        return stack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slotIn) { return slotIn.container != resultSlots && super.canTakeItemForPickAll(stack, slotIn); }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents helper) { craftSlots.fillStackedContents(helper); }

    @Override
    public void clearCraftingContent() { craftSlots.clearContent(); resultSlots.clearContent(); }

    @Override
    public boolean recipeMatches(RecipeHolder<CraftingRecipe> recipe) { return recipe.value().matches(craftSlots.asCraftInput(), player.level()); }

    @Override
    public int getResultSlotIndex() { return 0; }

    @Override
    public int getGridWidth() { return 2; }

    @Override
    public int getGridHeight() { return 2; }

    @Override
    public int getSize() { return 5; }

    @Override
    public RecipeBookType getRecipeBookType() { return RecipeBookType.CRAFTING; }

    @Override
    public boolean shouldMoveToInventory(int slotIndex) { return slotIndex != getResultSlotIndex(); }
}
