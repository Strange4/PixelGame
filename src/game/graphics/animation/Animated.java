package game.graphics.animation;

import game.graphics.sheets.AnimationSpriteSheet;

import java.awt.image.BufferedImage;

public abstract class Animated {
    protected Animation[] animations;
    protected final AnimationSpriteSheet spriteSheet;
    protected int currentAnimation = 0;

    /**
     * An animated abstract class that sets all the animations for an entity
     * @param spriteSheet the animation spritesheet that is used to create the animation
     */
    public Animated(AnimationSpriteSheet spriteSheet){
        this.spriteSheet = spriteSheet;
        if(spriteSheet.isSpritesByColumn()){
            this.animations = new Animation[spriteSheet.getSPRITE_COL_COUNT()];
        } else{
            this.animations = new Animation[spriteSheet.getSPRITE_ROW_COUNT()];
        }
        loadAnimations();
    }

    /**
     * sets the delay between frames for a specific animation
     * @param delayBetweenFrames the new delay
     * @param animationNumber the animation that is updated
     */
    public void setDelayBetweenFrames(int delayBetweenFrames, int animationNumber){
        animations[animationNumber].setDelayBetweenFrames(delayBetweenFrames);
    }

    private void loadAnimations(){
        for(int i=0;i<animations.length;i++){
            animations[i] = new Animation(this.spriteSheet.getAnimationFrames(i), 10);
        }
    }

    /**
     * updates the current animation playing
     */
    public void updateAnimation(){
        animations[currentAnimation].update();
    }

    /**
     * changes the current animation playing
     * @param animationNumber the new animation that is played
     */
    public void changeAnimation(int animationNumber){
        for(int i=0;i<animations.length;i++){
            if(i != animationNumber){
                animations[i].resetAnimation();
            }
        }
        this.currentAnimation = animationNumber;
    }

    /**
     * gets the current frame of the current animation
     * @return a bufferedimage of the animation frame
     */
    public BufferedImage getCurrentFrame(){
        return animations[currentAnimation].getCurrentFrame();
    }

    /**
     * resets all animations to their starting frames
     */
    public void resetAnimations(){
        for(Animation a : animations){
            a.resetAnimation();
        }
    }
}

