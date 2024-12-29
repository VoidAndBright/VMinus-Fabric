package lixir.vminus.vision.visions;

import org.jetbrains.annotations.Nullable;

public class BlockVision {
    String[] block_tags;
    float slipperiness;
    float speed_multiplier;
    float jump_multiplier;
    float blast_resistance;
    boolean banned;
    BlockVision(String[] block, float slipperiness,float speed_multiplier,float jump_multiplier,boolean banned){
        this.block_tags = block;
        this.slipperiness = slipperiness;
        this.speed_multiplier = speed_multiplier;
        this.jump_multiplier = jump_multiplier;
        this.banned = banned;
    }
    public void addVision() {
        Visions.BLOCK_VISIONS.add(this);
    }
    public float getSlipperiness() {
        return slipperiness;
    }
    public float getSpeedMultiplier() {
        return speed_multiplier;
    }
    public float getJumpMultiplier() {
        return jump_multiplier;
    }
    public float getBlastResistance() {
        return blast_resistance;
    }
}
