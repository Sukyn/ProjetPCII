package CellClasses;

import MVC.Model;

import java.awt.*;

public class CellContent {
    /* declaration of variables for position in the grid and on the screen */
    public Cell contentCellPosition;
    public double contentPosX, contentPosY;
    public Image sprite;
    /** constructor */
    public CellContent(Cell c, Image s){
        /* initialisation of positions */
        this.contentCellPosition = c;
        this.contentPosX = c.posCenterX - Model.cellSize/2.;
        this.contentPosY = c.posCenterY - Model.cellSize/2.;
        this.sprite = s;
    }

    /** Method setContentCellPosition
     * set the position of this object in the cell in parameters
     * @param c, Cell
     */
    public void setContentCellPosition(Cell c){
        contentCellPosition = c;
    }

    /** Method getContentCellPosition
     * return the cell containing this object
     * @return Cell
     */
    public Cell getContentCellPosition(){
        return contentCellPosition;
    }

    /** Method getSprite
     * return the image corresponding to this particular image
     * @return Image
     */
    public Image getSprite(){
        return sprite;
    }
}

