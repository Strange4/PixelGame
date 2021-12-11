package game.entity;

import game.graphics.sheets.AnimationSpriteSheet;
import game.entity.esm.EntityState;
import game.entity.esm.EntityStateManager;
import game.util.Vector2D;
import game.graphics.animation.Animated;

public abstract class Entity extends Animated {
    protected final int WIDTH = this.spriteSheet.getSPRITE_WIDTH();
    protected final int HEIGHT = this.spriteSheet.getSPRITE_HEIGHT();
    protected final double BASESPEED = 3;
    protected double modifiedSpeed = BASESPEED;
    protected double scale = 1;
    protected EntityState state;
    protected Vector2D position;
    protected Vector2D lastMovement = new Vector2D(0,0);
    protected EntityStateManager esm;

    public Entity(AnimationSpriteSheet spriteSheet, Vector2D position) {
        super(spriteSheet);
        this.position = position;
        this.esm = new EntityStateManager();
    }

    public void move(Vector2D movement){
        movement = movement.multiplyScalar(this.BASESPEED);
        this.lastMovement = movement;
        Vector2D newPosition =  this.position.add(movement);
        if(newPosition.getX() < 0) newPosition.setX(0);
        if(newPosition.getY() < 0) newPosition.setY(0);
        if(newPosition.getX() > 800 - WIDTH) newPosition.setX(800 - WIDTH);
        if(newPosition.getY() > 450 - HEIGHT) newPosition.setY(450 - HEIGHT);
        this.position = newPosition;
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

    public Vector2D getPosition(){
        return this.position;
    }

    public double getX(){ return this.position.getX(); }
    public double getY(){ return this.position.getY(); }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void updateState(){
        this.esm.update(this);
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}