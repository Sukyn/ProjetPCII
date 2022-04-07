package CellClasses;


import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Class CellObstacle
 * Extends class CellContent
 * Represents the presence of an obstacle on a cell
 */
public class CellObstacle extends CellContent {
    /**
     * Class constructor of the CellObstacle class
     * @param c : cell in which this object is
     * @param i : image to be displayed at the location of the cell
     */
    public CellObstacle (Cell c, BufferedImage i)  {
        super(c,i);
    }
}
