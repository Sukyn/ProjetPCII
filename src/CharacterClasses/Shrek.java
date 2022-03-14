package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class Shrek extends Character {
    /** constructor */
    public Shrek(MVC.Model m, Cell c, Image s, double moveSpeed, int health, int strength) {
        /* call to the constructor of Character */
        super(m, c, s, moveSpeed, health, strength, false, "Shrek", 0, 0, 0);
    }
}
