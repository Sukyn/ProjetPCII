package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Donkey extends Character {
    /** constructor */
    public Donkey(MVC.Model m, Cell c, BufferedImage s, double moveSpeed, int health, int strength) {
        /* call to the constructor of Character */
        super(m, c, s, moveSpeed, health, strength, false, "Donkey",100, 10, 10, "ally");
    }

    /** Method takePowder
     * activates the power of the donkey, he starts flying
     */
    public void takePowder() {
        this.isFlying = true;
    }

    /** Method endPowder
     * deactivates the power of the donkey, he stops flying
     */
    public void endPowder() {
        this.isFlying = false;
    }
}
