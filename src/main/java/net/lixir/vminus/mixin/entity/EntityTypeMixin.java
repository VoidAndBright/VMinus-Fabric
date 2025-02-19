package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.vision.type.EntityTypeVision;
import net.lixir.vminus.vision.accessor.EntityTypeVisionAccessor;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public class EntityTypeMixin implements EntityTypeVisionAccessor {
    @Unique
    private EntityTypeVision entity_type_vision;
    @Unique
    private final EntityType<?> entity_type = (EntityType<?>)(Object)this;

    @Inject(method = "isSummonable",at = @At("HEAD"), cancellable = true)
    private void is_summonable(CallbackInfoReturnable<Boolean> cir){
        if (entity_type_vision != null && entity_type_vision.get_banned(entity_type) != null){
            cir.setReturnValue(!entity_type_vision.get_banned(entity_type));
        }
    }

    public EntityTypeVision vminus$get_vision() {
        return entity_type_vision;
    }

    public void vminus$set_vision(EntityTypeVision entity_type_vision) {
        this.entity_type_vision = entity_type_vision;
    }
}
