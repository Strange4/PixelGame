package game.graphics.animation;

import game.graphics.sheets.AnimationSpriteSheet;

import java.awt.image.BufferedImage;

public abstract class Animated {
    protected Animation[] animations;
    protected final AnimationSpriteSheet spriteSheet;
    protected int currentAnimation = 0;

    public Animated(AnimationSpriteSheet spriteSheet){
        this.spriteSheet = spriteSheet;
        if(spriteSheet.isSpritesByColumn()){
            this.animations = new Animation[spriteSheet.getSPRITE_COL_COUNT()];
        } else{
            this.animations = new Animation[spriteSheet.getSPRITE_ROW_COUNT()];
        }
        loadAnimations();
    }

    public void changeAnimationDelay(int delayBetweenFrames, int animationNumber){
        animations[animationNumber].setDelayBetweenFrames(delayBetweenFrames);
    }

    private void loadAnimations(){
        for(int i=0;i<animations.length;i++){
            animations[i] = new Animation(this.spriteSheet.getAnimationFrames(i), 10);
        }
    }

    public void updateAnimation(){
        animations[currentAnimation].update();
    }
    public void changeAnimation(int animationNumber){
        for(int i=0;i<animations.length;i++){
            if(i != animationNumber){
                animations[i].resetAnimation();
            }
        }
        this.currentAnimation = animationNumber;
    }

    public BufferedImage getCurrentFrame(){
        return animations[currentAnimation].getCurrentFrame();
    }
}