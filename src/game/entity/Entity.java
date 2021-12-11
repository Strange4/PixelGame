package game.entity;

import game.entity.esm.EntityState;
import game.entity.esm.EntityStateManager;
import game.graphics.Animated;
import game.graphics.SpriteSheet;
import game.util.Vector2D;

public abstract class Entity extends Animated {
    protected final int WIDTH = this.spriteSheet.getSPRITE_WIDTH();
    protected final int HEIGHT = this.spriteSheet.getSPRITE_HEIGHT();
    protected final double BASESPEED = 3;
    protected double modifiedSpeed = BASESPEED;
    protected EntityState state;
    protected Vector2D position;
    protected Vector2D lastMovement = new Vector2D(0,0);
    protected EntityStateManager esm;


    public Entity(SpriteSheet spriteSheet, Vector2D position, int maxFrames) {
        super(spriteSheet);
        this.position = position;
        this.esm = new EntityStateManager();
        System.out.println(this.getCurrentFrame());
        super.changeAnimation(maxFrames);
    }

    public void move(Vector2D movement){
        movement = movement.multiplyScalar(this.BASESPEED);
        this.lastMovement = movement;
        this.position = this.position.addVector(movement);
//        this.lastMovement.setX(this.position.getX());
//        this.lastMovement.setY(this.position.getY());
//        this.position.addX(movement.getX() * this.modifiedSpeed);
//        this.position.addY(movement.getY() * this.modifiedSpeed);
    }

    public EntityState getState(){return this.state;}
    public void setState(EntityState state){this.state = state;}

    public void setModifiedSpeed(double speed){this.modifiedSpeed = speed;}
    public double getModifiedSpeed(){return this.modifiedSpeed;}

    public double getBASESPEED() {
        return BASESPEED;
    }

    public Vector2D getLastMovement() {
        return this.lastMovement;
    }

    public void setLastMovement(Vector2D lastMovement) {
        this.lastMovement = lastMovement;
    }

    public double getX(){ return this.position.getX(); }
    public double getY(){ return this.position.getY(); }

    public void updateState(){
        this.esm.update(this);
    }
}