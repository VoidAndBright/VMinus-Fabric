package net.lixir.vminus.screen.widgets;

import net.lixir.vminus.VMinus;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CapeButtonWidget extends ClickableWidget {
    private static final Identifier TEXTURE = new Identifier(VMinus.MOD_ID, "textures/screen/cape_menu.png");
    public static CapeType selected = CapeType.NONE;
    boolean locked = false;
    public CapeType type;
    public enum CapeType {
        NONE,
        PROTOTYPE,
        BEEPER,
        GHOST,
        MARROW,
        TROLL,
        SHROUD,
    }
    public CapeButtonWidget(int x, int y, CapeType type,boolean locked) {
        super(x, y, 12, 16, Text.empty());
        this.type = type;
        this.locked = false;
    }

    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        if (!locked){
            selected = type;
        }
    }

    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        switch(type){
            case NONE -> context.drawTexture(TEXTURE, this.getX(), this.getY(), 178,2, width, height);
            case PROTOTYPE -> context.drawTexture(TEXTURE, this.getX(), this.getY(), 192 ,20, width, height);
            case BEEPER -> context.drawTexture(TEXTURE, this.getX(), this.getY(), 206 ,20, width, height);
            case GHOST -> context.drawTexture(TEXTURE, this.getX(), this.getY(), 206 ,2, width, height);
            case MARROW -> context.drawTexture(TEXTURE, this.getX(), this.getY(), 220 ,2, width, height);
            case TROLL -> context.drawTexture(TEXTURE, this.getX(), this.getY(), 178,20, width, height);
            case SHROUD -> context.drawTexture(TEXTURE, this.getX(), this.getY(), 192,2, width, height);
        }
        if (selected == type) {
            context.drawTexture(TEXTURE, this.getX()+6, this.getY()+10, 234 ,2, 8, 8);
        }
        if (locked){
            context.drawTexture(TEXTURE, this.getX()+3, this.getY()+7, 233 ,12, 10, 11);
        }
    }
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        builder.put(NarrationPart.TITLE,Text.literal("Hey"));
        builder.put(NarrationPart.USAGE,Text.literal("Hey"));
        builder.put(NarrationPart.HINT,Text.literal("Hey"));
        builder.put(NarrationPart.POSITION,Text.literal("Hey"));
    }
}
