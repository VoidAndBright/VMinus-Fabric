package net.lixir.vminus.util.tag;

import net.lixir.vminus.VMinus;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VMinusDamageTypeTags {
    public static final TagKey<DamageType> BLAST = register_tag("protection/blast");
    public static final TagKey<DamageType> BLUNT = register_tag("protection/blunt");
    public static final TagKey<DamageType> FALL = register_tag("protection/fall");
    public static final TagKey<DamageType> FIRE = register_tag("protection/fire");
    public static final TagKey<DamageType> MAGIC = register_tag("protection/magic");

    private static TagKey<DamageType> register_tag(String name) {
        return TagKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(VMinus.MOD_ID, name));
    }
}
