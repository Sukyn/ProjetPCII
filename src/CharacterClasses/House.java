package CharacterClasses;

import CellClasses.Cell;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class House extends Character {
    /** constructor */
    public BufferedImage originalSprite;
    public House(MVC.Model m, Cell c, BufferedImage s, double moveSpeed, int health, int strength) {
        /* call to the constructor of Character */
        super(m, c, s, moveSpeed, health, strength, false, "Swamp", 0, 0, 0, "ally");
        originalSprite = s;
    }

    @Override
    public void loseHP(int hit) {
        super.loseHP(hit);
        health -= hit;
        RescaleOp op = new RescaleOp(health/(float)maxHealth, 0, null);
        this.sprite = op.filter(originalSprite, null);
    }

    @Override
    public void healHP(int recover) {
        super.healHP(recover);
        RescaleOp op = new RescaleOp(health/(float)maxHealth, 0, null);
        this.sprite = op.filter(originalSprite, null);
    }

}

