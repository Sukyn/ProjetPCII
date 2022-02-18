package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class Donkey extends Character {
    public Donkey(MVC.Model m, Cell c, Image s, double moveSpeed, int health, int strength) {
        super(m, c, s, moveSpeed, health, strength, false, 100, 10, 10);
    }

    public void takePowder() {
        this.isFlying = true;
    }

    public void endPowder() {
        this.isFlying = false;
    }
}
