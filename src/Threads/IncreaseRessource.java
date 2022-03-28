package Threads;

import CellClasses.CellRessource;

import java.util.TimerTask;

public class IncreaseRessource extends TimerTask {
    CellRessource ressource;
    int cpt = 0;
    int max;
    public IncreaseRessource(CellRessource r){
        super();
        this.ressource = r;
    }
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        ressource.addRessource(1);

    }
}
