package net.lixir.vminus.vision;

import net.lixir.vminus.vision.value.VisionValue;

import java.util.Comparator;
import java.util.Vector;

public class Vision {
    public static <T,V,TV extends VisionValue<T,V>> Vector<TV> sum(Vector<TV> base, Vector<TV> added){
        if (base != null && added != null){
            Vector<TV> vector = new Vector<>();
            vector.addAll(base);
            vector.addAll(added);
            vector.sort(Comparator.comparingInt(TV::get_priority));
            return vector;
        }
        else if (base == null && added != null) return added;
        else return base;
    }
    public static <T,V,TV extends VisionValue<T,V>> T unwrap(V vision_type, Vector<TV> vision_elements){
        if (vision_elements == null) return null;
        for (TV vision_element:vision_elements) {
            if (vision_element.is_conditions_true(vision_type)) return vision_element.get_value();
        }
        return null;
    }
}
