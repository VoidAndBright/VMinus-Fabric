package lixir.vminus;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.mojang.authlib.yggdrasil.response.User;
import lixir.vminus.block.VMinusBlocks;
import lixir.vminus.command.VMinusCommands;
import lixir.vminus.entity.VMinusEntities;
import lixir.vminus.item.VMinusItems;
import lixir.vminus.keybinds.VMinusKeyBinds;
import lixir.vminus.network.VMinusPacketReceiver;
import lixir.vminus.screen.VMinusScreenHandlers;
import net.fabricmc.api.ModInitializer;
import com.google.gson.Gson;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.InputStream;


public class VMinus implements ModInitializer {
	public static final String MOD_ID = "vminus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public void onInitialize() {
		LOGGER.info("VMinus Is Loading");

		VMinusCommands.register();
		VMinusBlocks.register();
		VMinusEntities.register();
		VMinusScreenHandlers.register();
		VMinusItems.register();
		VMinusKeyBinds.register();
		VMinusPacketReceiver.register();
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			public Identifier getFabricId() {
				return new Identifier("vminus", "data");
			}

			public void reload(ResourceManager manager) {
				for(Identifier id : manager.findResources("block_visions", path -> path.getPath().endsWith(".json")).keySet()) {
					Gson gson = new Gson();
					LOGGER.info("HEY LOOK AT ME ME ME M E ME {}", id.getPath());

					try (FileReader reader = new FileReader(id.getPath())) {
						Vision vision = gson.fromJson(reader, Vision.class);
						LOGGER.info("{}", (Object) vision.getBlocks());
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}
		});
	}
}