package MVC;

import CellClasses.Cell;
import CharacterClasses.Character;

import java.awt.event.*;

import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;

public class Controller implements MouseListener, KeyListener, ActionListener {
    View view;
    Model model;
    int cpt;
    /** constructor */
    public Controller(View v, Model m) {
        cpt = 0;
        view = v;
        model = m;
        view.frame.addMouseListener(this);
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
        if (e.getButton() == BUTTON1) {
            Cell selectedSell = Model.grid.getClosestCell(e.getX(), e.getY()-31);
            if (!selectedSell.isSelected) {
                Model.grid.selectedCell.isSelected = false;
                Model.grid.setSelectedCell(selectedSell);
                selectedSell.isSelected = true;
                System.out.print(" Position de la case sélectionnée : ");
                System.out.print(selectedSell.posCenterX);
                System.out.print(" , ");
                System.out.println(selectedSell.posCenterY);

            }
            System.out.print(" Position de la souris :  ");
            System.out.print(e.getX());
            System.out.print(" & ");
            System.out.println(e.getY());
        /* on a right click and if there is a character in the selected cell, move the character to the closest cell form right click */
        } else {
            Character character = Model.grid.getSelectedCell().getCellCharacterContent();
            if (e.getButton() == BUTTON3 && character != null) {
                if ((Model.grid.getClosestCell(e.getX(), e.getY()).getCellContent() == null
                        || ((Model.grid.getClosestCell(e.getX(), e.getY())).getCellContent().getClass() != Character.class)
                        && character.isFlying)
                        && !character.move.isMoving && !Model.grid.getClosestCell(e.getX(), e.getY()).isTargeted) {
                    character.move = character.moveCharModel();

                    character.move.setDestination(Model.grid.getClosestCell(e.getX(), e.getY()));
                    character.addTimer();
                }
            }
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

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
            Character c = Model.grid.getSelectedCell().getCellCharacterContent();
            if (c != null)
                c.collect();
        }
    }
}
