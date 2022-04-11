package CellClasses;


import MVC.*;
import CharacterClasses.Character;
import MVC.Views.*;


/**
 * Class Cell
 * Element of the grid
 * Contains information about the position and the content of the cell
 */
public class Cell {
    /* Declaration of variables : */
    /* For position */
    public int posX, posY, posCenterX, posCenterY;

    /* For content and selection */
    public CellContent content;
    public Character characterContent;
    public boolean isTargeted = false;
    public boolean isSelected = false;


    /**
     * Class constructor of the Cell class
     * @param x : the abscissa of the cell
     * @param y : the ordinate of the cell
     */
    public Cell(int x, int y) {
        /* Initialization of the position variables */
        this.posX = x;
        this.posY = y;
        /* Initialization of the position for the center of the cell in the window depending on its line */
        /* This allows the cells to be displayed as an hexagonal grid even though the indexes are those of a rectangular one */
        if (posY%2 == 0){
            this.posCenterX = (this.posX - 1) * Model.cellSize-this.posX *12 + this.posX * GameView.shift + Model.cellSize/2;

        } else {
            this.posCenterX = (this.posX - 1) * Model.cellSize-this.posX *12 + this.posX * GameView.shift + Model.cellSize/2 - (6- GameView.shift/2) + Model.cellSize/2;
        }
        this.posCenterY = (Model.cellSize*3/4 - 5 + GameView.shift)*this.posY - (Model.cellSize/4);


    }


    /**
     * Method setTargeted
     * Defines this cell as the destination of the character passed in parameter
     */
    public void setTargeted() { isTargeted = true;
    }


    /**
     * Method setCellContent
     * Sets the cell content of this object to the value of the passed parameter
     * @param newContent : the CellContent to set as content of the cell
     */
    public void setCellContent(CellContent newContent){ content = newContent; }


    /**
     * Function getCellContent
     * Returns the current cell content of this object
     * @return CellContent : the content of the cell
     */
    public CellContent getCellContent(){ return  content; }


    /**
     * Method setCellCharacterContent
     * Sets the cell character content of this object to the value of the passed parameter
     * @param newCharacter : the Character to set as the character content of the cell
     */
    public void setCellCharacterContent(Character newCharacter){ characterContent = newCharacter; }


    /**
     * Function getCellCharacterContent
     * Returns the content of the cell a character if is one, otherwise
     * @return Character : the character content of the cell
     */
    public Character getCellCharacterContent(){ return characterContent; }
}
