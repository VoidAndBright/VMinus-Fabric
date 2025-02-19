package net.lixir.vminus;

import net.fabricmc.api.ClientModInitializer;
import net.lixir.vminus.entity.VMinusEntities;
import net.lixir.vminus.overlay.VMinusOverlays;
import net.lixir.vminus.screen.VMinusScreenHandlers;

public class VMinusClient implements ClientModInitializer {
	public void onInitializeClient() {
		VMinusEntities.client_register();
		VMinusScreenHandlers.client_register();
		VMinusOverlays.client_register();
	}
}