package lixir.vminus.network;

import lixir.vminus.screen.CapesScreenFactory;
import lixir.vminus.screen.DefaultScreenFactory;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class VMinusPacketReceiver {
    public static void register(){
        ServerPlayNetworking.registerGlobalReceiver(PacketIdentifiers.CAPE_MENU_PACKET_IDENTIFIER,new CapeMenuPacketHandler());
        ServerPlayNetworking.registerGlobalReceiver(PacketIdentifiers.DEFAULT_MENU_PACKET_IDENTIFIER,new DefaultMenuPacketHandler());

    }
    static class CapeMenuPacketHandler implements ServerPlayNetworking.PlayChannelHandler {
        public void receive(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
            serverPlayerEntity.openHandledScreen(new CapesScreenFactory());
        }
    }
    static class DefaultMenuPacketHandler implements ServerPlayNetworking.PlayChannelHandler {
        public void receive(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
            serverPlayerEntity.openHandledScreen(new DefaultScreenFactory());
        }
    }
}
