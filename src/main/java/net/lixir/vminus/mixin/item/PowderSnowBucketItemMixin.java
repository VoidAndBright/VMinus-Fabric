package net.lixir.vminus.mixin.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBucketItem.class)
public class PowderSnowBucketItemMixin extends BlockItem {
    public PowderSnowBucketItemMixin(Block block, Settings settings) {
        super(block, settings);
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void useOn(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        ItemStack itemStack = context.getStack();
        if (itemStack.getMaxCount() == 1)
            return;
        ActionResult action_result = super.useOnBlock(context);
        if (action_result.isAccepted() && player != null && !player.isCreative()) {
            ItemStack emptyBucket = new ItemStack(Items.BUCKET);
            if (!player.getInventory().insertStack(emptyBucket)) {
                player.dropItem(emptyBucket, false);
            }
        }
        cir.setReturnValue(action_result);
    }
}
