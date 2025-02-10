package net.lixir.vminus.vision;

import com.google.gson.Gson;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.vision.type.*;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import java.io.IOException;
import java.io.Reader;

public class VisionReloadListeners {
    private static final Gson GSON = new Gson();//.registerTypeAdapter(ItemVision.class,new ItemVisionDeserializer()).create();
    public static class VisionReloadListener implements SimpleSynchronousResourceReloadListener {
        public Identifier getFabricId() {
            return new Identifier(VMinus.MOD_ID, "visions");
        }
        public void reload(ResourceManager manager) {
            Visions.clear();
            for(Resource resource : manager.findResources("visions/block", VisionReloadListener::is_json).values()) {
                try (Reader reader = resource.getReader()){
                    BlockVision blockVision = JsonHelper.deserialize(GSON,reader, BlockVision.class);
                    Visions.DATA_PACK_BLOCK_VISIONS.add(blockVision);
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
            for(Resource resource : manager.findResources("visions/enchantment", VisionReloadListener::is_json).values()) {
                try (Reader reader = resource.getReader()){
                    EnchantmentVision enchantmentVision = JsonHelper.deserialize(GSON,reader, EnchantmentVision.class);
                    Visions.DATA_PACK_ENCHANTMENT_VISIONS.add(enchantmentVision);
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
            for(Resource resource : manager.findResources("visions/entity_type", VisionReloadListener::is_json).values()) {
                try (Reader reader = resource.getReader()){
                    EntityTypeVision entityTypeVision = JsonHelper.deserialize(GSON,reader, EntityTypeVision.class);
                    Visions.DATA_PACK_ENTITY_TYPE_VISIONS.add(entityTypeVision);
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
            for(Resource resource : manager.findResources("visions/item", VisionReloadListener::is_json).values()) {
                try (Reader reader = resource.getReader()){
                    ItemVision itemVision = JsonHelper.deserialize(GSON,reader, ItemVision.class);
                    Visions.DATA_PACK_ITEM_VISIONS.add(itemVision);
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
            for(Resource resource : manager.findResources("visions/status_effect", VisionReloadListener::is_json).values()) {
                try (Reader reader = resource.getReader()){
                    StatusEffectVision statusEffectVision = JsonHelper.deserialize(GSON,reader, StatusEffectVision.class);
                    Visions.DATA_PACK_STATUS_EFFECT_VISIONS.add(statusEffectVision);
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
        }
        private static boolean is_json(Identifier identifier){
            return identifier.getPath().endsWith(".json");
        }
    }
    public static void register() {
        VMinus.LOGGER.info("Reload Listener is registering");
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new VisionReloadListener());
    }
}
