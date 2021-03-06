package game;

import game.keyboard.KeyHandler;
import game.keyboard.Keyboard;
import game.layers.GameLayerManager;

import java.awt.event.KeyEvent;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas implements Runnable {
    public final int width;
    public final int height;

    private Thread thread;
    private boolean running;
    private final KeyHandler keyHandler;
    private GameLayerManager gsm;
    private BufferStrategy bs;
    private double lastUpdateTime = System.nanoTime();

    public GameCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setVisible(true);
        // Keyboard Events Handler
        Keyboard keyboard = new Keyboard(KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, KeyEvent.VK_Q, KeyEvent.VK_ENTER);
        this.keyHandler = KeyHandler.getInstance();
        this.keyHandler.init(keyboard);
        addKeyListener(this.keyHandler);
    }



    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    private void init() {
        running = true;
        createBufferStrategy(3);
        gsm = new GameLayerManager();
        bs = getBufferStrategy();
    }

    /**
     * sets the main game loop and controls the frame rate
     */
    @Override
    public void run() {
        init();
        final double BILLION = 1e9;
        int frameCount = 0;
        int lastSecond = (int) (lastUpdateTime / BILLION);
        int oldFrameCount = 0;
        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;
            // updates only when the allowed time before update has passed and the min
            // number of updates are done
            double GAME_HERTZ = 60.0;
            double MIN_TIME_BEFORE_UPDATE = BILLION / GAME_HERTZ;
            int MIN_UPDATES_BEFORE_RENDER = 5;
            while ((now - lastUpdateTime) > MIN_TIME_BEFORE_UPDATE && (updateCount < MIN_UPDATES_BEFORE_RENDER)) {
                input();
                update();
                lastUpdateTime += MIN_TIME_BEFORE_UPDATE;
                updateCount++;
            }
            if (now - lastUpdateTime > MIN_TIME_BEFORE_UPDATE) {
                lastUpdateTime = now - MIN_TIME_BEFORE_UPDATE;
            }
            input();
            render();
            frameCount++;

            // Counting the fps each second
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecond) {
                if (frameCount != oldFrameCount) {
                    System.out.println("Frame count: " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecond = thisSecond;
            }
        }
    }

    /**
     * stops the thread
     */
    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        gsm.update();
    }


    private void input() {
        gsm.input(keyHandler);
    }

    /**
     * renders the graphics of each state
     */
    private void render() {
        Graphics2D graphics = (Graphics2D) bs.getDrawGraphics();
        gsm.render(graphics, 2);
        graphics.dispose();
        bs.show();
    }
}