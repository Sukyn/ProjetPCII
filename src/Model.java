import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class Model {
    public static int cellSize = 100;
    public Grid grid;
    public ArrayList<Character> chars = new ArrayList<>();
    public ArrayList<CellContent> items = new ArrayList<>();
    public Model(int height, int width) {
        this.grid = new Grid(height, width);

        //c.setCellContent();
        Cell c = grid.cells.get(height/2).get(width/2);
        c.isSelected = true;
        grid.selectedCell = c;
        addChar("Assets/Shrek.png", height/2, width/2, 1);
        addChar("Assets/Fiona.png", height/2, width/2+1, 5);
        addItem("Assets/Buche.png",height/2, width/2-1);
        addItem("Assets/Buche.png",height/2+2, width/2+1);
        addItem("Assets/Buche.png",height/2-2, width/2+1);
    }

    private void addChar(String file, int posX, int posY, int moveSpeed) {
        Image image = null;
        Cell cell = grid.cells.get(posX).get(posY);
        try {
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Character chara = new Character(cell, image, moveSpeed);
        cell.setCellContent(chara);
        chars.add(chara);
    }

    private void addItem(String file, int posX, int posY) {
        Image image = null;
        Cell cell = grid.cells.get(posX).get(posY);
        try {
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CellContent item = new CellContent(cell, image);
        cell.setCellContent(item);
        items.add(item);
    }
}


class Grid {
    final int height;
    final int width;
    ArrayList< ArrayList< Cell > > cells;
    public Cell selectedCell;
    /** Constructeur */
    public Grid(int height, int width){
        /* initialisation of  variables height et width */
        this.height = height;
        this.width = width;
        /* Creation of a list for the cells*/
        this.cells = new ArrayList<>(height);
        for (int i = 0; i < height; i++){
            cells.add(new ArrayList<>());
            for (int j = 0; j < width; j++){
                Cell c = new Cell(i, j);
                cells.get(i).add(c);
            }
        }
    }


    /** Method to get neighbors of the Cell 
     * TODO */
    /*
    private ArrayList<Cell> getNeighbors(Cell target) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        neighbors.add(cells.get(target.posX).get(target.posY + 1));
        neighbors.add(cells.get(target.posX).get(target.posY - 1));
        if (target.posX %2 == 0) {
            neighbors.add(cells.get(target.posX +1).get(target.posY + 1));
            neighbors.add(cells.get(target.posX +1).get(target.posY));
            neighbors.add(cells.get(target.posX -1).get(target.posY + 1));
            neighbors.add(cells.get(target.posX -1).get(target.posY));
        } else {
            neighbors.add(cells.get(target.posX +1).get(target.posY - 1));
            neighbors.add(cells.get(target.posX +1).get(target.posY));
            neighbors.add(cells.get(target.posX -1).get(target.posY - 1));
            neighbors.add(cells.get(target.posX -1).get(target.posY));
        }
        return neighbors;
    }
    */

    /*
    public Cell getCenter() {
        return cells.get(height/2).get(width/2);
    }
    */


    /** Method setSelectedCell
     * set the selected cell in the grid
     * @param c, a cell
     */
    public void setSelectedCell(Cell c){
        selectedCell = c;
        System.out.println(c.getCellContent());
    }

    /** Method getClosestCell
     * return the closest cell from a position in the window, generally used with coordinates from a click
     * @param x, int
     * @param y, int
     * @return Cell
     */
    public Cell getClosestCell(int x, int y){
        /* defines the minimal distance from a cell depending on a circle that is the size of a Cell and the shift plus a little margin */
        double dist = Model.cellSize/2. + View.shift + 10;
        /* initialize closestCell for execution sake */
        Cell closestCell = selectedCell;
        /* we go through all cells to find the closest one, can be upgraded to go through only the one close to the coordinates */
        for (ArrayList<Cell> cellList : cells) {
            for (Cell cell : cellList) {
                /* calculates the distance between the coordinates and the current cell */
                double comp = Math.sqrt(Math.pow(x-cell.posCenterX, 2) + Math.pow(y-cell.posCenterY, 2));
                /* if it is inferior to the current minima */
                if ( comp < dist){
                    /* we replace the minima with a new one and the closest cell with the current cell */
                    dist = comp;
                    closestCell = cell;
                }
            }
        }
        /* return the closest cell found*/
        return closestCell;
    }
}

class Cell {
    public int posX;
    public int posY;
    public int posCenterX;    public int posCenterY;
    public CellContent content;
    public boolean isTargeted = false;
    public boolean isSelected = false;
    /** constructor */
    public Cell(int x, int y) {
        this.posX = x;
        this.posY = y;
        if (posY%2 == 0){
            this.posCenterX = (this.posX - 1) * Model.cellSize-this.posX *12 + this.posX * View.shift + Model.cellSize/2;
        } else {
            this.posCenterX = (this.posX - 1) * Model.cellSize-this.posX *12 + this.posX * View.shift + Model.cellSize/2 - (6-View.shift/2) + Model.cellSize/2;
        }
        this.posCenterY = (3 * Model.cellSize / 4) * (this.posY - 1) + this.posY * View.shift + Model.cellSize/2;


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
    public  Character getCellCharacterContent(){
        if (content.getClass() == Character.class)
            return (Character) content;
        else
            return null;
    }
}

class CellContent {
    public Cell contentCellPosition;
    public float contentPosX, contentPosY;
    public Image sprite;
    Move move;
    /** construtor */
    public CellContent(Cell c, Image s){
        this.contentCellPosition = c;
        this.contentPosX = c.posCenterX - Model.cellSize/2;
        this.contentPosY = c.posCenterY - Model.cellSize/2;
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


class Character extends CellContent{
    double speed;

    /** constructor */
    public Character(Cell c, Image s, double moveSpeed) {
        super(c, s);
        speed = moveSpeed;
        this.move = moveCharModel();
    }

    /** Method to move the character
     * params int xTarget, wanted x position
     * int yTarget, wanted y position
     * */
    public Move moveCharModel() {
        return new Move (this, super.getContentCellPosition());
    }
}

class Move extends TimerTask {
    Character movingChar;
    Cell initialPos, finalPos;
    double coefDirX;
    double coefDirY;
    boolean isMoving = false;
    public Move(Character c, Cell start){

        super();
        this.initialPos = start;
        this.finalPos = start;
        this.movingChar = c;
    }

    public void setDestination(Cell end) {
        this.finalPos = end;
        this.coefDirX = (finalPos.posCenterX - initialPos.posCenterX)/100.;
        this.coefDirY = (finalPos.posCenterY - initialPos.posCenterY)/100.;
        end.setTargeted(movingChar);
        isMoving = true;
    }
    @Override
    public void run()  {
        if (isMoving && (Math.abs(finalPos.posCenterX - movingChar.contentPosX - Model.cellSize/2) >= 5) || Math.abs(finalPos.posCenterY - movingChar.contentPosY - Model.cellSize/2) >= 5){
            /*
            if (coefDirX > 0 && movingChar.contentPosX + movingChar.speed * coefDirX < finalPos.posCenterX - Model.cellSize/2. || coefDirX < 0 && movingChar.contentPosX + movingChar.speed * coefDirX > finalPos.posCenterX - Model.cellSize/2.) {
                movingChar.contentPosX += movingChar.speed * coefDirX;
            } else {
                movingChar.contentPosX = finalPos.posCenterX - Model.cellSize/2;
            }
            if (coefDirY > 0 && movingChar.contentPosY + movingChar.speed* coefDirY < finalPos.posCenterY - Model.cellSize/2. || coefDirY < 0 && movingChar.contentPosY + movingChar.speed* coefDirY > finalPos.posCenterY - Model.cellSize/2.) {
                movingChar.contentPosY += movingChar.speed * coefDirY;
            } else {
                movingChar.contentPosY = finalPos.posCenterY - Model.cellSize/2;
                movingChar.setContentCellPosition(finalPos);
                initialPos.setCellContent(null);
                finalPos.setCellContent(movingChar);
            }

             */
            if (Math.abs(finalPos.posCenterX - movingChar.contentPosX - Model.cellSize/2) >= 5) {
                movingChar.contentPosX += movingChar.speed * coefDirX;

            }
            if (Math.abs(finalPos.posCenterY - movingChar.contentPosY - Model.cellSize/2) >= 5) {
                movingChar.contentPosY += movingChar.speed * coefDirY;
            }
            /*
            System.out.print(coefDirX);
            System.out.print(" ");
            System.out.print(coefDirY);
            System.out.print(" ");
            System.out.print(movingChar.contentPosX);
            System.out.print(" ");
            System.out.print(movingChar.contentPosY);
            System.out.print(" ");
            System.out.print(initialPos.posCenterX);
            System.out.print(" ");
            System.out.print(initialPos.posCenterY);
            System.out.print(" ");
            System.out.print(finalPos.posCenterX);
            System.out.print(" ");
            System.out.print(finalPos.posCenterY);
            System.out.println(" ");

             */
        }
        else if (isMoving) {
            movingChar.setContentCellPosition(finalPos);
            initialPos.setCellContent(null);
            finalPos.setCellContent(movingChar);
            initialPos = finalPos;
            isMoving = false;
            finalPos.isTargeted = false;
        }
    }

}
