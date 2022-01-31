import java.util.ArrayList;

public class Model {

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
            cells.add( new ArrayList<Cell>());

            for (int j = 0; j < width; j++){
                cells.get(i).add(new Cell(i, j));
            }
        }
    }

    /** Method to get neighbors of the Cell 
     * TODO */
    private ArrayList<Cell> getNeighbors(Cell target) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
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
    Cell(int x, int y) {
        this.pos_x = x;
        this.pos_y = y;
    }
}

class Character {

}
