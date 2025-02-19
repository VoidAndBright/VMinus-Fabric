package net.lixir.vminus.mixin.item;

import net.lixir.vminus.vision.Vision;
import net.lixir.vminus.vision.type.EnchantmentVision;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantedBookItem.class)
public class EnchantedBookItemMixin {
    @Inject(method = "addEnchantment", at = @At("HEAD"), cancellable = true)
    private static void addEnchantment(ItemStack stack, EnchantmentLevelEntry entry, CallbackInfo ci) {
        Enchantment enchantment = entry.enchantment;
        EnchantmentVision enchantment_vision = Vision.get_vision(enchantment);
        if (enchantment_vision != null && enchantment_vision.get_banned(enchantment) != null && enchantment_vision.get_banned(enchantment))
            ci.cancel();
    }
}
