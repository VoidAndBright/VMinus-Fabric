package net.lixir.vminus.mixins.entities;

import net.minecraft.server.level.ServerWorld;
import net.minecraft.world.entity.AgeableMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Animal.class)
public class AnimalMixin {
    @Inject(method = "spawnChildFromBreeding", at = @At("HEAD"), cancellable = true)
    public void spawnChildFromBreeding(ServerWorld ServerWorld, Animal otherParent, CallbackInfo ci) {
        Animal thisParent = (Animal) (Object) this;

        AgeableMob child = otherParent.getBreedOffspring(ServerWorld, thisParent);
        if (child != null) {

            child.setBaby(true);
            child.moveTo(otherParent.getX(), otherParent.getY(), otherParent.getZ(), 0.0F, 0.0F);

            String thisParentVariant = thisParent.getPersistentData().getString("variant");
            String otherParentVariant = otherParent.getPersistentData().getString("variant");

            String childVariant = ServerWorld.random.nextBoolean() ? thisParentVariant : otherParentVariant;

            child.getPersistentData().putString("variant", childVariant);

            thisParent.finalizeSpawnChildFromBreeding(ServerWorld, otherParent, child);
            ServerWorld.addFreshEntityWithPassengers(child);
        }
        ci.cancel();
    }
}
