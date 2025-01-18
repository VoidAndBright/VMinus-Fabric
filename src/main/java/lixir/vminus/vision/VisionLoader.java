package lixir.vminus.vision;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lixir.vminus.VMinus;
import lixir.vminus.vision.deserielizer.TestVisionDeserializer;
import lixir.vminus.vision.type.*;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class VisionLoader {
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(TestVision.class, new TestVisionDeserializer()).create();
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
                        Visions.BLOCK_VISION_JSONS.add(blockVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/entity_type", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        EntityTypeVision entityTypeVision = JsonHelper.deserialize(GSON,reader, EntityTypeVision.class);
                        Visions.ENTITY_TYPE_VISION_JSONS.add(entityTypeVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/item", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        ItemVision itemVision = JsonHelper.deserialize(GSON,reader, ItemVision.class);
                        Visions.ITEM_VISION_JSONS.add(itemVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/status_effect", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        StatusEffectVision statusEffectVision = JsonHelper.deserialize(GSON,reader, StatusEffectVision.class);
                        Visions.STATUS_EFFECT_VISION_JSONS.add(statusEffectVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/enchantment", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        EnchantmentVision enchantmentVision = JsonHelper.deserialize(GSON,reader, EnchantmentVision.class);
                        Visions.ENCHANTMENT_VISION_JSONS.add(enchantmentVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Map.Entry<Identifier, Resource> entry : manager.findResources("visions/test", path -> path.getPath().endsWith(".json")).entrySet()) {
                    try (Reader reader = entry.getValue().getReader()){
                        TestVision enchantmentVision = JsonHelper.deserialize(GSON,reader, TestVision.class);
                        VMinus.LOGGER.info("{}",enchantmentVision.banned);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        });
    }
}
