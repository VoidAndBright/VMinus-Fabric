package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.Vision;
import net.lixir.vminus.vision.value.status_effect.StatusEffectVisionBoolean;
import net.lixir.vminus.vision.value.status_effect.StatusEffectVisionString;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Vector;

public class StatusEffectVision {
    private final String[] status_effects;
    private final Vector<StatusEffectVisionBoolean> banned;
    private final Vector<StatusEffectVisionString> particle;
    private final Vector<StatusEffectVisionString> color;
    private final Vector<StatusEffectVisionString> category;

    public StatusEffectVision(StatusEffectVision vision_left,StatusEffectVision vision_right){
        this.status_effects = new String[]{};
        this.banned = Vision.sum(vision_left.banned,vision_right.banned);
        this.particle = Vision.sum(vision_left.particle,vision_right.particle);
        this.color = Vision.sum(vision_left.color,vision_right.color);
        this.category = Vision.sum(vision_left.category,vision_right.category);
    }
    public Boolean get_banned(StatusEffect status_effect){
        return Vision.unwrap(status_effect,banned);
    }
    public String get_particle(StatusEffect status_effect){
        return Vision.unwrap(status_effect,particle);
    }
    public Integer get_color(StatusEffect status_effect){
        String string = Vision.unwrap(status_effect,color);
        if (string == null) return null;
        return Integer.parseInt(string,16);
    }
    public StatusEffectCategory get_category(StatusEffect status_effect){
        String string = Vision.unwrap(status_effect,category);
        if (string == null) return null;
        return switch (string){
            case "harmful"->StatusEffectCategory.HARMFUL;
            case "neutral"->StatusEffectCategory.NEUTRAL;
            default -> StatusEffectCategory.BENEFICIAL;
        };
    }

    public StatusEffect[] get_status_effects(){
        Vector<StatusEffect> vector_enchantments = new Vector<>();
        for (String status_effect_entry:status_effects){
            if (status_effect_entry.startsWith("*")){
                vector_enchantments.addAll(Registries.STATUS_EFFECT.stream().toList());
            }
            if (status_effect_entry.startsWith("!#")) {
                String item_tag = status_effect_entry.substring(2);
                TagKey<StatusEffect> item_tag_key = TagKey.of(RegistryKeys.STATUS_EFFECT, new Identifier(item_tag));
                if (Registries.STATUS_EFFECT.getEntryList(item_tag_key).isPresent())
                    vector_enchantments.removeAll(Registries.STATUS_EFFECT.getOrCreateEntryList(item_tag_key).stream().map(RegistryEntry::value).toList());
            }
            else if (status_effect_entry.startsWith("#")) {
                String item_tag = status_effect_entry.substring(1);
                TagKey<StatusEffect> item_tag_key = TagKey.of(RegistryKeys.STATUS_EFFECT, new Identifier(item_tag));
                if (Registries.STATUS_EFFECT.getEntryList(item_tag_key).isPresent())
                    vector_enchantments.addAll(Registries.STATUS_EFFECT.getOrCreateEntryList(item_tag_key).stream().map(RegistryEntry::value).toList());
            }
            else if (status_effect_entry.startsWith("!")) vector_enchantments.remove(Registries.STATUS_EFFECT.get(new Identifier(status_effect_entry.substring(1))));
            else vector_enchantments.add(Registries.STATUS_EFFECT.get(new Identifier(status_effect_entry)));
        }
        return vector_enchantments.toArray(StatusEffect[]::new);
    }
}
