package net.lixir.vminus.mixin.world;

import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @Inject(method = "addStack(Lnet/minecraft/item/ItemStack;)I", at = @At("RETURN"), cancellable = true)
    private void addStack(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        ItemVision item_vision = Visions.get_item_vision(stack.getItem());
        if (item_vision != null && item_vision.get_banned() != null && item_vision.get_banned()) cir.setReturnValue(-1);
    }
    @Inject(method = "addStack(ILnet/minecraft/item/ItemStack;)I", at = @At("RETURN"), cancellable = true)
    private void addStack(int slot, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        ItemVision item_vision = Visions.get_item_vision(stack.getItem());
        if (item_vision != null && item_vision.get_banned() != null && item_vision.get_banned()) cir.setReturnValue(-1);
    }
    @Inject(method = "getOccupiedSlotWithRoomForStack", at = @At("HEAD"), cancellable = true)
    private void getOccupiedSlotWithRoomForStack(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        ItemVision item_vision = Visions.get_item_vision(stack.getItem());
        if (item_vision != null && item_vision.get_banned() != null && item_vision.get_banned()) cir.setReturnValue(-1);
    }
    @Inject(method = "insertStack(Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void insertStack(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        ItemVision item_vision = Visions.get_item_vision(stack.getItem());
        if (item_vision != null && item_vision.get_banned() != null) cir.setReturnValue(item_vision.get_banned());
    }
    @Inject(method = "insertStack(ILnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void insertStack(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        ItemVision item_vision = Visions.get_item_vision(stack.getItem());
        if (item_vision != null && item_vision.get_banned() != null) cir.setReturnValue(item_vision.get_banned());
    }
    @Inject(method = "offerOrDrop", at = @At("HEAD"), cancellable = true)
    private void offerOrDrop(ItemStack stack, CallbackInfo ci) {
        ItemVision item_vision = Visions.get_item_vision(stack.getItem());
        if (item_vision != null && item_vision.get_banned() != null && item_vision.get_banned()) ci.cancel();
    }
    @Inject(method = "offer", at = @At("HEAD"), cancellable = true)
    private void offer(ItemStack stack, boolean drop, CallbackInfo ci) {
        ItemVision item_vision = Visions.get_item_vision(stack.getItem());
        if (item_vision != null && item_vision.get_banned() != null && item_vision.get_banned()) ci.cancel();
    }
}
