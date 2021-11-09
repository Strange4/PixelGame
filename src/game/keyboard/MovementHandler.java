package game.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.util.Vector2D;

public class MovementHandler implements KeyListener {
    private Vector2D directional2DVector = new Vector2D(0, 0);
    private final int UP = 87;
    private final int DOWN = 83;
    private final int RIGHT = 65;
    private final int LEFT = 68;
    private Keyboard keyboard;

    public MovementHandler(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Vector2D getDirectional2DVector() {
        // Resetting vector
        this.directional2DVector.setX(0);
        this.directional2DVector.setY(0);
        Key[] pressed = keyboard.getPressedKeys();
        for (Key key : pressed) {
            switch (key.getKeyCode()) {
            case UP:
                // System.out.println("UP");
                this.directional2DVector.addX(this.directional2DVector.getX() - 1);
                break;
            case DOWN:
                // System.out.println("DOWN");
                this.directional2DVector.addX(this.directional2DVector.getX() + 1);
                break;
            case RIGHT:
                // System.out.println("RIGHT");
                this.directional2DVector.addY(this.directional2DVector.getY() + 1);
                break;
            case LEFT:
                // System.out.println("LEFT");
                this.directional2DVector.addY(this.directional2DVector.getY() - 1);
                break;
            }
        }
        return this.directional2DVector;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (Key key : this.keyboard.acceptedKeys) {
            if (e.getKeyCode() == key.getKeyCode()) {
                key.toggleIsPressed();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (Key key : this.keyboard.acceptedKeys) {
            if (e.getKeyCode() == key.getKeyCode()) {
                key.toggleIsPressed();
            }
        }
    }
}
