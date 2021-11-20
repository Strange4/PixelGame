package game.entity;

import game.graphics.Animated;
import game.graphics.AnimationSpriteSheet;
import game.graphics.SpriteSheet;

public abstract class Entity extends Animated {

    public Entity(AnimationSpriteSheet spriteSheet) {
        super(spriteSheet);
    }
}
