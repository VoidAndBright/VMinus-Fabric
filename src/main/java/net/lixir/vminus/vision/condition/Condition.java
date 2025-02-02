package net.lixir.vminus.vision.condition;

public interface Condition<V> {
    boolean is_false(V vision_type);
}
