package game.layers;

import game.entity.Entity;
import game.entity.EntityManager;
import game.entity.types.Player;
import game.graphics.SpriteSheet;
import game.keyboard.KeyHandler;
import game.util.MouseHandler;
import game.util.Vector2D;

import java.awt.*;

public class PlayLayer extends GameLayer {
    private SpriteSheet sprite;
    private Entity entity;
    private final EntityManager em;

    public PlayLayer(GameLayerManager glm) {
        super(glm);
//        sprite = ;
//        entity = ;
        this.em = new EntityManager();
        this.em.addEntity(new Player(new SpriteSheet("Bot Wheel/ken.png", 85, 85, false, false), new Vector2D(50, 50), 6));
////        entity.changeAnimation(6);
    }

    @Override
    public void update() {
        this.em.update();
//        entity.updateAnimation();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler keyHandler) {
//        keyHandler.handleKey();
//        Vector2D v = movementHandler.getDirectional2DVector();
//        System.out.println(movementHandler.getState());
//        this.entity.move(v);
    }

    @Override
    public void render(Graphics2D graphics2D, int scale) {
        this.em.render(graphics2D, scale);
//        BufferedImage img = entity.getCurrentFrame();
//        graphics2D.drawImage(img, (int) entity.getX(), (int) entity.getY(), img.getWidth() * scale, img.getHeight() * scale, null);
    }
}
