package net.lixir.vminus.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
    @Accessor("activeStatusEffects")
    Map<StatusEffect, StatusEffectInstance> getActiveEffects();

    @Accessor("effectsChanged")
    boolean hasEffectsChanged();

    @Accessor("effectsChanged")
    void setEffectsChanged(boolean effectsDirty);

    @Invoker("onStatusEffectUpgraded")
    void invokeOnEffectUpdated(StatusEffectInstance instance, boolean apply, @Nullable Entity source);

    @Invoker("onStatusEffectRemoved")
    void invokeOnEffectRemoved(StatusEffectInstance instance);

    @Invoker("updatePotionVisibility")
    void invokeUpdateInvisibilityStatus();

    @Invoker("updateGlowing")
    void invokeUpdateGlowingStatus();
}
