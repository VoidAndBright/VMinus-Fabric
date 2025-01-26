package net.lixir.vminus.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.enums.Instrument;
import net.minecraft.sound.BlockSoundGroup;

public class DefaultiumBlock extends Block {
    public DefaultiumBlock() {
        super(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).sounds(BlockSoundGroup.NETHERITE).strength(1f, 10f).luminance(15));
    }

}
