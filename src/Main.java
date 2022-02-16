import MVC.*;
import MVC.Views.View;

import javax.swing.JFrame;
import java.io.IOException;


public class Main {
    public static void main(String [] args) throws IOException {
        int width = View.HEIGHT*4/(Model.cellSize*3)+1;
        int height = View.WIDTH/ Model.cellSize+1;
        Model model = new Model(height,width);
        View view = new View(model);
        new Controller(view, model);
    }

}

