package net.lixir.vminus.mixin.recipe;

import net.lixir.vminus.vision.direct.ItemVisionable;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapelessRecipe.class)
public class ShapelessRecipeMixin {
    @Shadow @Final
    ItemStack output;
    @Inject(method = "getOutput", at = @At("RETURN"), cancellable = true)
    public void return_result_item(DynamicRegistryManager registryManager, CallbackInfoReturnable<ItemStack> cir) {
        Item item = output.getItem();
        ItemVision item_vision = ItemVisionable.get_vision(item);
        if(item_vision != null)
            if(item_vision.get_replacement(item) != null) cir.setReturnValue(new ItemStack(item_vision.get_replacement(item),output.getCount()));
            else if(item_vision.get_banned(item) != null && item_vision.get_banned(item)) cir.setReturnValue(ItemStack.EMPTY);
    }
}
