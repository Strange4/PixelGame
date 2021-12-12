package game.layers;

import game.entity.Entity;
import game.entity.esm.EntityState;
import game.entity.types.Player;
import game.graphics.Camera;
import game.graphics.sheets.AnimationSpriteSheet;
import game.graphics.tilemap.TileMap;
import game.entity.EntityManager;
import game.entity.types.Enemy;
import game.keyboard.KeyHandler;
import game.util.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayLayer extends GameLayer {
    private final TileMap tl;
    private final EntityManager em;
    private int CURRENT_DESPAWN = 0;
    public final long GAME_START = System.nanoTime();


    public PlayLayer(GameLayerManager glm) {
        super(glm);
        tl = new TileMap("Maps/try#2.tmx");
        this.em = new EntityManager();
        addPlayer();
        // adds the randomly placed chorus fruits on the map
        for(int i = 0; i < 12; i++){
            addRandomEntity();
        }
    }

    private void addPlayer(){
        AnimationSpriteSheet playerSprite = new AnimationSpriteSheet("Knight/KnightRun_strip.png", 96, 64, false, true);
        Player entity = new Player(playerSprite, new Vector2D(50, 50));
        entity.setDelayBetweenFrames(4,0);
        this.em.addEntity(entity);
    }

    private void addRandomEntity(){
        AnimationSpriteSheet sprite = new AnimationSpriteSheet("Knight/chorus_fruit.png", 32, 32, false, true);
        Random r = new Random();
        Vector2D v = new Vector2D(r.nextInt(700),r.nextInt(400));
        Enemy enemy = new Enemy(sprite, v);
        enemy.setScale(1.15);
        this.em.addEntity(enemy);
    }

    private void killRandomEntity(){
        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        for(Entity entity : this.em.getEntities()){
            if(entity instanceof Enemy enemy){
                enemyArrayList.add(enemy);
            }
        }
        Random r = new Random();
        int bound = enemyArrayList.size();
        Enemy randomEnemy = enemyArrayList.get(r.nextInt(bound));
        randomEnemy.setState(EntityState.STATE_DEAD);
    }

    private void gameOver(){
        this.glm.addState(new GameOverLayer(this.glm));
        this.glm.removeState(this);
    }

    /**
     * updates the player layer
     */
    @Override
    public void update() {
        if(this.em.getEnemies().size() == 0) gameOver();
        this.CURRENT_DESPAWN++;
        int DESPAWN_TICK = 40;
        if(CURRENT_DESPAWN == DESPAWN_TICK){
            CURRENT_DESPAWN = 0;
            addRandomEntity();
            killRandomEntity();
        }
        this.em.update();
    }

    /**
     * the keyhandlers are ignored int this layer because the EnitityMachine already does it
     */
    @Override
    public void input(KeyHandler keyHandler) {
    }

    /**
     * Renders the player layer
     * @param graphics2D the graphics on which to render
     * @param scale the scale to render
     */
    @Override
    public void render(Graphics2D graphics2D, int scale) {
        this.em.setScale(scale);
        this.em.getPlayer().setScale(scale);
        tl.render(graphics2D, 400,225, 500, 800, 400, 225);
        this.em.render(graphics2D);
    }
}
