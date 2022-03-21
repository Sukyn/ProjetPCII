package MVC;

import Threads.SpawnEnemy;

import java.io.IOException;
import java.util.Timer;


public class MainGame {
    static Model model;
    static View view;
    public static Controller controller;

    public static void main(String [] args) throws IOException {
        int width = View.HEIGHT*4/(Model.cellSize*3)+1;
        int height = View.WIDTH/ Model.cellSize+1;
        model = new Model(height,width);
        view = new View(model);
        controller = new Controller(view, model);

        Timer timer = new Timer();
        SpawnEnemy spawn = new SpawnEnemy(model);
        timer.schedule(spawn, 0, 5000);
    }
}

