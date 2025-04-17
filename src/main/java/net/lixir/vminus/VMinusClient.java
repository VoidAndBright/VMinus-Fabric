package net.lixir.vminus;

import net.fabricmc.api.ClientModInitializer;
import net.lixir.vminus.command.VMinusCommands;
import net.lixir.vminus.config.VMinusConfigManager;
import net.lixir.vminus.events.VMinusEvents;

public class VMinusClient implements ClientModInitializer {
	public void onInitializeClient() {
		VMinusEvents.client_register();
		VMinusConfigManager.load();
		VMinusCommands.client_register();
	}
}