package net.lixir.vminus.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.entity.defaultium.DefaultiumEntity;
import net.lixir.vminus.entity.defaultium.DefaultiumEntityModel;
import net.lixir.vminus.entity.defaultium.DefaultiumEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VMinusEntities {

    public static final EntityType<DefaultiumEntity> DEFAULTIUM_ENTITY = RegisterEntity("defaultium_entity", FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DefaultiumEntity::new).dimensions(DefaultiumEntity.getDimensions()).build());
    public static <T extends Entity> EntityType<T> RegisterEntity(String name, EntityType<T> EntityType){
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(VMinus.MOD_ID, name), EntityType);
    }
    public static void register() {
        FabricDefaultAttributeRegistry.register(DEFAULTIUM_ENTITY, DefaultiumEntity.createAttributes());
        VMinus.LOGGER.info("Registering Entities for " + VMinus.MOD_ID);
    }
    public static void client_register(){
        EntityRendererRegistry.register(DEFAULTIUM_ENTITY, DefaultiumEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(VMinusModelLayers.DEFAULTIUM_ENTITY, DefaultiumEntityModel::getTexturedModelData);
    }
}
