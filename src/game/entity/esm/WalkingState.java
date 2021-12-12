package game.entity.esm;

import game.entity.Entity;
import game.util.Vector2D;

public class WalkingState extends OnGroundState{
    public static EntityState localState = EntityState.STATE_WALKING;

    // Walking state
    public static void update(Entity entity, Vector2D movement){
        if(entity.getState() == localState){
            entity.move(movement);
            entity.updateAnimation();
        } else {
            OnGroundState.update(entity);
        }
    }
}
