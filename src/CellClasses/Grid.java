package CellClasses;

import MVC.*;
import MVC.Views.*;

import java.util.ArrayList;

public class Grid {
    /* declaration of variables for size and content */
    public final int height;
    public final int width;
    public ArrayList<ArrayList<Cell>> cells;
    public Cell selectedCell;

    /**
     * Constructeur
     */
    public Grid(int height, int width) {
        /* initialisation of  variables height et width */
        this.height = height;
        this.width = width;
        /* Creation of a list for the cells*/
        this.cells = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                Cell c = new Cell(i, j);
                cells.get(i).add(c);
            }
        }
    }

    /** Method getNeighbors
     * return the neighbors in the grid of a cell passed in parameter
     * @param target, Cell
     * @return neighbors, a list a neighbors of target cell
     */
    public ArrayList<Cell> getNeighbors(Cell target) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        /* if the cell is not close to an edge in posX, we had the above and or bellow cell */
        if (target.posX > 0) {
            neighbors.add(cells.get(target.posX - 1).get(target.posY));
        }
        if (target.posX < width) {
            neighbors.add(cells.get(target.posX + 1).get(target.posY));
        }

        /* depending on the line, we had different cells to the list */
        if (target.posY %2 == 1) {
            /* if the cell is not close to an edge in posY, we had the left and or right cell */
            if (target.posY < height) {
                if (target.posX < width) {
                    neighbors.add(cells.get(target.posX + 1).get(target.posY + 1));
                }
                neighbors.add(cells.get(target.posX).get(target.posY + 1));
            }
            if (target.posX < width && target.posX > 0) {
                neighbors.add(cells.get(target.posX + 1).get(target.posY - 1));
            }
            if (target.posX > 0) {
                neighbors.add(cells.get(target.posX).get(target.posY - 1));
            }
        } else {
            if (target.posY < height) {
                neighbors.add(cells.get(target.posX - 1).get(target.posY + 1));
                neighbors.add(cells.get(target.posX).get(target.posY + 1));
            }
            if (target.posY > 0) {
                neighbors.add(cells.get(target.posX -1).get(target.posY - 1));
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
     * set the selected cell in the grid
     *
     * @param c, a cell
     */
    public void setSelectedCell(Cell c) {
        selectedCell = c;
        System.out.println(c.getCellContent());
        System.out.println(c.getCellCharacterContent());
    }

    /**
     * Method getClosestCell
     * return the closest cell from a position in the window,
     * generally used with coordinates from a click
     * @param x, int
     * @param y, int
     * @return Cell
     */
    public Cell getClosestCell(int x, int y) {
        /* defines the minimal distance from a cell depending on a circle that is the size of a Cell and the shift plus a little margin */
        double dist = Model.cellSize / 2. + GameView.shift + 10;
        /* initialize closestCell for execution sake */
        Cell closestCell = selectedCell;
        /* we go through all cells to find the closest one, can be upgraded to go through only the one close to the coordinates */
        for (ArrayList<Cell> cellList : cells) {
            for (Cell cell : cellList) {
                /* calculates the distance between the coordinates and the current cell */
                double comp = Math.sqrt(Math.pow(x - cell.posCenterX, 2) + Math.pow(y - cell.posCenterY, 2));
                /* if it is inferior to the current minima */
                if (comp < dist) {
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
