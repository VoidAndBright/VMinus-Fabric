package net.lixir.vminus.mixin.recipe;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.Vision;
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

    @Shadow public abstract boolean isEmpty();

    @Inject(method = "test(Lnet/minecraft/item/ItemStack;)Z",at=@At("HEAD"), cancellable = true)
    public void test(ItemStack itemstack, CallbackInfoReturnable<Boolean> cir){
        if (itemstack == null) cir.setReturnValue(false);
        else if (this.isEmpty()) cir.setReturnValue(itemstack.isEmpty());
        else cir.setReturnValue(matchesIngredient(itemstack));
    }
    @Unique
    private boolean matchesIngredient(ItemStack targetStack) {
        return iterate_entries(entries,targetStack,0);
    }
    @Unique
    private boolean iterate_entries(Ingredient.Entry[] entries,ItemStack target_itemstack, int index){
        if (index < entries.length) {
            if (iterate_itemstack(entries[index].getStacks().toArray(ItemStack[]::new), target_itemstack,0)) return true;
            return iterate_entries(entries,target_itemstack,index+1);
        }
        else return false;
    }
    @Unique
    private boolean iterate_itemstack(ItemStack[] itemstacks,ItemStack target_itemstack,int index){
        if (index < itemstacks.length){
            Item item = itemstacks[index].getItem();
            ItemVision itemVision = Vision.get_vision(item);
            if (itemVision != null)
                if(itemVision.get_replacement(item) != null && itemVision.get_replacement(item) == target_itemstack.getItem()) return true;
                else if (itemVision.get_banned(item) != null && !itemVision.get_banned(item) && itemstacks[index].isOf(target_itemstack.getItem())) return true;
            return iterate_itemstack(itemstacks,target_itemstack,index+1);
        }
        else return false;
    }
}
