package net.lixir.vminus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.lixir.vminus.util.tag.VMinusEntityTypeTags;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class VMinusEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public VMinusEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output,registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(VMinusEntityTypeTags.HORSES)
                .add(EntityType.HORSE)
                .add(EntityType.SKELETON_HORSE)
                .add(EntityType.ZOMBIE_HORSE);

        getOrCreateTagBuilder(VMinusEntityTypeTags.ILLAGERS)
                .add(EntityType.VINDICATOR)
                .add(EntityType.PILLAGER)
                .add(EntityType.EVOKER);

        getOrCreateTagBuilder(VMinusEntityTypeTags.SPIDERS)
                .add(EntityType.SPIDER)
                .add(EntityType.CAVE_SPIDER);

        getOrCreateTagBuilder(VMinusEntityTypeTags.ZOMBIES)
                .add(EntityType.DROWNED)
                .add(EntityType.HUSK)
                .add(EntityType.ZOMBIE)
                .add(EntityType.ZOMBIE_VILLAGER);
    }
}
