package CellClasses;

import CharacterClasses.Character;

enum RessourceType {flower, iron, powder, gold}

public class CellRessource {
    RessourceType ressourceType;
    Cell position;
    final int maxAmount;
    int currentAmount;
    boolean depleted;

    public CellRessource (RessourceType r, Cell c, int max){
        this.ressourceType = r;
        this.position = c;
        this.maxAmount = max;
        this.currentAmount = max;
        this.depleted = false;
    }

    public void collect(Character character){
        if (character.contentCellPosition == position) {
            switch (this.ressourceType) {
                case flower -> {
                    if (character.flowerInv < character.maxIronInv) {
                        currentAmount--;
                        character.flowerInv++;
                    }
                }
                case iron -> {
                    if (character.ironInv < character.maxIronInv) {
                        currentAmount--;
                        character.ironInv++;
                    }
                }
                case powder -> {
                    if (character.powderInv < character.maxPowderInv) {
                        currentAmount--;
                        character.powderInv++;
                    }
                }
                case gold -> {
                }
            }
        }
    }
}
