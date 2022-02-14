import java.util.TimerTask;

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
        double norme = Math.sqrt(coefDirX*coefDirX + coefDirY*coefDirY);
        this.coefDirX = coefDirX/norme;
        this.coefDirY = coefDirY/norme;
        end.setTargeted(movingChar);
        isMoving = true;
    }
    @Override
    public void run()  {
        if (isMoving && (coefDirX > 0 && finalPos.posCenterX > movingChar.contentPosX + Model.cellSize/2)
                || (coefDirX < 0 && finalPos.posCenterX < movingChar.contentPosX + Model.cellSize/2)
                || (coefDirY > 0 && finalPos.posCenterY > movingChar.contentPosY + Model.cellSize/2)
                || (coefDirY < 0 && finalPos.posCenterY < movingChar.contentPosY + Model.cellSize/2)){
            if ((coefDirX > 0 && finalPos.posCenterX > movingChar.contentPosX + Model.cellSize/2) || (coefDirX < 0 && finalPos.posCenterX < movingChar.contentPosX + Model.cellSize/2))
                movingChar.contentPosX += movingChar.speed * coefDirX;
            if ((coefDirY > 0 && finalPos.posCenterY > movingChar.contentPosY + Model.cellSize/2) || (coefDirY < 0 && finalPos.posCenterY < movingChar.contentPosY + Model.cellSize/2))
                movingChar.contentPosY += movingChar.speed * coefDirY;
        }
        else if (isMoving) {
            movingChar.setContentCellPosition(finalPos);
            initialPos.setCellContent(null);
            finalPos.setCellContent(movingChar);
            initialPos = finalPos;
            isMoving = false;
            finalPos.isTargeted = false;
            movingChar.timer.cancel();
        }
    }
}

