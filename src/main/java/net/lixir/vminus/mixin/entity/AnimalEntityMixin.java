package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.entity.EntityVariant;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin {
    @Unique
    private final AnimalEntity animal_entity = (AnimalEntity) (Object) this;

    @Inject(method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;)V", at = @At("HEAD"), cancellable = true)
    public void spawnChildFromBreeding(ServerWorld server_world, AnimalEntity other_parent, CallbackInfo ci) {
        PassiveEntity child = other_parent.createChild(server_world, animal_entity);
        if (child != null) {
            child.setBaby(true);
            child.refreshPositionAndAngles(other_parent.getX(), other_parent.getY(), other_parent.getZ(), 0.0F, 0.0F);
            String this_parent_variant = ((EntityVariant) animal_entity).vminus$get_variant();
            EntityVariant entity_variant_other = (EntityVariant) other_parent;
            String other_parent_variant = entity_variant_other.vminus$get_variant();
            String child_variant = server_world.random.nextBoolean() ? this_parent_variant : other_parent_variant;
            entity_variant_other.vminus$set_variant(child_variant);
            animal_entity.breed(server_world, other_parent, child);
            server_world.spawnEntityAndPassengers(child);
        }
        ci.cancel();
    }
}
