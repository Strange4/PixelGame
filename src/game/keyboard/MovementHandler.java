package game.keyboard;

import java.awt.event.KeyEvent;
import game.util.Vector2D;

public class MovementHandler {
    private Vector2D directional2DVector = new Vector2D(0, 0);
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
                case KeyEvent.VK_W:
                    this.directional2DVector.addY(-1);
                    break;
                case KeyEvent.VK_S:
                    this.directional2DVector.addY(1);
                    break;
                case KeyEvent.VK_D:
                    this.directional2DVector.addX(1);
                    break;
                case KeyEvent.VK_A:
                    this.directional2DVector.addX(-1);
                    break;
            }
        }
        return this.directional2DVector;
    }
}