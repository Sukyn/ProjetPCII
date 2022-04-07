package CellClasses;


import Threads.IncreaseRessource;
import java.awt.image.BufferedImage;
import java.util.Timer;


/**
 * Class CellResource
 * Extends class CellContent
 * Represents the presence of a resource source on the cell
 * Contains information about the resource  in the cell
 */
public class CellRessource extends CellContent {
    /* Declaration of variables representing the state of the resource present on the cell */
    RessourceType ressourceType;
    final int maxAmount;
    int currentAmount;
    boolean depleted;
    Timer timer;
    IncreaseRessource increaseRessource;


    /**
     * Class constructor of the class CellResource
     * @param r : type of the resource on the cell
     * @param c : cell corresponding to this object
     * @param i : image representing the resource, to be displayed on the cell in the view
     * @param max : maximum quantity of the resource that can be present in the cell
     */
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


    /**
     * Function getCurrentAmount
     * Returns the current amount of the resource
     * @return currentAmount : the current amount of the resource on the cell
     */
    public int getCurrentAmount(){ return currentAmount; }


    /**
     * Method takeResource
     * Diminishes the amount of the resource in line with the value passed in parameters
     * @param value : quantity of resource to be taken
     */
    public void takeRessource(int value){ currentAmount -= value; }


    /**
     * Method addResource
     * Increases the amount of the resource in line with the value passed in parameters
     * @param value : quantity of resource to be added
     */
    public void addRessource(int value){ currentAmount += value; }


    /**
     * Function getResourceType
     * Returns the type of the resource
     * @return resourceType : the type of the resource in the cell
     */
    public RessourceType getRessourceType(){ return ressourceType; }


}
