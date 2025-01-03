package lixir.vminus.datagen;

import lixir.vminus.attribute.VMinusAttributes;
import lixir.vminus.block.VMinusBlocks;
import lixir.vminus.entity.VMinusEntities;
import lixir.vminus.item.VMinusItems;
import lixir.vminus.keybinds.VMinusKeyBinds;
import lixir.vminus.screen.screens.CapeScreen;
import lixir.vminus.screen.screens.DefaultScreen;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class VMinusLangProvider extends FabricLanguageProvider {
    public VMinusLangProvider(FabricDataOutput dataOutput) {super(dataOutput);}
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(VMinusItems.DEFAULTIUM_ITEM,"Defaultium Item");
        translationBuilder.add(VMinusBlocks.DEFAULTIUM_BLOCK,"Defaultium Block");
        translationBuilder.add(VMinusEntities.DEFAULTIUM_ENTITY,"Defaultium Entity");
        translationBuilder.add(VMinusAttributes.PROTECTION,"Protection");
        translationBuilder.add(VMinusAttributes.BLAST_PROTECTION,"Blast Protection");
        translationBuilder.add(VMinusAttributes.BLUNT_PROTECTION,"Blunt Protection");
        translationBuilder.add(VMinusAttributes.FALL_PROTECTION,"Fall Protection");
        translationBuilder.add(VMinusAttributes.MAGIC_PROTECTION,"Magic Protection");
        translationBuilder.add(VMinusAttributes.FIRE_PROTECTION,"Fire Protection");
        translationBuilder.add(VMinusAttributes.MINING_SPEED,"Mining Speed");
        translationBuilder.add(VMinusAttributes.CRITICAL_DAMAGE,"Critical Damage");
        translationBuilder.add(VMinusKeyBinds.CAPE_KEY_BIND.getTranslationKey(),"Capes");
        translationBuilder.add(CapeScreen.TITLE.getString(),"VMinus Capes");
        translationBuilder.add(DefaultScreen.TITLE.getString(),"Defaultium");
    }
}
