package lixir.vminus.vision.visions;

public class FuelItemVision {
    String[] items;
    int time;
    FuelItemVision(String[] items,int time){
        this.items = items;
        this.time = time;
    }
    public void addVision() {
          Visions.FUEL_ITEM_VISIONS.add(this);
    }
}
