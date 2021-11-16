package game.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import game.util.Vector2D;

public class MovementHandler implements KeyListener {
    private Vector2D directional2DVector = new Vector2D(0, 0);
    private Keyboard keyboard;
    private Vector2D lastMovement = new Vector2D(0, 0);

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
                // System.out.println("UP");
                this.directional2DVector.addY(-1);
                this.lastMovement.addY(-1);
                break;
            case KeyEvent.VK_S:
                // System.out.println("DOWN");
                this.directional2DVector.addY(1);
                this.lastMovement.addY(1);
                break;
            case KeyEvent.VK_D:
                // System.out.println("RIGHT");
                this.directional2DVector.addX(1);
                this.lastMovement.addX(1);
                break;
            case KeyEvent.VK_A:
                // System.out.println("LEFT");
                this.directional2DVector.addX(-1);
                this.lastMovement.addX(-1);
                break;
                case KeyEvent.VK_SPACE:
                Vector2D v = new Vector2D(this.lastMovement.getX(), this.lastMovement.getY());
                v.multiplyScalar(3);
                this.directional2DVector.setX(v.getX());
                this.directional2DVector.setY(v.getY());
                System.out.println(v);
                return this.directional2DVector;
            }
        }
        if(this.directional2DVector.getX() != 0 && this.directional2DVector.getY() != 0){
            this.lastMovement.setX(this.directional2DVector.getX());
            this.lastMovement.setY(this.directional2DVector.getY());
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
                key.setIsPressed(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (Key key : this.keyboard.acceptedKeys) {
            if (e.getKeyCode() == key.getKeyCode()) {
                key.setIsPressed(false);
            }
        }
    }
}
