package lixir.vminus.vision.properties;

import net.fabricmc.loader.api.FabricLoader;

public class Condition {
    String is_mod_loaded;
    boolean inverted;

    public Condition(boolean inverted, String is_mod_loaded) {
        this.inverted = inverted;
        this.is_mod_loaded = is_mod_loaded;
    }

    public boolean is_true(){
        if (FabricLoader.getInstance().isModLoaded(is_mod_loaded))
            return !inverted;
        return true;
    }
}
