package lixir.vminus.vision.visions;

public class ItemVision {
    String[] item_tags;
    int time;
    ItemVision(String[] item_tags, int time){
        this.item_tags = item_tags;
        this.time = time;
    }
    public void addVision() {
          Visions.ITEM_VISIONS.add(this);
    }
}
