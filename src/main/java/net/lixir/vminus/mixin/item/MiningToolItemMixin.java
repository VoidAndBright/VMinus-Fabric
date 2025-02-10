package net.lixir.vminus.mixin.item;

import com.google.common.collect.Multimap;
import net.lixir.vminus.attribute.VMinusAttributes;
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

import java.util.Collection;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin {
    @Unique
    private final MiningToolItem THIS = (MiningToolItem) (Object) this;
    @Unique
    private final MiningToolItemAccessor ACCESSOR = (MiningToolItemAccessor) THIS;

    @Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
    public void getDestroySpeed(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> ci) {
        float newSpeed = 0;
        EquipmentSlot slot = EquipmentSlot.MAINHAND;
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = stack.getAttributeModifiers(slot);
        for (EntityAttribute attribute : modifiers.keySet()) {
            Collection<EntityAttributeModifier> attributeModifiers = modifiers.get(attribute);
            for (EntityAttributeModifier modifier : attributeModifiers) {
                if (attribute == VMinusAttributes.MINING_SPEED) {
                    newSpeed += (float) modifier.getValue();
                }
            }
        }
        if (state.isIn(ACCESSOR.get_effective_blocks())) {
            ci.setReturnValue(newSpeed != 0 ? newSpeed : ACCESSOR.get_mining_speed());
        } else {
            ci.setReturnValue(1.0F);
        }
    }
}
