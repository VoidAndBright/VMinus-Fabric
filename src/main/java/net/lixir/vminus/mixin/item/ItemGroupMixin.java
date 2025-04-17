package net.lixir.vminus.mixin.item;

import net.lixir.vminus.vision.direct.ItemVisionable;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Set;

@Mixin(ItemGroup.class)

public class ItemGroupMixin {
    @Unique
    private final ItemGroup item_group = (ItemGroup) (Object) this;
    @Unique
    private final ItemGroupAccessor item_group_accessor = (ItemGroupAccessor)item_group;
    @Inject(method = "updateEntries",at = @At("TAIL"))
    private void updateEntries(ItemGroup.DisplayContext displayContext, CallbackInfo ci){
        Set<ItemStack> search_tab_stacks = ItemStackSet.create();
        search_tab_stacks.addAll(item_group_accessor.get_search_tab_stacks().stream().filter(ItemGroupMixin::filter_banned).map(ItemGroupMixin::replace_item_stacks).toList());
        item_group_accessor.set_search_tab_stacks(search_tab_stacks);
        Collection<ItemStack> display_stacks = ItemStackSet.create();
        display_stacks.addAll(item_group_accessor.get_display_stacks().stream().filter(ItemGroupMixin::filter_banned).map(ItemGroupMixin::replace_item_stacks).toList());
        item_group_accessor.set_display_stacks(display_stacks);
    }
    @Unique
    private static boolean filter_banned(ItemStack item_stack){
        Item item = item_stack.getItem();
        ItemVision item_vision = ItemVisionable.get_vision(item);
        return item_vision == null || item_vision.get_banned(item) == null || !item_vision.get_banned(item);
    }
    @Unique
    private static ItemStack replace_item_stacks(ItemStack item_stack){
        Item item = item_stack.getItem();
        ItemVision item_vision = ItemVisionable.get_vision(item);
        return item_vision != null && item_vision.get_replacement(item) != null ? new ItemStack(item_vision.get_replacement(item)) : item_stack;
    }
}
