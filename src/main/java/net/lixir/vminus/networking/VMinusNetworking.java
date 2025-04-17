package net.lixir.vminus.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.cape.Cape;
import net.lixir.vminus.cape.CapeOwner;
import net.lixir.vminus.util.PersistentNbt;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class VMinusNetworking {
    public static final Identifier CAPE_PACKET = new Identifier(VMinus.MOD_ID,"cape");
    public static void register(){
        ServerPlayNetworking.registerGlobalReceiver(CAPE_PACKET,VMinusNetworking::set_cape_handler);
    }

    private static void set_cape_handler(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        CapeOwner.cast(serverPlayerEntity).set_cape(Cape.from_string(packetByteBuf.readString()));
    }
}
