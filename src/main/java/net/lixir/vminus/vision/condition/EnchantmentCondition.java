package net.lixir.vminus.vision.condition;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;

public class EnchantmentCondition implements Condition<Enchantment> {
    public String is_mod_loaded;
    public boolean inverted;

    public EnchantmentCondition(boolean inverted, String is_mod_loaded) {
        this.inverted = inverted;
        this.is_mod_loaded = is_mod_loaded;
    }

    public boolean is_false(Enchantment enchantment) {
        if (is_mod_loaded != null && FabricLoader.getInstance().isModLoaded(is_mod_loaded)) return inverted;
        return true;
    }
}
