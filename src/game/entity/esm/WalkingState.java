package game.entity.esm;

import game.entity.Entity;
import game.util.Vector2D;

public class WalkingState extends OnGroundState{
    public static void update(Entity e, Vector2D movement){
        e.move(movement);
        e.updateAnimation();
    }
}
