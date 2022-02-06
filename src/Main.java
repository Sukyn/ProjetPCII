import javax.swing.JFrame;
import java.io.IOException;

public class Main {
    public static void main(String [] args) throws IOException {
        Model model = new Model();
        View view = new View(model);
        Controler controler = new Controler(view);


        //testing
//        Shrek shrek = new Shrek(0, 0);
//        System.out.println(shrek.xPos);
//        shrek.moveCharModel(10, 10);
//        System.out.println(shrek.yPos);



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

