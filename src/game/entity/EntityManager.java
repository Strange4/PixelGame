package game.entity;

import game.entity.esm.EntityState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EntityManager {
    ArrayList<Entity> entities;

    public EntityManager(){
        this.entities = new ArrayList<>();
    }

    public void update(){
        for(int i=0;i<this.entities.size();i++){
            entities.get(i).updateState();
             if(entities.get(i).getState() == EntityState.STATE_DEAD){
                 removeEntity(entities.get(i));
             }
            // This works
//            entities.get(i).updateAnimation();
            // This doesn't
        }
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void render(Graphics2D graphics2D, double scale){
        for(int i = 0; i < entities.size(); i++){
            Entity entity = entities.get(i);
            BufferedImage img = entity.getCurrentFrame();
            graphics2D.drawRect((int) entity.getX(), (int) entity.getY(), (int) (entity.getWIDTH() * scale), (int) (entity.getHEIGHT() * scale));
            graphics2D.drawImage(img, (int) entity.getX(), (int) entity.getY(), (int) (img.getWidth() * scale), (int) (img.getHeight() * scale), null);
        }
    }

    public void addEntity(Entity e){
        this.entities.add(e);
    }

    public void removeEntity(Entity e){
        this.entities.remove(e);
    }

    public void removeEntity(int i){
        this.entities.remove(i);
    }

}
