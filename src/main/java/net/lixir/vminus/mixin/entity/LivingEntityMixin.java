package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.VMinus;
import net.lixir.vminus.entity.EntityVariant;
import net.lixir.vminus.vision.Vision;
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
public abstract class LivingEntityMixin implements EntityVariant {
    @Unique
    private final LivingEntity living_entity = (LivingEntity) (Object) this;
    @Unique
    private final LivingEntityAccessor accessor = (LivingEntityAccessor)this;
    @Unique
    String variant;
    @Inject(method = "getLootTable", at = @At("HEAD"), cancellable = true)
    public void getLootTable(CallbackInfoReturnable<Identifier> cir) {
        String entity_name = Registries.ENTITY_TYPE.getId(living_entity.getType()).getPath();
        if (variant != "") cir.setReturnValue(new Identifier("vminus:entities/variant/" + entity_name + "/" + variant));
    }
    @Inject(method = "tickStatusEffects", at = @At("HEAD"), cancellable = true)
    private void tickStatusEffects(CallbackInfo ci) {
        World world = living_entity.getWorld();
        Iterator<StatusEffect> iterator = living_entity.getActiveStatusEffects().keySet().iterator();
        while (iterator.hasNext()) {
            StatusEffect status_effect = iterator.next();
            StatusEffectInstance status_effect_instance = living_entity.getActiveStatusEffects().get(status_effect);
            if (!status_effect_instance.update(living_entity, () -> accessor.on_effect_upgraded(status_effect_instance, true, null))) {
                if (!world.isClient()) {
                    iterator.remove();
                    accessor.on_effect_removed(status_effect_instance);
                }
            } else if (status_effect_instance.getDuration() % 600 == 0) {
                accessor.on_effect_upgraded(status_effect_instance, false, null);
            }
        }
        if (accessor.get_effects_changed()) {
            if (!world.isClient()) {
                accessor.update_potion_visibility();
                accessor.update_glowing();
            }
            accessor.set_effects_changed(false);
        }
        int colour = -1;
        boolean beneficial = false;

        for (StatusEffectInstance status_effect_instance : living_entity.getActiveStatusEffects().values()) {
            StatusEffect status_effect = status_effect_instance.getEffectType();
            if (!status_effect.isInstant()) {
                StatusEffectVision status_effect_vision = Vision.get_vision(status_effect);
                if (status_effect_vision == null || status_effect_vision.get_particle(status_effect) == null) {
                    colour = status_effect.getColor();
                    beneficial = status_effect.getCategory() == StatusEffectCategory.BENEFICIAL;
                    break;
                }
            }
        }

        if (colour > 0) {
            Random random = living_entity.getRandom();
            boolean triggered = random.nextBoolean();

            if (beneficial) {
                triggered &= random.nextInt(8) == 0;
            }

            if (triggered) {
                double d0 = (colour >> 16 & 255) / 255.0D;
                double d1 = (colour >> 8 & 255) / 255.0D;
                double d2 = (colour & 255) / 255.0D;
                living_entity.getWorld().addParticle(beneficial ? ParticleTypes.AMBIENT_ENTITY_EFFECT : ParticleTypes.ENTITY_EFFECT,
                        living_entity.getParticleX(0.5D), living_entity.getRandomBodyY(), living_entity.getParticleZ(0.5D), d0, d1, d2);
            }
        }
        ci.cancel();
    }


    @Inject(method = "hurtByWater", at = @At("HEAD"), cancellable = true)
    private void isSensitiveToWater(CallbackInfoReturnable<Boolean> cir) {
        EntityType<?> entity_type = living_entity.getType();
        EntityTypeVision entity_type_vision = Vision.get_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_water_sensitive(entity_type) != null)
            cir.setReturnValue(entity_type_vision.get_water_sensitive(entity_type));
    }
    @Inject(method = "canBreatheInWater", at = @At("HEAD"), cancellable = true)
    private void return_underwater_breathing(CallbackInfoReturnable<Boolean> cir) {
        EntityType<?> entity_type = living_entity.getType();
        EntityTypeVision entity_type_vision = Vision.get_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_underwater_breathing(entity_type) != null)
            cir.setReturnValue(entity_type_vision.get_underwater_breathing(entity_type));
    }
    @Inject(method = "getSoundVolume", at = @At("HEAD"), cancellable = true)
    private void getSoundVolume(CallbackInfoReturnable<Float> cir) {
        EntityType<?> entity_type = living_entity.getType();
        EntityTypeVision entity_type_vision = Vision.get_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_volume(entity_type) != null)
            cir.setReturnValue(Math.max(0f, entity_type_vision.get_volume(entity_type)));
    }
    @Inject(method = "getXpToDrop", at = @At("HEAD"), cancellable = true)
    private void return_exp_amount(CallbackInfoReturnable<Integer> cir) {
        EntityType<?> entity_type = living_entity.getType();
        EntityTypeVision entity_type_vision = Vision.get_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_exp_drop_amount(entity_type) != null)
            cir.setReturnValue(Math.max(0, entity_type_vision.get_exp_drop_amount(entity_type)));
    }
    @Inject(method = "writeCustomDataToNbt",at = @At("HEAD"))
    private void write_variant_nbt(NbtCompound nbt, CallbackInfo ci){
        nbt.putString("Variant", variant != null ? variant : "");
    }
    @Inject(method = "readCustomDataFromNbt",at = @At("HEAD"))
    private void read_variant_nbt(NbtCompound nbt, CallbackInfo ci){
        this.variant = nbt.getString("Variant");
    }
    public void vminus$set_variant(String variant) {
        this.variant = variant;
    }
    public String vminus$get_variant(){
        return variant;
    }
}
