package lixir.vminus.datagen;

import lixir.vminus.VMinus;
import lixir.vminus.attribute.VMinusAttributes;
import lixir.vminus.block.VMinusBlocks;
import lixir.vminus.entity.VMinusEntities;
import lixir.vminus.item.VMinusItems;
import lixir.vminus.keybinds.VMinusKeyBinds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class VMinusLangProvider extends FabricLanguageProvider {
    public VMinusLangProvider(FabricDataOutput dataOutput) {super(dataOutput);}
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(VMinusItems.DEFAULT_ITEM,"Default Item");
        translationBuilder.add(VMinusBlocks.DEFAULT_BLOCK,"Default Block");
        translationBuilder.add(VMinusEntities.DEFAULT_ENTITY,"Default Entity");
        translationBuilder.add(VMinusAttributes.PROTECTION,"Protection");
        translationBuilder.add(VMinusAttributes.BLAST_PROTECTION,"Blast Protection");
        translationBuilder.add(VMinusAttributes.BLUNT_PROTECTION,"Blunt Protection");
        translationBuilder.add(VMinusAttributes.FALL_PROTECTION,"Fall Protection");
        translationBuilder.add(VMinusAttributes.MAGIC_PROTECTION,"Magic Protection");
        translationBuilder.add(VMinusAttributes.FIRE_PROTECTION,"Fire Protection");
        translationBuilder.add(VMinusAttributes.MINING_SPEED,"Mining Speed");
        translationBuilder.add(VMinusAttributes.CRITICAL_DAMAGE,"Critical Damage");
        translationBuilder.add(VMinusKeyBinds.CAPE_KEYBINDING.getTranslationKey(),"Capes");
        translationBuilder.add(VMinus.MOD_ID+".screen.capes.title","Capes");
    }
}