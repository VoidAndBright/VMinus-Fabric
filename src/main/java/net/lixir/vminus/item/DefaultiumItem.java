package net.lixir.vminus.item;

import net.lixir.vminus.screen.screens.DefaultScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DefaultiumItem extends Item {
    public DefaultiumItem(Item.Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.openHandledScreen( new SimpleNamedScreenHandlerFactory(DefaultScreen::OpenDefaultScreen, DefaultScreen.TITLE));
        return super.use(world, user, hand);
    }
}
