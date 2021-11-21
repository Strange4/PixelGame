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
            entities.get(i).update();
        }
    }

    public void render(Graphics2D graphics2D, int scale){
        for(int i = 0; i < entities.size(); i++){
            Entity entity = entities.get(i);
            entity.update();
            BufferedImage img = entity.getCurrentFrame();
            graphics2D.drawImage(img, (int) entity.getX(), (int) entity.getY(), img.getWidth() * scale, img.getHeight() * scale, null);
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
