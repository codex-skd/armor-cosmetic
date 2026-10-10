package com.skd.armorcosmetic.impl.network.payload;

import com.skd.armorcosmetic.ArmorCosmetic;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record PayloadOpenNormalInventoryScreen() implements CustomPacketPayload {

    public static final Type<PayloadOpenNormalInventoryScreen> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ArmorCosmetic.MODID, "open_normal_inventory_screen"));

    public static final StreamCodec<FriendlyByteBuf, PayloadOpenNormalInventoryScreen> STREAM_CODEC = StreamCodec.of(PayloadOpenNormalInventoryScreen::encode, PayloadOpenNormalInventoryScreen::decode);

    private static PayloadOpenNormalInventoryScreen decode(FriendlyByteBuf buffer) {
        return new PayloadOpenNormalInventoryScreen();
    }

    private static void encode(FriendlyByteBuf buffer, PayloadOpenNormalInventoryScreen payload) {
    }

    @Override
    public Type<PayloadOpenNormalInventoryScreen> type() {
        return TYPE;
    }

}
