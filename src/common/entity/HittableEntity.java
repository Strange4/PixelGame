package common.entity;

import common.combat.Hitbox;
import common.combat.HpManager;
import game.util.Vector2D;

import java.util.ArrayList;

public class HittableEntity extends Entity {
    private final HpManager hpManager;
    public final Hitbox hitbox;

    public HittableEntity(int id, Vector2D size, Vector2D position, HpManager hpManager) {
        super(id, size, position);

        // Creates a hitbox associated with the owner and with the same size as the owner.
        this.hitbox = new Hitbox(this.getBonds(), 1, this);
        this.hpManager = hpManager;
    }

    @Override
    public void update() {

    }

    public void onCollision(HittableEntity[] entity) {
    }
}
