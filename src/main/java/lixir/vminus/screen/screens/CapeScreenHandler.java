package lixir.vminus.screen.screens;

import lixir.vminus.screen.VMinusScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CapeScreenHandler extends ScreenHandler {
    public CapeScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId);
    }
    public CapeScreenHandler(int syncId) {
        super(VMinusScreenHandlers.CAPE_SCREEN_HANDLER, syncId);
    }

    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        return ItemStack.EMPTY;
    }
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
