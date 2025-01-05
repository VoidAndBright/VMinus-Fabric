package lixir.vminus.keybind;

import lixir.vminus.VMinus;
import lixir.vminus.network.VMinusPacketHandlers;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class VMinusKeyBinds {
    public static final KeyBinding CAPE_KEY_BIND = registerKeyBind("open_capes_menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "misc");
    public static void capeKeyBindHandler(MinecraftClient minecraftClient) {
        while (CAPE_KEY_BIND.wasPressed()){
            if (minecraftClient.player instanceof ClientPlayerEntity){
                minecraftClient.player.fallDistance=10;
                ClientPlayNetworking.send(VMinusPacketHandlers.OPEN_CAPE_SCREEN_PACKET, PacketByteBufs.empty());
            }
        }
    }
    private static KeyBinding registerKeyBind(String name, InputUtil.Type input, int key, String category) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding("key."+ VMinus.MOD_ID + name, input, key, "key.categories."+category ));
    }
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(VMinusKeyBinds::capeKeyBindHandler);
    }
}
