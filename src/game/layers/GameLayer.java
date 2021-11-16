package game.layers;

import game.keyboard.MovementHandler;
import game.util.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;

public abstract class GameLayer {
    private GameLayerManager glm;
    public GameLayer(GameLayerManager glm){
        this.glm = glm;
    }

    abstract void update();
    abstract void input(MouseHandler mouse, MovementHandler mHandler);
    abstract void render(Graphics2D graphics2D, int scale);

}
