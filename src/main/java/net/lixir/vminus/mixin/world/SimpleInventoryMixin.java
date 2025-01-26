package net.lixir.vminus.mixin.world;

import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleInventory.class)
public class SimpleInventoryMixin {

    @Inject(method = "addStack", at = @At("HEAD"), cancellable = true)
    private void add_stack(ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        ItemVision item_vision = Visions.get_item_vision(stack.getItem());
        if (item_vision != null && item_vision.get_banned() != null && item_vision.get_banned()) cir.setReturnValue(ItemStack.EMPTY);
    }
    @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
    private void can_insert(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        ItemVision item_vision = Visions.get_item_vision(stack.getItem());
        if (item_vision != null && item_vision.get_banned() != null) cir.setReturnValue(!item_vision.get_banned());
    }
    @Inject(method = "setStack", at = @At("HEAD"), cancellable = true)
    private void set_stack(int slot, ItemStack stack, CallbackInfo ci) {
        ItemVision item_vision = Visions.get_item_vision(stack.getItem());
        if (item_vision != null && item_vision.get_banned() != null && item_vision.get_banned()) ci.cancel();
    }
}
