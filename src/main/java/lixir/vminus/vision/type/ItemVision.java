package lixir.vminus.vision.type;

import lixir.vminus.vision.VisionElement;

public class ItemVision {
    public String[] items;
    private final VisionElement<Integer>[] fuel_time;
    private final VisionElement<Boolean>[] banned;
    public ItemVision(ItemVision itemVision){
        this.items = null;
        this.fuel_time = itemVision.fuel_time;
        this.banned = itemVision.banned;
    }

    public Boolean get_banned(){
        if(banned == null) return null;
        return VisionElement.get_value(banned);
    }
    public Integer get_fuel_time(){
        if(fuel_time == null) return null;
        return VisionElement.get_value(fuel_time);
    }

}
