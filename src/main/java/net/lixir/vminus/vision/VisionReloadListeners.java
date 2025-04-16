package net.lixir.vminus.vision;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.events.VMinusEvents;
import net.lixir.vminus.vision.type.*;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.IOException;
import java.io.Reader;

public class VisionReloadListeners {
    public static class VisionReloadListener implements SimpleSynchronousResourceReloadListener {
        public Identifier getFabricId() {
            return new Identifier(VMinus.MOD_ID, "visions");
        }
        public void reload(ResourceManager manager) {
            VMinusEvents.block_visions = manager.findResources("visions/block", VisionReloadListener::is_json).values().stream().map(VisionReloadListener::block_vision).toArray(BlockVision[]::new);
            VMinusEvents.enchantment_visions = manager.findResources("visions/enchantment", VisionReloadListener::is_json).values().stream().map(VisionReloadListener::enchantment_vision).toArray(EnchantmentVision[]::new);
            VMinusEvents.entity_type_visions = manager.findResources("visions/entity_type", VisionReloadListener::is_json).values().stream().map(VisionReloadListener::entityType_vision).toArray(EntityTypeVision[]::new);
            VMinusEvents.status_effect_visions = manager.findResources("visions/status_effect", VisionReloadListener::is_json).values().stream().map(VisionReloadListener::status_effect_vision).toArray(StatusEffectVision[]::new);
            VMinusEvents.item_visions = manager.findResources("visions/item", VisionReloadListener::is_json).values().stream().map(VisionReloadListener::item_vision).toArray(ItemVision[]::new);
        }
        private static boolean is_json(Identifier identifier){
            return identifier.getPath().endsWith(".json");
        }
        private static BlockVision block_vision(Resource resource){
            try (Reader reader = resource.getReader()){
                return JsonHelper.deserialize(VMinus.GSON,reader, BlockVision.class);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        private static EnchantmentVision enchantment_vision(Resource resource){
            try (Reader reader = resource.getReader()){
                return JsonHelper.deserialize(VMinus.GSON,reader, EnchantmentVision.class);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        private static EntityTypeVision entityType_vision(Resource resource){
            try (Reader reader = resource.getReader()){
                return JsonHelper.deserialize(VMinus.GSON,reader, EntityTypeVision.class);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        private static ItemVision item_vision(Resource resource){
            try (Reader reader = resource.getReader()){
                return JsonHelper.deserialize(VMinus.GSON,reader, ItemVision.class);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        private static StatusEffectVision status_effect_vision(Resource resource){
            try (Reader reader = resource.getReader()){
                return JsonHelper.deserialize(VMinus.GSON,reader, StatusEffectVision.class);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }

    }
    public static void register() {
        VMinus.LOGGER.info("Reload Listener is registering");
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new VisionReloadListener());
    }
}
