package net.lixir.vminus.mixin.enchantment;

import net.lixir.vminus.vision.Visions;
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
    private final Enchantment THIS = (Enchantment) (Object) this;

    @Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
    private void isTreasureOnly(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(THIS);
        cir.setReturnValue(enchantment_vision != null && enchantment_vision.get_curse(THIS) != null ? cir.getReturnValue() : false);
    }

    @Inject(method = "isCursed", at = @At("HEAD"), cancellable = true)
    private void isCurse(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(THIS);
        cir.setReturnValue(enchantment_vision != null && enchantment_vision.get_curse(THIS) != null ? cir.getReturnValue() : false);
    }

    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void canEnchant(ItemStack itemstack, CallbackInfoReturnable<Boolean> cir) {
        Item item = itemstack.getItem();
        ItemVision item_vision = Visions.get_item_vision(item);
        if (item_vision != null && item_vision.get_enchantable(item) != null)
            cir.setReturnValue(item_vision.get_enchantable(item));
    }
}
