package net.lixir.vminus.mixin.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public class BucketItemMixin {
    @Inject(method = "getEmptiedStack", at = @At("HEAD"), cancellable = true)
    private static void getEmptySuccessItem(ItemStack itemStack, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        if (itemStack.getCount() == 1)
            return;
        if (!player.getAbilities().creativeMode) {
            if (itemStack.getItem() instanceof BucketItem) {
                ItemStack emptyBucket = new ItemStack(Items.BUCKET);
                if (!player.getInventory().insertStack(emptyBucket)) {
                    player.dropItem(emptyBucket, false);
                }
                itemStack.decrement(1);
                cir.setReturnValue(itemStack);
            }
        }
    }
}
