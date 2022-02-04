import javax.swing.JPanel;
import java.awt.*;

public class View extends JPanel{
    public static final int HEIGHT = 200;
    public static final int LENGHT = 400;

    Model model;
    public View(Model m) {
        model = m;
        setPreferredSize(new Dimension(LENGHT, HEIGHT));
    }
}
