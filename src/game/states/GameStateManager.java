package game.states;

import game.util.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class GameStateManager {
    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    private final ArrayList<GameState> states;

    public GameStateManager() {
        states = new ArrayList<>();
        addState(new PlayState(this));
    }

    public void addState(GameState state){
        if(states.contains(state)){
            throw new IllegalArgumentException("this state has already been added");
        }
        states.add(state);
    }
    public void removeState(GameState state){
        if(!states.contains(state)){
            throw new IllegalArgumentException("this state has not been added");
        }
        states.remove(state);
    }

    public void update(){
        for (GameState state : states) {
            state.update();
        }
    }

    public void input(MouseHandler mouse, KeyHandler key){
        for (GameState state : states) {
            state.input(mouse, key);
        }
    }

    public void render(Graphics2D graphics2D, int scale){
        for (GameState state : states) {
            state.render(graphics2D, scale);
        }
    }
}
