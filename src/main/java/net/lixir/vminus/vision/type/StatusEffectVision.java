package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.value.status_effect.StatusEffectVisionBoolean;
import net.lixir.vminus.vision.value.status_effect.StatusEffectVisionString;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Vector;

public class StatusEffectVision {
    private final String[] status_effects;
    private final StatusEffectVisionBoolean[] banned;
    private final StatusEffectVisionString[] particle;
    private final StatusEffectVisionString[] color;
    private final StatusEffectVisionString[] category;

    public StatusEffectVision(StatusEffectVision vision_left,StatusEffectVision vision_right){
        this.status_effects = new String[]{};
        this.banned = VisionHelper.collect_vision_values(vision_left.banned,vision_right.banned).toArray(StatusEffectVisionBoolean[]::new);
        this.particle = VisionHelper.collect_vision_values(vision_left.particle,vision_right.particle).toArray(StatusEffectVisionString[]::new);
        this.color = VisionHelper.collect_vision_values(vision_left.color,vision_right.color).toArray(StatusEffectVisionString[]::new);
        this.category = VisionHelper.collect_vision_values(vision_left.category,vision_right.category).toArray(StatusEffectVisionString[]::new);
    }
    public Boolean get_banned(StatusEffect status_effect){
        return VisionHelper.vision_value(status_effect,banned);
    }
    public String get_particle(StatusEffect status_effect){
        return VisionHelper.vision_value(status_effect,particle);
    }
    public String get_color(StatusEffect status_effect){
        return VisionHelper.vision_value(status_effect,color);
    }
    public String get_category(StatusEffect status_effect){
        return VisionHelper.vision_value(status_effect,category);
    }

    public StatusEffect[] get_status_effects(Vector<StatusEffect> vector_enchantments,int index){
        if (index < status_effects.length){
            String status_effect_entry = status_effects[index];
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
            return get_status_effects(vector_enchantments,index+1);
        }
        else return vector_enchantments.toArray(StatusEffect[]::new);
    }
}
