package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.entity.EntityVariantAccessor;
import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.EntityTypeVision;
import net.lixir.vminus.vision.type.StatusEffectVision;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements EntityVariantAccessor {
    @Shadow @Final
    private Map<StatusEffect, StatusEffectInstance> activeStatusEffects;

    @Shadow
    protected abstract void spawnItemParticles(ItemStack stack, int count);

    @Shadow
    public abstract Map<StatusEffect, StatusEffectInstance> getActiveStatusEffects();

    @Shadow
    protected abstract void onStatusEffectUpgraded(StatusEffectInstance effect, boolean reapplyEffect, @Nullable Entity source);

    @Shadow
    public abstract net.minecraft.util.math.random.Random getRandom();

    @Shadow
    public abstract Collection<StatusEffectInstance> getStatusEffects();

    @Shadow
    private boolean effectsChanged;

    @Shadow
    protected abstract void updatePotionVisibility();

    @Shadow
    protected abstract void updateGlowing();

    @Shadow
    protected abstract void onStatusEffectRemoved(StatusEffectInstance effect);

    @Unique
    private final LivingEntity THIS = (LivingEntity) (Object) this;
    @Unique
    String variant;
    @Unique
    private final EntityType<?> ENTITY_TYPE = THIS.getType();
    @Inject(method = "getLootTable", at = @At("HEAD"), cancellable = true)
    public void getLootTable(CallbackInfoReturnable<Identifier> cir) {
        String entity_name = Registries.ENTITY_TYPE.getId(ENTITY_TYPE).getPath();
        if (!variant.equals("normal"))
            cir.setReturnValue(new Identifier("vminus:entities/variant/" + entity_name + "/" + variant));
    }
    @Inject(method = "tickStatusEffects", at = @At("HEAD"), cancellable = true)
    private void tickStatusEffects(CallbackInfo ci) {
        World world = THIS.getWorld();
        Iterator<StatusEffect> iterator = getActiveStatusEffects().keySet().iterator();
        while (iterator.hasNext()) {
            StatusEffect status_effect = iterator.next();
            StatusEffectInstance status_effect_instance = getActiveStatusEffects().get(status_effect);
            if (!status_effect_instance.update(THIS, () -> onStatusEffectUpgraded(status_effect_instance, true, null))) {
                if (!world.isClient()) {
                    iterator.remove();
                    onStatusEffectRemoved(status_effect_instance);
                }
            } else if (status_effect_instance.getDuration() % 600 == 0) {
                onStatusEffectUpgraded(status_effect_instance, false, null);
            }
        }
        if (effectsChanged) {
            if (!world.isClient()) {
                updatePotionVisibility();
                updateGlowing();
            }
            effectsChanged = false;
        }
        int colour = -1;
        boolean benefical = false;

        for (StatusEffectInstance status_effect_instance : getActiveStatusEffects().values()) {
            StatusEffect status_effect = status_effect_instance.getEffectType();
            if (!status_effect.isInstant()) {
                StatusEffectVision status_effect_vision = Visions.get_status_effect_vision(status_effect);
                if (status_effect_vision == null || status_effect_vision.get_particle(status_effect) == null) {
                    colour = status_effect.getColor();
                    benefical = status_effect.getCategory() == StatusEffectCategory.BENEFICIAL;
                    break;
                }
            }
        }

        if (colour > 0) {
            Random random = getRandom();
            boolean triggered = random.nextBoolean();

            if (benefical) {
                triggered &= random.nextInt(8) == 0;
            }

            if (triggered) {
                double d0 = (colour >> 16 & 255) / 255.0D;
                double d1 = (colour >> 8 & 255) / 255.0D;
                double d2 = (colour & 255) / 255.0D;
                THIS.getWorld().addParticle(benefical ? ParticleTypes.AMBIENT_ENTITY_EFFECT : ParticleTypes.ENTITY_EFFECT,
                        THIS.getParticleX(0.5D), THIS.getRandomBodyY(), THIS.getParticleZ(0.5D), d0, d1, d2);
            }
        }
        ci.cancel();
    }


    @Inject(method = "hurtByWater", at = @At("HEAD"), cancellable = true)
    private void isSensitiveToWater(CallbackInfoReturnable<Boolean> cir) {
        EntityTypeVision entity_type_vision = Visions.get_entity_type_vision(ENTITY_TYPE);
        if (entity_type_vision != null && entity_type_vision.get_water_sensitive(ENTITY_TYPE) != null)
            cir.setReturnValue(entity_type_vision.get_water_sensitive(ENTITY_TYPE));
    }
    @Inject(method = "canBreatheInWater", at = @At("HEAD"), cancellable = true)
    private void return_underwater_breathing(CallbackInfoReturnable<Boolean> cir) {
        EntityTypeVision entity_type_vision = Visions.get_entity_type_vision(ENTITY_TYPE);
        if (entity_type_vision != null && entity_type_vision.get_underwater_breathing(ENTITY_TYPE) != null)
            cir.setReturnValue(entity_type_vision.get_underwater_breathing(ENTITY_TYPE));
    }
    @Inject(method = "getSoundVolume", at = @At("HEAD"), cancellable = true)
    private void getSoundVolume(CallbackInfoReturnable<Float> cir) {
        EntityTypeVision entity_type_vision = Visions.get_entity_type_vision(ENTITY_TYPE);
        if (entity_type_vision != null && entity_type_vision.get_volume(ENTITY_TYPE) != null)
            cir.setReturnValue(Math.max(0f, entity_type_vision.get_volume(ENTITY_TYPE)));
    }
    @Inject(method = "getXpToDrop", at = @At("HEAD"), cancellable = true)
    private void return_exp_amount(CallbackInfoReturnable<Integer> cir) {
        EntityTypeVision entity_type_vision = Visions.get_entity_type_vision(ENTITY_TYPE);
        if (entity_type_vision != null && entity_type_vision.get_exp_drop_amount(ENTITY_TYPE) != null)
            cir.setReturnValue(Math.max(0, entity_type_vision.get_exp_drop_amount(ENTITY_TYPE)));
    }
    @Inject(method = "writeCustomDataToNbt",at = @At("HEAD"))
    private void write_variant_nbt(NbtCompound nbt, CallbackInfo ci){
        nbt.putString("Variant",variant != null ? variant : "normal");
        VMinus.LOGGER.info(variant != null ? variant : "normal");
    }
    @Inject(method = "readCustomDataFromNbt",at = @At("HEAD"))
    private void read_variant_nbt(NbtCompound nbt, CallbackInfo ci){
        this.variant = !Objects.equals(nbt.getString("Variant"), "") ? nbt.getString("Variant") : "normal";
        VMinus.LOGGER.info(variant);
    }
    public void vminus$set_variant(String new_variant) {
        variant = new_variant;
        VMinus.LOGGER.info(variant);
    }
    public String vminus$get_variant(){
        VMinus.LOGGER.info(variant);
        return variant;
    }
}
