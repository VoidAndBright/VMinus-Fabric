package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.vision.implement.ItemVisionable;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Unique
    ItemEntity item_entity = (ItemEntity)(Object)this;
    @Inject(method = "isFireImmune",at = @At("HEAD"), cancellable = true)
    private void fireImmune(CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemstack = item_entity.getStack();
        ItemVision item_vision = ItemVisionable.get_vision(itemstack.getItem());
        if (item_vision != null && item_vision.get_fire_resistant(itemstack.getItem()) != null) {
            cir.setReturnValue(item_vision.get_fire_resistant(itemstack.getItem()));
        }
    }
}
