package net.lixir.vminus.datagen;

import net.lixir.vminus.attribute.VMinusAttributes;
import net.lixir.vminus.block.VMinusBlocks;
import net.lixir.vminus.entity.VMinusEntities;
import net.lixir.vminus.item.VMinusItems;
import net.lixir.vminus.keybind.VMinusKeyBinds;
import net.lixir.vminus.screen.screens.CapeScreen;
import net.lixir.vminus.screen.screens.DefaultScreen;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class VMinusLangProvider extends FabricLanguageProvider {
    public VMinusLangProvider(FabricDataOutput dataOutput) {super(dataOutput);}
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(VMinusItems.DEFAULTIUM_ITEM,"Defaultium Item");
        translationBuilder.add(VMinusBlocks.DEFAULTIUM_BLOCK,"Defaultium Block");
        translationBuilder.add(VMinusBlocks.DEFAULTIUM_HEAD,"Defaultium Head");
        translationBuilder.add(VMinusEntities.DEFAULTIUM_ENTITY,"Defaultium Entity");
        translationBuilder.add(VMinusAttributes.PROTECTION,"Protection");
        translationBuilder.add(VMinusAttributes.BLAST_PROTECTION,"Blast Protection");
        translationBuilder.add(VMinusAttributes.BLUNT_PROTECTION,"Blunt Protection");
        translationBuilder.add(VMinusAttributes.FALL_PROTECTION,"Fall Protection");
        translationBuilder.add(VMinusAttributes.MAGIC_PROTECTION,"Magic Protection");
        translationBuilder.add(VMinusAttributes.FIRE_PROTECTION,"Fire Protection");
        translationBuilder.add(VMinusAttributes.MINING_SPEED,"Mining Speed");
        translationBuilder.add(VMinusAttributes.CRITICAL_DAMAGE,"Critical Damage");
        translationBuilder.add(VMinusAttributes.MOB_DETECTION_RANGE,"Critical Damage");
        translationBuilder.add(VMinusAttributes.HEALTH_LOST_STAT_BOOST,"Health Lost Stat Boost");
        translationBuilder.add(VMinusAttributes.MOMENTUM,"Momentum");
        translationBuilder.add(VMinusAttributes.TRANSLUCENCE,"Translucence");
        translationBuilder.add(VMinusKeyBinds.CAPE_KEY_BIND.getTranslationKey(),"Capes");
        translationBuilder.add(CapeScreen.TITLE.getString(),"VMinus Capes");
        translationBuilder.add(DefaultScreen.TITLE.getString(),"Defaultium");
    }
}
