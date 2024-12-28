package lixir.vminus.vision;

import com.google.gson.Gson;
import lixir.vminus.VMinus;
import lixir.vminus.vision.visions.BannedBlockVision;
import lixir.vminus.vision.visions.BlockVision;
import lixir.vminus.vision.visions.FuelItemVision;
import lixir.vminus.vision.visions.Visions;
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
            static final Gson gson = new Gson();
            public void reload(ResourceManager manager) {
                Visions.clear();
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/blocks", path -> path.getPath().endsWith("banned.json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        BannedBlockVision visionBannedBlocks = JsonHelper.deserialize(gson,reader, BannedBlockVision.class);
                        visionBannedBlocks.addVision();
                        VMinus.LOGGER.info("{}",visionBannedBlocks);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/blocks", path -> path.getPath().endsWith("block.json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        BlockVision blockVision = JsonHelper.deserialize(gson,reader, BlockVision.class);
                        blockVision.addVision();
                        VMinus.LOGGER.info("{}",blockVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/items", path -> path.getPath().endsWith("fuel.json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        FuelItemVision fuelItemVision = JsonHelper.deserialize(gson,reader, FuelItemVision.class);
                        fuelItemVision.addVision();
                        VMinus.LOGGER.info("{}",fuelItemVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        });
    }
}
