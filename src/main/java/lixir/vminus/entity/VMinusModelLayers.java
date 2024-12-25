package lixir.vminus.entity;

import lixir.vminus.VMinus;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class VMinusModelLayers {
    public static final EntityModelLayer DEFAULT_ENTITY = new EntityModelLayer(new Identifier(VMinus.MOD_ID, "default_entity"), "main");
}
