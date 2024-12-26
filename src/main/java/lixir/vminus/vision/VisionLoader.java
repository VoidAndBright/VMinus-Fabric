package lixir.vminus.vision;

import com.google.gson.Gson;
import lixir.vminus.VMinus;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class VisionLoader {
    public static void register() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            public Identifier getFabricId() {
                return new Identifier(VMinus.MOD_ID, "visions");
            }
            public void reload(ResourceManager manager) {
                var gson = new Gson();
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/block_visions", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        VisionBannedBlocks visionBannedBlocks = JsonHelper.deserialize(gson,reader, VisionBannedBlocks.class);
                        visionBannedBlocks.getBlockTags();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/fuel_visions", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        VisionFuelItems visionFuelItems = JsonHelper.deserialize(gson,reader, VisionFuelItems.class);
                        visionFuelItems.getItemTags();
                        VisionData.load_fuels();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
