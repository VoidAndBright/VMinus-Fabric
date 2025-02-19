package net.lixir.vminus.mixin.enchantment;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.Vision;
import net.lixir.vminus.vision.type.EnchantmentVision;
import net.lixir.vminus.vision.accessor.EnchantmentVisionAccessor;
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
public class EnchantmentMixin implements EnchantmentVisionAccessor {
    @Unique
    private EnchantmentVision enchantment_vision;
    @Unique
    private final Enchantment enchantment = (Enchantment) (Object) this;

    @Inject(method = "getMinLevel", at = @At("HEAD"), cancellable = true)
    private void getMinLevel(CallbackInfoReturnable<Integer> cir) {
        if (enchantment_vision != null && enchantment_vision.get_max_level(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_max_level(enchantment));
    }

    @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
    private void getMaxLevel(CallbackInfoReturnable<Integer> cir) {
        if (enchantment_vision != null && enchantment_vision.get_max_level(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_max_level(enchantment));
    }

    @Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
    private void isTreasureOnly(CallbackInfoReturnable<Boolean> cir) {
        if (enchantment_vision != null && enchantment_vision.get_treasure(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_treasure(enchantment));
    }

    @Inject(method = "isCursed", at = @At("HEAD"), cancellable = true)
    private void isCurse(CallbackInfoReturnable<Boolean> cir) {
        if (enchantment_vision != null && enchantment_vision.get_curse(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_curse(enchantment));
    }

    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void canEnchant(ItemStack itemstack, CallbackInfoReturnable<Boolean> cir) {
        Item item = itemstack.getItem();
        ItemVision item_vision = Vision.get_vision(item);
        if (item_vision != null && item_vision.get_enchantable(item) != null)
            cir.setReturnValue(item_vision.get_enchantable(item));
    }

    @Inject(method = "canCombine", at = @At("HEAD"), cancellable = true)
    private void isCompatibleWith(Enchantment otherEnchantment, CallbackInfoReturnable<Boolean> cir) {
        if (enchantment_vision != null && enchantment_vision.get_tradeable(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_tradeable(enchantment));
    }

    @Inject(method = "isAvailableForEnchantedBookOffer", at = @At("HEAD"), cancellable = true)
    private void isTradeable(CallbackInfoReturnable<Boolean> cir) {
        if (enchantment_vision != null && enchantment_vision.get_tradeable(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_tradeable(enchantment));
    }

    @Inject(method = "isAvailableForRandomSelection", at = @At("HEAD"), cancellable = true)
    private void isDiscoverable(CallbackInfoReturnable<Boolean> cir) {
        if (enchantment_vision != null && enchantment_vision.get_tradeable(enchantment) != null)
            cir.setReturnValue(enchantment_vision.get_tradeable(enchantment));
    }

    @Inject(method = "getRarity", at = @At("HEAD"), cancellable = true)
    private void getRarity(CallbackInfoReturnable<Enchantment.Rarity> cir) {
        if (enchantment_vision != null && enchantment_vision.get_tradeable(enchantment) != null)
            cir.setReturnValue(VisionHelper.rarity(enchantment_vision.get_rarity(enchantment)));
    }

    @Override
    public EnchantmentVision vminus$get_vision() {
        return enchantment_vision;
    }

    @Override
    public void vminus$set_vision(EnchantmentVision enchantment_vision) {
        this.enchantment_vision = enchantment_vision;
    }
}
