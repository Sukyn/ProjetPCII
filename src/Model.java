import java.util.ArrayList;

public class Model {
    public int gridHeight = View.HEIGHT;
    public int gridWidth = View.WIDTH;
    public int cellSize = 100;

    public Model() {
        Grid grid = new Grid(gridHeight / cellSize, gridWidth / cellSize);

    }

}


class Grid {
    final int radius = 7;
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
            }
        }
    }

    /** Method to get neighbors of the Cell 
     * TODO */
    private ArrayList<Cell> getNeighbors(Cell target) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        neighbors.add(cells.get(target.pos_x).get(target.pos_y + 1));
        neighbors.add(cells.get(target.pos_x).get(target.pos_y - 1));
        if (target.pos_x%2 == 0) {
            neighbors.add(cells.get(target.pos_x+1).get(target.pos_y + 1));
            neighbors.add(cells.get(target.pos_x+1).get(target.pos_y));
            neighbors.add(cells.get(target.pos_x-1).get(target.pos_y + 1));
            neighbors.add(cells.get(target.pos_x-1).get(target.pos_y));
        } else {
            neighbors.add(cells.get(target.pos_x+1).get(target.pos_y - 1));
            neighbors.add(cells.get(target.pos_x+1).get(target.pos_y));
            neighbors.add(cells.get(target.pos_x-1).get(target.pos_y - 1));
            neighbors.add(cells.get(target.pos_x-1).get(target.pos_y));
        }
        return neighbors;
    }

    public Cell getCenter() {
        return cells.get(height/2).get(width/2);
    }
}

class Cell {
    public int pos_x;
    public int pos_y;
    public Cell(int x, int y) {
        this.pos_x = x;
        this.pos_y = y;
    }
}

class Character {
    public int xPos, yPos;
    public Character(int x, int y) {
        xPos = x;
        yPos = y;
    }

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

