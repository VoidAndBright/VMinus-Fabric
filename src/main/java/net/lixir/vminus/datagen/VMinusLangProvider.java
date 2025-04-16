package net.lixir.vminus.datagen;

import com.terraformersmc.modmenu.config.option.EnumConfigOption;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.lixir.vminus.block.VMinusBlocks;
import net.lixir.vminus.cape.Cape;
import net.lixir.vminus.config.VMinusConfigs;
import net.lixir.vminus.entity.VMinusEntities;
import net.lixir.vminus.entity.attribute.VMinusEntityAttributes;
import net.lixir.vminus.item.VMinusItems;
import net.lixir.vminus.screen.screens.DefaultScreen;
import net.lixir.vminus.screen.screens.VMinusConfigScreen;

public class VMinusLangProvider extends FabricLanguageProvider {

    public VMinusLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(VMinusItems.DEFAULTIUM_ITEM, "Defaultium Item");
        translationBuilder.add(
            VMinusBlocks.DEFAULTIUM_BLOCK,
            "Defaultium Block"
        );
        translationBuilder.add(VMinusBlocks.DEFAULTIUM_HEAD, "Defaultium Head");
        translationBuilder.add(
            VMinusEntities.DEFAULTIUM_ENTITY,
            "Defaultium Entity"
        );
        translationBuilder.add(VMinusEntityAttributes.PROTECTION, "Protection");
        translationBuilder.add(
            VMinusEntityAttributes.BLAST_PROTECTION,
            "Blast Protection"
        );
        translationBuilder.add(
            VMinusEntityAttributes.BLUNT_PROTECTION,
            "Blunt Protection"
        );
        translationBuilder.add(
            VMinusEntityAttributes.FALL_PROTECTION,
            "Fall Protection"
        );
        translationBuilder.add(
            VMinusEntityAttributes.MAGIC_PROTECTION,
            "Magic Protection"
        );
        translationBuilder.add(
            VMinusEntityAttributes.FIRE_PROTECTION,
            "Fire Protection"
        );
        translationBuilder.add(
            VMinusEntityAttributes.MINING_SPEED,
            "Mining Speed"
        );
        translationBuilder.add(
            VMinusEntityAttributes.CRITICAL_DAMAGE,
            "Critical Damage"
        );
        translationBuilder.add(
            VMinusEntityAttributes.MOB_DETECTION_RANGE,
            "Critical Damage"
        );
        translationBuilder.add(VMinusEntityAttributes.JUMP_BOOST, "Jump boost");
        translationBuilder.add(VMinusEntityAttributes.WIDTH, "Width");
        translationBuilder.add(VMinusEntityAttributes.HEIGHT, "Height");
        translationBuilder.add(DefaultScreen.TITLE.getString(), "Defaultium");
        translationBuilder.add(VMinusConfigScreen.TITLE.getString(), "VMinus Configs");
        translationBuilder.add(VMinusConfigs.CAPE.asOption().toString(),"Cape");
        translationBuilder.add(config_enum_key(VMinusConfigs.CAPE,Cape.NONE),"None");
        translationBuilder.add(config_enum_key(VMinusConfigs.CAPE,Cape.BEEPER),"Beeper");
        translationBuilder.add(config_enum_key(VMinusConfigs.CAPE,Cape.SHROUD),"Shroud");
        translationBuilder.add(config_enum_key(VMinusConfigs.CAPE,Cape.GHOST),"Ghost");
        translationBuilder.add(config_enum_key(VMinusConfigs.CAPE,Cape.MARROW),"Marrow");
        translationBuilder.add(config_enum_key(VMinusConfigs.CAPE,Cape.PROTOTYPE),"Prototype");
        translationBuilder.add(config_enum_key(VMinusConfigs.CAPE,Cape.TROLL),"Troll");
        translationBuilder.add(config_enum_key(VMinusConfigs.CAPE,Cape.PHOTON),"Photon");
    }
    private static <E extends Enum<E>> String config_enum_key(EnumConfigOption<E> enum_config, E enum_value){
        return "option.modmenu." + enum_config.getKey() + "." + enum_value.toString().toLowerCase();
    }
}
