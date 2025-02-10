package net.lixir.vminus.mixin.enchantment;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.EnchantmentVision;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Unique
    private final Enchantment THIS = (Enchantment) (Object) this;

    @Inject(method = "getMinLevel", at = @At("HEAD"), cancellable = true)
    private void getMinLevel(CallbackInfoReturnable<Integer> cir) {
        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(THIS);
        if (enchantment_vision != null && enchantment_vision.get_max_level(THIS) != null)
            cir.setReturnValue(enchantment_vision.get_max_level(THIS));
    }

    @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
    private void getMaxLevel(CallbackInfoReturnable<Integer> cir) {
        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(THIS);
        if (enchantment_vision != null && enchantment_vision.get_max_level(THIS) != null)
            cir.setReturnValue(enchantment_vision.get_max_level(THIS));
    }

    @Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
    private void isTreasureOnly(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(THIS);
        if (enchantment_vision != null && enchantment_vision.get_treasure(THIS) != null)
            cir.setReturnValue(enchantment_vision.get_treasure(THIS));
    }

    @Inject(method = "isCursed", at = @At("HEAD"), cancellable = true)
    private void isCurse(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(THIS);
        if (enchantment_vision != null && enchantment_vision.get_curse(THIS) != null)
            cir.setReturnValue(enchantment_vision.get_curse(THIS));
    }

    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void canEnchant(ItemStack itemstack, CallbackInfoReturnable<Boolean> cir) {
        Item item = itemstack.getItem();
        ItemVision item_vision = Visions.get_item_vision(item);
        if (item_vision != null && item_vision.get_enchantable(item) != null)
            cir.setReturnValue(item_vision.get_enchantable(item));
    }

    @Inject(method = "canCombine", at = @At("HEAD"), cancellable = true)
    private void isCompatibleWith(Enchantment otherEnchantment, CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(THIS);
        if (enchantment_vision != null && enchantment_vision.get_tradeable(THIS) != null)
            cir.setReturnValue(enchantment_vision.get_tradeable(THIS));
    }

    @Inject(method = "isAvailableForEnchantedBookOffer", at = @At("HEAD"), cancellable = true)
    private void isTradeable(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(THIS);
        if (enchantment_vision != null && enchantment_vision.get_tradeable(THIS) != null)
            cir.setReturnValue(enchantment_vision.get_tradeable(THIS));
    }

    @Inject(method = "isAvailableForRandomSelection", at = @At("HEAD"), cancellable = true)
    private void isDiscoverable(CallbackInfoReturnable<Boolean> cir) {
        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(THIS);
        if (enchantment_vision != null && enchantment_vision.get_tradeable(THIS) != null)
            cir.setReturnValue(enchantment_vision.get_tradeable(THIS));
    }

    @Inject(method = "getRarity", at = @At("HEAD"), cancellable = true)
    private void getRarity(CallbackInfoReturnable<Enchantment.Rarity> cir) {
        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(THIS);
        if (enchantment_vision != null && enchantment_vision.get_tradeable(THIS) != null)
            cir.setReturnValue(VisionHelper.rarity(enchantment_vision.get_rarity(THIS)));
    }
}
