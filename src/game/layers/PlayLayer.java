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
import game.util.MouseHandler;
import game.util.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayLayer extends GameLayer {
    private Entity entity;
    private TileMap tl;
    private Camera camera;
    private final EntityManager em;
    private final int DESPAWN_TICK = 50;
    private int CURRENT_DESPAWN = 0;

    public PlayLayer(GameLayerManager glm) {
        super(glm);
        AnimationSpriteSheet sprite = new AnimationSpriteSheet("Knight/KnightRun_strip.png", 96, 64, false, true);
        tl = new TileMap("Maps/try#2.tmx");
        Player entity = new Player(sprite, new Vector2D(50, 50));
        entity.setDelayBetweenFrames(4,0);
        camera = new Camera(entity,2,400, 225);
        this.em = new EntityManager();
        this.em.addEntity(entity);
        this.em.addEntity(new Enemy(sprite, new Vector2D(50, 50)));
        for(int i = 0; i < 10; i++){
            addRandomEntity();
        }
    }

    public void addRandomEntity(){
        AnimationSpriteSheet sprite = new AnimationSpriteSheet("Knight/KnightRun_strip.png", 96, 64, false, true);
        Random r = new Random();
        Vector2D v = new Vector2D(r.nextInt(700),r.nextInt(400));
        Enemy enemy = new Enemy(sprite, v);
        this.em.addEntity(enemy);
    }

    public void killRandomEntity(){
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

    @Override
    public void update() {
        this.CURRENT_DESPAWN++;
        if(CURRENT_DESPAWN == DESPAWN_TICK){
            CURRENT_DESPAWN = 0;
            addRandomEntity();
            killRandomEntity();
            System.out.println("Points: " + this.em.getPlayer().getPoints());
        }
        this.em.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler keyHandler) {
    }

    @Override
    public void render(Graphics2D graphics2D, int scale) {
//        camera.renderMap(graphics2D,tl,3);
        tl.render(graphics2D, 400,225, 500, 800, 400, 225);
        this.em.render(graphics2D, scale * 0.5);
        graphics2D.setColor(Color.orange);
        graphics2D.fillRect(0, 0, 100, 30);
        graphics2D.setColor(Color.black);
        graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        graphics2D.drawString("Points: " + this.em.getPlayer().getPoints(), 2, 20);
    }
}
