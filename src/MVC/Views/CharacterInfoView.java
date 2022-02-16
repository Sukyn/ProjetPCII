package MVC.Views;

import MVC.Model;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CharacterInfoView extends JPanel {

    public static final int HEIGHT = GameView.HEIGHT/7;
    public static final int WIDTH = GameView.WIDTH + CellInfoView.WIDTH;
    Model model;

    /** constructor */
    public CharacterInfoView(Model m) {
        /* assigns model value from parameter */
        this.model = m;
        /* set window default size*/
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                revalidate();
                repaint();
            }
        }).start();
    }

    /** Method paint
     * draw all the content in the window
     * @param g, Graphics
     */
    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
