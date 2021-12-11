package game.entity.types;

import game.entity.Entity;
import game.graphics.sheets.AnimationSpriteSheet;
import game.util.Vector2D;

public class Passive extends Entity {
    public Passive(AnimationSpriteSheet spriteSheet, Vector2D position, int maxFrames) {
        super(spriteSheet, position);
    }
}
