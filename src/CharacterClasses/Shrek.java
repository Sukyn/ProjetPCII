package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Shrek extends Character {
    /** constructor */
    public Shrek(MVC.Model m, Cell c, BufferedImage s, double moveSpeed, int health, int strength) {
        /* call to the constructor of Character */
        super(m, c, s, moveSpeed, health, strength, false, "Shrek", 0, 0, 0, "ally");
    }
}
