package game.graphics.animation;

import org.junit.Test;

import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

public class AnimationTest {
    @Test
    public void instance(){
        new Animation(new BufferedImage[2],10);
    }

    @Test
    public void currentFrame(){
        BufferedImage[] images = new BufferedImage[1];
        images[0] = new BufferedImage(10,30, BufferedImage.TYPE_INT_ARGB);
        Animation a = new Animation(images, 1);
        BufferedImage currentImage = a.getCurrentFrame();
        assertEquals(images[0].getWidth(), currentImage.getWidth());
        assertEquals(images[0].getHeight(), currentImage.getHeight());

        int width  = currentImage.getWidth();
        int height = currentImage.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                assertEquals(currentImage.getRGB(x,y), images[0].getRGB(x,y));
            }
        }
    }

    @Test
    public void update(){
        BufferedImage[] images = new BufferedImage[2];
        images[0] = new BufferedImage(10,30, BufferedImage.TYPE_INT_ARGB);
        images[1] = new BufferedImage(15, 45, BufferedImage.TYPE_INT_ARGB);
        Animation a = new Animation(images, 1);
        a.update();
        BufferedImage currentImage = a.getCurrentFrame();
        assertEquals(images[1].getWidth(), currentImage.getWidth());
        assertEquals(images[1].getHeight(), currentImage.getHeight());

        int width  = currentImage.getWidth();
        int height = currentImage.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                assertEquals(currentImage.getRGB(x,y), images[1].getRGB(x,y));
            }
        }
    }

    @Test
    public void delay(){
        BufferedImage[] images = new BufferedImage[2];
        images[0] = new BufferedImage(10,30, BufferedImage.TYPE_INT_ARGB);
        images[1] = new BufferedImage(15, 45, BufferedImage.TYPE_INT_ARGB);
        Animation a = new Animation(images, 1);
        a.setDelayBetweenFrames(2);
        // need to update twice before the image changes
        a.update();
        a.update();
        BufferedImage currentImage = a.getCurrentFrame();
        assertEquals(images[1].getWidth(), currentImage.getWidth());
        assertEquals(images[1].getHeight(), currentImage.getHeight());

        int width  = currentImage.getWidth();
        int height = currentImage.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                assertEquals(currentImage.getRGB(x,y), images[1].getRGB(x,y));
            }
        }
    }
}
