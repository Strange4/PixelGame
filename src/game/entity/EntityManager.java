package game.entity;

import game.entity.esm.EntityState;
import game.entity.types.Enemy;
import game.entity.types.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EntityManager {
    private ArrayList<Entity> entities;
    private double scale;

    public EntityManager(){
        this.entities = new ArrayList<>();
    }

    public void update(){
        for(int i=0;i<this.entities.size();i++){
            collides();
            entities.get(i).updateState();
            if(entities.get(i).getState() == EntityState.STATE_DEAD){
                removeEntity(entities.get(i));
            }

            // This works
//            entities.get(i).updateAnimation();
            // This doesn't
        }
    }

    public void collides(){
        ArrayList<Enemy> enemies = getEnemies();
        Player player = getPlayer();
        for(Enemy enemy : enemies){
            if(collides(player, enemy)){
                enemy.setState(EntityState.STATE_DEAD);
                player.addPoint();
            }
        }
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void render(Graphics2D graphics2D, double scale){
        for(Entity entity : entities){
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

    public boolean collides(Player player, Enemy enemy){
        if((player.getX() + player.getWIDTH()) * scale > enemy.getX() * scale && player.getX()* scale < (enemy.getX() + enemy.getWIDTH()) * scale && (player.getY() + player.getHEIGHT()) * scale > enemy.getY() * scale && player.getY() * scale < (enemy.getY() + enemy.getHEIGHT()) * scale) return true;
        return false;
    }

    public Player getPlayer() {
        for (Entity entity : getEntities()) {
            if (entity instanceof Player player) {
                return player;
            }
        }
        throw new UnsupportedOperationException("No player");
    }

    public ArrayList<Enemy> getEnemies(){
        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        for(Entity entity : getEntities()){
            if(entity instanceof Enemy enemy){
                enemyArrayList.add(enemy);
            }
        }
        return enemyArrayList;
    }

    public void setScale(double scale){
        this.scale = scale;
    }

}
