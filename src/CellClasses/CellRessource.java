package CellClasses;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CellRessource extends CellContent {
    RessourceType ressourceType;
    final int maxAmount;
    int currentAmount;
    boolean depleted;

    /** constructor */
    public CellRessource (RessourceType r, Cell c, BufferedImage i, int max)  {
        super(c,i);
        this.ressourceType = r;
        this.maxAmount = max;
        this.currentAmount = max;
        this.depleted = false;

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
    /** Method getRessourceType
     * returns the ressource type
     * @return ressourceType;
     */
    public RessourceType getRessourceType(){ return ressourceType; }


}
