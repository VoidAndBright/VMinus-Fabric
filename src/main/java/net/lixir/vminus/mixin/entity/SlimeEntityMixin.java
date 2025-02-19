package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.entity.EntityVariant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin {
    @Redirect(method = "remove",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private boolean spawn_slime_entity_with_variant(World world, Entity entity){
        EntityVariant.set_variant(entity,EntityVariant.get_variant(entity));
        return world.spawnEntity(entity);
    }
}
