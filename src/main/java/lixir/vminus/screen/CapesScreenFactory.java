package lixir.vminus.screen;

import lixir.vminus.VMinus;
import lixir.vminus.screen.screens.CapeScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class CapesScreenFactory implements NamedScreenHandlerFactory {
    public static final Text TITLE = Text.translatable(VMinus.MOD_ID+".screen.capes.title");
    public Text getDisplayName() {return TITLE;}
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CapeScreenHandler(syncId,playerInventory);
    }
}
