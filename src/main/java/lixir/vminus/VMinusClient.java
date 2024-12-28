package lixir.vminus;

import lixir.vminus.entity.VMinusEntities;
import lixir.vminus.keybinds.VMinusKeyBinds;
import lixir.vminus.screen.VMinusScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.registry.tag.ItemTags;


public class VMinusClient implements ClientModInitializer {

	public void onInitializeClient() {
		VMinusEntities.clientRegister();
		VMinusScreenHandlers.clientRegister();
	}
}