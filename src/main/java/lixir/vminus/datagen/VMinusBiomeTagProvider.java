package lixir.vminus.datagen;

import lixir.vminus.tag.VMinusBiomeTags;
import lixir.vminus.tag.VMinusDamageTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class VMinusBiomeTagProvider extends FabricTagProvider<Biome> {
    public VMinusBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME,registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(VMinusBiomeTags.IS_PLAINS)
                .add(BiomeKeys.PLAINS)
                .add(BiomeKeys.SNOWY_PLAINS)
                .add(BiomeKeys.SUNFLOWER_PLAINS);
        getOrCreateTagBuilder(VMinusBiomeTags.IS_SWAMP)
                .add(BiomeKeys.MANGROVE_SWAMP)
                .add(BiomeKeys.SWAMP);
    }
}