package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.entity.EntityVariantAccessor;
import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.EntityTypeVision;
import net.lixir.vminus.vision.type.StatusEffectVision;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements EntityVariantAccessor {
    @Unique
    private final LivingEntity THIS = (LivingEntity) (Object) this;
    @Unique
    private final LivingEntityAccessor ACCESSOR = (LivingEntityAccessor)this;
    @Unique
    String variant;
    @Inject(method = "getLootTable", at = @At("HEAD"), cancellable = true)
    public void getLootTable(CallbackInfoReturnable<Identifier> cir) {
        String entity_name = Registries.ENTITY_TYPE.getId(THIS.getType()).getPath();
        if (!variant.equals("normal"))
            cir.setReturnValue(new Identifier("vminus:entities/variant/" + entity_name + "/" + variant));
    }
    @Inject(method = "tickStatusEffects", at = @At("HEAD"), cancellable = true)
    private void tickStatusEffects(CallbackInfo ci) {
        World world = THIS.getWorld();
        Iterator<StatusEffect> iterator = THIS.getActiveStatusEffects().keySet().iterator();
        while (iterator.hasNext()) {
            StatusEffect status_effect = iterator.next();
            StatusEffectInstance status_effect_instance = THIS.getActiveStatusEffects().get(status_effect);
            if (!status_effect_instance.update(THIS, () -> ACCESSOR.on_effect_upgraded(status_effect_instance, true, null))) {
                if (!world.isClient()) {
                    iterator.remove();
                    ACCESSOR.on_effect_removed(status_effect_instance);
                }
            } else if (status_effect_instance.getDuration() % 600 == 0) {
                ACCESSOR.on_effect_upgraded(status_effect_instance, false, null);
            }
        }
        if (ACCESSOR.get_effects_changed()) {
            if (!world.isClient()) {
                ACCESSOR.update_potion_visibility();
                ACCESSOR.update_glowing();
            }
            ACCESSOR.set_effects_changed(false);
        }
        int colour = -1;
        boolean beneficial = false;

        for (StatusEffectInstance status_effect_instance : THIS.getActiveStatusEffects().values()) {
            StatusEffect status_effect = status_effect_instance.getEffectType();
            if (!status_effect.isInstant()) {
                StatusEffectVision status_effect_vision = Visions.get_status_effect_vision(status_effect);
                if (status_effect_vision == null || status_effect_vision.get_particle(status_effect) == null) {
                    colour = status_effect.getColor();
                    beneficial = status_effect.getCategory() == StatusEffectCategory.BENEFICIAL;
                    break;
                }
            }
        }

        if (colour > 0) {
            Random random = THIS.getRandom();
            boolean triggered = random.nextBoolean();

            if (beneficial) {
                triggered &= random.nextInt(8) == 0;
            }

            if (triggered) {
                double d0 = (colour >> 16 & 255) / 255.0D;
                double d1 = (colour >> 8 & 255) / 255.0D;
                double d2 = (colour & 255) / 255.0D;
                THIS.getWorld().addParticle(beneficial ? ParticleTypes.AMBIENT_ENTITY_EFFECT : ParticleTypes.ENTITY_EFFECT,
                        THIS.getParticleX(0.5D), THIS.getRandomBodyY(), THIS.getParticleZ(0.5D), d0, d1, d2);
            }
        }
        ci.cancel();
    }


    @Inject(method = "hurtByWater", at = @At("HEAD"), cancellable = true)
    private void isSensitiveToWater(CallbackInfoReturnable<Boolean> cir) {
        EntityType<?> entity_type = THIS.getType();
        EntityTypeVision entity_type_vision = Visions.get_entity_type_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_water_sensitive(entity_type) != null)
            cir.setReturnValue(entity_type_vision.get_water_sensitive(entity_type));
    }
    @Inject(method = "canBreatheInWater", at = @At("HEAD"), cancellable = true)
    private void return_underwater_breathing(CallbackInfoReturnable<Boolean> cir) {
        EntityType<?> entity_type = THIS.getType();
        EntityTypeVision entity_type_vision = Visions.get_entity_type_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_underwater_breathing(entity_type) != null)
            cir.setReturnValue(entity_type_vision.get_underwater_breathing(entity_type));
    }
    @Inject(method = "getSoundVolume", at = @At("HEAD"), cancellable = true)
    private void getSoundVolume(CallbackInfoReturnable<Float> cir) {
        EntityType<?> entity_type = THIS.getType();
        EntityTypeVision entity_type_vision = Visions.get_entity_type_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_volume(entity_type) != null)
            cir.setReturnValue(Math.max(0f, entity_type_vision.get_volume(entity_type)));
    }
    @Inject(method = "getXpToDrop", at = @At("HEAD"), cancellable = true)
    private void return_exp_amount(CallbackInfoReturnable<Integer> cir) {
        EntityType<?> entity_type = THIS.getType();
        EntityTypeVision entity_type_vision = Visions.get_entity_type_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_exp_drop_amount(entity_type) != null)
            cir.setReturnValue(Math.max(0, entity_type_vision.get_exp_drop_amount(entity_type)));
    }
    @Inject(method = "writeCustomDataToNbt",at = @At("HEAD"))
    private void write_variant_nbt(NbtCompound nbt, CallbackInfo ci){
        nbt.putString("Variant",variant != null ? variant : "normal");
        VMinus.LOGGER.info(variant != null ? variant : "normal");
    }
    @Inject(method = "readCustomDataFromNbt",at = @At("HEAD"))
    private void read_variant_nbt(NbtCompound nbt, CallbackInfo ci){
        this.variant = nbt.getString("Variant") != "" ? nbt.getString("Variant") : "normal";
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
