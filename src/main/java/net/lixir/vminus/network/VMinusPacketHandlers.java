package net.lixir.vminus.network;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.screen.screens.CapeScreen;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class VMinusPacketHandlers {
    public static final Identifier OPEN_CAPE_SCREEN_PACKET = new Identifier(VMinus.MOD_ID, "cape");
    public static void register(){
        ServerPlayNetworking.registerGlobalReceiver(OPEN_CAPE_SCREEN_PACKET, VMinusPacketHandlers::open_cape_packet_handler);
    }
    private static void open_cape_packet_handler(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler serverPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        serverPlayerEntity.openHandledScreen(new SimpleNamedScreenHandlerFactory(CapeScreen::open_cape_screen,CapeScreen.TITLE));
    }
}
