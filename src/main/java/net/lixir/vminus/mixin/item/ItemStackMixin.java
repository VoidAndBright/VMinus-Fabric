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
    @Shadow public abstract Item getItem();

    @Shadow public abstract NbtCompound getOrCreateNbt();

    @Shadow public abstract boolean isEnchantable();

    @Shadow public abstract @Nullable NbtCompound getNbt();

    @Shadow public abstract int getDamage();

    @Shadow public abstract int getMaxDamage();

    @Shadow public abstract boolean hasEnchantments();

    @Shadow public abstract boolean isEmpty();

    @Unique
    private final ItemStack This = (ItemStack) (Object) this;

    @Inject(method = "damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z",at=@At("RETURN"), cancellable = true)
    public void replace_item_upon_breaking(int amount, Random random, ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            ItemVision item_vision = Visions.get_item_vision(this.getItem());
            if (item_vision != null && item_vision.get_break_replacement() != null) {
                ItemStack replacement_stack = new ItemStack(VisionHelper.item(item_vision.get_break_replacement()));
                if(item_vision.get_transfer_nbt() != null && item_vision.get_transfer_nbt()) {
                    replacement_stack.setNbt(this.getOrCreateNbt());
                    replacement_stack.setDamage(1);
                }
                PlayerInventory player_inventory = player.getInventory();
                int inventory_slot_index = player_inventory.getSlotWithStack(This);
                if (inventory_slot_index != -1)
                    player_inventory.setStack(inventory_slot_index, This);
                else{
                    int armor_slot_index = get_armor_slot_with_stack(player_inventory,This);
                    if(armor_slot_index != -1) player_inventory.armor.set(armor_slot_index, replacement_stack);
                    else if(player_inventory.offHand.get(0).isOf(this.getItem())) player_inventory.offHand.set(0, replacement_stack);
                    player.incrementStat(Stats.BROKEN.getOrCreateStat(this.getItem()));
                }
                cir.setReturnValue(false);
            }
        }
    }
    @Inject(method = "getMaxDamage", at = @At("RETURN"), cancellable = true)
    public void return_max_damage(CallbackInfoReturnable<Integer> cir) {
        ItemVision item_vision = Visions.get_item_vision(this.getItem());
        if (item_vision!=null && item_vision.get_max_damage() != null) {
            cir.setReturnValue(item_vision.get_max_damage());
        }
    }
    @Inject(method = "isEnchantable", at = @At("HEAD"), cancellable = true)
    private void return_enchantable(CallbackInfoReturnable<Boolean> cir) {
        ItemVision item_vision = Visions.get_item_vision(this.getItem());
        if (item_vision!= null && item_vision.get_enchantable() != null) {
            cir.setReturnValue(item_vision.get_enchantable());
        }
    }
    @Inject(method = "hasGlint", at = @At("HEAD"), cancellable = true)
    private void return_glinted(CallbackInfoReturnable<Boolean> cir){
        ItemVision item_vision = Visions.get_item_vision(this.getItem());
        if (item_vision!= null && item_vision.get_glinted() != null) {
            cir.setReturnValue(item_vision.get_glinted());
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
            if (enchantment_vision.get_banned() != null && enchantment_vision.get_banned()) ci.cancel();
            NbtCompound nbt_compound = this.getOrCreateNbt();
            int enchantment_limit = nbt_compound.contains("enchantment_limit") ? nbt_compound.getInt("enchantment_limit") : 1000;
            int total_enchantment_level = this.hasEnchantments() ? get_total_enchantment_level(This) : 0;
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
