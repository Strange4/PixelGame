package game.layers;

import game.keyboard.KeyHandler;

import java.awt.*;
import java.util.ArrayList;

public class GameLayerManager {
    private final ArrayList<GameLayer> states;

    public GameLayerManager() {
        states = new ArrayList<>();
        addState(new GameStartLayer(this));
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

    public PlayLayer getPlayLayer(){
        for(GameLayer state :states){
            if(state instanceof PlayLayer playLayer){
                return playLayer;
            }
        }
        return null;
    }

    public void input(KeyHandler kHandler){
        for (GameLayer state : states) {
            state.input(kHandler);
        }
    }

    public void render(Graphics2D graphics2D, int scale){
        for (GameLayer state : states) {
            state.render(graphics2D, scale);
        }
    }
}
