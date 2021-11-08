package game.entity;

import game.graphics.Animated;
import game.graphics.SpriteSheet;

public abstract class Entity extends Animated {

    public Entity(SpriteSheet spriteSheet) {
        super(spriteSheet);
    }
}
