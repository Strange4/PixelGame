package game.layers;

import game.keyboard.KeyHandler;

import java.awt.*;

public abstract class GameLayer {
    protected GameLayerManager glm;

    /**
     * A game layer that takes input, is rendered and updated
     * @param glm the Game layer manager of this game layer
     */
    public GameLayer(GameLayerManager glm){
        this.glm = glm;
    }


    /**
     * updates the layer
     */
    public abstract void update();

    /**
     * takes input from the mouse and key handler
     * @param kHandler
     */
    public abstract void input(KeyHandler kHandler);
    public abstract void render(Graphics2D graphics2D, int scale);

}
