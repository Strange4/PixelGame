package game.entity;

import game.graphics.animation.Animated;
import game.graphics.sheets.AnimationSpriteSheet;

public abstract class Entity extends Animated {

    public Entity(AnimationSpriteSheet spriteSheet) {
        super(spriteSheet);
    }
}
