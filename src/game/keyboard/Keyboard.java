package game.keyboard;

import java.util.ArrayList;

public class Keyboard extends ArrayList<Key> {
    public Key[] acceptedKeys;

    // Creates a keyboard that holds the keys used for the game
    public Keyboard(int... acceptedKeys) {
        this.acceptedKeys = new Key[acceptedKeys.length];
        for (int i = 0; i < this.acceptedKeys.length; i++) {
            this.acceptedKeys[i] = new Key(acceptedKeys[i]);
        }
    }


    /**
     *  Returns an array of all the pressed keys
     * @return Key[] all the pressed keys
     */
    public Key[] getPressedKeys() {
        ArrayList<Key> pressedKeys = new ArrayList<Key>();
        for (Key key : acceptedKeys) {
            if (key.getIsPressed()) {
                pressedKeys.add(key);
            }
        }
        return pressedKeys.toArray(new Key[pressedKeys.size()]);
    }

    /**
     * Returns the last pressed key on the keyboard
     * @return Key the last pressed key
     */
    public Key getLastPressedKey(){
        Key[] keys = getPressedKeys();
        if(keys.length > 0){
            return keys[keys.length - 1];
        } else {
            return null;
        }
    }
}
