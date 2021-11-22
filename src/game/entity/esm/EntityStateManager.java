package game.entity.esm;

import game.entity.Enemy;
import game.entity.Entity;
import game.entity.Player;
import game.keyboard.KeyHandler;

public class EntityStateManager {
    private EntityState state;

    public EntityStateManager(){
        this.state = EntityState.STATE_STANDING;
    }

    public void update(Entity e){
        if(e instanceof Player){
            handlePlayer((Player) e);
        } else if(e instanceof Enemy){
            handleEnemy((Enemy) e);
        } else if(e instanceof Passive){
            handlePassive((Passive) e);
        } else {
            throw new IllegalArgumentException("Entity not supported");
        }
    }

    private void handlePlayer(Player player){
        player.updateAnimation();
    }
    private void handleEnemy(Enemy enemy){
        enemy.updateAnimation();
    }
    private void handlePassive(Passive passive){
        passive.updateAnimation();
    }
}
