package game.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EntityManager {
    ArrayList<Entity> entities;

    public EntityManager(){
        this.entities = new ArrayList<>();
    }

    public void update(){
        for(int i = 0; i < entities.size(); i++){
            // This works
//            entities.get(i).updateAnimation();
            // This doesn't
            entities.get(i).updateState();
        }
    }

    public void render(Graphics2D graphics2D, double scale){
        for(int i = 0; i < entities.size(); i++){
            Entity entity = entities.get(i);
            BufferedImage img = entity.getCurrentFrame();
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
