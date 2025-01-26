package net.lixir.vminus.mixin.recipe;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpecialCraftingRecipe.class)
public abstract class SpecialCraftingRecipeMixin {

    @Shadow public abstract ItemStack getOutput(DynamicRegistryManager registryManager);

    @Inject(method = "getOutput",at=@At("HEAD"), cancellable = true)
    private void returnResult(DynamicRegistryManager registryManager, CallbackInfoReturnable<ItemStack> cir){
        ItemVision item_vision = Visions.get_item_vision(cir.getReturnValue().getItem());
        if(item_vision != null)
            if(item_vision.get_replacement() != null) cir.setReturnValue(new ItemStack(VisionHelper.item(item_vision.get_replacement()),cir.getReturnValue().getCount()));
            else if(item_vision.get_banned() != null && item_vision.get_banned()) cir.setReturnValue(ItemStack.EMPTY);
    }
}
