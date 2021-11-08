package game;

import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(){
        setTitle("Demo");

        // Full Screen
//        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        setUndecorated(true);
//        add(new GameCanvas( (int) screenSize.getWidth(), (int) screenSize.getHeight()));
//        device.setFullScreenWindow(this);

//        Normal
        add(new GameCanvas(800, 450));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }

}
