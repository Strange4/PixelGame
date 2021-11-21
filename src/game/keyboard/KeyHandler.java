package game.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    public static KeyHandler getInstance(){
        return INSTANCE;
    }

//    public void handleKey() {
//        Key[] pressed = keyboard.getPressedKeys();
//        for (Key key : pressed) {
//            switch (key.getKeyCode()) {
//                case KeyEvent.VK_W:
//                    System.out.println("UP");
//                    break;
//                case KeyEvent.VK_S:
//                    System.out.println("DOWN");
////                    return "DOWN";
//                    break;
//                case KeyEvent.VK_A:
//                    System.out.println("LEFT");
////                    return "LEFT";
//                    break;
//                case KeyEvent.VK_D:
//                    System.out.println("RIGHT");
////                    return "RIGHT";
//                    break;
//                case KeyEvent.VK_SPACE:
//                    System.out.println("DASH");
////                    return "DASH";
//                    break;
//                case KeyEvent.VK_ESCAPE:
//                    System.out.println("PAUSE");
////                    return "PAUSE";
//                    break;
//                case KeyEvent.VK_Q:
//                    System.out.println("SECONDARY");
////                    return "SECONDARY";
//                    break;
//            }
//        }
////        return "NULL";
//    }
    // Not used but has to be implemented
    @Override
    public void keyTyped(KeyEvent e) {}

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
