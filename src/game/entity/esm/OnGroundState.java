package game.entity.esm;

import game.entity.Entity;
import game.keyboard.Key;

public abstract class OnGroundState {
    public void handleInput(Entity entity, Key key){}
    public void update(Entity entity){}
}
