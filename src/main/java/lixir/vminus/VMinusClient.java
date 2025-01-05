package lixir.vminus;

import lixir.vminus.entity.VMinusEntities;
import lixir.vminus.screen.VMinusScreenHandlers;
import net.fabricmc.api.ClientModInitializer;


public class VMinusClient implements ClientModInitializer {

	public void onInitializeClient() {
		VMinusEntities.clientRegister();
		VMinusScreenHandlers.clientRegister();
	}
}