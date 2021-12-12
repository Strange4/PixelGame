package game.graphics.tilemap;

import game.graphics.sheets.SpriteSheet;

import java.awt.image.BufferedImage;

public class TileSet {
    private final int firstGid;
    private final int lastGid;
    private final String name;
    private final int tileWidth;
    private final int tileHeight;
    private final int nbColumns;
    private final int imageWidth;
    private final int imageHeight;
    private final int nbRows;
    private final String imageSource;
    private final SpriteSheet sheet;


    /**
     * A tileset of tiles represented from a tmx file
     * @param firstGid the id of the first tile
     * @param name the name of the tileset
     * @param tileWidth the width of each tile
     * @param tileHeight the height of each tile
     * @param nbColumns the number of columns of the tileset
     * @param imageSource the source of the image containing the tileset
     * @param imageWidth the width of the image
     * @param imageHeight the height of the image
     */
    public TileSet(int firstGid, String name, int tileWidth, int tileHeight, int nbColumns, String imageSource, int imageWidth, int imageHeight) {
        this.firstGid = firstGid;
        this.name = name;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.nbColumns = nbColumns;
        this.imageSource = imageSource;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.sheet = new SpriteSheet(imageSource, tileWidth, tileHeight);
        this.nbRows = this.imageHeight / this.tileHeight;
        this.lastGid = (this.nbColumns * nbRows) + firstGid -1;
    }

    public BufferedImage getTileImage(int tileId){
        if(tileId < this.firstGid || tileId > this.lastGid){
            throw new IllegalArgumentException("This tileID is beyond the scope of the this tileset. tileID: " + tileId +", firstgid: " + this.firstGid + ", lastgid: "+this.lastGid);
        }
        int setTID = tileId - this.firstGid; // starts at 0
        int row = (int) Math.floor(setTID / this.nbColumns); // starts at 0
        int column = setTID - (row*this.nbColumns); // starts at 0
        return this.sheet.getSprite(row, column);
    }

    public int getLastGid() {
        return this.lastGid;
    }

    public int getFirstGid() {
        return this.firstGid;
    }
}
