package net.lixir.vminus.mixin.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public class MilkBucketItemMixin {
    @Inject(method = "finishUsing", at = @At("RETURN"))
    private void finishUsingItem(ItemStack itemstack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (itemstack.getMaxCount() == 1)
            return;
        if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode && !itemstack.isEmpty()) {
            ItemStack emptyBucket = new ItemStack(Items.BUCKET);
            if (!player.getInventory().insertStack(emptyBucket)) {
                player.dropItem(emptyBucket, false);
            }
        }
    }
}
