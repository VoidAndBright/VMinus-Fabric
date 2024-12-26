package lixir.vminus.mixin.block;

import lixir.vminus.tag.VMinusItemTags;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Inject(at = @At("RETURN"), method = "canWalkOnPowderSnow", cancellable = true)
    private static void canWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity livingEntity) {
            ItemStack livingEntityBootSlot = livingEntity.getEquippedStack(EquipmentSlot.FEET);
            NbtCompound nbtCompound = livingEntity.getEquippedStack(EquipmentSlot.FEET).getNbt();
            if (nbtCompound != null && !nbtCompound.getBoolean("broken")) {
                boolean lightFooted = livingEntityBootSlot.isIn(VMinusItemTags.LIGHTFOOTED);
                cir.setReturnValue(lightFooted);
                return;
            }
            if (livingEntity instanceof HorseEntity horse) {
                ItemStack horseEntityArmorSlot = horse.getEquippedStack(EquipmentSlot.CHEST);
                NbtCompound nbtCompoundHorse = horseEntityArmorSlot.getNbt();
                if (nbtCompoundHorse != null && !nbtCompoundHorse.getBoolean("broken")) {
                    boolean lightFooted = horseEntityArmorSlot.isIn(VMinusItemTags.LIGHTFOOTED);
                    cir.setReturnValue(lightFooted);
                    return;
                }
            }
        }
        cir.setReturnValue(false);
    }
}
