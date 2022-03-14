package MVC;

import java.io.IOException;


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
    }
}

