package net.lixir.vminus.vision;

import net.lixir.vminus.block.property.AxisProperty;
import net.lixir.vminus.vision.properties.Attribute;
import net.lixir.vminus.vision.properties.SoundGroup;
import net.lixir.vminus.vision.value.VisionValue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.*;
import java.util.function.IntFunction;

public class VisionHelper {
    public static <T,V,TV extends VisionValue<T,V>> TV[] collect_vision_values(TV[] left, TV[] right, IntFunction<TV[]> array){
        if (left != null && right != null){
            final Vector<TV> values = new Vector<>(List.of(left));
            values.addAll(List.of(right));
            values.sort(Comparator.comparingInt(TV::get_priority));
            return values.toArray(array);
        }
        else if (left == null && right != null) return right;
        else return left;
    }
    public static <T,V,TV extends VisionValue<T,V>> T vision_value(V vision_type, TV[] vision_elements){
        return iterate_vision_values(vision_type,vision_elements,0);
    }
    private static <T,V,TV extends VisionValue<T,V>> T iterate_vision_values(V vision_type, TV[] vision_elements, int index){
        if (vision_elements == null) return null;
        if (index < vision_elements.length){
            TV vision_element = vision_elements[index];
            if (vision_element.is_conditions_true(vision_type, vision_element.get_conditions(),0)) return vision_element.get_value();
            else return iterate_vision_values(vision_type,vision_elements,index+1);
        }
        return null;
    }
    public static int hex(String hex) {
        if (hex.startsWith("#")) {
            return hex(hex.substring(1));
        }
        return Integer.parseInt(hex, 16);
    }
    public static Direction direction(String direction_string) {
        return Direction.valueOf(direction_string.toLowerCase());
    }
    public static BlockState new_direction(BlockState blockState, Direction direction) {
        Property<?> facing_property = blockState.getBlock().getStateManager().getProperty("facing");
        if (facing_property instanceof DirectionProperty directionProperty && directionProperty.getValues().contains(direction))
            return blockState.with(directionProperty, direction);
        Property<?> axis_property = blockState.getBlock().getStateManager().getProperty("axis");
        if (axis_property instanceof EnumProperty<?> enum_property && enum_property.getValues().contains(direction.getAxis())) {
            EnumProperty<Direction.Axis> direction_axis_property = (AxisProperty) enum_property;
            return blockState.with(direction_axis_property, direction.getAxis());
        }
        return null;
    }
    public static Item item(String string){
        if (string == null) return null;
        return Registries.ITEM.get(new Identifier(string));
    }
    public static Block block(String string){
        if (string == null) return null;
        return Registries.BLOCK.get(new Identifier(string));
    }

    public static StatusEffectCategory category(String category) {
        return StatusEffectCategory.valueOf(category.toUpperCase());
    }
    public static Enchantment.Rarity rarity(String rarity) {
        return Enchantment.Rarity.valueOf(rarity.toUpperCase());
    }
    public static BlockSoundGroup block_sound_group(SoundGroup sound_group) {
        if (sound_group == null) return null;
        float sound_event_volume = sound_group.volume != null ? sound_group.volume : 1f;
        float sound_event_pitch = sound_group.pitch != null ? sound_group.pitch : 1f;
        SoundEvent break_sound_event = sound_group.break_sound != null ? Registries.SOUND_EVENT.get(new Identifier(sound_group.break_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent step_sound_event = sound_group.step_sound != null ? Registries.SOUND_EVENT.get(new Identifier(sound_group.step_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent place_sound_event = sound_group.place_sound != null ? Registries.SOUND_EVENT.get(new Identifier(sound_group.place_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent hit_sound_event = sound_group.hit_sound != null ? Registries.SOUND_EVENT.get(new Identifier(sound_group.hit_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        SoundEvent fall_sound_event = sound_group.fall_sound != null ? Registries.SOUND_EVENT.get(new Identifier(sound_group.fall_sound)) : SoundEvents.BLOCK_STONE_BREAK;
        return new BlockSoundGroup(sound_event_volume, sound_event_pitch, break_sound_event, step_sound_event, place_sound_event, hit_sound_event, fall_sound_event);
    }
    public static EntityAttribute attribute(Attribute attribute){
        if (attribute.get_identifier() == null) return null;
        return Registries.ATTRIBUTE.get(new Identifier(attribute.get_identifier()));
    }
    public static EquipmentSlot equipment_slot(Attribute attribute){
        return EquipmentSlot.valueOf(attribute.get_slot().toUpperCase());
    }
    public static EntityAttributeModifier attribute_modifier(Attribute attribute){
        UUID uuid = attribute.get_uuid() != null ? UUID.fromString(attribute.get_uuid()) : UUID.randomUUID();
        EntityAttributeModifier.Operation operation = attribute.get_operation() != null ? EntityAttributeModifier.Operation.valueOf(attribute.get_operation().toUpperCase()) : EntityAttributeModifier.Operation.ADDITION;
        return new EntityAttributeModifier(uuid,attribute.get_name(),attribute.get_value(),operation);
    }
}
