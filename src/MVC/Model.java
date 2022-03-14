package MVC;

import CharacterClasses.*;
import CellClasses.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Model {
    public static int cellSize = 100;
    public static Grid grid;
    public Fiona fiona;
    public Shrek shrek;
    public Donkey donkey;
    public Dragon dragon;
    public ArrayList<CellContent> items = new ArrayList<>();
    public Model(int height, int width) {
        grid = new Grid(height, width);

        Cell c = grid.cells.get(height/2).get(width/2);
        c.isSelected = true;
        grid.selectedCell = c;
        addSpecialChar("CharacterClasses.Shrek");
        addSpecialChar("CharacterClasses.Fiona");
        addSpecialChar("CharacterClasses.Donkey");
        addSpecialChar("CharacterClasses.Dragon");
        addItem("Dessins/Stem1.png",height/2, width/2-1);
        addItem("Assets/Buche.png",height/2+2, width/2+1);
        addItem("Assets/Buche.png",height/2-2, width/2+1);
        addItem("Assets/Tree.png",height/2-3, width/2+1);
        addItem("Dessins/Boulder1.png",height/2, width/2+3);
        addItem("Assets/Tree.png",height/2-2, width/2+3);
        addItem("Assets/Rock.png",height/2+4, width/2+1);


    }

    private void addSpecialChar(String specialChar) {
        switch (specialChar) {
            case "CharacterClasses.Fiona" -> {
                Image image = null;
                Cell cell = grid.cells.get(grid.height / 2).get(grid.width / 2 + 1);
                try {
                    image = ImageIO.read(new File("Assets/Fiona.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Fiona chara = new Fiona(this, cell, image, 10, 10, 15);
                cell.setCellContent(chara);
                this.fiona = chara;
            }
            case "CharacterClasses.Shrek" -> {
                Image image = null;
                Cell cell = grid.cells.get(grid.height / 2).get(grid.width / 2);
                try {
                    image = ImageIO.read(new File("Assets/Shrek.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Shrek chara = new Shrek(this, cell, image, 6, 50, 20);
                cell.setCellContent(chara);
                this.shrek = chara;
            }
            case "CharacterClasses.Donkey" -> {
                Image image = null;
                Cell cell = grid.cells.get(grid.height / 2 + 1).get(grid.width / 2 + 1);
                try {
                    image = ImageIO.read(new File("Assets/Donkey.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Donkey chara = new Donkey(this, cell, image, 6, 50, 20);
                cell.setCellContent(chara);
                this.donkey = chara;
            }
            case "CharacterClasses.Dragon" -> {
                Image image = null;
                Cell cell = grid.cells.get(grid.height / 2 + 2).get(grid.width / 2 + 2);
                try {
                    image = ImageIO.read(new File("Assets/Dragon.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Dragon chara = new Dragon(this, cell, image, 6, 50, 20);
                cell.setCellContent(chara);
                this.dragon = chara;
            }
        }

    }

    private void addItem(String file, int posX, int posY) {
        Image image = null;
        Cell cell = grid.cells.get(posX).get(posY);
        try {
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CellContent item = new CellContent(cell, image);
        cell.setCellContent(item);
        items.add(item);
    }
}


