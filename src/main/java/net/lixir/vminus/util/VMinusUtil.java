package net.lixir.vminus.util;

import net.lixir.vminus.mixin.util.RarityAccessor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.Collection;

public class VMinusUtil {
    public static Rarity new_rarity(Formatting formatting){
        return RarityAccessor.rarity_constructor("",0,formatting);
    }
}
