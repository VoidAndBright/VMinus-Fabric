package lixir.vminus.mixin.server;

import lixir.vminus.VMinus;
import lixir.vminus.screen.widgets.CapeButtonWidget;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListEntry.class)
public class CapeMixin {
    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    private void getCapeTexture(CallbackInfoReturnable<Identifier> cir){
        if (CapeButtonWidget.selected != CapeButtonWidget.CapeType.NONE){
            Identifier texture = switch (CapeButtonWidget.selected){
                case BEEPER -> new Identifier(VMinus.MOD_ID,"textures/cape/beeper_cape.png");
                case GHOST -> new Identifier(VMinus.MOD_ID,"textures/cape/ghost_cape.png");
                case MARROW -> new Identifier(VMinus.MOD_ID,"textures/cape/marrow_cape.png");
                case TROLL -> new Identifier(VMinus.MOD_ID,"textures/cape/troll_cape.png");
                case SHROUD -> new Identifier(VMinus.MOD_ID,"textures/cape/shroud_cape.png");
                case PROTOTYPE -> new Identifier(VMinus.MOD_ID,"textures/cape/prototype_cape.png");
                default -> null;
            };
            cir.setReturnValue(texture);
        }
    }
}
