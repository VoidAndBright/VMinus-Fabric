package net.lixir.vminus.vision.type;

import net.lixir.vminus.vision.VisionElement;

public class TestVision implements Vision{
    public boolean banned;
    public VisionElement<Boolean>[] replace;
    //This exists for testing with Deserializer
    public TestVision(boolean banned) {
        this.banned = banned;
    }
    public TestVision(VisionElement<Boolean>[] replace) {
        this.replace = replace;
    }


    @Override
    public String[] get_targets() {
        return new String[0];
    }
}
