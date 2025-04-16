package net.lixir.vminus.vision.value;

import net.lixir.vminus.vision.condition.Condition;

public interface VisionValue<T,V>{
    T get_value();
    int get_priority();
    Condition<V>[] get_conditions();
    default boolean is_conditions_true(V vision_type){
        //Condition<V>[] conditions = get_conditions();
        //if (conditions == null) return true;
        //for (Condition<V> condition:conditions){
        //    if (condition.is_false(vision_type)) return false;
        //}
        return true;
    }
}
