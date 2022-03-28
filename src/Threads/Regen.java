package Threads;

import CellClasses.Cell;
import MVC.Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

public class Regen extends TimerTask {
    CharacterClasses.Character character;
    int cpt = 0;
    int max;
    public Regen(CharacterClasses.Character c){
        super();
        this.character = c;
        this.max = c.getMaxHealth();
    }

    @Override
    public void run()  {
        this.cpt += 10;
        if (cpt >= max) {
            while (true) {
                int posX = (int) (Math.random() * 3);
                int posY = (int) (Math.random() * 3);
                Cell cell = character.model.grid.cells.get((character.model.grid.height / 2)+1-posX).get((character.model.grid.width / 2)+1-posY);
                if (cell.getCellContent() == null && cell.getCellCharacterContent() == null && !cell.isTargeted) {
                    character.contentCellPosition = cell;
                    character.contentPosX = cell.posCenterX - Model.cellSize / 2.;
                    character.contentPosY = cell.posCenterY - Model.cellSize / 2.;
                    cell.setCellCharacterContent(character);
                    character.isDead = false;
                    character.healHP(max);
                    character.move.isMoving = false;
                    super.cancel();
                    break;
                }

            }
        }
    }
}
