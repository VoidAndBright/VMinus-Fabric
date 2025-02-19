package net.lixir.vminus.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {

    @Accessor("effectsChanged")
    boolean get_effects_changed();

    @Accessor("effectsChanged")
    void set_effects_changed(boolean effectsDirty);

    @Invoker("onStatusEffectUpgraded")
    void on_effect_upgraded(StatusEffectInstance instance, boolean apply, Entity source);

    @Invoker("onStatusEffectRemoved")
    void on_effect_removed(StatusEffectInstance instance);

    @Invoker("updatePotionVisibility")
    void update_potion_visibility();

    @Invoker("updateGlowing")
    void update_glowing();
}
