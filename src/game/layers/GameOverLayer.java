package game.layers;

import game.GameCanvas;
import game.keyboard.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;

public class GameOverLayer extends GameLayer{

    public GameOverLayer(GameLayerManager glm) {
        super(glm);
    }

    private int calculateScore(){
        long currentTime = System.nanoTime();
        long timeTaken = currentTime - GameCanvas.GAME_START;
        int score = (int) (timeTaken/1000);
        return score;
    }

    @Override
    void update() {

    }

    @Override
    void input(MouseHandler mouse, KeyHandler kHandler) {

    }

    @Override
    void render(Graphics2D graphics2D, int scale) {
        graphics2D.fillRect(0, 0,800,450);
        graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 100));
        graphics2D.setColor(Color.orange);
        graphics2D.drawString("GAME OVER", 100,100);
    }
}
