package net.lixir.vminus.mixin.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Collection;
import java.util.Set;

@Mixin(ItemGroup.class)
public interface ItemGroupAccessor {
    @Accessor("displayStacks")
    Collection<ItemStack> get_display_stacks();
    @Accessor("searchTabStacks")
    Set<ItemStack> get_search_tab_stacks();
    @Accessor("displayStacks")
    void set_display_stacks(Collection<ItemStack> new_search_tab_stacks);
    @Accessor("searchTabStacks")
    void set_search_tab_stacks(Set<ItemStack> new_search_tab_stacks);
}
