package game.layers;

import game.util.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;

public class GameLayerManager {
    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    private final ArrayList<GameLayer> states;

    public GameLayerManager() {
        states = new ArrayList<>();
        addState(new PlayLayer(this));
    }

    public void addState(GameLayer state){
        if(states.contains(state)){
            throw new IllegalArgumentException("this state has already been added");
        }
        states.add(state);
    }
    public void removeState(GameLayer state){
        if(!states.contains(state)){
            throw new IllegalArgumentException("this state has not been added");
        }
        states.remove(state);
    }

    public void update(){
        for (GameLayer state : states) {
            state.update();
        }
    }

    public void input(MouseHandler mouse, KeyHandler key){
        for (GameLayer state : states) {
            state.input(mouse, key);
        }
    }

    public void render(Graphics2D graphics2D, int scale){
        for (GameLayer state : states) {
            state.render(graphics2D, scale);
        }
    }
}
