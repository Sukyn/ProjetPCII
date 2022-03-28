package MVC.Views;

import CellClasses.Cell;
import CharacterClasses.Character;
import MVC.Controller;
import MVC.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CharacterInfoView extends JPanel {

    public static final int HEIGHT = GameView.HEIGHT/7;
    public static final int WIDTH = GameView.WIDTH + CellInfoView.WIDTH;
    Model model;
    JButton buttonRecolte, buttonDrop, buttonIncreaseStrength, buttonIncreaseHp, buttonHeal;

    /** constructor */
    public CharacterInfoView(Model m) {
        /* assigns model value from parameter */
        this.model = m;
        /* set window default size*/
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        buttonRecolte = new JButton("Récolte");
        add(buttonRecolte);
        buttonDrop = new JButton("Drop");
        add(buttonDrop);
        buttonHeal = new JButton("Heal");
        add(buttonHeal);
        buttonIncreaseStrength = new JButton("Améliorer la force");
        add(buttonIncreaseStrength);
        buttonIncreaseHp = new JButton("Améliorer les points de vie");
        add(buttonIncreaseHp);
        System.out.println(buttonRecolte.getActionCommand());
    }

    public void setController(Controller c){
        buttonRecolte.addActionListener(c);
        buttonDrop.addActionListener(c);
        buttonIncreaseHp.addActionListener(c);
        buttonIncreaseStrength.addActionListener(c);
        buttonHeal.addActionListener(c);
    }

    /** Method paint
     * draw all the content in the window
     * @param g, Graphics
     */
    public void paintComponent(Graphics g) {
        Cell c = model.grid.getSelectedCell();
        Character character = c.getCellCharacterContent();
        String charaName;

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (character != null) {
            if (Objects.equals(character.type, "ally")){
                this.buttonDrop.setEnabled(true);
                this.buttonRecolte.setEnabled(true);
                this.buttonIncreaseHp.setEnabled(model.getGlobalPowder() > 0);
                this.buttonIncreaseStrength.setEnabled(model.getGlobalIron() > 0);
                this.buttonHeal.setEnabled(model.getGlobalFlower() > 0);
            } else {
                this.buttonDrop.setEnabled(false);
                this.buttonRecolte.setEnabled(false);
                this.buttonIncreaseHp.setEnabled(false);
                this.buttonIncreaseStrength.setEnabled(false);
                this.buttonHeal.setEnabled(false);
            }
            charaName = character.name;
            g.setColor(Color.BLACK);
            int x = 5;
            int y = 15;
            int yOffset = 20;
            g.drawString("Character:" + charaName, x, y);
            y += yOffset;
            g.setColor(Color.RED);
            g.fillRect(x-1, y-1-11, 102, 14);
            g.setColor(Color.GREEN);
            g.fillRect(x, y-11, Math.max(1,100*character.getHealth()/character.getMaxHealth()), 12);
            g.setColor(Color.BLACK);
            g.drawString("Health:" + character.getHealth() + "/" + character.getMaxHealth(), x, y);
            y += yOffset;
            g.drawString("Strength:" + character.getStrength() + "  Speed:" + character.getSpeed(), x, y);
            y += yOffset;
            g.drawString("Flower:" + character.getF() + "/" + character.getMaxF() + "   Iron:" + character.getI() + "/" + character.getMaxI() + "   Powder:" + character.getP() + "/" + character.getMaxP(), x, y);
        } else {
            this.buttonDrop.setEnabled(false);
            this.buttonRecolte.setEnabled(false);
            this.buttonIncreaseHp.setEnabled(false);
            this.buttonIncreaseStrength.setEnabled(false);
            this.buttonHeal.setEnabled(false);
        }

    }
}
