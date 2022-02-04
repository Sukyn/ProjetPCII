import javax.swing.JFrame;

public class Main {
    public static void main(String [] args) {
        Model model = new Model();
        View view = new View(model);
        Controler controler = new Controler(view);


        /* Creting of a new JFrame, with default close operation set */
        JFrame shrex = new JFrame("SHREX");
        shrex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Adding view to the JFrame */
        shrex.add(view);

        /* Displaying view */
        shrex.pack();
        shrex.setVisible(true);
    }

}

