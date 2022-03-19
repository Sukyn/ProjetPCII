package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class Fiona extends Character {
    /** constructor */
    public Fiona(MVC.Model m, Cell c, Image s, double moveSpeed, int health, int strength) {
        /* call to the constructor of Character */
        super(m, c, s, moveSpeed, health, strength, false, "Fiona", 10, 10, 100, "ally");
    }

    /** Method boostSpeed
     * increase the speed of Fiona
     * à mettre dans Character ?
     */
    public void boostSpeed(double boost) {
        speed += boost;
    }

}
