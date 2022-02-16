package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;

public class Donkey extends Character {
    public Donkey(Cell c, Image s, double moveSpeed, int health, int strength) {
        super(c, s, moveSpeed, health, strength, false);
    }

    public void takePowder() {
        this.isFlying = true;
    }

    public void endPowder() {
        this.isFlying = false;
    }
}
