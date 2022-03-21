package MVC;

import CellClasses.Cell;
import CharacterClasses.Character;

import java.awt.event.*;

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
    }

    private void move(Cell cell, Cell endCell) {
        Character character = model.grid.getSelectedCell().getCellCharacterContent();
        if (character != null || character.type.equals("enemy")) {
            if ((cell.getCellContent() == null
                    || (cell.getCellContent().getClass() != Character.class)
                    && character.isFlying)
                    && !character.move.isMoving && !cell.isTargeted) {
                character.move = character.moveCharModel();

                character.move.setDestination(endCell);
                character.addTimer(100);
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
            move(model.grid.getSelectedCell(), cell);
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
            move(model.grid.getSelectedCell(), cell);
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
        System.out.println(e);
        if (e.getActionCommand().equals("Récolte")){
            Character c = model.grid.getSelectedCell().getCellCharacterContent();
            if (c != null)
                c.collect();
        }
    }
}
