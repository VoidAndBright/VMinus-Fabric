package lixir.vminus.tag;

import lixir.vminus.VMinus;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VMinusEntityTypeTags {

    public static final TagKey<EntityType<?>> HORSES = registerTag("horses");
    public static final TagKey<EntityType<?>> ILLAGERS = registerTag("illagers");
    public static final TagKey<EntityType<?>> SPIDERS = registerTag("spiders");
    public static final TagKey<EntityType<?>> ZOMBIES = registerTag("zombies");

    private static TagKey<EntityType<?>> registerTag(String name) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(VMinus.MOD_ID, name));
    }
}
