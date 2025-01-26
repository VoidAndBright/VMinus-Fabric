package net.lixir.vminus.vision;

import net.lixir.vminus.vision.properties.Condition;

public class VisionElement<T>{
    public T return_value;
    public Condition[] conditions;
    public VisionElement(T value, Condition[] conditions) {
        this.return_value = value;
        this.conditions = conditions;
    }
    public T get_value() {
        return return_value;
    }
    public boolean conditions_is_true(){
        if (conditions == null) return true;
        return iterate_conditions(conditions,0);
    }
    private static boolean iterate_conditions(Condition[] conditions, int index){
        if (conditions == null) return true;
        if (index < conditions.length){
            Condition condition = conditions[index];
            if (!condition.is_true()) return false;
            return iterate_conditions(conditions,index+1);
        }
        return true;
    }
}
