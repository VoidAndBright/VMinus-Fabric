package lixir.vminus.vision.visions;

public class BannedBlockVision {
    String[] blocks;
    boolean banned;
    BannedBlockVision(String[] blocks, boolean banned){
        this.blocks = blocks;
        this.banned = banned;
    }
    public void addVision() {
          Visions.BANNED_BLOCK_VISIONS.add(this);
    }
}
