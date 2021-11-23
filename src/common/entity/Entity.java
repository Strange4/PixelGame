package common.entity;

import game.util.Vector2D;

/**
 * Represents a basic entity that can be spawned on the game world.
 */
public abstract class Entity {
    public final int id;

    private Rect bonds;

    private boolean shouldBeRemoved;

    public Entity(int id, Vector2D size, Vector2D position) {
        this.id = id;
        this.bonds = new Rect(position, size);
    }

    public Vector2D getPosition() {
        return this.bonds.getPosition();
    }

    public void setPosition(Vector2D position) {
        this.bonds.setX(position.getX());
        this.bonds.setY(position.getY());
    }

    public Rect getBonds() {
        return  this.bonds;
    }

   public boolean shouldBeRemoved() {
       return this.shouldBeRemoved;
   }

    public abstract void update();

}
