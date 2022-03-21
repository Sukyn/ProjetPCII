package MVC;

import MVC.Views.CellInfoView;
import MVC.Views.CharacterInfoView;
import MVC.Views.GameView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class View {
    public JFrame frame;
    public static final int HEIGHT = 700;
    public static final int WIDTH = 1000;
    public GameView gameView;
    public CellInfoView cellInfoView;
    public CharacterInfoView characterInfoView;
    Model model;

    /** constructor */
    public View(Model m) throws IOException {
        /* we get screen informations */
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

        /* Creating of a new JFrame, with default close operation set */
        this.frame = new JFrame("SHREX");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        /* on définit les contraintes pour le placement de la zone d'affichage et de commande */
        GridBagConstraints c = new GridBagConstraints();
        /* we place the component in the first line and column */
        c.gridx = 0;
        c.gridy = 0;
        /* Adding gameView to the JFrame */
        gameView = new GameView(m);
        frame.add(gameView, c);

        /* we place the component in the first line and second column */
        c.gridx = 1;
        c.gridy = 0;
        /* Adding cellInfoView to the JFrame */
        cellInfoView = new CellInfoView(m);
        frame.add(cellInfoView, c);

        /* we place the component in the second line and first column */
        c.gridx = 0;
        c.gridy = 1;
        /* the component will span across 2 columns */
        c.gridwidth = 2;
        /* Adding characterInfoView to the JFrame */
        characterInfoView = new CharacterInfoView(m);
        frame.add(characterInfoView, c);

        /* Displaying view */
        frame.pack();
        /* we place the window in the center of the screen */
        frame.setLocation((device.getDisplayMode().getWidth() - frame.getWidth())/2, (device.getDisplayMode().getHeight() - frame.getHeight())/2);

        frame.setVisible(true);
        /* assigns model value from parameter */
        this.model = m;
        /* set window default size*/
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.revalidate();
                frame.repaint();
            }
        }).start();
    }
}




/*
 * import javax.swing.*;
 * import java.awt.*;
 * import java.awt.image.BufferedImage;
 * import java.io.File;
 * import java.io.IOException;
 * import javax.imageio.ImageIO;
 *
 * public class View extends JPanel{
 *     public static final int HEIGHT = 1000;
 *     public static final int LENGHT = 1000;
 *     public static JFrame shrex;
 *
 *     Model model;
 *     public View(Model m) throws IOException {
 *
 *
 *         /* Creating of a new JFrame, with default close operation set *
 *shrex=new JFrame("SHREX");
 *shrex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 *
 *
 *model=m;
 *setPreferredSize(new Dimension(LENGHT,HEIGHT));
 *         // width of the image
 *int width=400;
 *
 *         // height of the image
 *int height=400;
 *
 *         // For storing image in RAM
 *BufferedImage image=null;
 *
 *         // READ IMAGE
 *try{
 *File input_file=new File(
 *"C:/Users/Mayeul/Pictures/Inspiration_Quotes/Party_Shit.PNG");
 *
 *             // image file path create an object of
 *             // BufferedImage type and pass as parameter the
 *             // width,  height and image int
 *             // type. TYPE_INT_ARGB means that we are
 *             // representing the Alpha , Red, Green and Blue
 *             // component of the image pixel using 8 bit
 *             // integer value.
 *
 *image=ImageIO.read(input_file);
 *
 *             // Reading input file
 *             //image = ImageIO.read(input_file);
 *
 *System.out.println("Reading complete.");
 *}
 *catch(IOException e){
 *System.out.println("Error: "+e);
 *}
 *
 *
 *File input_file_2=new File("C:\\Users\\Mayeul\\Pictures\\Saved Pictures\\01-15-57-R-13795190-1561298060-2756.jpeg.jpg");
 *
 *
 *Image dimg=image.getScaledInstance(100,100,Image.SCALE_SMOOTH);
 *
 *ImageIcon imageIcon=new ImageIcon(dimg);
 *         //JFrame jFrame = new JFrame();
 *
 *shrex.setLayout(new FlowLayout());
 *
 *         //shrex.setSize(100, 100);
 *JLabel jLabel=new JLabel();
 *
 *jLabel.setIcon(imageIcon);
 *shrex.add(jLabel);
 *shrex.setVisible(true);
 *
 *shrex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 *}
 *}
 */