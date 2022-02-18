package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class Dragon extends Character {
    public Dragon(MVC.Model m, Cell c, Image s, double moveSpeed, int health, int strength) {
        super(m, c, s, moveSpeed, health, strength, true, 10, 100, 10);
    }
}
