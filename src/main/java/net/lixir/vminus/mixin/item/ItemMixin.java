package net.lixir.vminus.mixin.item;

import net.lixir.vminus.vision.type.ItemVision;
import net.lixir.vminus.vision.accessor.ItemVisionAccessor;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin implements ItemVisionAccessor {
    @Unique
    private ItemVision item_vision;

    @Override
    public ItemVision vminus$get_vision() {
        return item_vision;
    }

    @Override
    public void vminus$set_vision(ItemVision new_item_vision) {
        this.item_vision = new_item_vision;
    }

}
