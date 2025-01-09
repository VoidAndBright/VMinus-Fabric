package lixir.vminus.vision.type;

import lixir.vminus.vision.Conditioned;

public class BlockVision {
    public String[] blocks;
    public Boolean banned;
    public Float slipperiness;
    public Float speed_multiplier;
    public Float jump_multiplier;
    public Float blast_resistance;
    public Float hardness;
    public Integer luminance;
	public Integer volume;
	public Integer pitch;
	public String breakSound;
	public String stepSound;
	public String placeSound;
	public String hitSound;
	public String fallSound;
    public BlockVision(BlockVision blockVision){
        this.blocks = null;
        this.slipperiness = blockVision.slipperiness;
        this.speed_multiplier = blockVision.speed_multiplier;
        this.jump_multiplier = blockVision.jump_multiplier;
        this.blast_resistance = blockVision.blast_resistance;
        this.hardness = blockVision.hardness;
        this.luminance = blockVision.luminance;
        this.banned = blockVision.banned;
        this.volume = blockVision.volume;
        this.pitch = blockVision.pitch;
        this.breakSound = blockVision.breakSound;
        this.stepSound = blockVision.stepSound;
        this.placeSound = blockVision.placeSound;
        this.hitSound = blockVision.hitSound;
        this.fallSound = blockVision.fallSound;
    }
}
