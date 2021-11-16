package game.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteSheet {
    private final BufferedImage SPRITESHEET;
    private ArrayList<ArrayList<BufferedImage>> ALL_SPRITES;
    private final int SPRITE_WIDTH;
    private final int SPRITE_HEIGHT;
    private int SPRITE_COL_COUNT;
    private int SPRITE_ROW_COUNT;
    private final boolean spaceAdjusted;
    private final boolean spritesByColumn;

    public SpriteSheet(String file, int spriteWidth, int spriteHeight, boolean spritesByColumn, boolean adjustForSpace){
        this.spaceAdjusted = adjustForSpace;
        this.SPRITE_WIDTH = spriteWidth;
        this.SPRITE_HEIGHT = spriteHeight;
        this.spritesByColumn = spritesByColumn;
        SPRITESHEET = loadSpriteSheet(file);
        if((((double) SPRITESHEET.getWidth()) / ((double) spriteWidth) %1) != 0){
            throw new IllegalArgumentException("The sprite sheet is not divisible by the sprite width");
        }
        if((((double) SPRITESHEET.getHeight()) / ((double) spriteHeight) %1) != 0){
            throw new IllegalArgumentException("The sprite sheet is not divisible by the sprite height");
        }
        loadAllSprites();
    }

    private BufferedImage loadSpriteSheet(String file){
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(file));
        } catch (IOException e){
            System.out.println("There was an error getting the sprite");
            e.printStackTrace();
        }
        if(sprite == null){
            throw new IllegalArgumentException("The file given was not found");
        }
        return sprite;
    }

    /**
     * loads all the sprites from the spritesheet into the ALL_SPRITES array
     */
    private void loadAllSprites(){
        SPRITE_COL_COUNT = SPRITESHEET.getWidth() / SPRITE_WIDTH;
        SPRITE_ROW_COUNT = SPRITESHEET.getHeight() / SPRITE_HEIGHT;
        if(this.spritesByColumn){
            ALL_SPRITES = new ArrayList<>(SPRITE_COL_COUNT);
            for(int column=0;column<SPRITE_COL_COUNT;column++){
                ALL_SPRITES.add(new ArrayList<>());
                for(int row=0;row<SPRITE_ROW_COUNT;row++){
                    BufferedImage sprite = getSprite(row, column);
                    if(sprite != null){
                        ALL_SPRITES.get(column).add(getSprite(row, column));
                    }
                }
            }
        } else {
            ALL_SPRITES = new ArrayList<>(SPRITE_ROW_COUNT);
            for(int row=0;row<SPRITE_ROW_COUNT;row++){
                ALL_SPRITES.add(new ArrayList<>());
                for(int column=0;column<SPRITE_COL_COUNT;column++){
                    BufferedImage sprite = getSprite(row, column);
                    if(sprite != null) {
                        ALL_SPRITES.get(row).add(getSprite(row, column));
                    }
                }
            }
        }

    }

    /**
     * checks if all the pixels of an image are transparent
     * @param image the image to check
     * @return returns true if all the pixels of an image are transparent, false otherwise
     */
    private boolean imageIsEmpty(BufferedImage image){
        Dimension[] dimensions = calculateImageBounds(image);
        int minX = dimensions[0].width;
        int minY = dimensions[0].height;
        int maxX = dimensions[1].width;
        int maxY = dimensions[1].height;
        return minX + maxX == -1 && minY + maxY == -1;
    }

    /**
     * returns the sprite from the spritesheet at the specified row and column
     * @param row the row where the sprite is contained in the spritesheet (starts at 0)
     * @param column the row where the sprite is contained in the spritesheet (starts at 0)
     * @return the sprite as a BufferedImage
     */
    public BufferedImage getSprite(int row, int column){
        if(row < 0 || column < 0){
            throw new IllegalArgumentException("The row and the column cannot be bellow 0");
        }
        BufferedImage sprite = SPRITESHEET.getSubimage(column * SPRITE_WIDTH,row * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
        Dimension[] dimensions = calculateImageBounds(sprite);
        int minX = dimensions[0].width;
        int minY = dimensions[0].height;
        int maxX = dimensions[1].width;
        int maxY = dimensions[1].height;
        if(imageIsEmpty(sprite)){
            return null;
        }
        if(this.spaceAdjusted){
            return sprite.getSubimage(minX, minY, maxX-minX, maxY-minY);
        }
        return sprite;
    }

    /**
     * Calculates the bounds of a transparent image based on the pixels that are not transparent
     * @param image the image used to calculate the bounds
     * @return a Dimension array of size 2. The first containing the minX and minY values, the second containing the maxX and maxY values
     */
    private Dimension[] calculateImageBounds(BufferedImage image){
        int height = image.getHeight();
        int width = image.getWidth();
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for(int y=0;y< height;y++){
            for(int x=0;x< width;x++){
                int alpha = (image.getRGB(x,y) >> 24) & 0xFF;
                if(alpha != 0){
                    minX = Math.min(x, minX);
                    maxX = Math.max(x, maxX);
                    minY = Math.min(y, minY);
                    maxY = Math.max(y, maxY);
                }
            }
        }
        Dimension[] dimensions = new Dimension[2];
        dimensions[0] = new Dimension(minX, minY);
        dimensions[1] = new Dimension(maxX, maxY);
        return dimensions;
    }

    /**
     * gets the animation frames from a single animation in the spritesheet
     * @param animationNumber the position of the animation in the spritesheet starting from 0
     * @return an arrayList containing the Frames of the animation
     */
    public ArrayList<BufferedImage> getAnimationFrames(int animationNumber){
        return ALL_SPRITES.get(animationNumber);
    }

    public boolean isSpritesByColumn() {
        return spritesByColumn;
    }

    public int getSPRITE_COL_COUNT() {
        return SPRITE_COL_COUNT;
    }

    public int getSPRITE_ROW_COUNT() {
        return SPRITE_ROW_COUNT;
    }

    public int getSPRITE_HEIGHT(){ return this.SPRITE_HEIGHT;}
    public int getSPRITE_WIDTH(){ return this.SPRITE_WIDTH;}
}
