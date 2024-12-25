package lixir.vminus.screen;

import lixir.vminus.VMinus;
import lixir.vminus.screen.screens.DefaultScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class DefaultScreenFactory implements NamedScreenHandlerFactory {
    public Text getDisplayName() {
        return Text.translatable(VMinus.MOD_ID+".default_screen.title");
    }
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DefaultScreenHandler(syncId,playerInventory);
    }
}
