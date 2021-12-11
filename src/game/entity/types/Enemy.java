package game.entity.types;

import game.entity.Entity;
import game.graphics.sheets.AnimationSpriteSheet;
import game.util.Vector2D;

public class Enemy extends Entity {
    public Enemy(AnimationSpriteSheet spriteSheet, Vector2D position) {
        super(spriteSheet, position);
    }
}
