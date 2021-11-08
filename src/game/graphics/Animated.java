package game.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Animated {
    protected Animation[] animations;
    protected final SpriteSheet spriteSheet;
    protected int currentAnimation = 0;

    public Animated(SpriteSheet spriteSheet){
        this.spriteSheet = spriteSheet;
        if(spriteSheet.isSpritesByColumn()){
            this.animations = new Animation[spriteSheet.getSPRITE_COL_COUNT()];
        } else{
            this.animations = new Animation[spriteSheet.getSPRITE_ROW_COUNT()];
        }
        loadAnimations();
    }

    private void loadAnimations(){

        if(this.spriteSheet.isSpritesByColumn()){
            for(int i=0;i<animations.length;i++){
                BufferedImage[] frames = new BufferedImage[spriteSheet.getColumn(currentAnimation).size()];
                frames = spriteSheet.getColumn(currentAnimation).toArray(frames);
                animations[i] = new Animation(frames, 10);
            }
        }else{
            for(int i=0;i<animations.length;i++){
                BufferedImage[] frames = new BufferedImage[spriteSheet.getRow(currentAnimation).size()];
                frames = spriteSheet.getRow(currentAnimation).toArray(frames);
                animations[i] = new Animation(frames, 100);
            }
        }
    }
    public void updateAnimation(){
        animations[currentAnimation].update();
    }
    public void changeAnimation(int row){
        this.currentAnimation = row;
    }

    public BufferedImage getCurrentFrame(){
        return animations[currentAnimation].getCurrentFrame();
    }
}
