package game;

import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(){
        setTitle("Chorus Grabber");
        add(new GameCanvas(800, 800*9/16));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }

}
