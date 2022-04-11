package CellClasses;


import MVC.*;
import MVC.Views.*;
import java.util.ArrayList;


/**
 * Class Grid
 * Represents the grid on which the game is played
 * Contains Cells
 */
public class Grid {
    /* Declaration of variables for size and content */
    public final int height;
    public final int width;
    public ArrayList<ArrayList<Cell>> cells;
    public Cell selectedCell;


    /**
     * Class constructor of the class Grid
     * @param height : the desired height of the grid
     * @param width : the desired width of the grid
     */
    public Grid(int height, int width) {
        /* Initialization of  variables height et width */
        this.height = height;
        this.width = width;

        /* Creation of a list for the cells*/
        this.cells = new ArrayList<>(height);		// creation of the "external" list (the list of lists)
        for (int i = 0; i < height; i++) {
            cells.add(new ArrayList<>());			// creation of the "internal" lists (nested inside the external one)
            for (int j = 0; j < width; j++) {
                Cell c = new Cell(i, j);			// initialization of the cells
                cells.get(i).add(c);				// the cells are then added to the list
            }
        }
    }

    /**
     * Method getNeighbors
     * Returns the neighbors in the grid of a cell passed in parameter
     * @param target : the cell of which the neighbors are wanted
     * @return neighbors : a list a neighbors of the target cell
     */
    public ArrayList<Cell> getNeighbors(Cell target) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        /* If the cell is not close to an edge in posX, we had the above and or bellow cell */
        if (target.posX > 0) {
            neighbors.add(cells.get(target.posX - 1).get(target.posY));
        }
        if (target.posX < width) {
            neighbors.add(cells.get(target.posX + 1).get(target.posY));
        }

        /* Depending on the line, we had different cells to the list */
        if (target.posY %2 == 1) {
            /* If the cell is not close to an edge in posY, we had the left and or right cell */
            if (target.posY < height-2) {
                if (target.posX < width-2) {
                    neighbors.add(cells.get(target.posX + 1).get(target.posY + 1));
                }
                neighbors.add(cells.get(target.posX).get(target.posY + 1));
            }
            if (target.posX < width-1 && target.posX > 0) {
                neighbors.add(cells.get(target.posX + 1).get(target.posY - 1));
            }
            if (target.posX > 0) {
                neighbors.add(cells.get(target.posX).get(target.posY - 1));
            }
        } else {
            if (target.posY < height-1) {
                if (target.posX > 0) {
                    neighbors.add(cells.get(target.posX - 1).get(target.posY + 1));
                }
                neighbors.add(cells.get(target.posX).get(target.posY + 1));
            }
            if (target.posY > 0) {
                if (target.posX > 0) {
                    neighbors.add(cells.get(target.posX - 1).get(target.posY - 1));
                }
                neighbors.add(cells.get(target.posX).get(target.posY - 1));
            }
        }
        return neighbors;
    }

    /*
    public Cell getCenter() {
        return cells.get(height/2).get(width/2);
    }
    */


    /**
     * Method setSelectedCell
     * Sets the selected cell in the grid
     *     * @param c : a cell
     */
    public void setSelectedCell(Cell c) {
        selectedCell = c;
        /*
        System.out.println(c.getCellContent());
        System.out.println(c.getCellCharacterContent());
        */
    }

    /**
     * Method getSelectedCell
     * Returns the currently selected cell
     * @return selectedCell
     */
    public Cell getSelectedCell() {
        return selectedCell;
    }

    /**
     * Method getClosestCell
     * Returns the closest cell from a position in the window,
     * generally used with coordinates from a click
     * @param x : int
     * @param y : int
     * @return Cell
     */
    public Cell getClosestCell(int x, int y) {
        /* Defines the minimal distance from a cell depending on a circle that is the size of a Cell and the shift plus a little margin */
        double dist = Model.cellSize / 2. + GameView.shift + 10;
        /* Initializes closestCell for execution sake */
        Cell closestCell = selectedCell;
        /* We go through all cells to find the closest one, can be upgraded to go through only the one close to the coordinates */
        for (ArrayList<Cell> cellList : cells) {
            for (Cell cell : cellList) {
                /* Calculates the distance between the coordinates and the current cell */
                double comp = Math.sqrt(Math.pow(x - cell.posCenterX, 2) + Math.pow(y - cell.posCenterY, 2));
                /* If it is inferior to the current minima */
                if (comp < dist) {
                    /* We replace the minima with a new one and the closest cell with the current cell */
                    dist = comp;
                    closestCell = cell;
                }
            }
        }
        /* Returns the closest cell found*/
        return closestCell;
    }
}
