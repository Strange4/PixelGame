package game;

import game.states.GameStateManager;
import game.util.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas implements Runnable{
    public final int width;
    public final int height;

    private Thread thread;
    private boolean running;
    private MouseHandler mouse;
    private KeyHandler key;
    private GameStateManager gsm;
    private BufferStrategy bs;

    public GameCanvas(int width, int height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if(thread == null){
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    private void init(){
        running = true;
        createBufferStrategy(3);
        mouse = new MouseHandler();
        key = new KeyHandler();
        gsm = new GameStateManager();
        bs = getBufferStrategy();
    }

    /**
     * sets the main game loop and controls the frame rate
     */
    @Override
    public void run() {
        init();
        final double GAME_HERTZ = 60.0;
        final double BILLION = 1e9;
        final double MIN_TIME_BEFORE_UPDATE = BILLION / GAME_HERTZ; // in nanoseconds
        final int MIN_UPDATES_BEFORE_RENDER = 5;

        double lastUpdateTime = System.nanoTime();

        final double TARGET_FPS = 60;
        final double TOTAL_TIME_BEFORE_RENDER = BILLION / TARGET_FPS; // in nanoseconds
        double lastRenderTime;

        int frameCount = 0;
        int lastSecond = (int) (lastUpdateTime / BILLION);
        int oldFrameCount = 0;
        while(running){
            double now = System.nanoTime();
            int updateCount = 0;
            // updates only when the allowed time before update has passed and the min number of updates are done
            while((now - lastUpdateTime) > MIN_TIME_BEFORE_UPDATE && (updateCount < MIN_UPDATES_BEFORE_RENDER)){
                update();
                input(mouse, key);
                lastUpdateTime += MIN_TIME_BEFORE_UPDATE;
                updateCount++;
            }


            if(now - lastUpdateTime > MIN_TIME_BEFORE_UPDATE){
                lastUpdateTime = now - MIN_TIME_BEFORE_UPDATE;
            }
            input(mouse, key);
            render();
            draw();

            lastRenderTime = now;
            frameCount++;

            // Counting the fps each second
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if(thisSecond > lastSecond){
                if(frameCount != oldFrameCount){
                    System.out.println("Frame count: " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecond = thisSecond;
            }
            // sets the max frame count and lets the computer breath before looping again
//            while (((now - lastRenderTime) < TOTAL_TIME_BEFORE_RENDER) && ((now - lastUpdateTime) < MIN_TIME_BEFORE_UPDATE) ){
//                Thread.yield();
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                now = System.nanoTime();
//            }
        }
    }

    /**
     * stops the thread
     */
    public void stop(){
        try{
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void update(){
        gsm.update();
    }

    /**
     * Gives the input data to the game state manager
     * @param mouse the mouse handler of the canvas
     * @param key the key handler of the canvas
     */
    private void input(MouseHandler mouse, KeyHandler key){
        gsm.input(mouse, key);
    }

    /**
     * renders the graphics of each state
     */
    private void render(){
        Graphics2D graphics = (Graphics2D) bs.getDrawGraphics();
        graphics.fillRect(0,0, width, height);
        gsm.render(graphics, 5);
        graphics.dispose();
        bs.show();
    }

    private void draw(){
    }
}