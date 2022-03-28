package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class House extends Character {
    /** constructor */
    public House(MVC.Model m, Cell c, Image s, double moveSpeed, int health, int strength) {
        /* call to the constructor of Character */
        super(m, c, s, moveSpeed, health, strength, false, "Swamp", 0, 0, 0, "ally");
    }
}

