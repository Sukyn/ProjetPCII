package Threads;

import CellClasses.Cell;
import CellClasses.CellObstacle;
import MVC.Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

public class SpawnEnemy extends TimerTask {
    CellClasses.Grid grid;
    MVC.Model model;
    public SpawnEnemy(MVC.Model model){
        super();
        this.model = model;
        this.grid = model.grid;
    }

    @Override
    public void run()  {

        int posX = (int)(Math.random() * (grid.width-1));
        int posY = (int)(Math.random() * (grid.height-1));
        Cell cell = grid.cells.get(posX).get(posY);
        if (cell.getCellContent() == null && cell.getCellCharacterContent() == null && !cell.isTargeted) {
            Image image = null;
            try {
                image = ImageIO.read(new File("Assets/Slime.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            int moveSpeed = 0;
            int health = 10;
            int strength = 10;
            boolean flying = false;
            String name = "Blob";
            int maxF = 0;
            int maxI = 0;
            int maxP = 0;
            model.addChar(cell, image, moveSpeed, health, strength, flying, name, maxF, maxI, maxP);
        }
    }
}


