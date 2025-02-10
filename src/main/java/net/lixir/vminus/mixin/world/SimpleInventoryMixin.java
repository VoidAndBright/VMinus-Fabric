package net.lixir.vminus.mixin.world;

import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleInventory.class)
public class SimpleInventoryMixin {

    @Inject(method = "addStack", at = @At("HEAD"), cancellable = true)
    private void add_stack(ItemStack itemstack, CallbackInfoReturnable<ItemStack> cir) {
        Item item = itemstack.getItem();
        ItemVision item_vision = Visions.get_item_vision(item);
        if (item_vision != null && item_vision.get_banned(item) != null && item_vision.get_banned(item)) cir.setReturnValue(ItemStack.EMPTY);
    }
    @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
    private void can_insert(ItemStack itemstack, CallbackInfoReturnable<Boolean> cir) {
        Item item = itemstack.getItem();
        ItemVision item_vision = Visions.get_item_vision(itemstack.getItem());
        if (item_vision != null && item_vision.get_banned(item) != null) cir.setReturnValue(!item_vision.get_banned(item));
    }
    @Inject(method = "setStack", at = @At("HEAD"), cancellable = true)
    private void set_stack(int slot, ItemStack itemstack, CallbackInfo ci) {
        Item item = itemstack.getItem();
        ItemVision item_vision = Visions.get_item_vision(itemstack.getItem());
        if (item_vision != null && item_vision.get_banned(item) != null && item_vision.get_banned(item)) ci.cancel();
    }
}
