package net.lixir.vminus.mixin.enchantment;

import net.lixir.vminus.vision.direct.EnchantmentVisionable;
import net.lixir.vminus.vision.type.EnchantmentVision;
import net.minecraft.enchantment.DepthStriderEnchantment;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DepthStriderEnchantment.class)
public class DepthStriderEnchantmentMixin {
    @Unique
    private final Enchantment enchantment = (Enchantment) (Object) this;

    @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
    private void getMaxLevel(CallbackInfoReturnable<Integer> cir) {
        EnchantmentVision enchantment_vision = EnchantmentVisionable.get_vision(enchantment);
        if (enchantment_vision != null && enchantment_vision.get_max_level(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_max_level(enchantment));
    }
}
