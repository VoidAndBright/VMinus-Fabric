package net.lixir.vminus.mixin.item;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Collection;

@Mixin(ItemStack.class)
public interface ItemStackAccessor {
    @Invoker("parseBlockTag")
    static Collection<Text> parse_block_tag(String tag){
        throw new AssertionError();
    }

    @Invoker("getHideFlags")
    int get_hide_flags();

    @Accessor("count")
    void set_count(int value);
}