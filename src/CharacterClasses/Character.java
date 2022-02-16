package CharacterClasses;

import CellClasses.*;
import Threads.Move;

import java.awt.*;
import java.util.Timer;

public class Character extends CellContent {
    boolean isFlying;
    public double speed;
    int health;
    int strength;
    double basicSpeed;
    int maxHealth;
    int basicStrength;
    public Timer timer;
    public Move move;
    boolean isDead = false;
    /** constructor */
    public Character(Cell c, Image s, double moveSpeed, int health, int strength, boolean flying) {
        super(c, s);
        this.speed = moveSpeed;
        this.health = health;
        this.strength = strength;
        this.basicSpeed = moveSpeed;
        this.maxHealth = health;
        this.basicStrength = strength;
        this.isFlying = flying;
        this.move = moveCharModel();
    }

    public void addTimer() {
        this.timer = new Timer();
        timer.schedule(this.move, 0, 100);
    }

    /** Method to move the character
     * params int xTarget, wanted x position
     * int yTarget, wanted y position
     * */
    public Move moveCharModel() {
        return new Move(this, super.getContentCellPosition());
    }
    public void loseHP(int hit) { health -= hit; if (health <= 0) isDead = true; }
    public void healHP(int recover) { if (health+recover <= maxHealth) health+= recover; else health=maxHealth;}
}

