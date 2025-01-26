package net.lixir.vminus;

import net.lixir.vminus.entity.VMinusEntities;
import net.lixir.vminus.screen.VMinusScreenHandlers;
import net.fabricmc.api.ClientModInitializer;


public class VMinusClient implements ClientModInitializer {

	public void onInitializeClient() {
		VMinusEntities.client_register();
		VMinusScreenHandlers.client_register();
	}
}