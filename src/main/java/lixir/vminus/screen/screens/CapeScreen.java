package lixir.vminus.screen.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import lixir.vminus.VMinus;
import lixir.vminus.screen.widgets.CapeButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class CapeScreen extends HandledScreen<CapeScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(VMinus.MOD_ID, "textures/screen/cape_menu.png");
    public static final Text TITLE = Text.translatable(VMinus.MOD_ID+".screen.capes.title");

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
        for (CapeButtonWidget.CapeType capeType : CapeButtonWidget.CapeType.values()){
            int displacement = switch (capeType){
                case NONE -> 0;
                case BEEPER -> 16;
                case GHOST -> 32;
                case MARROW -> 48;
                case TROLL -> 64;
                case SHROUD -> 80;
                case PROTOTYPE -> 96;
            };
            boolean locked = switch (capeType){
                case NONE, PROTOTYPE -> false;
                case BEEPER, SHROUD, TROLL, MARROW, GHOST -> true;
            };
            CapeButtonWidget widget = new CapeButtonWidget(x + 10 + displacement,y + 20, capeType, locked);
            this.addDrawableChild(widget);
        }
    }
    protected void drawBackground(DrawContext graphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        graphics.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }
    public static ScreenHandler OpenCapeScreen(int syncId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new CapeScreenHandler(syncId,playerInventory);
    }
}
