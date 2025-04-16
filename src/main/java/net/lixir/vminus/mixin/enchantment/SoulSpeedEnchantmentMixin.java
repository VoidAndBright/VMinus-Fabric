package net.lixir.vminus.mixin.enchantment;

import net.lixir.vminus.vision.implement.EnchantmentVisionable;
import net.lixir.vminus.vision.type.EnchantmentVision;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.SoulSpeedEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoulSpeedEnchantment.class)
public class SoulSpeedEnchantmentMixin {
    @Unique
    private final Enchantment enchantment = (Enchantment) (Object) this;
    @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
    private void getMaxLevel(CallbackInfoReturnable<Integer> cir) {
        EnchantmentVision enchantment_vision = EnchantmentVisionable.get_vision(enchantment);
        if (enchantment_vision != null && enchantment_vision.get_max_level(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_max_level(enchantment));
    }
    @Inject(method = "isAvailableForRandomSelection", at = @At("HEAD"), cancellable = true)
    private void isDiscoverable(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = EnchantmentVisionable.get_vision(enchantment);
        if (enchantment_vision != null && enchantment_vision.get_tradeable(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_tradeable(enchantment));
    }
    @Inject(method = "isAvailableForEnchantedBookOffer", at = @At("HEAD"), cancellable = true)
    private void isTradeable(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = EnchantmentVisionable.get_vision(enchantment);
        if (enchantment_vision != null && enchantment_vision.get_tradeable(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_tradeable(enchantment));
    }
    @Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
    private void isTreasureOnly(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = EnchantmentVisionable.get_vision(enchantment);
        if (enchantment_vision != null && enchantment_vision.get_treasure(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_treasure(enchantment));
    }
}
