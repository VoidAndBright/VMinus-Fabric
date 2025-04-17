package net.lixir.vminus.mixin.enchantment;

import net.lixir.vminus.vision.direct.EnchantmentVisionable;
import net.lixir.vminus.vision.type.EnchantmentVision;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Vector;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "getPossibleEntries", at = @At("HEAD"), cancellable = true)
    private static void getAvailableEnchantmentResults(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        Vector<EnchantmentLevelEntry> vector = new Vector<>();
        for (Enchantment enchantment : Registries.ENCHANTMENT) {
            if ((!enchantment.isTreasure() || treasureAllowed) && enchantment.isAvailableForRandomSelection() && (enchantment.isAcceptableItem(stack) || (stack.isOf(Items.BOOK) && enchantment.isAvailableForEnchantedBookOffer()))) {
                for (int index = enchantment.getMaxLevel(); index >= enchantment.getMinLevel(); --index) {
                    if (enchantment.getMinPower(index) <= power && power <= enchantment.getMaxPower(index)) {
                        EnchantmentVision enchantment_vision = EnchantmentVisionable.get_vision(enchantment);
                        if (enchantment_vision != null && enchantment_vision.get_banned(enchantment) != null && enchantment_vision.get_banned(enchantment)) continue;
                        vector.add(new EnchantmentLevelEntry(enchantment, index));
                        break;
                    }
                }
            }
        }
        cir.setReturnValue(vector);
    }
    /**
     * @author lixir
     * @reason To make getBlockEfficiency always return 0
     * in favor of adding attribute modifiers
     */
    @Overwrite
    public static int getEfficiency(LivingEntity entity) {
        return 0;
    }
}
