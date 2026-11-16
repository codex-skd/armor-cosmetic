package com.skd.armorcosmetic.impl.network.payload;

import com.skd.armorcosmetic.ArmorCosmetic;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record PayloadOpenNormalInventoryScreen() implements CustomPacketPayload {

    public static final Type<PayloadOpenNormalInventoryScreen> TYPE = new Type<>(ArmorCosmetic.id("open_normal_inventory_screen"));
    public static final StreamCodec<FriendlyByteBuf, PayloadOpenNormalInventoryScreen> STREAM_CODEC = StreamCodec.unit(new PayloadOpenNormalInventoryScreen());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
