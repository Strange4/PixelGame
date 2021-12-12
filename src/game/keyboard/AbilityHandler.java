package game.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AbilityHandler implements KeyListener {
    private final Keyboard keyboard;

    public AbilityHandler(Keyboard keyboard){
        this.keyboard = keyboard;
    }

    // Not used, but be implemented
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // Updating the state of each key of the keyboard
        for (Key key : this.keyboard.acceptedKeys) {
            if (e.getKeyCode() == key.getKeyCode()) {
                key.setIsPressed(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Updating the state of each key of the keyboard
        for (Key key : this.keyboard.acceptedKeys) {
            if (e.getKeyCode() == key.getKeyCode()) {
                key.setIsPressed(false);
            }
        }
    }
}
