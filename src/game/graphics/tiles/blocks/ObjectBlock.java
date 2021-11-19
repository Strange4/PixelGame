package game.graphics.tiles.blocks;

import game.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectBlock extends Block{

    public ObjectBlock(int width, int height, BufferedImage image, Vector2D position) {
        super(width, height, image, position);
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.setColor(Color.CYAN);
//        g.drawRect();
    }
}
