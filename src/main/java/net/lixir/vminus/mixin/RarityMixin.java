package net.lixir.vminus.mixin;

import net.lixir.vminus.util.VMinusFormatting;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Vector;

@Mixin(Rarity.class)
public class RarityMixin {
    @Mutable
    @Shadow
    @Final
    private static Rarity[] field_8905;
    @Unique
    private static final Rarity LEGENDARY = rarity_constructor("LEGENDARY", Formatting.GOLD);
    @Unique
    private static final Rarity INVERTED = rarity_constructor("INVERTED", Formatting.DARK_AQUA);
    @Unique
    private static final Rarity UNOBTAINABLE = rarity_constructor("UNOBTAINABLE", VMinusFormatting.INDIGO);
    @Unique
    private static final Rarity DELICACY = rarity_constructor("DELICACY", VMinusFormatting.PINK);
    @Invoker("<init>")
    private static Rarity rarity_constructor(String name, int id, Formatting formatting) {
        throw new AssertionError();
    }
    @Unique
    private static Rarity rarity_constructor(String name, Formatting formatting) {
        assert field_8905 != null;
        Vector<Rarity> rarities = new Vector<>(List.of(field_8905));
        Rarity rarity = rarity_constructor(name, rarities.size(), formatting);
        rarities.add(rarity);
        field_8905 = rarities.toArray(Rarity[]::new);
        return rarity;
    }
}
