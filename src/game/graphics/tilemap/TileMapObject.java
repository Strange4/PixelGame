package game.graphics.tilemap;

public class TileMapObject {
    private final double posX;
    private final double posY;
    private final double width;
    private final double height;

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
