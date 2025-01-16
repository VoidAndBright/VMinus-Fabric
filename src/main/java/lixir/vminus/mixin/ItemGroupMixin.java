package lixir.vminus.mixin;

import lixir.vminus.vision.type.ItemVision;
import lixir.vminus.vision.Visions;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mixin(ItemGroup.class)

public abstract class ItemGroupMixin {
    @Shadow private Collection<ItemStack> displayStacks;
    @Shadow private Set<ItemStack> searchTabStacks;
    @Inject(method = "updateEntries",at = @At(value = "TAIL"))
    private void updateEntries(ItemGroup.DisplayContext displayContext, CallbackInfo ci){
        List<ItemStack> banned_items = new ArrayList<>();
        for(ItemStack itemstack : this.searchTabStacks) {
            ItemVision itemVision = Visions.get_item_vision(itemstack.getItem());
            if (itemVision != null && itemVision.get_banned() != null && itemVision.get_banned()) {
                banned_items.add(itemstack);
            }
        }
        banned_items.forEach(searchTabStacks::remove);
        banned_items.forEach(displayStacks::remove);
    }
}
