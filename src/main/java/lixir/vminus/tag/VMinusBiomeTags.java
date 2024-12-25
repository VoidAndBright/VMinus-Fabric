package lixir.vminus.tag;

import lixir.vminus.VMinus;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class VMinusBiomeTags {
    public static final TagKey<Biome> IS_PLAINS = registerTag("is_plains");
    public static final TagKey<Biome> IS_SWAMP = registerTag("is_swamp");

    private static TagKey<Biome> registerTag(String name) {
        return TagKey.of(RegistryKeys.BIOME, new Identifier(VMinus.MOD_ID, name));
    }
}
