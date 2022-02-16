package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class Dragon extends Character {
    public Dragon(Cell c, Image s, double moveSpeed, int health, int strength) {
        super(c, s, moveSpeed, health, strength, true);
    }
}
