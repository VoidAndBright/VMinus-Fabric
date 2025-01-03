package lixir.vminus.item;

import lixir.vminus.VMinus;
import lixir.vminus.screen.screens.DefaultScreen;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DefaultiumItem extends Item {
    public DefaultiumItem() {
        super(new FabricItemSettings());
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.openHandledScreen( new SimpleNamedScreenHandlerFactory(DefaultScreen::OpenDefaultScreen, DefaultScreen.TITLE));
        return super.use(world, user, hand);
    }
}
