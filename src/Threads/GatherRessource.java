package Threads;

import CharacterClasses.Character;

import java.util.TimerTask;

public class GatherRessource extends TimerTask {
    Character chara;
    int cpt = 0;
    int max;
    public GatherRessource(Character c){
        super();
        this.chara = c;
    }
    /**
     * The action to be performed by this timerMove task.
     */
    @Override
    public void run() {
        if (chara.move.isMoving){
            chara.timerGather.cancel();
        } else {
            chara.collect();
        }
    }
}
