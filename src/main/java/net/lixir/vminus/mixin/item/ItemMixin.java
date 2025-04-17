package net.lixir.vminus.mixin.item;

import net.lixir.vminus.vision.direct.ItemVisionable;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.class)
public class ItemMixin implements ItemVisionable {
    @Unique
    private ItemVision item_vision;

    @Override
    public ItemVision get_vision() {
        return item_vision;
    }

    @Override
    public void set_vision(ItemVision new_item_vision) {
        this.item_vision = new_item_vision;
    }

}
