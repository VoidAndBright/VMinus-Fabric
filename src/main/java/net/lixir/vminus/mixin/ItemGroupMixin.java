package net.lixir.vminus.mixin;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.type.ItemVision;
import net.lixir.vminus.vision.Visions;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(ItemGroup.class)

public class ItemGroupMixin {
    @Shadow
    private Collection<ItemStack> displayStacks;
    @Shadow
    private Set<ItemStack> searchTabStacks;
    @Inject(method = "updateEntries",at = @At("TAIL"))
    private void updateEntries(ItemGroup.DisplayContext displayContext, CallbackInfo ci){
        Set<ItemStack> search_tab_stacks = ItemStackSet.create();
        for (ItemStack itemstack : searchTabStacks) {
            ItemVision itemVision = Visions.get_item_vision(itemstack.getItem());
            if (itemVision != null && itemVision.get_replacement() != null) search_tab_stacks.add(VisionHelper.itemstack(itemVision.get_replacement(),1));
            else if (itemVision == null || itemVision.get_banned() == null || !itemVision.get_banned()) search_tab_stacks.add(itemstack);
        }
        searchTabStacks = search_tab_stacks;
        Collection<ItemStack> display_stacks = ItemStackSet.create();
        for (ItemStack itemstack : displayStacks) {
            ItemVision itemVision = Visions.get_item_vision(itemstack.getItem());
            if (itemVision != null && itemVision.get_replacement() != null) display_stacks.add(VisionHelper.itemstack(itemVision.get_replacement(),1));
            else if (itemVision == null || itemVision.get_banned() == null || !itemVision.get_banned()) display_stacks.add(itemstack);
        }
        displayStacks = display_stacks;
    }
    @Unique
    private <T extends Collection<ItemStack>> T replace_or_remove_itemstacks(Iterator<ItemStack> itemstacks, T new_collection){
        if(itemstacks.hasNext()){
            ItemStack itemstack = itemstacks.next();
            new_collection.add(itemstack);
            return replace_or_remove_itemstacks(itemstacks,new_collection);
        }
        else return new_collection;
    }
}
