package net.lixir.vminus.vision.implement;

import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.item.Item;

public interface ItemVisionable {
    ItemVision vminus$get_vision();
    void vminus$set_vision(ItemVision item_vision);
    static <T extends Item> ItemVision get_vision(T item){
        return ((ItemVisionable)item).vminus$get_vision();
    }
    static <T extends Item> void set_vision(T item, ItemVision item_vision){
        ((ItemVisionable)item).vminus$set_vision(item_vision);
    }
}
