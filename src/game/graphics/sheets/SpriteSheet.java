package game.graphics.sheets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    protected final BufferedImage SPRITESHEET;
    protected BufferedImage[][] ALL_SPRITES; // Row then column
    protected int SPRITE_WIDTH;
    protected int SPRITE_HEIGHT;
    protected int SPRITE_COL_COUNT;
    protected int SPRITE_ROW_COUNT;

    /**
     * A spritesheet containing sprites of same width and height
     * @param file the path of the file containing the spritesheet image
     * @param spriteWidth the width of each sprite
     * @param spriteHeight the height of each sprite
     */
    public SpriteSheet(String file, int spriteWidth, int spriteHeight){
        this.SPRITE_WIDTH = spriteWidth;
        this.SPRITE_HEIGHT = spriteHeight;
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
        this.ALL_SPRITES = new BufferedImage[SPRITE_ROW_COUNT][SPRITE_COL_COUNT];
        for (int row = 0; row < SPRITE_ROW_COUNT; row++) {
            for (int column = 0; column < SPRITE_COL_COUNT; column++) {
                ALL_SPRITES[row][column] = SPRITESHEET.getSubimage(column * SPRITE_WIDTH, row * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT);
            }
        }
    }

    /**
     * gets a specific sprite from the spritesheet
     * @param row the row of the sprite
     * @param column the columns of the sprite
     * @return the image of the sprite
     */
    public BufferedImage getSprite(int row, int column){
        return ALL_SPRITES[row][column];
    }



    public int getSPRITE_COL_COUNT() {
        return SPRITE_COL_COUNT;
    }

    public int getSPRITE_ROW_COUNT() {
        return SPRITE_ROW_COUNT;
    }

    public int getSPRITE_WIDTH() {
        return SPRITE_WIDTH;
    }

    public int getSPRITE_HEIGHT() {
        return SPRITE_HEIGHT;
    }

    public void setSPRITE_WIDTH(int SPRITE_WIDTH) {
        this.SPRITE_WIDTH = SPRITE_WIDTH;
    }

    public void setSPRITE_HEIGHT(int SPRITE_HEIGHT) {
        this.SPRITE_HEIGHT = SPRITE_HEIGHT;
    }
}
