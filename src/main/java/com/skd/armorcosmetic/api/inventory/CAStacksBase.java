package com.skd.armorcosmetic.api.inventory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

public class CAStacksBase extends ItemStackHandler {

    protected static final Logger LOGGER = LogUtils.getLogger();
    protected final Map<String, Set<String>> hidden = new HashMap<>();
    protected boolean[] isSkinArmor;

    public CAStacksBase() {
        this(4);
    }

    public CAStacksBase(int size) {
        super(size);
        this.isSkinArmor = new boolean[size];
    }

    public boolean isSkinArmor(int slot) {
        validateSlotIndex(slot);
        return isSkinArmor[slot];
    }

    public void setSkinArmor(int slot, boolean value) {
        validateSlotIndex(slot);
        if (isSkinArmor[slot] == value) {
            return;
        }
        isSkinArmor[slot] = value;
        onContentsChanged(slot);
    }

    public boolean isHidden(String modid, String identifier) {
        return hidden.getOrDefault(modid, Collections.emptySet()).contains(identifier);
    }

    public boolean setHidden(String modid, String identifier, boolean hide) {
        if (hide) {
            return hidden.computeIfAbsent(modid, k -> new HashSet<>()).add(identifier);
        } else {
            return hidden.getOrDefault(modid, Collections.emptySet()).remove(identifier);
        }
    }

    public void forEachHidden(BiConsumer<String, String> consumer) {
        for (String modid : hidden.keySet()) {
            for (String identifier : hidden.get(modid)) {
                consumer.accept(modid, identifier);
            }
        }
    }

    @Override
    public void setSize(int size) {
        super.setSize(size);
        this.isSkinArmor = new boolean[size];
    }

    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        ListTag items = new ListTag();
        for (int i = 0; i < stacks.size(); i++) {
            ItemStack stack = stacks.get(i);
            if (!stack.isEmpty() || isSkinArmor[i]) {
                CompoundTag tag = new CompoundTag();
                tag.putInt("Slot", i);
                if (!stack.isEmpty()) {
                    Tag stackTag = ItemStack.CODEC.encodeStart(NbtOps.INSTANCE, stack).getOrThrow();
                    tag = (CompoundTag) stackTag;
                    tag.putInt("Slot", i);
                }
                if (isSkinArmor[i]) {
                    tag.putBoolean("isSkinArmor", true);
                }
                items.add(tag);
            }
        }
        CompoundTag result = new CompoundTag();
        result.put("Items", items);
        result.putInt("Size", stacks.size());
        String hiddenStr = hidden.entrySet().stream()
                .map(entry -> entry.getValue().stream()
                        .map(identifier -> entry.getKey() + ":" + identifier)
                        .collect(Collectors.joining("\0")))
                .collect(Collectors.joining("\0"));
        result.putString("Hidden", hiddenStr);
        return result;
    }

    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        int size = nbt.getInt("Size").orElse(stacks.size());
        setSize(size);
        nbt.getList("Items").ifPresent(list -> {
            for (int i = 0; i < list.size(); i++) {
                list.getCompound(i).ifPresent(tag -> {
                    tag.getInt("Slot").ifPresent(slot -> {
                        if (slot >= 0 && slot < stacks.size()) {
                            if (tag.contains("id")) {
                                ItemStack.CODEC.parse(NbtOps.INSTANCE, tag).resultOrPartial(LOGGER::error).ifPresent(stack -> stacks.set(slot, stack));
                            }
                            tag.getBoolean("isSkinArmor").ifPresent(value -> isSkinArmor[slot] = value);
                        }
                    });
                });
            }
        });
        hidden.clear();
        nbt.getString("Hidden").ifPresent(str -> {
            Arrays.stream(str.split("\0")).forEach(entry -> {
                int idx = entry.indexOf(':');
                if (idx != -1) {
                    hidden.computeIfAbsent(entry.substring(0, idx), k -> new HashSet<>())
                            .add(entry.substring(idx + 1));
                }
            });
        });
        onLoad();
    }
}
