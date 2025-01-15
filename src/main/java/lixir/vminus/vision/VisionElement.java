package lixir.vminus.vision;

import lixir.vminus.vision.properties.Condition;

public class VisionElement<T>{
    public T value;
    public Condition[] conditions;

    public VisionElement(T value, Condition[] conditions) {
        this.value = value;
        this.conditions = conditions;
    }
    public boolean conditions_is_true(){
        if (conditions == null) return true;
        return iterate_conditions(0);
    }
    private boolean iterate_conditions(int index){
        if (index < conditions.length){
            Condition condition = conditions[index];
            if (!condition.is_true()) return false;
            return iterate_conditions(index+1);
        }
        return true;
    }
    public static <T> T get_value(VisionElement<T>[] vision_elements){
        return iterate_vision_elements(vision_elements,0);
    }
    public static <T> T iterate_vision_elements(VisionElement<T>[] vision_elements, int index){
        if (index < vision_elements.length){
            VisionElement<T> vision_element = vision_elements[index];
            if (vision_element.conditions_is_true()){
                return vision_element.value;
            }
            return iterate_vision_elements(vision_elements,index+1);
        }
        return null;
    }
}
