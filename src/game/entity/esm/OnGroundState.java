package game.entity.esm;

import game.entity.Entity;

public abstract class OnGroundState {
    // Default state for all states
    public static EntityState localState = EntityState.STATE_STANDING;
    public static void update(Entity entity){
        if(entity.getState() == localState){
            entity.resetAnimations();
        } else {
            entity.setState(localState);
        }
    }
}
