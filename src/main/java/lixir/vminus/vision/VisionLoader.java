package lixir.vminus.vision;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import lixir.vminus.VMinus;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class VisionLoader {
    public static void register() {
    	ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(
            new SimpleSynchronousResourceReloadListener() {
                @Override
                public Identifier getFabricId () {
                    return new Identifier(VMinus.MOD_ID, "visions");
                }
                @Override
                public void reload (ResourceManager manager){
                    var gson = new Gson();
                    for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/block_visions", path -> path.getPath().endsWith("banned.json")).entrySet()) {
                        try (Reader reader = entry.getValue().getReader()) {
                            Vision vision = JsonHelper.deserialize(gson, reader, Vision.class);
                            VMinus.LOGGER.info("{}", (Object) vision.blocks);
                            VMinus.LOGGER.info("{}",vision.banned);
                        } catch (IllegalArgumentException | IOException | JsonParseException exception) {
                            VMinus.LOGGER.error("Couldn't parse data file from ", exception);
                        }
                    }
                }
            }
        );
    }
}
