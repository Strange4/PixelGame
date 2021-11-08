package game.graphics;

import java.awt.image.BufferedImage;

public class Animation {
    private final BufferedImage[] animationFrames;
    private final int delay;
    private int timesPlayed = 0;
    private final int totalFrameCount;
    private int timesUpdated = 0;
    private int currentFrame = 0;

    public Animation(BufferedImage[] animationFrames, int delayBetweenFrames){
        this.animationFrames = animationFrames;
        this.delay = delayBetweenFrames;
        totalFrameCount = animationFrames.length;
    }

    public void update(){
        timesUpdated++;
        if(timesUpdated == delay){
            timesUpdated = 0;
            currentFrame++;
            if(currentFrame == totalFrameCount) currentFrame = 0; timesPlayed++;
        }
    }

    public BufferedImage getCurrentFrame(){
        return this.animationFrames[currentFrame];
    }
}
