package lixir.vminus.tag;

import lixir.vminus.VMinus;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class VMinusDamageTypeTags {
    public static final TagKey<DamageType> BLAST = registerTag("protection/blast");
    public static final TagKey<DamageType> BLUNT = registerTag("protection/blunt");
    public static final TagKey<DamageType> FALL = registerTag("protection/fall");
    public static final TagKey<DamageType> FIRE = registerTag("protection/fire");
    public static final TagKey<DamageType> MAGIC = registerTag("protection/magic");

    private static TagKey<DamageType> registerTag(String name) {
        return TagKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(VMinus.MOD_ID, name));
    }
}
