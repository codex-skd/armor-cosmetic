package com.skd.armorcosmetic.impl.network.payload;

import com.skd.armorcosmetic.ArmorCosmetic;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record PayloadOpenNormalInventory() implements CustomPacketPayload {

    public static final Type<PayloadOpenNormalInventory> TYPE = new Type<>(ArmorCosmetic.id("open_normal_inventory"));
    public static final StreamCodec<FriendlyByteBuf, PayloadOpenNormalInventory> STREAM_CODEC = StreamCodec.unit(new PayloadOpenNormalInventory());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
