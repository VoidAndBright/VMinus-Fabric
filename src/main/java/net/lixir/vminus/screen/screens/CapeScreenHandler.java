package net.lixir.vminus.screen.screens;

import net.lixir.vminus.screen.VMinusScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class CapeScreenHandler extends ScreenHandler {
    public CapeScreenHandler(int syncId, PlayerInventory inventory) {super(VMinusScreenHandlers.CAPE_SCREEN_HANDLER, syncId);}
    public ItemStack quickMove(PlayerEntity player, int invSlot) {return ItemStack.EMPTY;}
    public boolean canUse(PlayerEntity player) {return true;}
}
