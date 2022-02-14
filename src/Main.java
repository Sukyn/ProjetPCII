import javax.swing.JFrame;
import java.io.IOException;


public class Main {
    public static void main(String [] args) throws IOException {
        int width = View.HEIGHT*4/(Model.cellSize*3)+1;
        int height = View.WIDTH/Model.cellSize+1;
        Model model = new Model(height,width);
        View view = new View(model);
        new Controller(view, model);
        /* Creating of a new JFrame, with default close operation set */
        JFrame shrex = new JFrame("SHREX");
        shrex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Adding view to the JFrame */
        shrex.add(view);
        /* Displaying view */
        shrex.pack();
        shrex.setVisible(true);
    }

}

