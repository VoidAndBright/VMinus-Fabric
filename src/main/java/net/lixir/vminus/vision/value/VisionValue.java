package net.lixir.vminus.vision.value;

import net.lixir.vminus.vision.condition.Condition;

public interface VisionValue<T,V>{
    T get_value();
    int get_priority();
    Condition<V>[] get_conditions();
    default boolean is_conditions_true(V vision_type, Condition<V>[] conditions, int index){
        if (conditions != null && index < conditions.length){
            Condition<V> condition = conditions[index];
            if (condition.is_false(vision_type)) return false;
            return is_conditions_true(vision_type,conditions,index+1);
        }
        else return true;
    }
}
