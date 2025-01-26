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
        if (entity instanceof LivingEntity livingEntity) {
            NbtCompound nbtCompound = livingEntity.getEquippedStack(EquipmentSlot.FEET).getNbt();
            if (nbtCompound != null && !nbtCompound.getBoolean("broken")) {
                boolean lightFooted = nbtCompound.getBoolean("lightfooted");
                cir.setReturnValue(lightFooted);
            }
            if (livingEntity instanceof HorseEntity horse) {
                NbtCompound nbtCompoundHorse = horse.getEquippedStack(EquipmentSlot.CHEST).getNbt();
                if (nbtCompoundHorse != null && !nbtCompoundHorse.getBoolean("broken")) {
                    boolean lightFooted = nbtCompoundHorse.getBoolean("lightfooted");
                    cir.setReturnValue(lightFooted);
                }
            }
        }
        cir.setReturnValue(false);
    }
}
