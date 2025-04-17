package net.lixir.vminus;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.lixir.vminus.screen.VMinusConfigScreen;


public class VMinusModMenu implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return VMinusConfigScreen::new;
    }
}
