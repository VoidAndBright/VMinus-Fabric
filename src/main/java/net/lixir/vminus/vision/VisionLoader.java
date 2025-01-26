package net.lixir.vminus.vision;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.vision.deserielizer.TestVisionDeserializer;
import net.lixir.vminus.vision.type.*;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.IOException;
import java.io.Reader;

public class VisionLoader {
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(TestVision.class, new TestVisionDeserializer()).create();
    public static void register() {
        VMinus.LOGGER.info("Data Loader is registering");
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            public Identifier getFabricId() {
                return new Identifier(VMinus.MOD_ID, "visions");
            }
            public void reload(ResourceManager manager) {
                Visions.clear();
                for(Resource resource : manager.findResources("visions/block", VisionLoader::is_json).values()) {
                    try (Reader reader = resource.getReader()){
                        BlockVision blockVision = JsonHelper.deserialize(GSON,reader, BlockVision.class);
                        Visions.BLOCK_VISION_JSONS.add(blockVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Resource resource : manager.findResources("visions/enchantment", VisionLoader::is_json).values()) {
                    try (Reader reader = resource.getReader()){
                        EnchantmentVision enchantmentVision = JsonHelper.deserialize(GSON,reader, EnchantmentVision.class);
                        Visions.ENCHANTMENT_VISION_JSONS.add(enchantmentVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Resource resource : manager.findResources("visions/entity_type", VisionLoader::is_json).values()) {
                    try (Reader reader = resource.getReader()){
                        EntityTypeVision entityTypeVision = JsonHelper.deserialize(GSON,reader, EntityTypeVision.class);
                        Visions.ENTITY_TYPE_VISION_JSONS.add(entityTypeVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Resource resource : manager.findResources("visions/item", VisionLoader::is_json).values()) {
                    try (Reader reader = resource.getReader()){
                        ItemVision itemVision = JsonHelper.deserialize(GSON,reader, ItemVision.class);
                        Visions.ITEM_VISION_JSONS.add(itemVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Resource resource : manager.findResources("visions/status_effect", VisionLoader::is_json).values()) {
                    try (Reader reader = resource.getReader()){
                        StatusEffectVision statusEffectVision = JsonHelper.deserialize(GSON,reader, StatusEffectVision.class);
                        Visions.STATUS_EFFECT_VISION_JSONS.add(statusEffectVision);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
                for(Resource resource : manager.findResources("visions/test", VisionLoader::is_json).values()) {
                    try (Reader reader = resource.getReader()){
                        TestVision enchantmentVision = JsonHelper.deserialize(GSON,reader, TestVision.class);
                        VMinus.LOGGER.info("{}",enchantmentVision.banned);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        });
    }
    private static boolean is_json(Identifier identifier){
        return identifier.getPath().endsWith(".json");
    }
}
