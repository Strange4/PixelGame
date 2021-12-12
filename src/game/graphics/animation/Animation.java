package game.graphics.animation;

import java.awt.image.BufferedImage;

public class Animation {
    private final BufferedImage[] animationFrames;
    private int delay;
    private final int totalFrameCount;
    private int timesUpdated = 0;
    private int currentFrame = 0;

    /**
     * An animation composed of animation frames
     * @param animationFrames the frames that the animation will go through
     * @param delayBetweenFrames the delay that the animation will wait before updating the animation
     */
    public Animation(BufferedImage[] animationFrames, int delayBetweenFrames){
        this.animationFrames = animationFrames;
        this.delay = delayBetweenFrames;
        totalFrameCount = animationFrames.length;
    }

    /**
     * sets the new delay between the animation frames
     * @param delay the new delay
     */
    public void setDelayBetweenFrames(int delay){
        this.delay = delay;
    }

    /**
     * updates the animation
     */
    public void update(){
        timesUpdated++;
        if(timesUpdated == delay){
            timesUpdated = 0;
            currentFrame++;
            if(currentFrame == totalFrameCount) currentFrame = 0;
        }
    }

    /**
     * gets the current Frame that animation is in
     * @return
     */
    public BufferedImage getCurrentFrame(){
        return this.animationFrames[currentFrame];
    }

    /**
     * resets the animation to it's starting frame
     */
    public void resetAnimation(){
        this.currentFrame = 0;
    }
}
