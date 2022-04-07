package MVC;

import CellClasses.*;
import CharacterClasses.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Model {
    ArrayList<Point> gridCoords;
    static ArrayList<Point> ressourcesCoords;
    ArrayList<Point> obstacleCoords;

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
        ressourcesCoords = new ArrayList<>();
        for (int i = 1; i < width; i++){
            switch (i){
                case 1, 8 -> {
                    for (int j = 3; j < 8; j++){
                        ressourcesCoords.add(new Point(j,i));
                    }
                }
                case 2-> {
                    for (int j = 3; j < 9; j++){
                        ressourcesCoords.add(new Point(j,i));
                    }
                }
                case 3, 7 -> {
                    for (int j = 2; j < 9; j++){
                        ressourcesCoords.add(new Point(j,i));
                    }
                }
                case 4, 6-> {
                    for (int j = 2; j < 10; j++){
                        if (j != 5 && j != 6)
                            ressourcesCoords.add(new Point(j,i));
                    }
                }
                case 5 -> {
                    for (int j = 1; j < 10; j++){
                        if (j != 4 && j != 5 && j != 6)
                            ressourcesCoords.add(new Point(j,i));
                    }
                }
            }

        }
        Cell c = grid.cells.get(height/2).get(width/2);
        c.isSelected = true;
        grid.selectedCell = c;
        addSpecialChar("CharacterClasses.Shrek");
        addSpecialChar("CharacterClasses.Fiona");
        addSpecialChar("CharacterClasses.House");
        addSpecialChar("CharacterClasses.Donkey");
        addSpecialChar("CharacterClasses.Dragon");

        Point p = createRessourceCoord();
        addRessource("Dessins/Boulder1.png",p.x, p.y, RessourceType.iron, 10);
        p = createRessourceCoord();
        addRessource("Assets/flowers.png",p.x, p.y, RessourceType.flower, 10);
        p = createRessourceCoord();
        addRessource("Assets/poudre.png",p.x, p.y, RessourceType.powder, 10);

        p = createRessourceCoord();
        addObstacle("Dessins/Stem1.png",p.x, p.y);
        p = createRessourceCoord();
        addObstacle("Dessins/Stem1.png",p.x, p.y);
        p = createRessourceCoord();
        addObstacle("Dessins/Stem1.png",p.x, p.y);
        p = createRessourceCoord();
        addObstacle("Dessins/Stem1.png",p.x, p.y);
        p = createRessourceCoord();
        addObstacle("Dessins/Stem1.png",p.x, p.y);
        p = createRessourceCoord();
        addObstacle("Dessins/Stem1.png",p.x, p.y);


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
                    image = ImageIO.read(new File("Assets/house.png"));
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

    private Point createRessourceCoord(){
        int selected = (int)(Math.random() * ressourcesCoords.toArray().length);
        Point p = ressourcesCoords.get(selected);
        ressourcesCoords.remove(selected);
        return p;
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


