package net.lixir.vminus.mixin.entity;

import net.lixir.vminus.vision.implement.ItemVisionable;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Unique
    private final PlayerEntity player_entity = (PlayerEntity) (Object) this;
    @Inject(method = "eatFood",at = @At("HEAD"), cancellable = true)
    private void get_burp_sound(World world, ItemStack itemstack, CallbackInfoReturnable<ItemStack> cir){
        player_entity.getHungerManager().eat(itemstack.getItem(), itemstack);
        Item item = itemstack.getItem();
        ItemVision item_vision = ItemVisionable.get_vision(item);
        if (item_vision != null && item_vision.get_food_properties(item) != null && item_vision.get_food_properties(item).get_burp_sound() != null){
            SoundEvent sound_event = Registries.SOUND_EVENT.get(new Identifier(item_vision.get_food_properties(item).get_burp_sound()));
            player_entity.playSound(sound_event, 0.5F, world.getRandom().nextFloat() * 0.1F + 0.9F);
        }
        if (player_entity instanceof ServerPlayerEntity server_player_entity) {
            Criteria.CONSUME_ITEM.trigger(server_player_entity, itemstack);
        }
        cir.setReturnValue(super.eatFood(world,itemstack));
    }
}
