package net.lixir.vminus.mixin.enchantment;

import net.lixir.vminus.vision.implement.EnchantmentVisionable;
import net.lixir.vminus.vision.implement.ItemVisionable;
import net.lixir.vminus.vision.type.EnchantmentVision;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.enchantment.BindingCurseEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BindingCurseEnchantment.class)
public class BindingCurseEnchantmentMixin {
    @Unique
    private final Enchantment enchantment = (Enchantment) (Object) this;

    @Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
    private void isTreasureOnly(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = EnchantmentVisionable.get_vision(enchantment);
        cir.setReturnValue(enchantment_vision != null && enchantment_vision.get_curse(enchantment) != null ? cir.getReturnValue() : false);
    }

    @Inject(method = "isCursed", at = @At("HEAD"), cancellable = true)
    private void isCurse(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = EnchantmentVisionable.get_vision(enchantment);
        cir.setReturnValue(enchantment_vision != null && enchantment_vision.get_curse(enchantment) != null ? cir.getReturnValue() : false);
    }

    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void canEnchant(ItemStack itemstack, CallbackInfoReturnable<Boolean> cir) {
        Item item = itemstack.getItem();
        ItemVision item_vision = ItemVisionable.get_vision(item);
        if (item_vision != null && item_vision.get_enchantable(item) != null)
            cir.setReturnValue(item_vision.get_enchantable(item));
    }
}
