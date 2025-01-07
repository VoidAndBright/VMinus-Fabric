package lixir.vminus.mixin;

import com.google.gson.JsonObject;
import lixir.vminus.vision.ItemVision;
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

public abstract class ItemGroupsMixin {
    @Shadow private Collection<ItemStack> displayStacks;
    @Shadow private Set<ItemStack> searchTabStacks;

    @Inject(method = "updateEntries",at = @At(value = "TAIL"))
    private void updateEntries(ItemGroup.DisplayContext displayContext, CallbackInfo ci){
        List<ItemStack> bannedItems = new ArrayList<>();
        for(ItemStack itemstack : this.searchTabStacks) {
            ItemVision itemVision = Visions.getItemVision(itemstack.getItem());
            if (itemVision != null) {
                bannedItems.add(itemstack);
            }
        }
        bannedItems.forEach(this.searchTabStacks::remove);
        bannedItems.clear();
        for(ItemStack itemstack : this.displayStacks) {
            ItemVision itemVision = Visions.getItemVision(itemstack.getItem());
            if (itemVision != null) {
                bannedItems.add(itemstack);
            }
        }
        bannedItems.forEach(this.displayStacks::remove);
    }
}
