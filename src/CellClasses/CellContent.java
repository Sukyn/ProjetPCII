package CellClasses;


import MVC.Model;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Class CellContent
 * Contains information about the content of the cell
 */
public class CellContent {
    /* Declaration of variables for position in the grid and on the screen */
    public Cell contentCellPosition;
    public double contentPosX, contentPosY;
    public BufferedImage sprite;


    /**
     * Class constructor of the CellContent class
     * @param c : the cell corresponding to this object
     * @param s : the sprite corresponding to the cell, a BufferedImage to be displayed at the cell position
     */
    public CellContent(Cell c, BufferedImage s){
        /* Initialization of positions */
        this.contentCellPosition = c;
        this.contentPosX = c.posCenterX - Model.cellSize/2.;
        this.contentPosY = c.posCenterY - Model.cellSize/2.;
        this.sprite = s;
    }


    /**
     * Method setContentCellPosition
     * Sets the position of this object to the cell passed in parameters
     * @param c : cell corresponding to this object
     */
    public void setContentCellPosition(Cell c){
        contentCellPosition = c;
    }


    /**
     * Method getContentCellPosition
     * Returns the cell containing this object
     * @return contenCellPosition : the cell containing this object
     */
    public Cell getContentCellPosition(){
        return contentCellPosition;
    }


    /**
     * Method getSprite
     * Returns the image representing the cell
     * @return sprite : image corresponding to this object
     */
    public Image getSprite(){
        return sprite;
    }
}

