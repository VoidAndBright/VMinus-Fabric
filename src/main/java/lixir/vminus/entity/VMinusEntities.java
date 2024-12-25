package lixir.vminus.entity;

import lixir.vminus.VMinus;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VMinusEntities {

    public static final EntityType<DefaultEntity> DEFAULT_ENTITY = RegisterEntity("porcupine", FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DefaultEntity::new).dimensions(DefaultEntity.getDimensions()).build());


    public static <T extends MobEntity> EntityType<T> RegisterEntity(String name, EntityType<T> EntityType)
    {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(VMinus.MOD_ID, name), EntityType);
    }

    public static void clientRegister(){
        EntityRendererRegistry.register(DEFAULT_ENTITY, DefaultEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(VMinusModelLayers.DEFAULT_ENTITY, DefaultEntityModel::getTexturedModelData);
    }
    public static void register() {
        FabricDefaultAttributeRegistry.register(DEFAULT_ENTITY, DefaultEntity.createAttributes());
        VMinus.LOGGER.info("Registering Entities for " + VMinus.MOD_ID);
    }
}
