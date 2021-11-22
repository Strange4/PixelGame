package game.entity.esm;

import game.entity.Entity;
import game.keyboard.Key;

public abstract class OnGroundState {
    public EntityState state = EntityState.STATE_STANDING;

    public void handleInput(Entity entity, Key key){}
    public static void update(Entity entity){
        EntityState state = entity.getState();
        if(state == EntityState.STATE_STANDING){
            entity.resetAnimations();
        }
    }
}
