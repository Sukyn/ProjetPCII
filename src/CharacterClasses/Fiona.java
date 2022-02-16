package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class Fiona extends Character {
    public Fiona(Cell c, Image s, double moveSpeed, int health, int strength) {
        super(c, s, moveSpeed, health, strength, false);
    }

    /**
     * Passif de CharacterClasses.Fiona :
     * Elle peut améliorer sa speed
     */
    public void boostSpeed(double boost) {
        speed += boost;
    }

    public void resetSpeed() {
        speed = basicSpeed;
    }
}
