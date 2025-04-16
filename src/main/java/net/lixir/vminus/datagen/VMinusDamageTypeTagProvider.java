package net.lixir.vminus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.lixir.vminus.util.tag.VMinusDamageTypeTags;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class VMinusDamageTypeTagProvider extends FabricTagProvider<DamageType> {
    public VMinusDamageTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.DAMAGE_TYPE,registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(VMinusDamageTypeTags.BLAST)
                .add(DamageTypes.BAD_RESPAWN_POINT)
                .add(DamageTypes.EXPLOSION)
                .add(DamageTypes.FIREWORKS)
                .add(DamageTypes.PLAYER_EXPLOSION)
                .add(DamageTypes.SONIC_BOOM)
                .add(DamageTypes.WITHER_SKULL);

        getOrCreateTagBuilder(VMinusDamageTypeTags.BLUNT)
                .add(DamageTypes.FALLING_ANVIL)
                .add(DamageTypes.ARROW)
                .add(DamageTypes.CACTUS)
                .add(DamageTypes.CRAMMING)
                .add(DamageTypes.FALLING_BLOCK)
                .add(DamageTypes.FALLING_STALACTITE)
                .add(DamageTypes.FLY_INTO_WALL)
                .add(DamageTypes.MOB_ATTACK)
                .add(DamageTypes.MOB_ATTACK_NO_AGGRO)
                .add(DamageTypes.MOB_PROJECTILE)
                .add(DamageTypes.PLAYER_ATTACK)
                .add(DamageTypes.STING)
                .add(DamageTypes.SWEET_BERRY_BUSH)
                .add(DamageTypes.THROWN)
                .add(DamageTypes.TRIDENT);

        getOrCreateTagBuilder(VMinusDamageTypeTags.FALL)
                .add(DamageTypes.FALL)
                .add(DamageTypes.STALAGMITE);

        getOrCreateTagBuilder(VMinusDamageTypeTags.FIRE)
                .add(DamageTypes.FIREBALL)
                .add(DamageTypes.IN_FIRE)
                .add(DamageTypes.ON_FIRE)
                .add(DamageTypes.UNATTRIBUTED_FIREBALL)
                .add(DamageTypes.LAVA)
                .add(DamageTypes.HOT_FLOOR);

        getOrCreateTagBuilder(VMinusDamageTypeTags.MAGIC)
                .add(DamageTypes.DRAGON_BREATH)
                .add(DamageTypes.INDIRECT_MAGIC)
                .add(DamageTypes.MAGIC)
                .add(DamageTypes.SONIC_BOOM)
                .add(DamageTypes.THORNS)
                .add(DamageTypes.WITHER);
    }
}
