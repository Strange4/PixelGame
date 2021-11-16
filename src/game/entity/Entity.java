package game.entity;

import game.graphics.Animated;
import game.graphics.SpriteSheet;
import game.util.Vector2D;

public abstract class Entity extends Animated {
    protected final int WIDTH = this.spriteSheet.getSPRITE_WIDTH();
    protected final int HEIGHT = this.spriteSheet.getSPRITE_HEIGHT();
    protected final int SPEED = 2;
    protected Vector2D position;

    public Entity(SpriteSheet spriteSheet, Vector2D position) {
        super(spriteSheet);
        this.position = position;
    }

    public void move(Vector2D movement, int speed){
//        this.position.addX();
    }

    public double getX(){ return this.position.getX(); }
    public double getY(){ return this.position.getY(); }
}
