public class Key {
    private final int keyCode;
    private boolean isPressed;

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

    public void toggleIsPressed() {
        this.isPressed = !this.isPressed;
    }
}
