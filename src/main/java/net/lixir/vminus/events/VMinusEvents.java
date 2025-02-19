package net.lixir.vminus.events;

import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.lixir.vminus.attribute.VMinusAttributes;
import net.lixir.vminus.util.Icon;
import net.lixir.vminus.vision.Vision;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.List;

public class VMinusEvents {
    private static void on_load(MinecraftServer minecraftServer, ServerWorld serverWorld) {
        Vision.set_visions();
    }
    public static void register(){
        ServerWorldEvents.LOAD.register(VMinusEvents::on_load);
        ItemTooltipCallback.EVENT.register(VMinusEvents::inspect_tooltip);
        ModifyItemAttributeModifiersCallback.EVENT.register(VMinusEvents::add_vision_attributes);
    }

    private static void add_vision_attributes(ItemStack itemstack, EquipmentSlot equipment_slot, Multimap<EntityAttribute, EntityAttributeModifier> entityAttributeEntityAttributeModifierMultimap) {
        if (equipment_slot == EquipmentSlot.HEAD && itemstack.getItem() == Items.GOLDEN_HELMET){
            entityAttributeEntityAttributeModifierMultimap.put(VMinusAttributes.HEALTH_LOST_STAT_BOOST,new EntityAttributeModifier("ooo",6,EntityAttributeModifier.Operation.ADDITION));
        }
        //Item item = itemstack.getItem();
        //boolean miningFlag = false;
        //ItemVision itemVision = Vision.get_vision(item);
        //List<VisionAttribute> visionAttributes = itemVision.attribute.getValues(new VisionConditionArguments.Builder().passItemStack(itemStack).build());
        //for (VisionAttribute visionAttribute : visionAttributes) {
        //    boolean replace = visionAttribute.replace();
        //    boolean remove = visionAttribute.remove();
        //    if (replace || remove) {
        //        Multimap<Attribute, AttributeModifier> originalModifiers = event.getOriginalModifiers();
        //        for (Attribute a : originalModifiers.keySet()) {
        //            for (AttributeModifier modifier : originalModifiers.get(a)) {
        //                String modifierId = Objects.requireNonNull(ForgeRegistries.ATTRIBUTES.getKey(a)).toString();
        //                if (modifierId.equals(visionAttribute.id())) {
        //                    event.removeModifier(a, modifier);
        //                }
        //            }
        //        }
        //        if (remove)
        //            continue;
        //    }
        //    EquipmentSlot equipmentSlot = visionAttribute.equipmentSlot();
        //    if (equipmentSlot == null) {
        //        if (item instanceof Equipable equipable) {
        //            equipmentSlot = equipable.getEquipmentSlot();
        //        } else {
        //            equipmentSlot = EquipmentSlot.MAINHAND;
        //        }
        //    }
        //    if (eventSlot == equipmentSlot)
        //        event.addModifier(visionAttribute.attribute(), visionAttribute.attributeModifier());
        //}
    }

    private static void inspect_tooltip(ItemStack item_stack, TooltipContext tooltip_context, List<Text> tooltip){
        if (tooltip == null)
            return;
        if (!Screen.hasAltDown()){
            Text first_line = tooltip.get(0);
            tooltip.removeAll(tooltip.stream().filter(text -> text != first_line).toList());
            tooltip.add(Text.literal(Icon.INSPECT_BAUBLE.get_icon() + " Inspection"));
        }
    }
}
