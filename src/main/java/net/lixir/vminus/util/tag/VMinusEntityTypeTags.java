package net.lixir.vminus.util.tag;

import net.lixir.vminus.VMinus;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VMinusEntityTypeTags {

    public static final TagKey<EntityType<?>> HORSES = register_tag("horses");
    public static final TagKey<EntityType<?>> ILLAGERS = register_tag("illagers");
    public static final TagKey<EntityType<?>> SPIDERS = register_tag("spiders");
    public static final TagKey<EntityType<?>> ZOMBIES = register_tag("zombies");

    private static TagKey<EntityType<?>> register_tag(String name) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(VMinus.MOD_ID, name));
    }
}
