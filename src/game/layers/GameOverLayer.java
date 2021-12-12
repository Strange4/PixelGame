package game.layers;

import game.GameCanvas;
import game.keyboard.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverLayer extends GameLayer{
    private final int score = calculateScore();

    public GameOverLayer(GameLayerManager glm) {
        super(glm);
    }

    private int calculateScore(){
        long currentTime = System.nanoTime();
        long timeTaken = currentTime - this.glm.getPlayLayer().GAME_START;
        int score = (int) (timeTaken/1000000);
        return score;
    }

    private String scoreToString(){
        String string = "";
        int milliseconds = score % 1000;
        int seconds = (score - milliseconds) / 1000;
        string += seconds + "." + milliseconds + " seconds";
        return string;
    }

    @Override
    void update() {

    }

    @Override
    void input(MouseHandler mouse, KeyHandler kHandler) {
        if(kHandler.getLastKey() != null && kHandler.getLastKey().getKeyCode() == KeyEvent.VK_ENTER){
            this.glm.addState(new PlayLayer(this.glm));
            this.glm.removeState(this);
        }
    }

    @Override
    void render(Graphics2D graphics2D, int scale) {
        graphics2D.fillRect(0, 0,800,450);
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 100));
        graphics2D.setColor(Color.orange);
        graphics2D.drawString("GAME OVER", 100,100);
        graphics2D.setColor(Color.white);
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 25));
        graphics2D.drawString("Time: " + scoreToString(), 300,150);
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 50));
        graphics2D.drawString("Congratulations", 220,200);
        graphics2D.drawString("Press Enter to restart", 160,250);

    }
}
