package game.layers;

import game.keyboard.KeyHandler;
import game.util.MouseHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameStartLayer extends GameLayer{

    public GameStartLayer(GameLayerManager glm) {
        super(glm);
    }

    @Override
    void update() {

    }

    @Override
    void input(MouseHandler mouse, KeyHandler kHandler) {
        if(kHandler.getLastKey() != null && kHandler.getLastKey().getKeyCode() == KeyEvent.VK_ENTER){
            this.glm.addState(new PlayLayer(this.glm));
            this.glm.removeState(this);
        }
    }

    @Override
    void render(Graphics2D graphics2D, int scale) {
        graphics2D.fillRect(0, 0,800,450);
        graphics2D.setColor(new Color(191, 64, 191));
        graphics2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        graphics2D.drawString("Welcome to Chorus Grabber!" , 80,100);
        graphics2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Grab all the berries as fast as possible" , 70,150);
        graphics2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        graphics2D.drawString("Controls: Press WASD to move and SPACE to dash" , 70,200);


        graphics2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        graphics2D.drawString("Press ENTER to start playing", 80,300);
    }
}
