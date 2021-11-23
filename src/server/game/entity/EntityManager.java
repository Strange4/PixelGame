package server.game.entity;

import common.entity.Entity;
import common.entity.HittableEntity;
import common.entity.Rect;
import server.game.collision.QuadTree;

import java.util.ArrayList;

/**
 * Holds a group of entities and updates them.
 */
public class EntityManager {
    ArrayList<Entity> spawnedEntities = new ArrayList<>();
    QuadTree qt = new QuadTree(7, new Rect(0, 0, 800, 600));

    public void addEntity(Entity entity) {
        spawnedEntities.add(entity);
    }

    private void update() {
        spawnedEntities.forEach(this::checkDead);
        spawnedEntities.forEach(this::updateEntity);
    }

    private void updateEntity(Entity entity) {
        // Send game tick info here.
        entity.update();
    }

    private void checkCollision() {
        spawnedEntities.stream()
                .filter(entity -> entity instanceof HittableEntity)
                .forEach(entity -> {
                    HittableEntity he = (HittableEntity) entity;
                   he.onCollision(qt.retrieve(he.hitbox));
                });
    }

    private void checkDead(Entity entity) {
        if (entity.shouldBeRemoved()) {
            this.spawnedEntities.remove(entity);
        }
    }

}
