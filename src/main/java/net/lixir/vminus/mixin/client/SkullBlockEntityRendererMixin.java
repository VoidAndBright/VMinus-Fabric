package net.lixir.vminus.mixin.client;

import net.lixir.vminus.entity.VMinusSkullType;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(SkullBlockEntityRenderer.class)
public class SkullBlockEntityRendererMixin {
    @Shadow @Final private Map<SkullBlock.SkullType, SkullBlockEntityModel> MODELS;

    @Shadow @Final private static Map<SkullBlock.SkullType, Identifier> TEXTURES;
    static {
        TEXTURES.put(VMinusSkullType.DEFAULTIUM,new Identifier("vminus:textures/entity/defaultium_entity.png"));
    }
}
