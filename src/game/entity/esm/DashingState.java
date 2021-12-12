package game.entity.esm;

import game.entity.Entity;
import game.util.Vector2D;


public class DashingState extends OnGroundState{
    public static EntityState localState = EntityState.STATE_DASHING;

    public static void update(Entity entity){
        if(entity.getState() == localState){
            // Dashing mechanic
            Vector2D oldMovement = entity.getLastMovement();
            entity.move(oldMovement.multiplyScalar(2));
            entity.setLastMovement(oldMovement);
            entity.updateAnimation();
        } else {
            // Resetting the state if the entity isn't dashing
            OnGroundState.update(entity);
        }
    }
}
