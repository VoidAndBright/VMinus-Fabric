/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.lixir.vminus.registry;

import net.lixir.vminus.VMinusMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VMinusAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, VMinusMod.MODID);
    public static final RegistryObject<Attribute> PROTECTION            = ATTRIBUTES.register("protection"             , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".protection"            , 0, -100, 100)).setSyncable(true));
    public static final RegistryObject<Attribute> BLAST_PROTECTION      = ATTRIBUTES.register("blast_protection"       , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".blast_protection"      , 0, -100, 100)).setSyncable(true));
    public static final RegistryObject<Attribute> MINING_SPEED          = ATTRIBUTES.register("mining_speed"           , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".mining_speed"          , 0, -255, 255)).setSyncable(true));
    public static final RegistryObject<Attribute> MAGIC_PROTECTION      = ATTRIBUTES.register("magic_protection"       , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".magic_protection"      , 0, -100, 100)).setSyncable(true));
    public static final RegistryObject<Attribute> FALL_PROTECTION       = ATTRIBUTES.register("fall_protection"        , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".fall_protection"       , 0, -100, 100)).setSyncable(true));
    public static final RegistryObject<Attribute> BLUNT_PROTECTION      = ATTRIBUTES.register("blunt_protection"       , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".blunt_protection"      , 0, -100, 100)).setSyncable(true));
    public static final RegistryObject<Attribute> FIRE_PROTECTION       = ATTRIBUTES.register("fire_protection"        , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".fire_protection"       , 0, -100, 100)).setSyncable(true));
    public static final RegistryObject<Attribute> CRITICAL_DAMAGE       = ATTRIBUTES.register("critical_damage"        , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".critical_damage"       , 0, 0, 100))   .setSyncable(true));
    public static final RegistryObject<Attribute> PROJECTILE_DAMAGE     = ATTRIBUTES.register("projectile_damage"      , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".projectile_damage"     , 0, -255, 255)).setSyncable(true));
    public static final RegistryObject<Attribute> MOB_DETECTION_RANGE   = ATTRIBUTES.register("mob_detection_range"    , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".mob_detection_range"   , 0, -100, 0))  .setSyncable(true));
    public static final RegistryObject<Attribute> HEALTH_LOST_STAT_BOOST= ATTRIBUTES.register("health_lost_stat_boost" , () -> (new RangedAttribute("attribute." + VMinusMod.MODID + ".health_lost_stat_boost", 0, -100, 100)).setSyncable(true));

    
    public static void register(FMLConstructModEvent event) {
        event.enqueueWork(() -> {
            ATTRIBUTES.register(FMLJavaModLoadingContext.get().getModEventBus());
        });
    }

    
    public static void addAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, PROTECTION.get());
        event.add(EntityType.PLAYER, BLASTPROTECTION.get());
        event.add(EntityType.PLAYER, MAGICPROTECTION.get());
        event.add(EntityType.PLAYER, FALLPROTECTION.get());
        event.add(EntityType.PLAYER, BLUNTPROTECTION.get());
        event.add(EntityType.PLAYER, FIREPROTECTION.get());
        event.add(EntityType.PLAYER, MOBDETECTIONRANGE.get());
    }
}
