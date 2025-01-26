package net.lixir.vminus.vision.properties;

import net.fabricmc.loader.api.FabricLoader;

public class ItemCondition implements Condition {
    String is_mod_loaded;
    boolean inverted;

    public ItemCondition(boolean inverted, String is_mod_loaded) {
        this.inverted = inverted;
        this.is_mod_loaded = is_mod_loaded;
    }

    public boolean is_true(){
        if (FabricLoader.getInstance().isModLoaded(is_mod_loaded))
            return !inverted;
        return true;
    }
}
