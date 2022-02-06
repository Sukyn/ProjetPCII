import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View extends JPanel {
    public static final int HEIGHT = 1000;
    public static final int WIDTH = 1000;
    public int hexLength = 100;

    Model model;

    public View(Model m) throws IOException {
        model = m;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        for (int i = 0; i < 10; i ++) {
            for (int j = 0; j < 10; j ++) {
                Hexagon hex = new Hexagon(hexLength * i, hexLength * j, hexLength);
                add(hex);
                //System.out.printf("cc%d\n", j + (10 * i));
            }
        }
        setLayout(new GridLayout(10, 10));
    }
}



class Hexagon extends JPanel {
    public int imgWidth, imgHeight;
    public float aspectRatio;
    public Hexagon(int x, int y, int length) throws IOException {
        //System.out.println("cc début");
        BufferedImage image = ImageIO.read(new File("Assets\\TOPS.jpg"));
        imgWidth = image.getWidth();
        imgHeight = image.getHeight();
        aspectRatio = (float)imgHeight / (float)imgWidth;

        Image resizedImage = image.getScaledInstance(length, (int) (aspectRatio * length), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(resizedImage);
        //System.out.println("cc1");
        JLabel label = new JLabel(icon);
        //System.out.println("cc2");
        add(label);
        //System.out.println("cc fin");
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