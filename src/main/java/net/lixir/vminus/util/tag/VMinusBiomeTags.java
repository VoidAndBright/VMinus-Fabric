package net.lixir.vminus.util.tag;

import net.lixir.vminus.VMinus;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class VMinusBiomeTags {
    public static final TagKey<Biome> IS_PLAINS = register_tag("is_plains");
    public static final TagKey<Biome> IS_SWAMP = register_tag("is_swamp");

    private static TagKey<Biome> register_tag(String name) {
        return TagKey.of(RegistryKeys.BIOME, new Identifier(VMinus.MOD_ID, name));
    }
}
