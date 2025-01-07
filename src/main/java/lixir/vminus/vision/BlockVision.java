package lixir.vminus.vision;

import net.minecraft.sound.BlockSoundGroup;

public class BlockVision {
    String[] blocks;
    public float slipperiness;
    public float speed_multiplier;
    public float jump_multiplier;
    public float blast_resistance;
    public int hardness;
    public int luminance;
    public boolean banned;
	public int volume;
	public int pitch;
	public String breakSound;
	public String stepSound;
	public String placeSound;
	public String hitSound;
	public String fallSound;
    public BlockVision(BlockVision block){
        this.blocks = null;
        this.slipperiness = block.slipperiness;
        this.speed_multiplier = block.speed_multiplier;
        this.jump_multiplier = block.jump_multiplier;
        this.blast_resistance = block.blast_resistance;
        this.hardness = block.hardness;
        this.luminance = block.luminance;
        this.banned = block.banned;
    }
}
