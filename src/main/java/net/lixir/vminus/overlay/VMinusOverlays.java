package net.lixir.vminus.overlay;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.attribute.VMinusAttributes;
import net.lixir.vminus.mixin.client.DrawContextAccessor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Collection;

public class VMinusOverlays {
    private static final Identifier HEALTH_LOST_STAT_BOOST_OVERLAY = new Identifier(VMinus.MOD_ID,"textures/misc/health_lost_stat_boost_overlay.png");

    private static void health_lost_stat_boost_overlay(DrawContext drawContext, float v) {
        if (drawContext instanceof DrawContextAccessor draw_context_accessor){
            PlayerEntity player_entity = draw_context_accessor.get_client().player;
            if (player_entity != null) {
                float health = player_entity.getHealth();
                float max_health = player_entity.getMaxHealth();
                float health_percentage =  health / max_health;
                double attribute_total = get_attribute_total_from_player_entity(player_entity, VMinusAttributes.HEALTH_LOST_STAT_BOOST);
                float opacity = 1f - health_percentage;
                if (attribute_total > 0) {
                    final int width = drawContext.getScaledWindowWidth();
                    final int height = drawContext.getScaledWindowWidth();
                    RenderSystem.disableDepthTest();
                    RenderSystem.depthMask(false);
                    RenderSystem.enableBlend();
                    RenderSystem.setShader(GameRenderer::getPositionTexProgram);
                    RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
                    RenderSystem.setShaderColor(1, 1, 1, opacity);
                    drawContext.drawTexture(HEALTH_LOST_STAT_BOOST_OVERLAY, 0, 0, 0.0F, 0.0F, width, height, width, height);
                    RenderSystem.depthMask(true);
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.enableDepthTest();
                    RenderSystem.disableBlend();
                    RenderSystem.setShaderColor(1, 1, 1, 1);
                }
            }
        }
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
        HudRenderCallback.EVENT.register(VMinusOverlays::health_lost_stat_boost_overlay);
    }
}
