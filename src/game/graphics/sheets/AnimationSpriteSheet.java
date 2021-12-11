package game.graphics.sheets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationSpriteSheet extends SpriteSheet {

    private ArrayList<ArrayList<BufferedImage>> animationFrames;
    private final boolean spaceAdjusted;
    private final boolean spritesByColumn;
    public AnimationSpriteSheet(String file, int spriteWidth, int spriteHeight, boolean spritesByColumn, boolean adjustForSpace) {
        super(file, spriteWidth, spriteHeight);
        this.spaceAdjusted = adjustForSpace;
        this.spritesByColumn = spritesByColumn;
        loadAnimationFrames();
    }

    private void loadAnimationFrames(){
        this.animationFrames = new ArrayList<>();
        if(this.spritesByColumn){
            for (int column = 0; column < this.SPRITE_COL_COUNT; column++) {
                animationFrames.add(new ArrayList<>());
                for (int row = 0; row < this.SPRITE_ROW_COUNT; row++) {
                    BufferedImage frame = getSprite(row, column);
                    if(!imageIsEmpty(frame)){
                        if(this.spaceAdjusted){
                            animationFrames.get(column).add(getAdjustedFrame(getSprite(row, column)));
                        } else {
                            animationFrames.get(column).add(getSprite(row, column));
                        }
                    }
                }
            }
        } else {
            for (int row = 0; row < this.SPRITE_ROW_COUNT; row++) {
                animationFrames.add(new ArrayList<>());
                for (int column = 0; column < this.SPRITE_COL_COUNT; column++) {
                    BufferedImage frame = getSprite(row, column);
                    if(!imageIsEmpty(frame)){
                        if(this.spaceAdjusted){
                            animationFrames.get(row).add(getAdjustedFrame(getSprite(row, column)));
                        } else {
                            animationFrames.get(row).add(getSprite(row, column));
                        }
                    }
                }
            }
        }
    }

    private BufferedImage getAdjustedFrame(BufferedImage frame){
        Dimension[] bounds = calculateImageBounds(frame);
        int minX = bounds[0].width;
        int minY = bounds[0].height;
        int maxX = bounds[1].width;
        int maxY = bounds[1].height;

        if(this.getSPRITE_HEIGHT()>maxY-minY){
            this.setSPRITE_HEIGHT(maxY-minY);
        }
        if(this.getSPRITE_WIDTH()>maxX-minX){
            this.setSPRITE_WIDTH(maxX-minX);
        }
        return frame.getSubimage(minX, minY, maxX-minX, maxY-minY);
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
    public BufferedImage[] getAnimationFrames(int animationNumber){
        BufferedImage[] frames = new BufferedImage[this.animationFrames.get(animationNumber).size()];
        return this.animationFrames.get(animationNumber).toArray(frames);
    }

    public boolean isSpritesByColumn() {
        return spritesByColumn;
    }
}
