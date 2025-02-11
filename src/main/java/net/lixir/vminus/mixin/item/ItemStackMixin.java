package net.lixir.vminus.mixin.item;

import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.Visions;
import net.lixir.vminus.vision.type.EnchantmentVision;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ItemStack.class)
public abstract class ItemStackMixin{


    @Unique
    private final ItemStack This = (ItemStack) (Object) this;

    @Inject(method = "damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z",at=@At("RETURN"), cancellable = true)
    public void replace_item_upon_breaking(int amount, Random random, ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            Item item = This.getItem();
            ItemVision item_vision = Visions.get_item_vision(item);
            if (item_vision != null && item_vision.get_break_replacement(item) != null) {
                ItemStack replacement_stack = new ItemStack(VisionHelper.item(item_vision.get_break_replacement(item)));
                if(item_vision.get_transfer_nbt(item) != null && item_vision.get_transfer_nbt(item)) {
                    replacement_stack.setNbt(This.getOrCreateNbt());
                    replacement_stack.setDamage(1);
                }
                PlayerInventory player_inventory = player.getInventory();
                int inventory_slot_index = player_inventory.getSlotWithStack(This);
                if (inventory_slot_index != -1)
                    player_inventory.setStack(inventory_slot_index, This);
                else{
                    int armor_slot_index = get_armor_slot_with_stack(player_inventory,This);
                    if(armor_slot_index != -1) player_inventory.armor.set(armor_slot_index, replacement_stack);
                    else if(player_inventory.offHand.get(0).isOf(item)) player_inventory.offHand.set(0, replacement_stack);
                    player.incrementStat(Stats.BROKEN.getOrCreateStat(item));
                }
                cir.setReturnValue(false);
            }
        }
    }
    @Inject(method = "getMaxDamage", at = @At("RETURN"), cancellable = true)
    public void return_max_damage(CallbackInfoReturnable<Integer> cir) {
        Item item = This.getItem();
        ItemVision item_vision = Visions.get_item_vision(item);
        if (item_vision!=null && item_vision.get_max_damage(item) != null) {
            cir.setReturnValue(item_vision.get_max_damage(item));
        }
    }
    @Inject(method = "isEnchantable", at = @At("HEAD"), cancellable = true)
    private void return_enchantable(CallbackInfoReturnable<Boolean> cir) {
        Item item = This.getItem();
        ItemVision item_vision = Visions.get_item_vision(item);
        if (item_vision!= null && item_vision.get_enchantable(item) != null) {
            cir.setReturnValue(item_vision.get_enchantable(item));
        }
    }
    @Inject(method = "hasGlint", at = @At("HEAD"), cancellable = true)
    private void return_glinted(CallbackInfoReturnable<Boolean> cir){
        Item item = This.getItem();
        ItemVision item_vision = Visions.get_item_vision(item);
        if (item_vision!= null && item_vision.get_glinted(item) != null) {
            cir.setReturnValue(item_vision.get_glinted(item));
        }
    }
    @Unique
    private int get_armor_slot_with_stack(PlayerInventory inventory, ItemStack target_itemstack){
        return iterate_armor_itemstack(inventory,target_itemstack,0);
    }
    @Unique
    private int iterate_armor_itemstack(PlayerInventory inventory, ItemStack target_itemstack, int index){
        if(index < inventory.armor.size()){
            if (inventory.getArmorStack(index).isOf(target_itemstack.getItem()))
                return index;
            return iterate_armor_itemstack(inventory,target_itemstack,index+1);
        }
        return -1;
    }
    @Inject(method = "addEnchantment", at = @At("HEAD"), cancellable = true)
    public void vision_enchantment(Enchantment enchantment, int level, CallbackInfo ci) {

        EnchantmentVision enchantment_vision = Visions.get_enchantment_vision(enchantment);
        if (enchantment_vision != null ) {
            if (enchantment_vision.get_banned(enchantment) != null && enchantment_vision.get_banned(enchantment)) ci.cancel();
            NbtCompound nbt_compound = This.getOrCreateNbt();
            int enchantment_limit = nbt_compound.contains("enchantment_limit") ? nbt_compound.getInt("enchantment_limit") : 1000;
            int total_enchantment_level = This.hasEnchantments() ? get_total_enchantment_level(This) : 0;
            if (total_enchantment_level + level > enchantment_limit) ci.cancel();
        }
    }
    @Unique
    private int get_total_enchantment_level(ItemStack itemstack){
        return iterate_enchantment_levels(EnchantmentHelper.get(itemstack).values().toArray(new Integer[0]),0,0);
    }
    @Unique
    private int iterate_enchantment_levels(Integer[] enchantment_levels, int total, int index){
        if (index < enchantment_levels.length){
            int enchantment_level = enchantment_levels[index];
            return iterate_enchantment_levels(enchantment_levels,total+enchantment_level,index+1);
        }
        else return total;
    }
}
