import java.util.ArrayList;

public class Model {
    public static int cellSize = 100;
    public Grid grid;
    public Model(int x, int y) {
         this.grid = new Grid(x, y);
    }

}


class Grid {
    final int height;
    final int width;
    ArrayList< ArrayList< Cell > > cells;

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
                cells.get(i).add(new Cell(i, j));
                if (i == height/2 && j == width/2) {
                    cells.get(i).get(j).isSelected = true;
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
}

class Cell {
    public int posX;
    public int posY;
    public boolean isSelected = false;
    public Cell(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
}

class Character {
    public int xPos, yPos;
    /** constructor */
    public Character(int x, int y) {
        xPos = x;
        yPos = y;
    }

    /** A getter for x position */
    public int getPosX(){ return xPos; }
    /** A getter for y position */
    public int getPosY(){ return yPos; }

    /** Method to move the character
     * params int xTarget, wanted x position
     * int yTarget, wanted y position
     * */
    public void moveCharModel(int xTarget, int yTarget) {
        this.xPos = xTarget;
        this.yPos = yTarget;
    }


}


class Shrek extends Character {
    public Shrek(int x, int y) {
        super(x, y);
    }

}

