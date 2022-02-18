package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class Shrek extends Character {
    public Shrek(MVC.Model m, Cell c, Image s, double moveSpeed, int health, int strength) {
        super(m, c, s, moveSpeed, health, strength, false, 0, 0, 0);
    }
}
