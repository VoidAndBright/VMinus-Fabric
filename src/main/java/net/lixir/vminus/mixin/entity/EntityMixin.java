package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.util.PersistentNbt;
import net.lixir.vminus.vision.direct.EntityTypeVisionable;
import net.lixir.vminus.vision.type.EntityTypeVision;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin implements PersistentNbt {
    @Unique
    private final NbtCompound persistent_nbt = new NbtCompound();
    @Unique
    Entity entity = (Entity)(Object)this;
    @Inject(method = "isFireImmune",at = @At("HEAD"), cancellable = true)
    private void is_fire_proof(CallbackInfoReturnable<Boolean> cir){
        EntityType<?> entity_type = this.entity.getType();
        EntityTypeVision entity_type_vision = EntityTypeVisionable.get_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_fire_proof(entity_type) != null)
            cir.setReturnValue(!entity_type_vision.get_fire_proof(entity_type));
    }
    @Inject(method = "isSilent", at = @At("HEAD"), cancellable = true)
    private void return_is_silent(CallbackInfoReturnable<Boolean> cir) {
        EntityType<?> entity_type = this.entity.getType();
        EntityTypeVision entity_type_vision = EntityTypeVisionable.get_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_silent(entity_type) != null)
            cir.setReturnValue(!entity_type_vision.get_silent(entity_type));
    }

    @Inject(method = "occludeVibrationSignals", at = @At("HEAD"), cancellable = true)
    private void return_dampens_vibrations(CallbackInfoReturnable<Boolean> cir) {
        EntityType<?> entity_type = this.entity.getType();
        EntityTypeVision entity_type_vision = EntityTypeVisionable.get_vision(entity_type);
        if (entity_type_vision != null && entity_type_vision.get_dampens_vibrations(entity_type) != null)
            cir.setReturnValue(!entity_type_vision.get_dampens_vibrations(entity_type));
    }

    public NbtCompound get_nbt() {
        return persistent_nbt;
    }
}
