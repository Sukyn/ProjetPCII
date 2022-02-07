import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class View extends JPanel {
    public static final int HEIGHT = 700;
    public static final int WIDTH = 1000;
    private Image hexagonImage;
    private Image selectedHexagonImage;
    private Image wallpaper;
    Model model;

    public View(Model m) throws IOException {
        this.model = m;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        BufferedImage image = ImageIO.read(new File("Assets\\hexa.png"));

        this.wallpaper = ImageIO.read(new File("Assets\\Swomp2.jpg"));
        this.hexagonImage = image.getScaledInstance(m.cellSize, m.cellSize, Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("Assets\\Shrek.png"));
        this.selectedHexagonImage = image.getScaledInstance(m.cellSize, m.cellSize, Image.SCALE_SMOOTH);
    }

    public void paint(Graphics g) {
        int shift = 10;
        g.drawImage(wallpaper, 0, 0, WIDTH, HEIGHT, this);
        for (ArrayList<Cell> hexagonList : model.grid.cells) {
            for (Cell hexagon : hexagonList) {
                Image image = hexagonImage;

                if (hexagon.isSelected) {
                    image = selectedHexagonImage;
                }
                if (hexagon.posY % 2 == 0) {
                    g.drawImage(image, (hexagon.posX - 1) * model.cellSize-hexagon.posX *12+hexagon.posX *shift, (3 * model.cellSize / 4) * (hexagon.posY - 1)+hexagon.posY *shift, this);
                } else {
                    g.drawImage(image, (hexagon.posX - 1) * model.cellSize-hexagon.posX *12+hexagon.posX *shift + model.cellSize / 2-(6-shift/2), (3 * model.cellSize / 4) * (hexagon.posY - 1)+hexagon.posY *shift, this);
                }

            }
        }
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