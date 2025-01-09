package lixir.vminus.vision;

import com.google.gson.Gson;
import lixir.vminus.VMinus;
import lixir.vminus.vision.type.BlockVision;
import lixir.vminus.vision.type.EntityVision;
import lixir.vminus.vision.type.ItemVision;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class VisionLoader {
    private static final Gson GSON = new Gson();
    public static void register() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            public Identifier getFabricId() {
                return new Identifier(VMinus.MOD_ID, "visions");
            }
            public void reload(ResourceManager manager) {
                Visions.clear();
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/block", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        BlockVision blockVision = JsonHelper.deserialize(GSON,reader, BlockVision.class);
                        Visions.RESOURCE_BLOCK_VISIONS.add(blockVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/entity", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        EntityVision itemVision = JsonHelper.deserialize(GSON,reader, EntityVision.class);
                        Visions.RESOURCE_ENTITY_VISIONS.add(itemVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/item", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        ItemVision itemVision = JsonHelper.deserialize(GSON,reader, ItemVision.class);
                        Visions.RESOURCE_ITEM_VISIONS.add(itemVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        });
    }
}
