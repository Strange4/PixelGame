package game.graphics.tiles.blocks;

import game.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
    protected int width;
    protected int height;
    protected BufferedImage image;
    protected Vector2D position;

    public Block(int width, int height, BufferedImage image, Vector2D position) {
        this.width = width;
        this.height = height;
        this.image = image;
        this.position = position;
    }

    public abstract boolean update();

    // Todo: calculate the x and y positions of the render based on the world camera
    public void render(Graphics2D g){
        g.drawImage(image, 10, 10, this.width, this.height, null);
    }
}
