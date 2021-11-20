package game.entity;

import java.util.ArrayList;

public class EntityManager {
    ArrayList<Entity> entities;

    public void update(){
        for(int i = 0; i < entities.size(); i++){
            entities.get(i).update();
        }
    }

    public void addEntity(Entity e){
        this.entities.add(e);
    }

    public void removeEntityObj(Entity e){
        this.entities.remove(e);
    }

    public void removeEntityPos(int i){
        this.entities.remove(i);
    }

}
