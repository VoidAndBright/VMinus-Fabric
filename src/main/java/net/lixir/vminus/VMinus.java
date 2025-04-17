package net.lixir.vminus;

import com.google.gson.Gson;
import net.fabricmc.api.ModInitializer;
import net.lixir.vminus.command.VMinusCommands;
import net.lixir.vminus.entity.attribute.VMinusEntityAttributes;
import net.lixir.vminus.events.VMinusEvents;
import net.lixir.vminus.networking.VMinusNetworking;
import net.lixir.vminus.vision.VisionReloadListeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VMinus implements ModInitializer {
	public static final String MOD_ID = "vminus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Gson GSON = new Gson();//.registerTypeAdapter(ItemVision.class,new ItemVisionDeserializer()).create();
	public void onInitialize() {
		LOGGER.info("VMinus Is Loading");
		VisionReloadListeners.register();
		VMinusCommands.register();
		VMinusEvents.register();
		VMinusEntityAttributes.register();
		VMinusNetworking.register();
	}
}