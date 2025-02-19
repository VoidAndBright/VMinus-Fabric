package net.lixir.vminus.attribute;


import net.lixir.vminus.VMinus;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VMinusAttributes {
    public static final String MOMENTUM_NBT_KEY = "MomentumStored";
    public static final String TRANSLUCENCE_KEY = "Translucency";
    public static final double MOMENTUM_DECAY_RATE = 0.003;
    public static final double MOMENTUM_BUILDUP_RATE = 0.001;
    public static final float TRANSLUCENCY_RATE = 0.01f;
    public static final String MOMENTUM_SPEED_NAME = "Momentum Speed Boost";

    public static final EntityAttribute PROTECTION = register_attribute("protection", new ClampedEntityAttribute(translation_key("protection"),0,-100,100)).setTracked(true);
    public static final EntityAttribute BLAST_PROTECTION = register_attribute("blast_protection", new ClampedEntityAttribute(translation_key("blast_protection"),0,-100,100)).setTracked(true);
    public static final EntityAttribute MINING_SPEED = register_attribute("mining_speed", new ClampedEntityAttribute(translation_key("mining_speed"),0,-255,255)).setTracked(true);
    public static final EntityAttribute MAGIC_PROTECTION = register_attribute("magic_protection", new ClampedEntityAttribute(translation_key("magic_protection"),0,-100,100)).setTracked(true);
    public static final EntityAttribute FALL_PROTECTION = register_attribute("fall_protection", new ClampedEntityAttribute(translation_key("fall_protection"),0,-100,100)).setTracked(true);
    public static final EntityAttribute BLUNT_PROTECTION = register_attribute("blunt_protection", new ClampedEntityAttribute(translation_key("blunt_protection"),0,-100,100)).setTracked(true);
    public static final EntityAttribute FIRE_PROTECTION = register_attribute("fire_protection", new ClampedEntityAttribute(translation_key("fire_protection"),0,-100,100)).setTracked(true);
    public static final EntityAttribute CRITICAL_DAMAGE = register_attribute("critical_damage", new ClampedEntityAttribute(translation_key("critical_damage"),0,0,100)).setTracked(true);
    public static final EntityAttribute MOB_DETECTION_RANGE = register_attribute("mob_detection_range", new ClampedEntityAttribute(translation_key("mob_detection_range"),0,-100,0)).setTracked(true);
    public static final EntityAttribute HEALTH_LOST_STAT_BOOST = register_attribute("health_lost_stat_boost", new ClampedEntityAttribute(translation_key("health_lost_stat_boost"),0, -100, 100)).setTracked(true);
    public static final EntityAttribute MOMENTUM = register_attribute("momentum", new ClampedEntityAttribute(translation_key("momentum"), 0, 0, 100)).setTracked(true);
    public static final EntityAttribute TRANSLUCENCE = register_attribute("translucence", new ClampedEntityAttribute(translation_key("translucence"),0, 0, 100)).setTracked(true);
    private static String translation_key(String name){
        return "attribute." + VMinus.MOD_ID + "." + name;
    }
    private static <T extends EntityAttribute> T register_attribute(String name, T attribute) {
        return Registry.register(Registries.ATTRIBUTE,new Identifier(VMinus.MOD_ID,name),attribute);
    }
    public static void register(){}
}
