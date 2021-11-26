package game.layers;

import game.entity.Entity;
import game.entity.Player;
import game.graphics.sheets.AnimationSpriteSheet;
import game.graphics.tilemap.TileMap;
import game.keyboard.MovementHandler;
import game.util.MouseHandler;
import game.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayLayer extends GameLayer {
    private Entity entity;
    private TileMap tl;

    public PlayLayer(GameLayerManager glm) {
        super(glm);
        AnimationSpriteSheet sprite = new AnimationSpriteSheet("Knight/KnightRun_strip.png", 96, 64, false, false);

        tl = new TileMap("Maps/try#2.tmx");
        entity = new Player(sprite, new Vector2D(50, 50));
        entity.setDelayBetweenFrames(4,0);
    }

    @Override
    public void update() {
        entity.updateAnimation();
    }

    @Override
    public void input(MouseHandler mouse, MovementHandler movementHandler) {
        this.entity.move(movementHandler.getDirectional2DVector());
    }

    @Override
    public void render(Graphics2D graphics2D, int scale) {
        BufferedImage img = entity.getCurrentFrame();
        tl.render(graphics2D, 400,225, 500, 800, 400, 225);
//        graphics2D.drawImage(img, 50, 50, img.getWidth() * scale, img.getHeight() * scale, null);
        graphics2D.drawImage(img, (int) entity.getX(), (int) entity.getY(), img.getWidth() * scale, img.getHeight() * scale, null);
    }
}
