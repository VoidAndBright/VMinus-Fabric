package lixir.vminus;

public class Vision {
    String[] blocks;
    boolean banned;
    Vision(String[] blocks,boolean banned){
        this.blocks = blocks;
        this.banned = banned;
    }


    public String[] getBlocks() {
        return blocks;
    }
}
