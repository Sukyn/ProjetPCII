package MVC;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import CellClasses.*;
import CharacterClasses.*;

public class View extends JPanel {
    public static final int HEIGHT = 700;
    public static final int WIDTH = 1000;
    public static final int shift = 10;
    private final Image hexagonImage;
    private final Image selectedHexagonImage;
    private final Image wallpaper;
    Model model;

    /** constructor */
    public View(Model m) throws IOException {
        /* assigns model value from parameter */
        this.model = m;
        /* set window default size*/
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        /* opens the different images we will use */
        this.wallpaper = ImageIO.read(new File("Assets/Swomp2.jpg"));
        BufferedImage image = ImageIO.read(new File("Assets/hexa.png"));
        this.hexagonImage = image.getScaledInstance(Model.cellSize, Model.cellSize, Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("Assets/hexaSelected.png"));
        this.selectedHexagonImage = image.getScaledInstance(Model.cellSize, Model.cellSize, Image.SCALE_SMOOTH);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                revalidate();
                repaint();
            }
        }).start();
    }

    /** Method paint
     * draw all the content in the window
     * @param g, Graphics
     */
    public void paintComponent(Graphics g) {
        g.drawImage(wallpaper, 0, 0, WIDTH, HEIGHT, this);
        drawGrid(g);
    }

    /** Method drawHexagon
     * draw an hexagon in the window depending on a cell and an image
     * @param g, Graphics
     * @param hexagon, Cell
     * @param image, Image
     */
    private void drawHexagon(Graphics g, Cell hexagon, Image image) {
        if (hexagon.posY % 2 == 0) {
            g.drawImage(image, (hexagon.posX - 1) * Model.cellSize-hexagon.posX *12+hexagon.posX *shift, (3 * Model.cellSize / 4) * (hexagon.posY - 1)+hexagon.posY *shift, this);
        } else {
            g.drawImage(image, (hexagon.posX - 1) * Model.cellSize-hexagon.posX *12+hexagon.posX *shift + Model.cellSize / 2-(6-shift/2), (3 * Model.cellSize / 4) * (hexagon.posY - 1)+hexagon.posY *shift, this);
        }
    }

    /** Method drawGrid
     * Draws the grid in the window
     * @param g, Graphics
     */
    private void drawGrid(Graphics g){
        /*we go through the grid and call drawHexagon to draw the different cells*/
        for (ArrayList<Cell> hexagonList : model.grid.cells) {
            for (Cell hexagon : hexagonList) {
                drawHexagon(g, hexagon, hexagonImage);
            }
        }
        drawContent(g, model.shrek);
        drawContent(g, model.fiona);
        drawContent(g, model.dragon);
        drawContent(g, model.donkey);
        for(CellContent item : model.items) {
            drawContent(g, item);
        }
        /* if there is a selected cell we draw the adequate sprite */
        try {
            drawHexagon(g, model.grid.selectedCell, selectedHexagonImage);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /** Method drawContent
     * draws a cell content depending on it's sprite
     * @param g : Graphics
     */

    private void drawContent(Graphics g, CellContent content){
        g.drawImage(content.getSprite(), (int)content.contentPosX, (int)content.contentPosY, Model.cellSize, Model.cellSize, this);
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