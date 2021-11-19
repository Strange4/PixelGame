package game.graphics.tilesv2;

import game.graphics.SpriteSheet;

public class TileSet {
    private final int firstGid;
    private final int lastGid;
    private final String name;
    private final int tileWidth;
    private final int tileHeight;
    private final int nbColumns;
    private final int imageWidth;
    private final int imageHeight;
    private final String imageSource;
    private final SpriteSheet sheet;


    public TileSet(int firstGid, String name, int tileWidth, int tileHeight, int nbColumns, String imageSource, int imageWidth, int imageHeight) {
        this.firstGid = firstGid;
        this.name = name;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.nbColumns = nbColumns;
        this.imageSource = imageSource;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.sheet = new SpriteSheet(imageSource, tileWidth, tileHeight, false, false);
        this.lastGid = (this.nbColumns * (this.imageHeight / this.tileHeight)) + firstGid -1;
    }

}
