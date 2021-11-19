package game.graphics.tiles.blocks;

import game.util.Vector2D;

import java.awt.image.BufferedImage;

public class NormalBlock extends Block{

    public NormalBlock(int width, int height, BufferedImage image, Vector2D position) {
        super(width, height, image, position);
    }

    @Override
    public boolean update() {
        return false;
    }
}
