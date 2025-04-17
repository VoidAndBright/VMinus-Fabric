package net.lixir.vminus.vision.direct;

import net.lixir.vminus.vision.type.EnchantmentVision;
import net.minecraft.enchantment.Enchantment;

public interface EnchantmentVisionable {
    EnchantmentVision get_vision();
    void set_vision(EnchantmentVision enchantment_vision);
    static <T extends Enchantment> EnchantmentVisionable convert(T enchantment) {
        return (EnchantmentVisionable)enchantment;
    }
    static <T extends Enchantment> EnchantmentVision get_vision(T enchantment){
        return convert(enchantment).get_vision();
    }
    static <T extends Enchantment> void set_vision(T enchantment, EnchantmentVision enchantment_vision){
        convert(enchantment).set_vision(enchantment_vision);
    }
}
