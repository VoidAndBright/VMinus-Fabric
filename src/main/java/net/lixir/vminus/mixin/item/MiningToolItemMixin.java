package net.lixir.vminus.mixin.item;

import com.google.common.collect.Multimap;
import net.lixir.vminus.entity.attribute.VMinusEntityAttributes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin {
    @Unique
    private final MiningToolItem THIS = (MiningToolItem) (Object) this;
    @Unique
    private final MiningToolItemAccessor ACCESSOR = (MiningToolItemAccessor) THIS;

    @Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
    public void return_mining_speed(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> ci) {
        float new_mining_speed = 0;
        EquipmentSlot slot = EquipmentSlot.MAINHAND;
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = stack.getAttributeModifiers(slot);
        for (EntityAttribute attribute : modifiers.keySet()) {
            for (EntityAttributeModifier entity_attribute_modifier : modifiers.get(attribute)) {
                if (attribute == VMinusEntityAttributes.MINING_SPEED) {
                    new_mining_speed += (float) entity_attribute_modifier.getValue();
                }
            }
        }
        if (state.isIn(ACCESSOR.get_effective_blocks())) {
            ci.setReturnValue(new_mining_speed != 0 ? new_mining_speed : ACCESSOR.get_mining_speed());
        } else {
            ci.setReturnValue(1.0F);
        }
    }
}
