package lixir.vminus.entity;

import lixir.vminus.VMinus;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DefaultEntityRenderer extends MobEntityRenderer<DefaultEntity, DefaultEntityModel<DefaultEntity>> {
    private static final Identifier TEXTURE = new Identifier(VMinus.MOD_ID, "textures/entity/default_entity.png");

    public DefaultEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new DefaultEntityModel<>(context.getPart(VMinusModelLayers.DEFAULT_ENTITY)), 0.6f);
    }

    
    public Identifier getTexture(DefaultEntity entity)
    {
        return TEXTURE;
    }

    
    public void render(DefaultEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
