package game.entity.esm;

import game.entity.Entity;

public class WalkingState extends OnGroundState{

    @Override
    public void update(Entity entity) {
        if(entity.getState() == EntityState.STATE_WALKING){

        } else {
            super.update(entity);
        }
    }
}
