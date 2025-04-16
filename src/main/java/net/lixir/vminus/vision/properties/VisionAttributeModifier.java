package net.lixir.vminus.vision.properties;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class VisionAttributeModifier {
    private final String identifier;
    private final String name;
    private final String operation;
    private final float value;
    private final String slot;
    private final String uuid;

    public VisionAttributeModifier(String identifier, String name, String operation, Float value, String slot, String uuid) {
        this.identifier = identifier;
        this.name = name;
        this.operation = operation;
        this.value = value;
        this.slot = slot;
        this.uuid = uuid;
    }

    public static EntityAttribute attribute(VisionAttributeModifier attribute){
        if (attribute.identifier == null) return null;
        return Registries.ATTRIBUTE.get(new Identifier(attribute.identifier));
    }
    public static EquipmentSlot equipment_slot(VisionAttributeModifier attribute){
        if (attribute.slot == null) return EquipmentSlot.MAINHAND;
        return switch (attribute.slot){
            case "offhand"->EquipmentSlot.OFFHAND;
            case "feet"->EquipmentSlot.FEET;
            case "legs"->EquipmentSlot.LEGS;
            case "chest"->EquipmentSlot.CHEST;
            case "head"->EquipmentSlot.HEAD;
            default -> EquipmentSlot.MAINHAND;
        };
    }
    public static EntityAttributeModifier attribute_modifier(VisionAttributeModifier attribute){
        UUID uuid = attribute.uuid != null ? UUID.fromString(attribute.uuid) : UUID.randomUUID();
        Operation operation = attribute.operation != null ? switch (attribute.operation){
            case "multiply base"-> Operation.MULTIPLY_BASE;
            case "multiply total"-> Operation.MULTIPLY_TOTAL;
            default -> Operation.ADDITION;
        } : Operation.ADDITION;
        return new EntityAttributeModifier(uuid,attribute.name,attribute.value,operation);
    }
}
