package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.value.enchantment.EnchantmentVisionBoolean;
import net.lixir.vminus.vision.value.enchantment.EnchantmentVisionInteger;
import net.lixir.vminus.vision.value.enchantment.EnchantmentVisionString;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Vector;

public class EnchantmentVision{
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
    public EnchantmentVision(EnchantmentVision left_vision,EnchantmentVision right_vision) {
        this.enchantments = new String[]{};
        this.banned = VisionHelper.collect_vision_values(left_vision.banned,right_vision.banned,EnchantmentVisionBoolean[]::new);
        this.curse = VisionHelper.collect_vision_values(left_vision.curse,right_vision.curse,EnchantmentVisionBoolean[]::new);
        this.treasure = VisionHelper.collect_vision_values(left_vision.treasure,right_vision.treasure,EnchantmentVisionBoolean[]::new);
        this.tradeable = VisionHelper.collect_vision_values(left_vision.tradeable,right_vision.tradeable,EnchantmentVisionBoolean[]::new);
        this.min_level = VisionHelper.collect_vision_values(left_vision.min_level,right_vision.min_level,EnchantmentVisionInteger[]::new);
        this.max_level = VisionHelper.collect_vision_values(left_vision.max_level,right_vision.max_level,EnchantmentVisionInteger[]::new);
        this.rarity = VisionHelper.collect_vision_values(left_vision.rarity,right_vision.rarity,EnchantmentVisionString[]::new);
    }
    public Boolean get_banned(Enchantment enchantment){
        return VisionHelper.vision_value(enchantment,banned);
    }
    public Boolean get_curse(Enchantment enchantment){
        return VisionHelper.vision_value(enchantment,curse);
    }
    public Boolean get_treasure(Enchantment enchantment){
        return VisionHelper.vision_value(enchantment,treasure);
    }
    public Boolean get_tradeable(Enchantment enchantment){
        return VisionHelper.vision_value(enchantment,tradeable);
    }
    public Integer get_min_level(Enchantment enchantment){
        return VisionHelper.vision_value(enchantment,min_level);
    }
    public Integer get_max_level(Enchantment enchantment){
        return VisionHelper.vision_value(enchantment,max_level);
    }
    public String get_rarity(Enchantment enchantment){
        return VisionHelper.vision_value(enchantment,rarity);
    }

    public Enchantment[] get_enchantments(Vector<Enchantment> vector_enchantments,int index){
        if (index < enchantments.length){
            String enchantment_entry = enchantments[index];
            if (enchantment_entry.startsWith("*")) return Registries.ENCHANTMENT.stream().toArray(Enchantment[]::new);
            else if (enchantment_entry.startsWith("!#")) {
                String block_tag =  enchantment_entry.substring(2);
                TagKey<Enchantment> block_tag_key = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(block_tag));
                vector_enchantments.removeAll(Registries.ENCHANTMENT.getOrCreateEntryList(block_tag_key).stream().map(RegistryEntry::value).toList());
            }
            else if (enchantment_entry.startsWith("#")) {
                String block_tag = enchantment_entry.substring(1);
                TagKey<Enchantment> block_tag_key = TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier(block_tag));
                vector_enchantments.addAll(Registries.ENCHANTMENT.getOrCreateEntryList(block_tag_key).stream().map(RegistryEntry::value).toList());
            }
            else if (enchantment_entry.startsWith("!")) vector_enchantments.remove(Registries.ENCHANTMENT.get(new Identifier(enchantment_entry.substring(1))));
            else vector_enchantments.add(Registries.ENCHANTMENT.get(new Identifier(enchantment_entry)));
            return get_enchantments(vector_enchantments,index+1);
        }
        return vector_enchantments.toArray(Enchantment[]::new);
    }
}
