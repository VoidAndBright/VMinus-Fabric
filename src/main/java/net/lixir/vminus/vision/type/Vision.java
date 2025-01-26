package net.lixir.vminus.vision.type;

import net.lixir.vminus.Functional;
import net.lixir.vminus.vision.VisionElement;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public interface Vision {
    String[] get_targets();
    default <T> T get_value(VisionElement<T>[] vision_elements){
        return iterate_vision_elements(vision_elements,0);
    }
    private <T> T iterate_vision_elements(VisionElement<T>[] vision_elements, int index){
        if (vision_elements != null && index < vision_elements.length){
            VisionElement<T> vision_element = vision_elements[index];
            if (vision_element.conditions_is_true()) return vision_element.get_value();
            else return iterate_vision_elements(vision_elements,index+1);
        }
        else return null;
    }
    default <T> String[] processed_entries(ArrayList<String> targets, String[] entries, int index, Registry<T> registry, RegistryKey<Registry<T>> registry_key){
        if (index < entries.length){
            String entry = entries[index];
            if (entry.startsWith("!#")) {
                String block_tag =  entry.substring(2);
                TagKey<T> block_tag_key = TagKey.of(registry_key, new Identifier(block_tag));
                Functional.for_each(registry.iterateEntries(block_tag_key).iterator(), block_registry_entry -> targets.remove(registry.getId(block_registry_entry.value()).toString()));
            }
            else if (entry.startsWith("#")) {
                String block_tag =  entry.substring(1);
                TagKey<T> block_tag_key = TagKey.of(registry_key, new Identifier(block_tag));
                Functional.for_each(registry.iterateEntries(block_tag_key).iterator(), block_registry_entry -> targets.add(registry.getId(block_registry_entry.value()).toString()));
            }
            else if (entry.startsWith("!")) {
                targets.remove(new Identifier(entry.substring(1)).toString());
            }
            else targets.add(new Identifier(entry).toString());
            return processed_entries(targets,entries,index+1,registry,registry_key);
        }
        else return targets.toArray(String[]::new);
    }
}
