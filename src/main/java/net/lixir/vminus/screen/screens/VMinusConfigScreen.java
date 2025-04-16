package net.lixir.vminus.screen.screens;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.lixir.vminus.VMinus;
import net.lixir.vminus.cape.Cape;
import net.lixir.vminus.config.VMinusConfigManager;
import net.lixir.vminus.config.VMinusConfigs;
import net.lixir.vminus.networking.VMinusNetworking;
import net.lixir.vminus.util.PersistentNbt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import static net.lixir.vminus.config.VMinusConfigs.CAPE;

public class VMinusConfigScreen extends GameOptionsScreen {
    private final Screen previous;
    private OptionListWidget list;
    public static final Text TITLE = Text.translatable("vminus.configs");
    public VMinusConfigScreen(Screen previous) {
        super(previous, MinecraftClient.getInstance().options, TITLE);
        this.previous = previous;
    }
    protected void init() {
        this.list = new OptionListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addSingleOptionEntry(CAPE.asOption());
        this.addSelectableChild(this.list);
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, this::create_button).position(this.width / 2 - 100, this.height - 27).size(200, 20).build());
    }
    private void create_button(ButtonWidget button){
        VMinusConfigManager.save();
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
        if (MinecraftClient.getInstance().player != null){
            String cape = Cape.to_string(CAPE.getValue());
            PersistentNbt.get(MinecraftClient.getInstance().player).putString("Cape",cape);
            ClientPlayNetworking.send(VMinusNetworking.CAPE_PACKET, PacketByteBufs.create().writeString(cape));
        }
        VMinusConfigManager.save();
    }

}
