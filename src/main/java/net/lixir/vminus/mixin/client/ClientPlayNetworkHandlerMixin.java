package net.lixir.vminus.mixin.client;

import net.lixir.vminus.vision.implement.ItemVisionable;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Unique
    ClientPlayNetworkHandler network_handler = (ClientPlayNetworkHandler) (Object) this;
    @Unique
    ClientPlayNetworkHandlerAccessor accessor = (ClientPlayNetworkHandlerAccessor) network_handler;
    @Inject(method = "onItemPickupAnimation",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V"))
    private void return_pick_up_sound(ItemPickupAnimationS2CPacket packet, CallbackInfo ci){
        ClientWorld world = accessor.get_world();
        Entity entity = world.getEntityById(packet.getEntityId());
        if (entity instanceof ItemEntity item_entity) {
            Item item = item_entity.getStack().getItem();
            ItemVision item_vision = ItemVisionable.get_vision(item);
            if (item_vision != null && item_vision.get_pick_up_sound(item) != null) {
                Identifier identifier = new Identifier(item_vision.get_pick_up_sound(item));
                Random random = accessor.get_random();
                world.playSound(entity.getX(), entity.getY(), entity.getZ(), Registries.SOUND_EVENT.get(identifier), SoundCategory.PLAYERS, 0.2F, (random.nextFloat() - random.nextFloat()) * 1.4F + 2.0F, false);
            }
        }
    }
}
