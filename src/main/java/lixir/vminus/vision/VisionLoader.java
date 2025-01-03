package lixir.vminus.vision;

import com.google.gson.Gson;
import lixir.vminus.VMinus;
import lixir.vminus.vision.visions.BlockVision;
import lixir.vminus.vision.visions.ItemVision;
import lixir.vminus.vision.visions.Visions;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
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
            static final Gson GSON = new Gson();
            public void reload(ResourceManager manager) {
                Visions.clear();
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/blocks", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        BlockVision blockVision = JsonHelper.deserialize(GSON,reader, BlockVision.class);
                        blockVision.addVision();
                        VMinus.LOGGER.info("{}",blockVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/items", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        ItemVision itemVision = JsonHelper.deserialize(GSON,reader, ItemVision.class);
                        VMinus.LOGGER.info("{}",itemVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        });
    }
}
