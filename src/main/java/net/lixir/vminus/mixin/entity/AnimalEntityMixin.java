package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.entity.EntityVariantAccessor;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin {
    @Shadow public abstract void breed(ServerWorld world, AnimalEntity other, @Nullable PassiveEntity baby);
    @Unique
    PassiveEntity THIS = (PassiveEntity) (Object) this;

    @Inject(method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;)V", at = @At("HEAD"), cancellable = true)
    public void spawnChildFromBreeding(ServerWorld server_world, AnimalEntity other_parent, CallbackInfo ci) {
        PassiveEntity child = other_parent.createChild(server_world, THIS);
        if (child != null) {
            child.setBaby(true);
            child.refreshPositionAndAngles(other_parent.getX(), other_parent.getY(), other_parent.getZ(), 0.0F, 0.0F);
            String this_parent_variant = ((EntityVariantAccessor) THIS).vminus$get_variant();
            String other_parent_variant = ((EntityVariantAccessor) other_parent).vminus$get_variant();
            String child_variant = server_world.random.nextBoolean() ? this_parent_variant : other_parent_variant;
            ((EntityVariantAccessor) other_parent).vminus$set_variant(child_variant);
            breed(server_world, other_parent, child);
            server_world.spawnEntityAndPassengers(child);
        }
        ci.cancel();
    }
}
