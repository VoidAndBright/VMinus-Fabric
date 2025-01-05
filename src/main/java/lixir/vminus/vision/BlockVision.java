package lixir.vminus.vision;

public class BlockVision {
    String[] blocks;
    public float slipperiness;
    public float speed_multiplier;
    public float jump_multiplier;
    public float blast_resistance;
    public int luminance;
    public boolean banned;
    public BlockVision(BlockVision block){
        this.blocks = null;
        this.slipperiness = block.slipperiness;
        this.speed_multiplier = block.speed_multiplier;
        this.jump_multiplier = block.jump_multiplier;
        this.blast_resistance = block.blast_resistance;
        this.luminance = block.luminance;
        this.banned = block.banned;
    }
}
