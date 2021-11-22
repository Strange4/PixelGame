package game.entity.types;

import game.entity.Entity;
import game.graphics.SpriteSheet;
import game.util.Vector2D;

public class Player extends Entity {

    public Player(SpriteSheet spriteSheet, Vector2D position, int maxFrames) {
        super(spriteSheet, position, maxFrames);
    }
}
