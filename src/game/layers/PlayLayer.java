package game.layers;

import game.entity.Entity;
import game.entity.Player;
import game.graphics.SpriteSheet;
import game.keyboard.MovementHandler;
import game.util.KeyHandler;
import game.util.MouseHandler;
import game.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayLayer extends GameLayer {
    private SpriteSheet sprite;
    private Entity entity;

    public PlayLayer(GameLayerManager glm) {
        super(glm);
        sprite = new SpriteSheet("Bot Wheel/ken.png", 85, 85, false, false);
        entity = new Player(sprite, new Vector2D(50, 50));
//        entity.changeAnimation(6);
    }

    @Override
    public void update() {
        entity.updateAnimation();
    }

    @Override
    public void input(MouseHandler mouse, MovementHandler movementHandler) {
        this.entity.move(movementHandler.getDirectional2DVector(), 2);
    }

    @Override
    public void render(Graphics2D graphics2D, int scale) {
        BufferedImage img = entity.getCurrentFrame();
        graphics2D.drawImage(img, (int) entity.getX(), (int) entity.getY(), img.getWidth() * scale, img.getHeight() * scale, null);
//        graphics2D.fillRect();
    }
}
