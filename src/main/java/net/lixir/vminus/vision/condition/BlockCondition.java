package net.lixir.vminus.vision.condition;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;

public class BlockCondition implements Condition<Block>{
    public String is_mod_loaded;
    public boolean inverted;

    public BlockCondition(boolean inverted, String is_mod_loaded) {
        this.inverted = inverted;
        this.is_mod_loaded = is_mod_loaded;
    }

    public boolean is_false(Block block) {
        if (is_mod_loaded != null && FabricLoader.getInstance().isModLoaded(is_mod_loaded)) return inverted;
        return true;
    }
}
