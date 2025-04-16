package net.lixir.vminus.vision.implement;

import net.lixir.vminus.vision.type.EnchantmentVision;
import net.minecraft.enchantment.Enchantment;

public interface EnchantmentVisionable {
    EnchantmentVision vminus$get_vision();
    void vminus$set_vision(EnchantmentVision enchantment_vision);
    static <T extends Enchantment> EnchantmentVision get_vision(T enchantment){
        return ((EnchantmentVisionable)enchantment).vminus$get_vision();
    }
    static <T extends Enchantment> void set_vision(T enchantment, EnchantmentVision enchantment_vision){
        ((EnchantmentVisionable)enchantment).vminus$set_vision(enchantment_vision);
    }
}
