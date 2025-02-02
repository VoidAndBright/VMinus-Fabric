package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.value.VisionValue;
import net.lixir.vminus.vision.value.enchantment.EnchantmentVisionBoolean;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import java.util.Vector;

public class EnchantmentVision implements Vision<Enchantment>{
    private final String[] enchantments;
    private final EnchantmentVisionBoolean[] banned;

    public EnchantmentVision(EnchantmentVision enchantment_vision) {
        this.enchantments = new String[]{};
        this.banned = enchantment_vision.banned;
    }
    public Boolean get_banned(){
        return get_value(banned);
    }

    public String[] get_targets() {
        return refine_entries(new Vector<>(),enchantments,0, Registries.ENCHANTMENT, RegistryKeys.ENCHANTMENT);
    }

    public Enchantment get_vision_type() {
        return null;
    }
}
