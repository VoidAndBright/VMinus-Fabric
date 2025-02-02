package net.lixir.vminus.vision.condition;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;

public class ItemCondition implements Condition<Item>{
    public String is_mod_loaded;
    public boolean inverted;

    public ItemCondition(boolean inverted, String is_mod_loaded) {
        this.inverted = inverted;
        this.is_mod_loaded = is_mod_loaded;
    }

    public boolean is_false(Item item){
        if (FabricLoader.getInstance().isModLoaded(is_mod_loaded))
            return !inverted;
        return true;
    }
}
