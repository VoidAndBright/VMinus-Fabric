package net.lixir.vminus.events;

import com.google.common.collect.Multimap;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.attribute.VMinusAttributes;
import net.lixir.vminus.entity.VMinusEntities;
import net.lixir.vminus.mixin.client.DrawContextAccessor;
import net.lixir.vminus.util.Icon;
import net.lixir.vminus.vision.Vision;
import net.lixir.vminus.vision.VisionHelper;
import net.lixir.vminus.vision.type.ItemVision;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class VMinusEvents {
    private static void on_load(MinecraftServer minecraftServer, ServerWorld serverWorld) {
        Vision.set_visions();
    }
    public static void register(){
        ServerWorldEvents.LOAD.register(VMinusEvents::on_load);
        ModifyItemAttributeModifiersCallback.EVENT.register(VMinusEvents::add_vision_attributes);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, VMinusEntities.DEFAULTIUM_ENTITY,5,5,10);
    }
    private static final Identifier HEALTH_LOST_STAT_BOOST_OVERLAY = new Identifier(VMinus.MOD_ID,"textures/misc/health_lost_stat_boost_overlay.png");

    private static void health_lost_stat_boost_overlay(DrawContext context, float v) {
        if (context instanceof DrawContextAccessor draw_context_accessor){
            PlayerEntity player_entity = draw_context_accessor.get_client().player;
            if (player_entity != null) {
                float health = player_entity.getHealth();
                float max_health = player_entity.getMaxHealth();
                float health_percentage =  health / max_health;
                double attribute_total = get_attribute_total_from_player_entity(player_entity, VMinusAttributes.HEALTH_LOST_STAT_BOOST);
                float opacity = 1f - health_percentage;
                if (attribute_total > 0) {
                    render_overlay(context,HEALTH_LOST_STAT_BOOST_OVERLAY,opacity);
                }
            }
        }
    }
    public static void render_overlay(DrawContext context, Identifier texture, float opacity) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        RenderSystem.setShaderColor(1, 1, 1, opacity);
        context.drawTexture(texture, 0, 0, 0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), context.getScaledWindowWidth(), context.getScaledWindowHeight());
        RenderSystem.depthMask(true);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }
    private static double get_attribute_total_from_player_entity(PlayerEntity player_entity, EntityAttribute entity_attribute){
        return Arrays.stream(EquipmentSlot.values()).mapToDouble(slot -> get_attribute_total_from_item(player_entity.getEquippedStack(slot), slot, entity_attribute)).sum();
    }
    private static double get_attribute_total_from_item(ItemStack item_stack, EquipmentSlot slot, EntityAttribute attribute) {
        Collection<EntityAttributeModifier> modifiers = item_stack.getAttributeModifiers(slot).get(attribute);
        if (!modifiers.isEmpty()) {
            return modifiers.stream().mapToDouble(EntityAttributeModifier::getValue).sum();
        }
        return 0.0;
    }
    public static void client_register(){
        HudRenderCallback.EVENT.register(VMinusEvents::health_lost_stat_boost_overlay);
        ItemTooltipCallback.EVENT.register(VMinusEvents::inspect_tooltip);
    }

    private static void add_vision_attributes(ItemStack itemstack, EquipmentSlot equipment_slot, Multimap<EntityAttribute, EntityAttributeModifier> entity_attribute_multimap) {

        Item item = itemstack.getItem();
        ItemVision item_vision = Vision.get_vision(item);
        if (item_vision != null) {
            if (item_vision.get_attributes(item) != null) {
                entity_attribute_multimap.clear();
                Arrays.stream(item_vision.get_attributes(item)).forEach(attribute -> {
                    if (VisionHelper.equipment_slot(attribute) == equipment_slot){
                        entity_attribute_multimap.put(VisionHelper.attribute(attribute),VisionHelper.attribute_modifier(attribute));
                    }
                });
            }
            else if (item_vision.get_add_attributes(item) != null){
                Arrays.stream(item_vision.get_attributes(item)).forEach(attribute -> {
                    if (VisionHelper.equipment_slot(attribute) == equipment_slot){
                        entity_attribute_multimap.put(VisionHelper.attribute(attribute),VisionHelper.attribute_modifier(attribute));
                    }
                });
            }
            else if (item_vision.get_remove_attributes(item) != null){
                Arrays.stream(item_vision.get_attributes(item)).forEach(attribute -> {
                    if (VisionHelper.equipment_slot(attribute) == equipment_slot){
                        entity_attribute_multimap.remove(VisionHelper.attribute(attribute),VisionHelper.attribute_modifier(attribute));
                    }
                });
            }
        }
        //boolean miningFlag = false;
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
