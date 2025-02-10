package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.EntityTypeVision;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public class EntityTypeMixin {
    @Unique
    private final EntityType<?> THIS = (EntityType<?>)(Object)this;
    @Inject(method = "isSummonable",at = @At("HEAD"), cancellable = true)
    private void is_summonable(CallbackInfoReturnable<Boolean> cir){
        EntityTypeVision entity_type_vision = Visions.get_entity_type_vision(THIS);
        if (entity_type_vision != null && entity_type_vision.get_banned(THIS) != null){
            cir.setReturnValue(!entity_type_vision.get_banned(THIS));
        }
    }
}
