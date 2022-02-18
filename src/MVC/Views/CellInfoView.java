package MVC.Views;

import MVC.Model;

import javax.swing.*;
import java.awt.*;

public class CellInfoView extends JPanel {
    public static final int HEIGHT = View.HEIGHT;
    public static final int WIDTH = View.WIDTH/7;
    Model model;

    /** constructor */
    public CellInfoView(Model m) {
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
