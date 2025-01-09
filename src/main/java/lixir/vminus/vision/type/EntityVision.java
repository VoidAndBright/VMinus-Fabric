package lixir.vminus.vision.type;

public class EntityVision {
    public String[] entities;
    public Boolean banned;
    public EntityVision(EntityVision entityVision){
        this.entities = null;
        this.banned = entityVision.banned;
    }
}
