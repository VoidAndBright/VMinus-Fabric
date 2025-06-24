package net.lixir.vminus.mixin.item;

import java.util.Collection;
import net.lixir.vminus.entity.attribute.VMinusEntityAttributes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
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
    private final MiningToolItem mining_tool_item =
        (MiningToolItem) (Object) this;

    @Unique
    private final MiningToolItemAccessor mining_tool_item_accessor =
        (MiningToolItemAccessor) mining_tool_item;

    @Inject(
        method = "getMiningSpeedMultiplier",
        at = @At("HEAD"),
        cancellable = true
    )
    public void return_mining_speed(
        ItemStack stack,
        BlockState state,
        CallbackInfoReturnable<Float> ci
    ) {
        if (state.isIn(mining_tool_item_accessor.get_effective_blocks())) {
            Collection<EntityAttributeModifier> modifiers = stack
                .getAttributeModifiers(EquipmentSlot.MAINHAND)
                .get(VMinusEntityAttributes.MINING_SPEED);
            float attribute_mining_speed = modifiers.isEmpty()
                ? mining_tool_item_accessor.get_mining_speed()
                : (float) modifiers
                    .stream()
                    .mapToDouble(EntityAttributeModifier::getValue)
                    .sum();
            ci.setReturnValue(
                attribute_mining_speed != 0
                    ? attribute_mining_speed
                    : mining_tool_item_accessor.get_mining_speed()
            );
        } else {
            ci.setReturnValue(1.0F);
        }
    }
}
