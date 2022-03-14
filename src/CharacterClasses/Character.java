package CharacterClasses;

import CellClasses.*;
import MVC.*;
import Threads.*;

import java.awt.*;
import java.util.Timer;

public class Character extends CellContent {
    /* declaration of variables : */
    /* related to movement */
    public boolean isFlying;
    public double speed;
    double basicSpeed;
    /* related to health */
    int health;
    int maxHealth;
    /* related to strength */
    int strength;
    int basicStrength;

    /* maximum inventory for ressources */
    public int maxFlowerInv;
    public int maxIronInv;
    public int maxPowderInv;
    /* current amount of ressources */
    public int flowerInv;
    public int ironInv;
    public int powderInv;
    /* model for the character */
    public Model model;
    /* Threads related variables */
    public Timer timer;
    public Move move;
    boolean isDead = false;
    /** constructor */
    public Character(Model model, Cell c, Image s, double moveSpeed, int health, int strength, boolean flying, int maxF, int maxI, int maxP) {
        /* call to CellContent constructor */
        super(c, s);
        /* initialisation of variables : */
        /* base value that will change and evolve in game */
        this.speed = moveSpeed;
        this.health = health;
        this.strength = strength;
        this.maxHealth = health;
        /* memory off initial values to reinitialise in case of a boost */
        this.basicSpeed = moveSpeed;
        this.basicStrength = strength;
        /* if the character is flying or not and it's thread for movement */
        this.isFlying = flying;
        this.move = moveCharModel();

        /* max inventory for ressources */
        this.maxFlowerInv = maxF;
        this.maxIronInv = maxI;
        this.maxPowderInv = maxP;
        /* initial inventory of ressources */
        this.flowerInv = 0;
        this.ironInv = 0;
        this.powderInv = 0;

        /* model */
        this.model = model;
    }


    /** Method addTimer
     * Creates a timer for this character
     */
    public void addTimer() {
        /* creation and initialisation of the timer */
        this.timer = new Timer();
        /* schedule for the timer */
        timer.schedule(this.move, 0, 100);
    }


    /** Method moveCharModel
     * Method to create a new move instruction for this character depending on the selected cell
     * @return Move, a new move for this character
     */
    public Move moveCharModel() {
        return new Move(this, super.getContentCellPosition());
    }

    /** Method loseHP
     * decrements health variable depending on the hit passed in parameter
     * @param hit, int
     */
    public void loseHP(int hit) { health -= hit; if (health <= 0) isDead = true; }
    /** Method healHP
     * increments health variable depending on the hit passed in parameter while staying under the maxHealth variable
     * @param recover, int
     */
    public void healHP(int recover) { if (health+recover <= maxHealth) health+= recover; else health=maxHealth;}

    /** Method resetSpeed
     * reset the speed of this character to its initial value
     */
    public void resetSpeed() {
        speed = basicSpeed;
    }

    /** Method resetStrength
     * reset the strength of this character to its initial value
     */
    public void resetStrength() {
        speed = basicStrength;
    }
}

