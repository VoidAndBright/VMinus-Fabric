package lixir.vminus.keybinds;

import lixir.vminus.network.PacketIdentifiers;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class DefaultKeyBinding implements ClientTickEvents.EndTick {
    @Override
    public void onEndTick(MinecraftClient minecraftClient) {
        while (VMinusKeyBinds.DEFAULT_KEYBINDING.wasPressed()){
            if (minecraftClient.player instanceof ClientPlayerEntity){
                ClientPlayNetworking.send(PacketIdentifiers.DEFAULT_MENU_PACKET_IDENTIFIER, PacketByteBufs.empty());
            }
        }
    }
}
