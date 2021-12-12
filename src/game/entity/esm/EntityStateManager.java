package game.entity.esm;

import game.entity.Entity;
import game.entity.types.Enemy;
import game.entity.types.Passive;
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
            // Do nothing
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
            this.state = EntityState.STATE_STANDING;
            player.setState(this.state);
            OnGroundState.update(player);
            return;
        }
        switch (last){
            case KeyEvent.VK_A:
            case KeyEvent.VK_W:
            case KeyEvent.VK_D:
            case KeyEvent.VK_S:
                this.state = EntityState.STATE_WALKING;
                player.setState(this.state);
                WalkingState.update(player, kh.getDirectionalVector());
                break;
            case KeyEvent.VK_SPACE:
                    this.state = EntityState.STATE_DASHING;
                    player.setState(this.state);
                    DashingState.update(player);
                break;
        }

    }
}
