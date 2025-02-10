package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.value.enchantment.EnchantmentVisionBoolean;
import net.lixir.vminus.vision.value.enchantment.EnchantmentVisionInteger;
import net.lixir.vminus.vision.value.enchantment.EnchantmentVisionString;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import java.util.Vector;

public class EnchantmentVision implements Vision<Enchantment>{
    private final String[] enchantments;
    private final EnchantmentVisionBoolean[] banned;
    private final EnchantmentVisionBoolean[] curse;
    private final EnchantmentVisionBoolean[] treasure;
    private final EnchantmentVisionBoolean[] tradeable;
    private final EnchantmentVisionInteger[] min_level;
    private final EnchantmentVisionInteger[] max_level;
    private final EnchantmentVisionString[] rarity;

    public EnchantmentVision(EnchantmentVision enchantment_vision) {
        this.enchantments = new String[]{};
        this.banned = enchantment_vision.banned;
        this.curse = enchantment_vision.curse;
        this.treasure = enchantment_vision.treasure;
        this.tradeable = enchantment_vision.tradeable;
        this.min_level = enchantment_vision.min_level;
        this.max_level = enchantment_vision.max_level;
        this.rarity = enchantment_vision.rarity;
    }
    public Boolean get_banned(Enchantment enchantment){
        return get_value(enchantment,banned);
    }
    public Boolean get_curse(Enchantment enchantment){
        return get_value(enchantment,curse);
    }
    public Boolean get_treasure(Enchantment enchantment){
        return get_value(enchantment,treasure);
    }
    public Boolean get_tradeable(Enchantment enchantment){
        return get_value(enchantment,tradeable);
    }
    public Integer get_min_level(Enchantment enchantment){
        return get_value(enchantment,min_level);
    }
    public Integer get_max_level(Enchantment enchantment){
        return get_value(enchantment,max_level);
    }
    public String get_rarity(Enchantment enchantment){
        return get_value(enchantment,rarity);
    }


    public String[] get_targets() {
        return get_targets(new Vector<>(),enchantments,0, Registries.ENCHANTMENT, RegistryKeys.ENCHANTMENT);
    }
}
