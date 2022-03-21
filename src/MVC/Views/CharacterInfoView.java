package MVC.Views;

import CellClasses.Cell;
import CharacterClasses.Character;
import MVC.Controller;
import MVC.MainGame;
import MVC.Model;

import javax.swing.*;
import java.awt.*;

public class CharacterInfoView extends JPanel {

    public static final int HEIGHT = GameView.HEIGHT/7;
    public static final int WIDTH = GameView.WIDTH + CellInfoView.WIDTH;
    Model model;
    JButton bouttonRecolte;

    /** constructor */
    public CharacterInfoView(Model m, Controller c) {
        /* assigns model value from parameter */
        this.model = m;
        /* set window default size*/
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        bouttonRecolte = new JButton("Récolte");
        add(bouttonRecolte);
        bouttonRecolte.addActionListener(MainGame.controller);

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
        Character character = c.getCellCharacterContent();
        String charaName = "none";

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (character != null) {
            charaName = character.name;
            g.setColor(Color.BLACK);
            int x = 5;
            int y = 15;
            int yOffset = 20;
            g.drawString("Character:" + charaName, x, y);
            y += yOffset;
            g.drawString("Health:" + character.getHealth() + "/" + character.getMaxHealth(), x, y);
            y += yOffset;
            g.drawString("Strength:" + character.getStrength() + "  Speed:" + character.getSpeed(), x, y);
            y += yOffset;
            g.drawString("Flower:" + character.getF() + "/" + character.getMaxF() + "   Iron:" + character.getI() + "/" + character.getMaxI() + "   Powder:" + character.getP() + "/" + character.getMaxP(), x, y);
        }

    }
}
