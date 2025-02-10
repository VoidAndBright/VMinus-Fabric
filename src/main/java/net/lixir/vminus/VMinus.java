package net.lixir.vminus;

import net.lixir.vminus.block.VMinusBlocks;
import net.lixir.vminus.command.VMinusCommands;
import net.lixir.vminus.entity.VMinusEntities;
import net.lixir.vminus.item.VMinusItems;
import net.lixir.vminus.keybind.VMinusKeyBinds;
import net.lixir.vminus.network.VMinusPacketHandlers;
import net.lixir.vminus.screen.VMinusScreenHandlers;
import net.lixir.vminus.vision.VisionReloadListeners;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VMinus implements ModInitializer {
	public static final String MOD_ID = "vminus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public void onInitialize() {
		LOGGER.info("VMinus Is Loading");
		VisionReloadListeners.register();
		VMinusCommands.register();
		VMinusBlocks.register();
		VMinusScreenHandlers.register();
		VMinusEntities.register();
		VMinusItems.register();
		VMinusKeyBinds.register();
		VMinusPacketHandlers.register();
	}
}