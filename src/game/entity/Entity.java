package game.entity;

import game.graphics.Animated;
import game.graphics.SpriteSheet;
import game.util.Vector2D;

public abstract class Entity extends Animated {
    protected final int WIDTH = this.spriteSheet.getSPRITE_WIDTH();
    protected final int HEIGHT = this.spriteSheet.getSPRITE_HEIGHT();
    protected double speed = 0.5;
    protected Vector2D position;
    protected Vector2D lastMovement = new Vector2D(0,0);


    public Entity(SpriteSheet spriteSheet, Vector2D position) {
        super(spriteSheet);
        this.position = position;
    }

    public void move(Vector2D movement){
        this.lastMovement.setX(movement.getX());
        this.lastMovement.setY(movement.getY());
        this.position.addX(movement.getX() * this.speed);
        this.position.addY(movement.getY() * this.speed);
    }

    public void setSPEED(double speed){this.speed = speed;}

    public double getX(){ return this.position.getX(); }
    public double getY(){ return this.position.getY(); }
}
