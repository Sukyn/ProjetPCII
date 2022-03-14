package MVC.Views;

import CellClasses.*;
import CharacterClasses.Character;
import MVC.Model;
import MVC.View;

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
        Cell c = Model.grid.getSelectedCell();
        CellContent content = c.getCellContent();
        Character character = c.getCellCharacterContent();
        boolean isObstacle = false;
        int ressourceQuantity = 0;
        RessourceType ressourceType = null;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (content == null || content.getClass() == CellRessource.class)
            isObstacle = false;
            if (content != null){
                ressourceQuantity = ((CellRessource)content).getCurrentAmount();
                ressourceType =  ((CellRessource)content).getRessourceType();
            }
        else
            isObstacle = true;

        g.drawString("Obstacle :", 5, 5);
        if (isObstacle)
            g.drawString("Yes", 20, 5);
        else
            g.drawString("No", 20, 5);

    }
}
