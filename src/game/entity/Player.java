package game.entity;

import game.graphics.sheets.AnimationSpriteSheet;
import game.util.Vector2D;

public class Player extends Entity {

    public Player(AnimationSpriteSheet spriteSheet, Vector2D position) {
        super(spriteSheet, position);
    }
}
