package CellClasses;

import MVC.*;
import CharacterClasses.Character;
import MVC.Views.*;

public class Cell {
    /* declaration of variables : */
    /* for position */
    public int posX;
    public int posY;
    public int posCenterX;    public int posCenterY;
    /* for content and selection */
    public CellContent content;
    public boolean isTargeted = false;
    public boolean isSelected = false;
    /** constructor */
    public Cell(int x, int y) {
        this.posX = x;
        this.posY = y;
        if (posY%2 == 0){
            this.posCenterX = (this.posX - 1) * Model.cellSize-this.posX *12 + this.posX * GameView.shift + Model.cellSize/2;
        } else {
            this.posCenterX = (this.posX - 1) * Model.cellSize-this.posX *12 + this.posX * GameView.shift + Model.cellSize/2 - (6- GameView.shift/2) + Model.cellSize/2;
        }
        this.posCenterY = (3 * Model.cellSize / 4) * (this.posY - 1) + this.posY * GameView.shift + Model.cellSize/2;
    }

    public void setTargeted(Character chara) {
        isTargeted = true;
    }

    /** Method setCellContent
     * set the cell content of this object with the passed parameter
     * @param newContent, the CellContent to set
     */
    public void setCellContent(CellContent newContent){
        content = newContent;
    }

    /** Method getCellContent
     * returns the current cell content of this object
     * @return CellContent
     */
    public  CellContent getCellContent(){
        return  content;
    }

    /** Method getCellCharacterContent
     * returns the content of the cell as a character if it is one, else, return null
     * @return Character
     */
    public Character getCellCharacterContent(){
        if (content.getClass() == Character.class)
            return (Character) content;
        else
            return null;
    }
}
