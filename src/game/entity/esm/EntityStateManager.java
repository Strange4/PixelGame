package game.entity.esm;

import game.entity.types.Enemy;
import game.entity.Entity;
import game.entity.types.Player;
import game.keyboard.Key;
import game.keyboard.KeyHandler;

import java.awt.event.KeyEvent;

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
        KeyHandler kh = KeyHandler.getInstance();
        Key key = kh.getLastKey();
        int last;
        if(key != null){
            last = key.getKeyCode();
        } else {
            return;
        }
        switch (last){
            case KeyEvent.VK_A:
            case KeyEvent.VK_W:
            case KeyEvent.VK_D:
            case KeyEvent.VK_S:
                // When we get a movement key pressed we want to get
                this.state = EntityState.STATE_WALKING;
                WalkingState.update(player, kh.getDirectionalVector());
                break;
            default:
                this.state = EntityState.STATE_STANDING;
                OnGroundState.update(player);
                break;
        }
    }
    private void handleEnemy(Enemy enemy){
//        enemy.updateAnimation();
    }
    private void handlePassive(Passive passive){
//        passive.updateAnimation();
    }
}
