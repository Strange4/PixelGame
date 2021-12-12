package game.keyboard;

import game.util.Vector2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Singleton
public final class KeyHandler implements KeyListener {
    private static final KeyHandler INSTANCE = new KeyHandler();
    private Keyboard keyboard;
    private MovementHandler mh;
    private AbilityHandler ah;

    private KeyHandler() {}

    public void init(Keyboard keyboard){
        this.keyboard = keyboard;
        this.mh = new MovementHandler(keyboard);
        this.ah = new AbilityHandler(keyboard);
    }

    /**
     * Returns the last pressed key on the keyboard
     * @return Key last pressed key on the keyboard
     */
    public Key getLastKey(){
        return this.keyboard.getLastPressedKey();
    }

    /**
     * Returns the KeyHandler instance of the singleton
     * @return KeyHandler
     */
    public static KeyHandler getInstance(){
        return INSTANCE;
    }

    /**
     * Returns the directional unit vector representing the movement of the player
     * @return Vector2D directional vector representing the movement of the player
     */
    public Vector2D getDirectionalVector(){
        return this.mh.getDirectional2DVector();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used but must be implemented
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
