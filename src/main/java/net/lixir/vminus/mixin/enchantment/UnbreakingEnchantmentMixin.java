package net.lixir.vminus.mixin.enchantment;

import net.lixir.vminus.vision.direct.EnchantmentVisionable;
import net.lixir.vminus.vision.direct.ItemVisionable;
import net.lixir.vminus.vision.type.EnchantmentVision;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(UnbreakingEnchantment.class)
public class UnbreakingEnchantmentMixin {
    @Unique
    private final Enchantment enchantment = (Enchantment) (Object) this;

    @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
    private void getMaxLevel(CallbackInfoReturnable<Integer> cir) {
        EnchantmentVision enchantment_vision = EnchantmentVisionable.get_vision(enchantment);
        if (enchantment_vision != null && enchantment_vision.get_max_level(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_max_level(enchantment));
    }
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void can_enchant(ItemStack itemstack, CallbackInfoReturnable<Boolean> cir) {
        Item item = itemstack.getItem();
        ItemVision item_vision = ItemVisionable.get_vision(item);
        cir.setReturnValue(item_vision != null && item_vision.get_enchantable(item) != null ? cir.getReturnValue(): false);
    }
}
