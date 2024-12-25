package lixir.vminus.screen.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import lixir.vminus.VMinus;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class CapeScreen extends HandledScreen<CapeScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(VMinus.MOD_ID, "textures/screen/cape_menu.png");

    public CapeScreen(CapeScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.playerInventoryTitleY = 1000;

    }

    protected void init() {
        super.init();
        addButtons();
    }

    public void render(DrawContext graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        drawMouseoverTooltip(graphics, mouseX, mouseY);
    }
    protected void addButtons(){
        ButtonWidget button = ButtonWidget.builder(Text.of("Hello World"), buttonWidget -> this.client.getToastManager().add(
                SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.of("Hello World!"), Text.of("This is a toast."))
        )).dimensions(x, y, 120, 20).build();
        this.addDrawableChild(button);
    }
    protected void drawBackground(DrawContext graphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        graphics.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }
}
