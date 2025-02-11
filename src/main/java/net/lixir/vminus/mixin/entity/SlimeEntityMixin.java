package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.entity.EntityVariantAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin {
    @Redirect(method = "remove",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private boolean spawn_slime_entity_with_variant(World instance, Entity entity){
        ((EntityVariantAccessor)entity).vminus$set_variant(((EntityVariantAccessor)this).vminus$get_variant());
        return instance.spawnEntity(entity);
    }
}
