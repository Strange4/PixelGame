package game.entity.esm;

import game.entity.Entity;
import game.util.Vector2D;


public class DashingState extends OnGroundState{
    public static EntityState localState = EntityState.STATE_DASHING;
    public static long lastUsed = 2000;

    public static void update(Entity entity){
        if(entity.getState() == localState){
            if ((System.currentTimeMillis() - lastUsed) >= 0) {
                lastUsed = System.currentTimeMillis();
                Vector2D oldMovement = entity.getLastMovement();
//                System.out.println("a: " + oldMovement);
                entity.move(oldMovement.multiplyScalar(2));
                entity.setLastMovement(oldMovement);
//                System.out.println("b: " + entity.getLastMovement() + "\n");
                entity.updateAnimation();
            }
        } else {
            OnGroundState.update(entity);
        }
    }
}
