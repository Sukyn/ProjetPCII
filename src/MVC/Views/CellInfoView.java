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
        Cell c = model.grid.getSelectedCell();
        CellContent content = c.getCellContent();
        Character character = c.getCellCharacterContent();
        boolean isObstacle = false;
        int ressourceQuantity = 0;
        String ressourceType = "none";
        String charaName = "none";
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        if (content == null || content.getClass() == CellRessource.class) {
            isObstacle = false;
            if (content != null && content.getClass() == CellRessource.class) {
                ressourceQuantity = ((CellRessource) content).getCurrentAmount();
                ressourceType = ((CellRessource) content).getRessourceType().name();
            }
        } else {
            isObstacle = true;
        }
        if (character != null){
            charaName = character.name;
        }
        int x = 5; int y = 15; int yOffset = 20;

        if (isObstacle)
            g.drawString("Obstacle:Yes", x, y);
        else
            g.drawString("Obstacle:No", x, y);
        y += yOffset;
        g.drawString("Ressource type:"+ressourceType, x, y);
        y += yOffset;
        g.drawString("Quantity:"+ressourceQuantity, x, y);
        y += yOffset;
        g.drawString("Character:"+charaName, x, y);

    }
}
