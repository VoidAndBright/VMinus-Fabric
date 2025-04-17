package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.Vision;
import net.lixir.vminus.vision.value.enchantment.EnchantmentVisionBoolean;
import net.lixir.vminus.vision.value.enchantment.EnchantmentVisionInteger;
import net.lixir.vminus.vision.value.enchantment.EnchantmentVisionString;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Vector;

public class EnchantmentVision{
    private final String[] enchantments;
    private final Vector<EnchantmentVisionBoolean> banned;
    private final Vector<EnchantmentVisionBoolean> curse;
    private final Vector<EnchantmentVisionBoolean> treasure;
    private final Vector<EnchantmentVisionBoolean> tradeable;
    private final Vector<EnchantmentVisionInteger> min_level;
    private final Vector<EnchantmentVisionInteger> max_level;
    private final Vector<EnchantmentVisionString> rarity;

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
        this.banned = Vision.sum(left_vision.banned,right_vision.banned);
        this.curse = Vision.sum(left_vision.curse,right_vision.curse);
        this.treasure = Vision.sum(left_vision.treasure,right_vision.treasure);
        this.tradeable = Vision.sum(left_vision.tradeable,right_vision.tradeable);
        this.min_level = Vision.sum(left_vision.min_level,right_vision.min_level);
        this.max_level = Vision.sum(left_vision.max_level,right_vision.max_level);
        this.rarity = Vision.sum(left_vision.rarity,right_vision.rarity);
    }
    public Boolean get_banned(Enchantment enchantment){
        return Vision.unwrap(enchantment,banned);
    }
    public Boolean get_curse(Enchantment enchantment){
        return Vision.unwrap(enchantment,curse);
    }
    public Boolean get_treasure(Enchantment enchantment){
        return Vision.unwrap(enchantment,treasure);
    }
    public Boolean get_tradeable(Enchantment enchantment){
        return Vision.unwrap(enchantment,tradeable);
    }
    public Integer get_min_level(Enchantment enchantment){
        return Vision.unwrap(enchantment,min_level);
    }
    public Integer get_max_level(Enchantment enchantment){
        return Vision.unwrap(enchantment,max_level);
    }
    public Rarity get_rarity(Enchantment enchantment){
        String string = Vision.unwrap(enchantment,rarity);
        if (string == null) return null;
        return switch (string){
            case "uncommon" -> Rarity.UNCOMMON;
            case "rare" -> Rarity.RARE;
            case "very rare" -> Rarity.VERY_RARE;
            default -> Rarity.COMMON;
        };
    }

    public Vector<Enchantment> get_enchantments(){
        Vector<Enchantment> vector_enchantments = new Vector<>();
        for (String enchantment_entry:enchantments){
            if (enchantment_entry.startsWith("*")) {
                String block_wild_card = enchantment_entry.substring(1);
                for (Enchantment enchantment:Registries.ENCHANTMENT){
                    Identifier identifier = Registries.ENCHANTMENT.getId(enchantment);
                    if (identifier == null) break;
                    if (identifier.toString().endsWith(block_wild_card)){
                        vector_enchantments.add(enchantment);
                    }
                }
            }
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
        }
        return vector_enchantments;
    }
}
