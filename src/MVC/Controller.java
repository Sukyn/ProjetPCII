package MVC;

import CellClasses.Cell;
import CellClasses.CellObstacle;
import CellClasses.CellRessource;
import CharacterClasses.Character;

import java.awt.event.*;
import java.util.Objects;

import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;

public class Controller implements MouseListener, KeyListener, ActionListener {
    View view;
    Model model;
    /** constructor */
    public Controller(View v, Model m) {
        view = v;
        model = m;
        view.frame.addMouseListener(this);
        view.characterInfoView.setController(this);
    }

    private void move(Cell endCell) {
        Character character = model.grid.getSelectedCell().getCellCharacterContent();
        if (character != null && !character.type.equals("enemy")) {
            if ((endCell.getCellContent() == null
                    || (endCell.getCellContent().getClass() == CellRessource.class)
                    || ((endCell.getCellContent().getClass() == CellObstacle.class)
                    && character.isFlying))
                    && (endCell.getCellCharacterContent() == null
                    || Objects.equals(endCell.getCellCharacterContent().type, "enemy"))
                    && !character.move.isMoving && !endCell.isTargeted) {
                character.move = character.moveCharModel();

                character.move.setDestination(endCell);
                character.addTimerMove(100);
            }
        }
    }
    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        /* on a left click, sets the selected cell to the closest cell from left click */
        Cell cell = model.grid.getClosestCell(e.getX(), e.getY()-31);
        if (e.getButton() == BUTTON1) {
            if (!cell.isSelected) {
                model.grid.selectedCell.isSelected = false;
                model.grid.setSelectedCell(cell);
                cell.isSelected = true;
            }
        } else if (e.getButton() == BUTTON3) {
            move(cell);
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Cell cell = model.grid.getClosestCell(e.getX(), e.getY()-31);
        if (!cell.isSelected && e.getButton() == BUTTON1) {
            model.grid.selectedCell.isSelected = false;
            model.grid.setSelectedCell(cell);
            cell.isSelected = true;
        }
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Cell cell = model.grid.getClosestCell(e.getX(), e.getY()-31);
        if (!cell.isSelected) {
            move(cell);
        }
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("salut");
        Character c = model.grid.getSelectedCell().getCellCharacterContent();
        if (c != null) {
            switch (e.getActionCommand()) {
                case "Récolte" -> {
                    c.gatherRessource = c.createGatherThread();
                    c.addTimerGather();
                }
                case "Drop" -> c.dropRessources();
                case "Améliorer la force" -> {
                    c.addStrength(5);
                    model.setGlobalIron(model.getGlobalIron() - 1);
                }
                case "Améliorer les points de vie" -> {
                    c.addMaxHp(5);
                    model.setGlobalPowder(model.getGlobalPowder() - 1);
                }
                case "Heal" -> {
                    c.healHP(10);
                    model.setGlobalFlower(model.getGlobalFlower() - 1);
                }
            }
        }
    }
}
