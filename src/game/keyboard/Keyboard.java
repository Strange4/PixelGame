import java.util.ArrayList;

public class Keyboard extends ArrayList<Key> {
    public Key[] acceptedKeys;

    public Keyboard(int[] acceptedKeys) {
        this.acceptedKeys = new Key[acceptedKeys.length];
        for (int i = 0; i < this.acceptedKeys.length; i++) {
            this.acceptedKeys[i] = new Key(acceptedKeys[i]);
        }
    }

    public void toggleKeyState(Key key) {
        key.toggleIsPressed();
    }

    public Key[] getPressedKeys() {
        ArrayList<Key> pressedKeys = new ArrayList<Key>();
        for (Key key : acceptedKeys) {
            if (key.getIsPressed()) {
                pressedKeys.add(key);
            }
        }
        return pressedKeys.toArray(new Key[pressedKeys.size()]);
    }
}
