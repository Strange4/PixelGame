package game.states;

import game.util.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;

public abstract class GameState {
    private GameStateManager gsm;
    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    abstract void update();
    abstract void input(MouseHandler mouse, KeyHandler key);
    abstract void render(Graphics2D graphics2D, int scale);

}
