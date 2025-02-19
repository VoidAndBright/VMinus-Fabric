package net.lixir.vminus.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.screen.screens.*;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class VMinusScreenHandlers
{
    public static final ScreenHandlerType<DefaultScreenHandler> DEFAULT_SCREEN_HANDLER = registerScreenHandler("default_screen_handler", DefaultScreenHandler::new);
    public static final ScreenHandlerType<CapeScreenHandler> CAPE_SCREEN_HANDLER = registerScreenHandler("cape_screen_handler", CapeScreenHandler::new);

    public static <T extends ScreenHandler> ScreenHandlerType<T> registerScreenHandler(String name, ScreenHandlerType.Factory<T> screenHandlerType) {
        return Registry.register(Registries.SCREEN_HANDLER, new Identifier(VMinus.MOD_ID, name), new ScreenHandlerType<>(screenHandlerType, FeatureSet.empty()));
    }
    public static void register() {}

    @Environment(EnvType.CLIENT)
    public static void client_register() {
        HandledScreens.register(DEFAULT_SCREEN_HANDLER, DefaultScreen::new);
        HandledScreens.register(CAPE_SCREEN_HANDLER, CapeScreen::new);
    }
}
