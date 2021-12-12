package game.graphics.tilemap;

public class TileMapObject {
    private final double posX;
    private final double posY;
    private final double width;
    private final double height;

    /**
     * A rectangular object that is contained on a tileMap
     * @param posX the x position of the object relative to the map
     * @param posY the y position of the object relative to the map
     * @param width the width of the object
     * @param height the height of the object
     */
    public TileMapObject(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
