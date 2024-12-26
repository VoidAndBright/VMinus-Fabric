package lixir.vminus;

import lixir.vminus.block.VMinusBlocks;
import lixir.vminus.command.VMinusCommands;
import lixir.vminus.entity.VMinusEntities;
import lixir.vminus.item.VMinusItems;
import lixir.vminus.keybinds.VMinusKeyBinds;
import lixir.vminus.network.VMinusPacketReceiver;
import lixir.vminus.screen.VMinusScreenHandlers;
import lixir.vminus.vision.VisionLoader;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VMinus implements ModInitializer {
	public static final String MOD_ID = "vminus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public void onInitialize() {
		LOGGER.info("VMinus Is Loading");
		VisionLoader.register();
		VMinusCommands.register();
		VMinusBlocks.register();
		VMinusEntities.register();
		VMinusScreenHandlers.register();
		VMinusItems.register();
		VMinusKeyBinds.register();
		VMinusPacketReceiver.register();
	}
}