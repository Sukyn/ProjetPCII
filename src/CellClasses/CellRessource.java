package CellClasses;

import Threads.IncreaseRessource;

import java.awt.image.BufferedImage;
import java.util.Timer;

public class CellRessource extends CellContent {
    RessourceType ressourceType;
    final int maxAmount;
    int currentAmount;
    boolean depleted;
    Timer timer;
    IncreaseRessource increaseRessource;

    /** constructor */
    public CellRessource (RessourceType r, Cell c, BufferedImage i, int max)  {
        super(c,i);
        this.ressourceType = r;
        this.maxAmount = max;
        this.currentAmount = max;
        this.depleted = false;
        this.timer = new Timer();
        increaseRessource = new IncreaseRessource(this);
        timer.schedule(this.increaseRessource, 0, 5000);
    }

    /** Method getCurrentAmount
     * returns the current amount of the ressource
     * @return currentAmount;
     */
    public int getCurrentAmount(){ return currentAmount; }

    /** Method takeRessource
     *
     */
    public void takeRessource(int value){ currentAmount -= value; }

    /** Method takeRessource
     *
     */
    public void addRessource(int value){ currentAmount += value; }

    /** Method getRessourceType
     * returns the ressource type
     * @return ressourceType;
     */
    public RessourceType getRessourceType(){ return ressourceType; }


}
