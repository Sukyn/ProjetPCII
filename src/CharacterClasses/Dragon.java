package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class Dragon extends Character {
    /** constructor */
    public Dragon(MVC.Model m, Cell c, Image s, double moveSpeed, int health, int strength) {
        /* call to the constructor of Character */
        super(m, c, s, moveSpeed, health, strength, true, "Dragon", 10, 100, 10);
    }
}
