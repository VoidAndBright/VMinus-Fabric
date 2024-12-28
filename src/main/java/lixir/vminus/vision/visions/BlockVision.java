package lixir.vminus.vision.visions;

public class BlockVision {
    String blocks;
    float slipperiness;
    float velocity_factor;
    float jump_velocity_factor;
    boolean banned;
    BlockVision(String block, float slipperiness,float velocity,boolean banned){
        this.blocks = block;
        this.slipperiness = slipperiness;
        this.velocity_factor = velocity;
        this.banned = banned;
    }
    public void addVision() {
        Visions.DATA_BLOCK_VISIONS.add(this);
    }
    public float getSlipperiness() {
        return slipperiness;
    }
    public float getVelocityFactor() {
        return velocity_factor;
    }
    public float getJumpVelocityFactor() {
        return jump_velocity_factor;
    }
}
