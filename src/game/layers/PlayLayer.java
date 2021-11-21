package game.layers;

import game.entity.Entity;
import game.entity.Player;
import game.graphics.sheets.AnimationSpriteSheet;
import game.graphics.tilemap.TileMap;
import game.util.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;

public class PlayLayer extends GameLayer {
    private Entity entity;
    private TileMap tl;

    public PlayLayer(GameLayerManager glm) {
        super(glm);
        AnimationSpriteSheet sprite = new AnimationSpriteSheet("Bot Wheel/ken.png", 85, 85, false, false);
        entity = new Player(sprite);
        tl = new TileMap("Maps/try#2.tmx");
    }

    @Override
    public void update() {
        entity.updateAnimation();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics2D graphics2D, int scale) {
//        BufferedImage img = entity.getCurrentFrame();
        tl.render(graphics2D, 400,225, 500, 800, 400, 225);
//        graphics2D.drawImage(img, 50, 50, img.getWidth() * scale, img.getHeight() * scale, null);
    }
}
