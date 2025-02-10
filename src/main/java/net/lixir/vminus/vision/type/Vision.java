package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public interface Vision<V>{
    default <T,TV extends VisionValue<T,V>> T get_value(V vision_type,TV[] vision_elements){
        return iterate_get_value(vision_type,vision_elements,0);
    }
    default <T,TV extends VisionValue<T,V>> T iterate_get_value(V vision_type,TV[] vision_elements, int index){
        if (vision_elements != null && index < vision_elements.length){
            TV vision_element = vision_elements[index];
            if (vision_element.is_conditions_true(vision_type, vision_element.get_conditions(),0)) return vision_element.get_value();
            else return iterate_get_value(vision_type,vision_elements,index+1);
        }
        return null;
    }
    default String[] get_targets(Vector<String> targets, String[] entries, int index, Registry<V> registry, RegistryKey<Registry<V>> registry_key){
        if (index < entries.length){
            String entry = entries[index];
            if (entry.startsWith("!#")) {
                String block_tag =  entry.substring(2);
                TagKey<V> block_tag_key = TagKey.of(registry_key, new Identifier(block_tag));
                registry.iterateEntries(block_tag_key).forEach(block_registry_entry -> targets.remove(registry.getId(block_registry_entry.value()).toString()));
            }
            else if (entry.startsWith("#")) {
                String block_tag = entry.substring(1);
                TagKey<V> block_tag_key = TagKey.of(registry_key, new Identifier(block_tag));
                registry.iterateEntries(block_tag_key).forEach(block_registry_entry -> targets.add(registry.getId(block_registry_entry.value()).toString()));
            }
            else if (entry.startsWith("!")) targets.remove(new Identifier(entry.substring(1)).toString());
            else targets.add(new Identifier(entry).toString());
            return get_targets(targets,entries,index+1,registry,registry_key);
        }
        else return targets.toArray(String[]::new);
    }
    default <T,TV extends VisionValue<T,V>> Vector<TV> group_vision_values(TV[] left, TV[] right){
        if (left != null && right != null){
            final Vector<TV> banned = new Vector<>(List.of(left));
            banned.addAll(List.of(right));
            banned.sort(Comparator.comparingInt(TV::get_priority));
            return banned;
        }
        else if (left == null && right != null) return new Vector<>(List.of(right));
        else if (left != null) return new Vector<>(List.of(left));
        else return new Vector<>();
    }
}
