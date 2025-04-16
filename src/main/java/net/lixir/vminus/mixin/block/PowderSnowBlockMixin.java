package net.lixir.vminus.mixin.block;

import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Inject(method = "canWalkOnPowderSnow",at = @At("RETURN"), cancellable = true)
    private static void canWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity living_entity) {
            NbtCompound nbt_compound = living_entity.getEquippedStack(EquipmentSlot.FEET).getNbt();
            if (nbt_compound != null && !nbt_compound.getBoolean("broken")) {
                boolean lightFooted = nbt_compound.getBoolean("lightfooted");
                cir.setReturnValue(lightFooted);
            }
            if (living_entity instanceof HorseEntity horse) {
                NbtCompound horse_nbt_compound = horse.getEquippedStack(EquipmentSlot.CHEST).getNbt();
                if (horse_nbt_compound != null && !horse_nbt_compound.getBoolean("broken")) {
                    boolean lightFooted = horse_nbt_compound.getBoolean("lightfooted");
                    cir.setReturnValue(lightFooted);
                }
            }
        }
        cir.setReturnValue(false);
    }
}
