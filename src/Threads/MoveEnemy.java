package Threads;


import CellClasses.Cell;
import CellClasses.CellObstacle;
import MVC.Model;

import java.util.TimerTask;

public class MoveEnemy extends TimerTask {
    CharacterClasses.Character movingChar;
    Cell initialPos, finalPos, currentTarget;
    Model model;
    public int arrowPos1X, arrowPos1Y, arrowPos2X, arrowPos2Y;
    public MoveEnemy(CharacterClasses.Character c, Cell start){
        super();
        this.initialPos = start;
        this.finalPos = start;
        this.movingChar = c;
        this.model = c.model;
        this.currentTarget = start;
    }

    public void setDestination(Cell end) {
        this.finalPos = end;
    }

    @Override
    public void run()  {
        if (initialPos == finalPos) {
            model.loseGlobalHP(movingChar.getStrength());
            finalPos.setCellCharacterContent(null);
            movingChar.timer.cancel();
        } else {
            if (currentTarget.getCellCharacterContent() != null) {
                if (currentTarget.getCellCharacterContent().type != "enemy") {
                    currentTarget.getCellCharacterContent().loseHP(movingChar.strength);
                }
            } else {
                movingChar.setContentCellPosition(currentTarget);
                movingChar.contentPosX = currentTarget.posCenterX - model.cellSize/2 ;
                movingChar.contentPosY = currentTarget.posCenterY - model.cellSize/2;
                initialPos.setCellCharacterContent(null);
                currentTarget.setCellCharacterContent(movingChar);
                initialPos = currentTarget;
            }
        }

        Cell previous = initialPos;
        double max = Double.MAX_VALUE;
        for (Cell ngh : model.grid.getNeighbors(initialPos)) {
            if (ngh != previous) {
                double comp = Math.sqrt(Math.pow(finalPos.posCenterX - ngh.posCenterX, 2) + Math.pow(finalPos.posCenterY - ngh.posCenterY, 2));

                if ( comp < max && (ngh.getCellContent() == null
                        || ngh.getCellContent().getClass() != CellObstacle.class
                        || movingChar.isFlying)) {
                    max = comp;
                    currentTarget = ngh;
                }
            }
        }
        arrowPos1X = initialPos.posCenterX;
        arrowPos1Y = initialPos.posCenterY;
        arrowPos2Y = currentTarget.posCenterY;
        arrowPos2X = currentTarget.posCenterX;
    }
}

