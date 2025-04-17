package net.lixir.vminus.mixin.recipe;

import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Ingredient.Entry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Ingredient.class)
public interface IngredientAccessor {
    @Accessor("entries")
    Entry[] get_entries();
}
