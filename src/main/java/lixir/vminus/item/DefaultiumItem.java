package lixir.vminus.item;

import lixir.vminus.VMinus;
import lixir.vminus.screen.screens.DefaultScreenHandler;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;

import static lixir.vminus.screen.CapesScreenFactory.TITLE;

public class DefaultiumItem extends Item {
    public DefaultiumItem() {
        super(new FabricItemSettings());
    }

    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if(clickType == ClickType.RIGHT){
            VMinus.LOGGER.info("Opened Menu");
            player.openHandledScreen( new SimpleNamedScreenHandlerFactory(DefaultiumItem::OpenDefaultScreen, TITLE));
        }
        return false;
    }
    private static ScreenHandler OpenDefaultScreen(int syncId, PlayerInventory inventory , PlayerEntity ignored){
        return new DefaultScreenHandler(syncId, inventory);
    }
}
