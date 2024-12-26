package lixir.vminus.mixin.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HorseArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.DyeableHorseArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseArmorFeatureRenderer.class)
public class HorseArmorFeatureRendererMixin {
   @Shadow @Final private HorseEntityModel<HorseEntity> model;
   @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/passive/HorseEntity;FFFFFF)V", at = @At(value = "HEAD"), cancellable = true)
   private void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, HorseEntity horseEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci){
       ItemStack itemStack = horseEntity.getArmorType();
       if (itemStack.getItem() instanceof HorseArmorItem horseArmorItem) {
           HorseArmorFeatureRenderer true_this = (HorseArmorFeatureRenderer)(Object)this;

           true_this.getContextModel().copyStateTo(this.model);
           this.model.animateModel(horseEntity, f, g, h);
           this.model.setAngles(horseEntity, f, g, j, k, l);
           float red;
           float green;
           float blue;
           if (horseArmorItem instanceof DyeableHorseArmorItem dyeHorseArmorItem) {
               int color = dyeHorseArmorItem.getColor(itemStack);
               red = (float) (color >> 16 & 255) / 255.0F;
               green = (float) (color >> 8 & 255) / 255.0F;
               blue = (float) (color & 255) / 255.0F;
           } else {
               red = 1.0F;
               green = 1.0F;
               blue = 1.0F;
           }
           VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider, RenderLayer.getEntityCutoutNoCull(horseArmorItem.getEntityTexture()), false, itemStack.hasGlint());
           this.model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, red, green, blue, 1.0F);
       }
       ci.cancel();
   }
}
