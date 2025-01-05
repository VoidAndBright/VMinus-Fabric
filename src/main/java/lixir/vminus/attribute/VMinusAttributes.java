package lixir.vminus.attribute;


import lixir.vminus.VMinus;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VMinusAttributes {
    public static final EntityAttribute PROTECTION =       registerAttribute("protection"      , new ClampedEntityAttribute("attribute." + VMinus.MOD_ID + ".protection"      ,0,-100,100)).setTracked(true);
    public static final EntityAttribute BLAST_PROTECTION = registerAttribute("blast_protection", new ClampedEntityAttribute("attribute." + VMinus.MOD_ID + ".blast_protection",0,-100,100)).setTracked(true);
    public static final EntityAttribute MINING_SPEED =     registerAttribute("mining_speed"    , new ClampedEntityAttribute("attribute." + VMinus.MOD_ID + ".mining_speed"    ,0,-255,255)).setTracked(true);
    public static final EntityAttribute MAGIC_PROTECTION = registerAttribute("magic_protection", new ClampedEntityAttribute("attribute." + VMinus.MOD_ID + ".magic_protection",0,-100,100)).setTracked(true);
    public static final EntityAttribute FALL_PROTECTION =  registerAttribute("fall_protection" , new ClampedEntityAttribute("attribute." + VMinus.MOD_ID + ".fall_protection" ,0,-100,100)).setTracked(true);
    public static final EntityAttribute BLUNT_PROTECTION = registerAttribute("blunt_protection", new ClampedEntityAttribute("attribute." + VMinus.MOD_ID + ".blunt_protection",0,-100,100)).setTracked(true);
    public static final EntityAttribute FIRE_PROTECTION =  registerAttribute("fire_protection" , new ClampedEntityAttribute("attribute." + VMinus.MOD_ID + ".fire_protection" ,0,-100,100)).setTracked(true);
    public static final EntityAttribute CRITICAL_DAMAGE =  registerAttribute("critical_damage" , new ClampedEntityAttribute("attribute." + VMinus.MOD_ID + ".critical_damage" ,0,0,100)).setTracked(true);

    static <T extends EntityAttribute> T registerAttribute(String name, T attribute){
        return Registry.register(Registries.ATTRIBUTE,new Identifier(VMinus.MOD_ID,name),attribute);
    }
}
