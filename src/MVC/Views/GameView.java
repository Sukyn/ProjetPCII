package MVC.Views;

import CellClasses.Cell;
import CellClasses.CellContent;
import MVC.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameView extends JPanel {
    public static final int HEIGHT = 700;
    public static final int WIDTH = 1000;
    public static final int shift = 10;
    private final Image hexagonImage;
    private final Image selectedHexagonImage;
    private final Image wallpaper;
    Model model;

    /** constructor */
    public GameView(Model m) throws IOException {
        this.model = m;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        /* opens the different images we will use */
        this.wallpaper = ImageIO.read(new File("Assets/Swomp2.jpg"));
        BufferedImage image = ImageIO.read(new File("Assets/hexa.png"));
        this.hexagonImage = image.getScaledInstance(Model.cellSize, Model.cellSize, Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("Assets/hexaSelected.png"));
        this.selectedHexagonImage = image.getScaledInstance(Model.cellSize, Model.cellSize, Image.SCALE_SMOOTH);
        /* On utilise pour cela un objet de type RescaleOp, qui possède une méthode de filtrage */

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
        g.setColor(Color.white);
        g.fillRect(0,0,200,14);
        g.setColor(Color.BLACK);
        g.drawString("Flower:" + model.getGlobalFlower() + "   Iron:" + model.getGlobalIron() + "   Powder:" + model.getGlobalPowder() + "    Gold:"+model.getGlobalGold() , 2, 12);

    }

    /** Method drawHexagon
     * draw an hexagon in the window depending on a cell and an image
     * @param g, Graphics
     * @param hexagon, Cell
     * @param image, Image
     */
    private void drawHexagon(Graphics g, Cell hexagon, Image image) {
        if (hexagon.posY % 2 == 0) {
            g.drawImage(image, (hexagon.posX - 1) * Model.cellSize-hexagon.posX *12+hexagon.posX *shift, (3 * Model.cellSize / 4) * (hexagon.posY - 1)+hexagon.posY *shift/2  , this);
        } else {
            g.drawImage(image, (hexagon.posX - 1) * Model.cellSize-hexagon.posX *12+hexagon.posX *shift + Model.cellSize / 2-(6-shift/2), (3 * Model.cellSize / 4) * (hexagon.posY - 1)+hexagon.posY *shift/2, this);
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
        for (ArrayList<Cell> row : model.grid.cells) {
            for (Cell c : row) {
                if (c.getCellContent() != null) {
                    drawContent(g, c.getCellContent());
                }
                CharacterClasses.Character cellContent = c.getCellCharacterContent();
                if (cellContent != null) {
                    drawContent(g, cellContent);
                    if (cellContent.type.equals("enemy")) {
                        Threads.MoveEnemy enemy = cellContent.moveEnemy;
                        g.drawLine(enemy.arrowPos1X, enemy.arrowPos1Y,
                                enemy.arrowPos2X, enemy.arrowPos2Y);
                    }
                }
            }
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
