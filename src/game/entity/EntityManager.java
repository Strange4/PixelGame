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

    /*
    * The ESM updates all entities on each tick and kills the dead enemy
     */
    public void update(){
        for(int i=0;i<this.entities.size();i++){
            collides();
            entities.get(i).updateState();
            if(entities.get(i).getState() == EntityState.STATE_DEAD){
                removeEntity(entities.get(i));
            }
        }
    }

    /*
    * This function checks if the player collides with a fruit and kills the fruit
     */
    public void collides(){
        ArrayList<Enemy> enemies = getEnemies();
        Player player = getPlayer();
        for(Enemy enemy : enemies){
            if(collides(player, enemy)){
                enemy.setState(EntityState.STATE_DEAD);
            }
        }
    }

    /**
     * @return An ArrayList of all the entities of the game
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }


    /**
     * Renders all the entities of the game
     * @param graphics2D Graphics2D object
     */
    public void render(Graphics2D graphics2D){
        for(Entity entity : entities){
            BufferedImage img = entity.getCurrentFrame();
            graphics2D.drawImage(img, (int) entity.getX(), (int) entity.getY(), (int) (img.getWidth() * entity.getScale()), (int) (img.getHeight() * entity.getScale()), null);
        }
    }

    public void addEntity(Entity e){
        this.entities.add(e);
    }

    public void removeEntity(Entity e){
        this.entities.remove(e);
    }

    /**
     * Returns if the player collides with an enemy
     * @param player Player entity
     * @param enemy Enemy entity
     * @return if the entities collide or not
     */
    public boolean collides(Player player, Enemy enemy){
        if((player.getX() + (player.getWIDTH() * player.getScale()))  > enemy.getX()  && player.getX() < (enemy.getX() + (enemy.getWIDTH() * enemy.getScale()))  && (player.getY() + (player.getHEIGHT() * player.getScale()))  > enemy.getY()  && player.getY()  < (enemy.getY() + (enemy.getHEIGHT() * enemy.getScale())) ) return true;
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
