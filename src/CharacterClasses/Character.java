package CharacterClasses;

import CellClasses.*;
import MVC.*;
import Threads.*;

import java.awt.image.BufferedImage;
import java.util.Objects;
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
    public Timer timerGather;
    public GatherRessource gatherRessource;
    public Timer timerMove;
    public Move move;
    public MoveEnemy moveEnemy;
    public boolean isDead = false;
    /** constructor */
    public Character(Model model, Cell c, BufferedImage s, double moveSpeed, int health, int strength, boolean flying, String name, int maxF, int maxI, int maxP, String type) {
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
        if (Objects.equals(type, "enemy")) {
            this.moveEnemy = moveEnemyModel();
        } else {
            this.move = moveCharModel();
        }
        this.gatherRessource = createGatherThread();
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

    /** Method addTimerMove
     * Creates a timerMove for the move thread of this character
     */
    public void addTimerGather() {
        /* creation and initialisation of the timerMove */
        this.timerGather = new Timer();
        /* schedule for the timerMove */
        timerGather.schedule(this.gatherRessource, 0, 1000);
    }

    /** Method addTimerMove
     * Creates a timerMove for the move thread of this character
     */
    public void addTimerMove(int period) {
        /* creation and initialisation of the timerMove */
        this.timerMove = new Timer();
        /* schedule for the timerMove */
        timerMove.schedule(this.move, 0, period);
    }

    public void addTimerEnemy(int delay, int period) {
        /* creation and initialisation of the timerMove */
        this.timerMove = new Timer();
        /* schedule for the timerMove */
        timerMove.schedule(this.moveEnemy, delay, period);
    }

    /** Method createGatherRessource
     * Method to create a gatherRessource instruction for this character
     * @return GatherRessource, a new gatherRessource thread for this character
     */
    public GatherRessource createGatherThread() {
        return new GatherRessource(this);
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
    public void loseHP(int hit) {
        health -= hit;
        if (health <= 0)  {
            isDead = true;
            if (type.equals("enemy")) {
                this.getContentCellPosition().setCellCharacterContent(null);
                this.moveEnemy = null;
                this.timerMove.cancel();
                model.addGlobalGold(1);
            } else {
                this.getContentCellPosition().setCellCharacterContent(null);
                regen(this);
            }
        }
    }

    public void regen(Character character) {
        Timer timer = new Timer();
        timer.schedule(new Regen(this), 0, 1000);
    }
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
        if (c != null && c.getClass() == CellRessource.class && ((CellRessource) c).getCurrentAmount() != 0) {
            CellRessource r = (CellRessource)c;
            /* it increases it's number of ressource depending of the type and while staying under the max*/
            switch (r.getRessourceType()) {
                /* case flower */
                case flower -> {
                    if (flowerInv < maxFlowerInv) {
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

    public void dropRessources(){
        model.setGlobalIron(model.getGlobalIron()+this.getI());
        this.ironInv = 0;
        model.setGlobalPowder(model.getGlobalPowder()+this.getP());
        this.powderInv = 0;
        model.setGlobalFlower(model.getGlobalFlower()+this.getF());
        this.flowerInv = 0;
    }

    public void addStrength(int x){
        this.strength += x;
    }
    public void addMaxHp(int x){
        this.maxHealth += x;
    }

    public int getHealth(){ return health;}
    public int getMaxHealth(){ return maxHealth;}
    public int getStrength(){ return strength;}
    public double getSpeed(){ return speed;}
    public int getF(){ return flowerInv;}
    public int getMaxF(){ return maxFlowerInv;}
    public int getI(){ return ironInv;}
    public int getMaxI(){ return maxIronInv;}
    public int getP(){ return powderInv;}
    public int getMaxP(){ return maxPowderInv;}


}

