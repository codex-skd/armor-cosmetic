package com.skd.armorcosmetic.impl.network.payload;

import com.skd.armorcosmetic.ArmorCosmetic;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record PayloadOpenCosArmorInventory() implements CustomPacketPayload {

    public static final Type<PayloadOpenCosArmorInventory> TYPE = new Type<>(ArmorCosmetic.id("open_cos_armor_inventory"));
    public static final StreamCodec<FriendlyByteBuf, PayloadOpenCosArmorInventory> STREAM_CODEC = StreamCodec.unit(new PayloadOpenCosArmorInventory());

    private static PayloadOpenCosArmorInventory decode(FriendlyByteBuf buf) {
        return new PayloadOpenCosArmorInventory();
    }

    private static void encode(FriendlyByteBuf buf, PayloadOpenCosArmorInventory payload) {
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
