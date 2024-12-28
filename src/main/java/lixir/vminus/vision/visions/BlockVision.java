package lixir.vminus.vision.visions;

public class BlockVision {
    String blocks;
    float slipperiness;
    float velocity_multiplier;
    float jump_velocity_multiplier;
    boolean banned;
    BlockVision(String block, float slipperiness,float velocity_multiplier,float jump_velocity_multiplier,boolean banned){
        this.blocks = block;
        this.slipperiness = slipperiness;
        this.velocity_multiplier = velocity_multiplier;
        this.jump_velocity_multiplier = jump_velocity_multiplier;
        this.banned = banned;
    }
    public void addVision() {
        Visions.DATA_BLOCK_VISIONS.add(this);
    }
    public float getSlipperiness() {
        return slipperiness;
    }
    public float getVelocityFactor() {
        return velocity_multiplier;
    }
    public float getJumpVelocityFactor() {
        return jump_velocity_multiplier;
    }
}
