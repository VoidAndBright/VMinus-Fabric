package net.lixir.vminus.mixin.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.StewItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StewItem.class)
public class StewItemMixin {
    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    public void vminus$finishUsingItem(ItemStack itemstack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
            itemstack.decrement(1);
            ItemStack bowl = new ItemStack(Items.BOWL);
            if (!player.getInventory().insertStack(bowl)) {
                player.dropItem(bowl, false);
            }
        }
        cir.setReturnValue(itemstack);
    }
}
