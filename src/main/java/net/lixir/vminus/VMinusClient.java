package net.lixir.vminus;

import net.fabricmc.api.ClientModInitializer;
import net.lixir.vminus.command.VMinusCommands;
import net.lixir.vminus.config.VMinusConfigManager;
import net.lixir.vminus.entity.VMinusEntities;
import net.lixir.vminus.events.VMinusEvents;
import net.lixir.vminus.screen.VMinusScreenHandlers;

public class VMinusClient implements ClientModInitializer {
	public void onInitializeClient() {
		VMinusEntities.client_register();
		VMinusEvents.client_register();
		VMinusConfigManager.load();
		VMinusScreenHandlers.client_register();
		VMinusCommands.client_register();
	}
}