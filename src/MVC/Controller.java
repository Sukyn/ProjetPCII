package MVC;

import CellClasses.CellContent;
import MVC.Views.View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.event.MouseEvent.*;

public class Controller implements MouseListener {
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
            model.grid.setSelectedCell(model.grid.getClosestCell(e.getX(), e.getY()));
        /* on a right click and if there is a character in the selected cell, move the character to the closest cell form right click */
        } else {
            CellContent content = model.grid.selectedCell.getCellContent();
            if (e.getButton() == BUTTON3 && content != null && content.getClass().getSuperclass() == CharacterClasses.Character.class) {
                CharacterClasses.Character chara = (CharacterClasses.Character) content;
                if ((model.grid.getClosestCell(e.getX(), e.getY()).getCellContent() == null
                        || ((model.grid.getClosestCell(e.getX(), e.getY())).getCellContent().getClass() != CharacterClasses.Character.class)
                        && chara.isFlying)
                        && !chara.move.isMoving && !model.grid.getClosestCell(e.getX(), e.getY()).isTargeted) {
                    chara.move = chara.moveCharModel();
                    chara.move.setDestination(model.grid.getClosestCell(e.getX(), e.getY()));
                    chara.addTimer();
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
}
