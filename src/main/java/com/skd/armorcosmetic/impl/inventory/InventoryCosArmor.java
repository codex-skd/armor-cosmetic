package com.skd.armorcosmetic.impl.inventory;

import java.util.ArrayList;
import java.util.Collection;

import com.skd.armorcosmetic.api.inventory.CAStacksBase;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class InventoryCosArmor extends CAStacksBase implements Container, MenuProvider {

    protected static final Component Name = Component.translatable("cos.gui.buttoncos");
    protected static final int MINSIZE = 4;
    protected final Collection<Object> listeners = new ArrayList<>();

    public InventoryCosArmor() {
        super(MINSIZE);
    }

    @Override
    public void clearContent() {
        for (int i = 0; i < getContainerSize(); i++) {
            setItem(i, ItemStack.EMPTY);
        }
    }

    @Override
    public int getContainerSize() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return slot >= 0 && slot < stacks.size() ? stacks.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack stack = getItem(slot);
        if (!stack.isEmpty()) {
            if (stack.getCount() <= amount) {
                setItem(slot, ItemStack.EMPTY);
                return stack;
            } else {
                ItemStack result = stack.split(amount);
                setChanged();
                return result;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack stack = getItem(slot);
        if (!stack.isEmpty()) {
            setItem(slot, ItemStack.EMPTY);
            return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot >= 0 && slot < stacks.size()) {
            stacks.set(slot, stack);
            onContentsChanged(slot);
        }
    }

    @Override
    public void setChanged() {
        for (Object listener : listeners) {
            if (listener instanceof ContentsChangeListener) {
                for (int i = 0; i < getContainerSize(); i++) {
                    ((ContentsChangeListener) listener).onInventoryChanged(this, i);
                }
            }
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public Component getDisplayName() {
        return Name;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new ContainerCosArmor(id, playerInventory, this, player);
    }

    @Override
    protected void onContentsChanged(int slot) {
        for (Object listener : listeners) {
            if (listener instanceof ContentsChangeListener) {
                ((ContentsChangeListener) listener).onInventoryChanged(this, slot);
            }
        }
    }

    public boolean setHidden(String modid, String identifier, boolean hidden) {
        boolean changed = super.setHidden(modid, identifier, hidden);
        if (changed) {
            for (Object listener : listeners) {
                if (listener instanceof HiddenFlagsChangeListener) {
                    ((HiddenFlagsChangeListener) listener).onHiddenFlagsChanged(this, modid, identifier);
                }
            }
        }
        return changed;
    }

    @Override
    protected void onLoad() {
        for (Object listener : listeners) {
            if (listener instanceof ContentsChangeListener) {
                for (int i = 0; i < getContainerSize(); i++) {
                    ((ContentsChangeListener) listener).onInventoryChanged(this, i);
                }
            }
            if (listener instanceof HiddenFlagsChangeListener) {
                for (var entry : hidden.entrySet()) {
                    for (String identifier : entry.getValue()) {
                        ((HiddenFlagsChangeListener) listener).onHiddenFlagsChanged(this, entry.getKey(), identifier);
                    }
                }
            }
        }
    }

    public boolean setUpdateListener(ContentsChangeListener listener) {
        return listeners.add(listener);
    }

    public boolean setUpdateListener(HiddenFlagsChangeListener listener) {
        return listeners.add(listener);
    }

    public interface ContentsChangeListener {
        void onInventoryChanged(InventoryCosArmor inventory, int slot);
    }

    public interface HiddenFlagsChangeListener {
        void onHiddenFlagsChanged(InventoryCosArmor inventory, String modid, String identifier);
    }
}
