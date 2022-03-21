package CharacterClasses;

import CellClasses.*;
import MVC.*;
import Threads.*;

import java.awt.*;
import java.util.Timer;


public class Character extends CellContent {
    /* declaration of variables : */
    public String name;
    /* related to movement */
    public boolean isFlying;
    public double speed;
    double basicSpeed;
    /* related to health */
    int health;
    int maxHealth;
    /* related to strength */
    public int strength;
    int basicStrength;
    public String type;

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
    public MoveEnemy moveEnemy;
    boolean isDead = false;
    /** constructor */
    public Character(Model model, Cell c, Image s, double moveSpeed, int health, int strength, boolean flying, String name, int maxF, int maxI, int maxP, String type) {
        /* call to CellContent constructor */
        super(c, s);
        /* initialisation of variables : */
        this.name = name;
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
        if (type == "enemy") {
            this.moveEnemy = moveEnemyModel();
        } else {
            this.move = moveCharModel();
        }
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
        this.type = type;
    }


    /** Method addTimer
     * Creates a timer for this character
     */
    public void addTimer(int period) {
        /* creation and initialisation of the timer */
        this.timer = new Timer();
        /* schedule for the timer */
        timer.schedule(this.move, 0, period);
    }

    public void addTimerEnemy(int delay, int period) {
        /* creation and initialisation of the timer */
        this.timer = new Timer();
        /* schedule for the timer */
        timer.schedule(this.moveEnemy, delay, period);
    }


    /** Method moveCharModel
     * Method to create a new move instruction for this character depending on the selected cell
     * @return Move, a new move for this character
     */
    public Move moveCharModel() {
        return new Move(this, super.getContentCellPosition());
    }
    public MoveEnemy moveEnemyModel() {
        return new MoveEnemy(this, super.getContentCellPosition());
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

    /** Method collect
     * the character passed in parameter collects this ressource
     */
    public void collect(){
        CellContent c = this.contentCellPosition.getCellContent();
        /* if the character is on the correct cell */
        if (c != null && c.getClass() == CellRessource.class) {
            CellRessource r = (CellRessource)c;
            /* it increases it's number of ressource depending of the type and while staying under the max*/
            switch (r.getRessourceType()) {
                /* case flower */
                case flower -> {
                    if (flowerInv < maxIronInv) {
                        /* decrease amount for this ressources and increases in character inventory */
                        r.takeRessource(1);
                        flowerInv++;
                    }
                }
                /* case iron */
                case iron -> {
                    if (ironInv < maxIronInv) {
                        /* decrease amount for this ressources and increases in character inventory */
                        r.takeRessource(1);
                        ironInv++;
                    }
                }
                /* case powder */
                case powder -> {
                    if (powderInv < maxPowderInv) {
                        /* decrease amount for this ressources and increases in character inventory */
                        r.takeRessource(1);
                        powderInv++;
                    }
                }
                /* case gold */
                case gold -> {
                }
            }
        }
    }


}

