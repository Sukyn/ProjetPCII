package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class Shrek extends Character {
    public Shrek(Cell c, Image s, double moveSpeed, int health, int strength) {
        super(c, s, moveSpeed, health, strength, false);
    }
}
