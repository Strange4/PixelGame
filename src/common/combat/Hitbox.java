package common.combat;

import common.entity.HittableEntity;
import common.entity.Rect;
import game.util.Vector2D;

import java.awt.*;

public class Hitbox {
    public final HittableEntity owner;
    private Rect bonds;
    private int layer;

    public Hitbox(Rect dimensions, int layer, HittableEntity entity) {
        this.owner = entity;
        this.layer = layer;

        this.bonds = dimensions;
    }

    public Hitbox(Vector2D position, Vector2D dimension, int layer, HittableEntity entity) {
        this.owner = entity;
        this.layer = layer;

        this.bonds = new Rect(position.getX(), position.getY(), dimension.getX(), dimension.getY());
        this.setLeft();
        this.setRight();
        this.setTop();
        this.setBottom();
    }

    public boolean isCollision(Hitbox secondHitBox) {
        if (this.bonds.getX() >= secondHitBox.bonds.getX() + secondHitBox.bonds.getWidth()) return false;
        if (this.bonds.getY() >= secondHitBox.bonds.getY() + secondHitBox.bonds.getHeight()) return false;
        if (secondHitBox.bonds.getX() >= this.bonds.getX() + this.bonds.getWidth()) return false;
        if (secondHitBox.bonds.getY() >= this.bonds.getY() + this.bonds.getHeight()) return false;
        return true;
    }

    public void setLeft() {
        this.bonds.setX(this.owner.getBonds().getX() + this.owner.getBonds().getWidth() / 2 - this.bonds.getWidth() / 2);
    }

    public void setRight() {
       this.bonds.setX(this.bonds.getX() + this.bonds.getWidth());
    }

    public void setTop() {
        this.bonds.setY(this.owner.getPosition().getY() + this.owner.getBonds().getHeight() / 2 - this.getRect().getHeight() / 2);
    }

    public void setBottom() {
        this.bonds.setY(this.bonds.getY() + this.bonds.getHeight());
    }

    public Rect getRect() {
        return this.getRect();
    }

}
