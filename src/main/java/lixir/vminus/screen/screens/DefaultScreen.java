package lixir.vminus.screen.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import lixir.vminus.VMinus;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class DefaultScreen extends HandledScreen<DefaultScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(VMinus.MOD_ID, "textures/screen/default_menu.png");
    public static final Text TITLE = Text.translatable(VMinus.MOD_ID+".screen.default.title");

    public DefaultScreen(DefaultScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    public void render(DrawContext graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        drawMouseoverTooltip(graphics, mouseX, mouseY);
    }

    protected void drawBackground(DrawContext graphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        graphics.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }
    public static ScreenHandler OpenDefaultScreen(int syncId, PlayerInventory inventory, PlayerEntity ignored){
        return new DefaultScreenHandler(syncId, inventory);
    }
}
