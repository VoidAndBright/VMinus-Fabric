package lixir.vminus.vision.type;

import lixir.vminus.vision.Conditioned;

public class ItemVision {
    public String[] items;
    public Integer fuel_time;
    public Conditioned<Boolean> banned;
    public ItemVision(ItemVision itemVision){
        this.items = null;
        this.fuel_time = itemVision.fuel_time;
        this.banned = itemVision.banned;
    }
}
