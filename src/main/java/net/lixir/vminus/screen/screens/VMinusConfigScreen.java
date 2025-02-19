package net.lixir.vminus.screen.screens;

import com.terraformersmc.modmenu.config.ModMenuConfig;
import com.terraformersmc.modmenu.config.ModMenuConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import static net.lixir.vminus.config.VMinusConfigs.TOOLTIP_REWORK;

public class VMinusConfigScreen extends GameOptionsScreen {
    private final Screen previous;
    private OptionListWidget list;

    public VMinusConfigScreen(Screen previous) {
        super(previous, MinecraftClient.getInstance().options, Text.translatable("vminus.configs"));
        this.previous = previous;

    }
    protected void init() {
        this.list = new OptionListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(TOOLTIP_REWORK.asOption());
        this.addSelectableChild(this.list);
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, this::create_button).position(this.width / 2 - 100, this.height - 27).size(200, 20).build());
    }
    private void create_button(ButtonWidget button){
        ModMenuConfigManager.save();
        assert this.client != null;
        this.client.setScreen(this.previous);
    }
    public void render(DrawContext DrawContext, int mouseX, int mouseY, float delta) {
        this.renderBackgroundTexture(DrawContext);
        this.list.render(DrawContext, mouseX, mouseY, delta);
        DrawContext.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 5, 16777215);
        super.render(DrawContext, mouseX, mouseY, delta);
    }
    public void removed() {
        ModMenuConfigManager.save();
    }

}
