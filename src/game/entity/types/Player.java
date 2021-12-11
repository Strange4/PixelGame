package game.entity.types;

import game.entity.Entity;
import game.graphics.sheets.AnimationSpriteSheet;
import game.util.Vector2D;

public class Player extends Entity {
    private int points;

    public Player(AnimationSpriteSheet spriteSheet, Vector2D position) {
        super(spriteSheet, position);
    }

    public void addPoint(){
        this.points++;
    }

    public int getPoints() {
        return points;
    }

}
