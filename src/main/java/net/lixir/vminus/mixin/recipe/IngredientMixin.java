package net.lixir.vminus.mixin.recipe;

import net.lixir.vminus.vision.direct.ItemVisionable;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Ingredient.class)
public abstract class IngredientMixin {
    @Shadow @Final
    private Ingredient.Entry[] entries;

    @Unique
    private final Ingredient ingredient = (Ingredient)(Object)this;

    @Inject(method = "test(Lnet/minecraft/item/ItemStack;)Z",at=@At("HEAD"), cancellable = true)
    public void test(ItemStack itemstack, CallbackInfoReturnable<Boolean> cir){
        if (itemstack == null) cir.setReturnValue(false);
        else if (ingredient.isEmpty()) cir.setReturnValue(false);
        else cir.setReturnValue(matches_ingredient(itemstack));
    }
    @Unique
    private boolean matches_ingredient(ItemStack target_itemstack) {
        for (Ingredient.Entry o:entries){
            for (ItemStack item_stack:o.getStacks()){
                Item item = item_stack.getItem();
                ItemVision itemVision = ItemVisionable.get_vision(item);
                if (itemVision != null)
                    if(itemVision.get_replacement(item) != null && itemVision.get_replacement(item) == target_itemstack.getItem()) return true;
                    else if (itemVision.get_banned(item) != null && !itemVision.get_banned(item) && item_stack.isOf(target_itemstack.getItem())) return true;
            }
        }
        return false;
    }
}
