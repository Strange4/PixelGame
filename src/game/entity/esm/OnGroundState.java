package game.entity.esm;

import game.entity.Entity;
import game.keyboard.Key;

public abstract class OnGroundState {
    public static EntityState localState = EntityState.STATE_STANDING;
    public static void update(Entity entity){
        if(entity.getState() == localState){
            entity.resetAnimations();
        } else {
            entity.setState(localState);
        }
    }
}
