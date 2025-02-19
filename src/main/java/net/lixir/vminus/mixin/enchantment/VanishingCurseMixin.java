package net.lixir.vminus.mixin.enchantment;

import net.lixir.vminus.vision.Vision;
import net.lixir.vminus.vision.type.EnchantmentVision;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.VanishingCurseEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VanishingCurseEnchantment.class)
public class VanishingCurseMixin {
    @Unique
    private final Enchantment enchantment = (Enchantment) (Object) this;
    @Inject(method = "isCursed", at = @At("HEAD"), cancellable = true)
    private void isCurse(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = Vision.get_vision(enchantment);
        cir.setReturnValue(enchantment_vision != null && enchantment_vision.get_curse(enchantment) != null ? cir.getReturnValue() : false);
    }

    @Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
    private void isTreasureOnly(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = Vision.get_vision(enchantment);
        if (enchantment_vision != null && enchantment_vision.get_treasure(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_treasure(enchantment));
    }
}
