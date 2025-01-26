package net.lixir.vminus.entity;

import net.lixir.vminus.VMinus;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DefaultiumEntityRenderer extends MobEntityRenderer<DefaultiumEntity, DefaultiumEntityModel<DefaultiumEntity>> {
    private static final Identifier TEXTURE = new Identifier(VMinus.MOD_ID, "textures/entity/defaultium_entity.png");

    public DefaultiumEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DefaultiumEntityModel<>(context.getPart(VMinusModelLayers.DEFAULTIUM_ENTITY)), 0.6f);
    }

    
    public Identifier getTexture(DefaultiumEntity entity)
    {
        return TEXTURE;
    }

    
    public void render(DefaultiumEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
