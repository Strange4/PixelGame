package game.states;

import game.entity.Entity;
import game.entity.Player;
import game.graphics.SpriteSheet;
import game.util.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayState extends GameState{
    private SpriteSheet sprite;
    private Entity entity;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        sprite = new SpriteSheet("Bot Wheel/ken.png", 85, 85, false, false);
        entity = new Player(sprite);
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
        BufferedImage img = entity.getCurrentFrame();
        graphics2D.drawImage(img, 50, 50, img.getWidth() * scale, img.getHeight() * scale, null);
    }
}
