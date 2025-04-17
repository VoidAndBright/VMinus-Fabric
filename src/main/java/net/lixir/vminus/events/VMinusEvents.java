package net.lixir.vminus.events;

import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.lixir.vminus.cape.Cape;
import net.lixir.vminus.cape.CapeOwner;
import net.lixir.vminus.config.VMinusConfigs;
import net.lixir.vminus.networking.VMinusNetworking;
import net.lixir.vminus.util.Icon;
import net.lixir.vminus.vision.direct.*;
import net.lixir.vminus.vision.properties.VisionAttributeModifier;
import net.lixir.vminus.vision.type.*;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Vector;


public class VMinusEvents {
    public static BlockVision[] block_visions = new BlockVision[]{};
    public static EnchantmentVision[] enchantment_visions = new EnchantmentVision[]{};
    public static EntityTypeVision[] entity_type_visions = new EntityTypeVision[]{};
    public static ItemVision[] item_visions = new ItemVision[]{};
    public static StatusEffectVision[] status_effect_visions = new StatusEffectVision[]{};
    private static void on_server_start(MinecraftServer minecraftServer) {
        for (BlockVision block_vision:block_visions){
            Vector<Block> blocks = block_vision.get_blocks();
            for (Block block:blocks){
                if (BlockVisionable.get_vision(block) != null) {
                    BlockVisionable.set_vision(block, new BlockVision(BlockVisionable.get_vision(block), block_vision));
                    continue;
                }
                BlockVisionable.set_vision(block,block_vision);
            }
        }
        for (EnchantmentVision enchantment_vision:enchantment_visions){
            for (Enchantment enchantment:enchantment_vision.get_enchantments()){
                if (EnchantmentVisionable.get_vision(enchantment) != null) {
                    EnchantmentVisionable.set_vision(enchantment, new EnchantmentVision(EnchantmentVisionable.get_vision(enchantment), enchantment_vision));
                    continue;
                }
                EnchantmentVisionable.set_vision(enchantment,enchantment_vision);
            }
        }
        for (EntityTypeVision entity_type_vision:entity_type_visions){
            for (EntityType<?> entity_type:entity_type_vision.get_entity_types()){
                if (EntityTypeVisionable.get_vision(entity_type) != null){
                    EntityTypeVisionable.set_vision(entity_type,new EntityTypeVision(EntityTypeVisionable.get_vision(entity_type),entity_type_vision));
                    continue;
                }
                EntityTypeVisionable.set_vision(entity_type,entity_type_vision);
            }
        }
        for(StatusEffectVision status_effect_vision:status_effect_visions){
            for (StatusEffect status_effect:status_effect_vision.get_status_effects()){
                if (StatusEffectVisionable.get_vision(status_effect) != null) {
                    StatusEffectVisionable.set_vision(status_effect, new StatusEffectVision(StatusEffectVisionable.get_vision(status_effect), status_effect_vision));
                    continue;
                }
                StatusEffectVisionable.set_vision(status_effect,status_effect_vision);
            }
        }
        for (ItemVision item_vision:item_visions){
            for (Item item:item_vision.get_items()){
                if (ItemVisionable.get_vision(item) != null) {
                    ItemVisionable.set_vision(item, new ItemVision(ItemVisionable.get_vision(item), item_vision));
                    continue;
                }
                ItemVisionable.set_vision(item,item_vision);
            }
        }
    }
    public static void register(){
        ServerLifecycleEvents.SERVER_STARTED.register(VMinusEvents::on_server_start);
        ServerLifecycleEvents.SERVER_STOPPED.register(VMinusEvents::on_server_stop);
        ModifyItemAttributeModifiersCallback.EVENT.register(VMinusEvents::add_vision_attributes);
        ClientPlayConnectionEvents.JOIN.register(VMinusEvents::on_client_join);
    }

    private static void on_client_join(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {
        if (minecraftClient.player == null) return;
        Cape cape = VMinusConfigs.CAPE.getValue();
        CapeOwner.cast(minecraftClient.player).set_cape(cape);
        ClientPlayNetworking.send(VMinusNetworking.CAPE_PACKET, PacketByteBufs.create().writeString(Cape.to_string(cape)));
    }

    private static void on_server_stop(MinecraftServer minecraftServer) {
        for (Block block : Registries.BLOCK){
            BlockVisionable.set_vision(block,null);
        }
        for (Enchantment enchantment : Registries.ENCHANTMENT){
            EnchantmentVisionable.set_vision(enchantment,null);
        }
        for (EntityType<?> entity_type : Registries.ENTITY_TYPE){
            EntityTypeVisionable.set_vision(entity_type,null);
        }
        for (StatusEffect status_effect : Registries.STATUS_EFFECT){
            StatusEffectVisionable.set_vision(status_effect,null);
        }
        for (Item item : Registries.ITEM){
            ItemVisionable.set_vision(item,null);
        }
    }



    public static void client_register(){
        ItemTooltipCallback.EVENT.register(VMinusEvents::inspect_tooltip);
    }

    private static void add_vision_attributes(ItemStack itemstack, EquipmentSlot equipment_slot, Multimap<EntityAttribute, EntityAttributeModifier> entity_attribute_multimap) {
        Item item = itemstack.getItem();
        ItemVision item_vision = ItemVisionable.get_vision(item);
        if (item_vision == null) return;
        if (item_vision.get_attributes(item) != null) {
            entity_attribute_multimap.clear();
            for (VisionAttributeModifier attribute:item_vision.get_attributes(item)) {
                if (VisionAttributeModifier.equipment_slot(attribute) != equipment_slot) break;
                entity_attribute_multimap.put(VisionAttributeModifier.attribute(attribute),VisionAttributeModifier.attribute_modifier(attribute));
            }
        }
        else if (item_vision.get_add_attributes(item) != null){
            for (VisionAttributeModifier attribute:item_vision.get_add_attributes(item)) {
                if (VisionAttributeModifier.equipment_slot(attribute) != equipment_slot) break;
                entity_attribute_multimap.put(VisionAttributeModifier.attribute(attribute),VisionAttributeModifier.attribute_modifier(attribute));
            }
        }
        else if (item_vision.get_remove_attributes(item) != null) {
            for (VisionAttributeModifier attribute : item_vision.get_remove_attributes(item)) {
                if (VisionAttributeModifier.equipment_slot(attribute) != equipment_slot) break;
                EntityAttribute vision_attribute = VisionAttributeModifier.attribute(attribute);
                for (EntityAttributeModifier modifier : entity_attribute_multimap.get(vision_attribute).stream().toList()) {
                    entity_attribute_multimap.remove(vision_attribute,modifier);
                }
            }
        }
    }

    private static void inspect_tooltip(ItemStack item_stack, TooltipContext tooltip_context, List<Text> tooltip){
        if (tooltip == null) return;
        if (Screen.hasAltDown()) return;
        Text first_line = tooltip.get(0);
        tooltip.removeAll(tooltip.stream().filter(text -> text != first_line).toList());
        tooltip.add(Text.literal(Icon.INSPECT_BAUBLE.get_icon() + " Inspection"));
    }
}
