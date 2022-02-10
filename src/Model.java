import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Model {
    public static int cellSize = 100;
    public Grid grid;
    public Model(int height, int width) {
         this.grid = new Grid(height, width);
    }

}


class Grid {
    final int height;
    final int width;
    ArrayList< ArrayList< Cell > > cells;
    public Cell selectedCell;
    /** Constructeur */
    public Grid(int height, int width){
        /* on initialise les variables height et width */
        this.height = height;
        this.width = width;
        /* Creation of a list for the cells*/
        this.cells = new ArrayList<>(height);
        for (int i = 0; i < height; i++){
            cells.add( new ArrayList<>());

            for (int j = 0; j < width; j++){
                Cell c = new Cell(i, j);
                cells.get(i).add(c);
                if (i == height/2 && j == width/2) {
                    c.isSelected = true;
                    selectedCell = c;
                    Image image = null;
                    try {
                        image = ImageIO.read(new File("Assets/Shrek.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    c.setCellContent(new Character(c, image, 0.01));
                }
            }
        }
    }

    /** Method to get neighbors of the Cell 
     * TODO */
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

    public Cell getCenter() {
        return cells.get(height/2).get(width/2);
    }

    public void setSelectedCell(Cell c){
        selectedCell = c;
        System.out.println(c.getCellContent());
    }

    public Cell getClosestCell(int x, int y){
        double dist = Model.cellSize/2. + View.shift + 10;
        Cell closestCell = selectedCell;
        for (ArrayList<Cell> cellList : cells) {
            for (Cell cell : cellList) {
                double comp = Math.sqrt(Math.pow(x-cell.posCenterX, 2) + Math.pow(y-cell.posCenterY, 2));
                if ( comp < dist){
                    dist = comp;
                    closestCell = cell;
                }
            }
        }
        return closestCell;
    }
}

class Cell {
    public int posX;
    public int posY;
    public int posCenterX;    public int posCenterY;
    public CellContent content;
    public boolean isSelected = false;
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

    public void setCellContent(CellContent newContent){
        content = newContent;
    }

    public  CellContent getCellContent(){
        return  content;
    }

    public  Character getCellCharacterContent(){
        if (content.getClass() == Character.class)
            return (Character) content;
        else
            return null;
    }
}

class CellContent {
    public Cell contentCellPosition;
    public int contentPosX, contentPosY;
    public Image sprite;
    public CellContent(Cell c, Image s){
        contentCellPosition = c;
        contentPosX = c.posCenterX - Model.cellSize/2;
        contentPosY = c.posCenterY - Model.cellSize/2;
        sprite = s;
    }

    public void setContentCellPosition(Cell c){
        contentCellPosition = c;
    }

    public Cell getContentCellPosition(){
        return contentCellPosition;
    }

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
    }

    /** Method to move the character
     * params int xTarget, wanted x position
     * int yTarget, wanted y position
     * */
    public void moveCharModel(Cell c) {
        //new Move (this, super.getContentCellPosition(), c).start();
        super.getContentCellPosition().setCellContent(null);
        // mettre en commentaire les deux prochaines lignes si on remet le thread
        super.setContentCellPosition(c);
        super.getContentCellPosition().setCellContent(this);
    }
}

class Move extends Thread{
    Character movingChar;
    Cell initialPos, finalPos;
    double coefDirX;
    double coefDirY;
    public Move(Character c, Cell start, Cell end){
        super();
        this.initialPos = start;
        this.finalPos = end;
        this.movingChar = c;
        this.coefDirX = (finalPos.posCenterX - initialPos.posCenterX)/100.;
        this.coefDirY = (finalPos.posCenterY - initialPos.posCenterY)/100.;
    }

    @Override
    public void run(){
        while (movingChar.contentPosX != finalPos.posCenterX - Model.cellSize/2 && movingChar.contentPosY != finalPos.posCenterY - Model.cellSize/2){
            if (coefDirX > 0 && movingChar.contentPosX + movingChar.speed * coefDirX < finalPos.posCenterX - Model.cellSize/2 || coefDirX < 0 && movingChar.contentPosX + movingChar.speed * coefDirX > finalPos.posCenterX - Model.cellSize/2) {
                movingChar.contentPosX += movingChar.speed * coefDirX;
            } else {
                movingChar.contentPosX = finalPos.posCenterX - Model.cellSize/2;
            }
            if (coefDirY > 0 && movingChar.contentPosY + movingChar.speed* coefDirY < finalPos.posCenterY - Model.cellSize/2 || coefDirY < 0 && movingChar.contentPosY + movingChar.speed* coefDirY > finalPos.posCenterY - Model.cellSize/2) {
                movingChar.contentPosY += movingChar.speed * coefDirY;
            } else {
                movingChar.contentPosY = finalPos.posCenterY - Model.cellSize/2;
            }
        }
        movingChar.setContentCellPosition(finalPos);
    }
}
/*
class Shrek extends Character {

}
*/
