package game.layers;

import game.entity.Entity;
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

public class PlayLayer extends GameLayer {
    private Entity entity;
    private TileMap tl;
    private Camera camera;
    private final EntityManager em;

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
    }

    @Override
    public void update() {
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
    }
}
