package game.keyboard;

public class Key {
    private final int keyCode;
    private boolean isPressed;

    // Holds a keycode associated to each key on the keyboard
    public Key(int keyCode) {
        this.keyCode = keyCode;
        this.isPressed = false;
    }

    public boolean getIsPressed() {
        return this.isPressed;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public void setIsPressed(boolean state) {
        this.isPressed = state;
    }
}
