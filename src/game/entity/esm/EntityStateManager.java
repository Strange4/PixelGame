package game.entity.esm;

import game.entity.Entity;

public class EntityStateManager {
    private EntityState state;

    public EntityStateManager(Entity e){
        this.state = EntityState.STATE_STANDING;
    }

    public void update(Entity e){

    }
}
