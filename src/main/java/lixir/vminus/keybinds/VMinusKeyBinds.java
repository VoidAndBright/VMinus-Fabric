package lixir.vminus.keybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class VMinusKeyBinds {
    public static final KeyBinding CAPE_KEYBINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.vminus.open_capes_menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "key.categories.misc"
    ));
    public static final KeyBinding DEFAULT_KEYBINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.vminus.open_default_menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_W, "key.categories.misc"
    ));
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(new CapeKeyBinding());
        ClientTickEvents.END_CLIENT_TICK.register(new DefaultKeyBinding());
    }
}
