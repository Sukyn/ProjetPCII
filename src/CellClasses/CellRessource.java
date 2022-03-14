package CellClasses;

import CharacterClasses.Character;

import java.awt.*;

enum RessourceType {flower, iron, powder, gold}

public class CellRessource extends CellContent {
    RessourceType ressourceType;
    final int maxAmount;
    int currentAmount;
    boolean depleted;

    /** constructor */
    public CellRessource (RessourceType r, Cell c, Image i, int max)  {
        super(c,i);
        this.ressourceType = r;
        this.maxAmount = max;
        this.currentAmount = max;
        this.depleted = false;

    }

    /** Method collect
     * the character passed in parameter collects this ressource
     * @param character, the character collecting
     */
    public void collect(Character character){
        /* if the character is on the correct cell */
        if (character.contentCellPosition == contentCellPosition) {
            /* it increases it's number of ressource depending of the type and while staying under the max*/
            switch (this.ressourceType) {
                /* case flower */
                case flower -> {
                    if (character.flowerInv < character.maxIronInv) {
                        /* decrease amount for this ressources and increases in character inventory */
                        currentAmount--;
                        character.flowerInv++;
                    }
                }
                /* case iron */
                case iron -> {
                    if (character.ironInv < character.maxIronInv) {
                        /* decrease amount for this ressources and increases in character inventory */
                        currentAmount--;
                        character.ironInv++;
                    }
                }
                /* case powder */
                case powder -> {
                    if (character.powderInv < character.maxPowderInv) {
                        /* decrease amount for this ressources and increases in character inventory */
                        currentAmount--;
                        character.powderInv++;
                    }
                }
                /* case gold */
                case gold -> {
                }
            }
        }
    }
}
