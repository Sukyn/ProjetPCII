package MVC;

import CellClasses.*;
import CharacterClasses.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Model {
    public static int cellSize = 100;
    public Grid grid;
    public Fiona fiona;
    public Shrek shrek;
    public Donkey donkey;
    public Dragon dragon;
    public House house;
    public int globalHP = 100;
    public int globalFlower;
    public int globalIron;
    public int globalPowder;
    public int globalGold;
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
        addObstacle("Dessins/Stem1.png",height/2, width/2-1);
        addObstacle("Assets/Buche.png",height/2+2, width/2+1);
        addObstacle("Assets/Buche.png",height/2-2, width/2+1);
        addObstacle("Assets/Tree.png",height/2-3, width/2+1);
        addRessource("Dessins/Boulder1.png",height/2, width/2+3, RessourceType.iron, 10);
        addObstacle("Assets/Tree.png",height/2-2, width/2+3);
        addObstacle("Assets/Rock.png",height/2+4, width/2+1);


    }

    public void loseGlobalHP(int i) {
        this.globalHP -= i;
    }
    public void addChar(Cell c, BufferedImage s, double moveSpeed, int health, int strength, boolean flying, String name, int maxF, int maxI, int maxP) {
        CharacterClasses.Character chara = new CharacterClasses.Character(this, c, s, moveSpeed, health, strength, flying, name, maxF, maxI, maxP, "enemy");
        c.setCellCharacterContent(chara);
        chara.moveEnemy = chara.moveEnemyModel();
        chara.moveEnemy.setDestination(grid.cells.get(grid.height / 2).get(grid.width / 2));
        chara.addTimerEnemy(0, 200);
    }
    private void addSpecialChar(String specialChar) {
        BufferedImage image = null;
        switch (specialChar) {
            case "CharacterClasses.Fiona" -> {
                Cell cell = grid.cells.get(grid.height / 2).get(grid.width / 2 + 1);
                try {
                    image = ImageIO.read(new File("Assets/Fiona.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Fiona chara = new Fiona(this, cell, image, 10, 10, 15);
                cell.setCellCharacterContent(chara);
                this.fiona = chara;
            }
            case "CharacterClasses.House" -> {
                Cell cell = grid.cells.get(grid.height / 2).get(grid.width / 2);
                try {
                    image = ImageIO.read(new File("Assets/House.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                House chara = new House(this, cell, image, 0, 1000, 1);
                cell.setCellCharacterContent(chara);
                this.house = chara;
            }
            case "CharacterClasses.Shrek" -> {
                Cell cell = grid.cells.get(grid.height / 2 -1).get(grid.width / 2);
                try {
                    image = ImageIO.read(new File("Assets/Shrek.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Shrek chara = new Shrek(this, cell, image, 6, 50, 20);
                cell.setCellCharacterContent(chara);
                this.shrek = chara;
            }
            case "CharacterClasses.Donkey" -> {
                Cell cell = grid.cells.get(grid.height / 2 + 1).get(grid.width / 2 + 1);
                try {
                    image = ImageIO.read(new File("Assets/Donkey.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Donkey chara = new Donkey(this, cell, image, 6, 50, 20);
                cell.setCellCharacterContent(chara);
                this.donkey = chara;
            }
            case "CharacterClasses.Dragon" -> {
                Cell cell = grid.cells.get(grid.height / 2 + 2).get(grid.width / 2 + 2);
                try {
                    image = ImageIO.read(new File("Assets/Dragon.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Dragon chara = new Dragon(this, cell, image, 6, 50, 20);
                cell.setCellCharacterContent(chara);
                this.dragon = chara;
            }
        }

    }

    private void addObstacle(String file, int posX, int posY) {
        BufferedImage image = null;
        Cell cell = grid.cells.get(posX).get(posY);
        try {
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CellContent item = new CellObstacle(cell, image);
        cell.setCellContent(item);
        items.add(item);
    }

    private void addRessource(String file, int posX, int posY, RessourceType r, int max) {
        BufferedImage image = null;
        Cell cell = grid.cells.get(posX).get(posY);
        try {
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CellContent item = new CellRessource(r, cell, image, max);
        cell.setCellContent(item);
        items.add(item);
    }

    public int getGlobalFlower(){ return this.globalFlower; }
    public int getGlobalIron(){ return this.globalIron; }
    public int getGlobalPowder(){ return this.globalPowder; }
    public void setGlobalFlower(int x){ this.globalFlower = x; }
    public void setGlobalIron(int x){ this.globalIron = x; }
    public void setGlobalPowder(int x){ this.globalPowder = x; }

    public int getGlobalGold(){ return this.globalGold; }
    public void addGlobalGold(int x){ this.globalGold += x; }
}


