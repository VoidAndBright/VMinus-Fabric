package net.lixir.vminus.vision.condition;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.effect.StatusEffect;

public class StatusEffectCondition implements Condition<StatusEffect>{
    public String is_mod_loaded;
    public boolean inverted;

    public StatusEffectCondition(boolean inverted, String is_mod_loaded) {
        this.inverted = inverted;
        this.is_mod_loaded = is_mod_loaded;
    }

    public boolean is_false(StatusEffect status_effect) {
        if (is_mod_loaded != null && FabricLoader.getInstance().isModLoaded(is_mod_loaded)) return inverted;
        return true;
    }
}
