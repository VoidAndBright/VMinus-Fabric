package net.lixir.vminus.vision.direct;

import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.item.Item;

public interface ItemVisionable {
    ItemVision get_vision();
    void set_vision(ItemVision item_vision);
    static <T extends Item> ItemVisionable convert(T item) {
        return (ItemVisionable)item;
    }
    static <T extends Item> ItemVision get_vision(T item){
        return convert(item).get_vision();
    }
    static <T extends Item> void set_vision(T item, ItemVision item_vision){
        convert(item).set_vision(item_vision);
    }
}
