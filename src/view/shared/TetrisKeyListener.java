/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.shared;

import controller.Status;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import model.Board;

/** 
 * Key listener class for Tetris.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 2 December 2017
 */
public final class TetrisKeyListener extends KeyAdapter implements Observer
{
    /** The game board. */
    private final Board myBoard;
    
    /** The status of the game. */
    private Status myStatus;
    
    /** 
     * Constructor.
     * 
     * @param theBoard the board associated with the key listener
     */
    public TetrisKeyListener(final Board theBoard)
    {
        super();
        
        // Want exact board location
        myBoard = theBoard;
        myStatus = Status.NO_GAME;
    }

    /** 
     * Checks which key was pressed and gives command to board.
     * 
     * @param theEvent the event that occurred on key press
     */
    @Override
    public void keyPressed(final KeyEvent theEvent)
    {        
        final boolean actionKey = theEvent.isActionKey();
        
        if (myStatus == Status.RUNNING_GAME)
        {
            if (actionKey)
            {
                arrowKeyAction(theEvent.getKeyCode());
            }
            else 
            {
                nonArrowKeyAction(theEvent.getKeyCode());
            }   
        }
    }
    
    /**
     * Calls correct action based on which arrow key was pressed.
     * 
     * @param theCode the key code
     */
    private void arrowKeyAction(final int theCode)
    {
        switch (theCode)
        {
            case KeyEvent.VK_LEFT:
                myBoard.left();
                break;
            
            case KeyEvent.VK_RIGHT:
                myBoard.right();
                break;
                
            case KeyEvent.VK_DOWN:
                myBoard.down();
                break;
                
            case KeyEvent.VK_UP:
                myBoard.rotate();
                break;
            
            default:
                break;
        }
    }
    
    /**
     * Calls correct action based on which non-arrow key was pressed.
     * 
     * @param theCode the key code
     */
    private void nonArrowKeyAction(final int theCode)
    {
        switch (theCode)
        {                    
            // Letter keys
            
            case KeyEvent.VK_A:
                myBoard.left();
                break;
                
            case KeyEvent.VK_D:
                myBoard.right();
                break;
                
            case KeyEvent.VK_S:
                myBoard.down();
                break;
                
            case KeyEvent.VK_W:
                myBoard.rotate();
                break;
                
            // Special keys
                
            case KeyEvent.VK_SPACE:
                myBoard.drop();
                break;
            
            default:
                break;
        }
    }

    /**
     * Updates the game status. 
     * 
     * @param theObject the object that sent the notification
     * @param theArg the message sent
     */
    @Override
    public void update(final Observable theObject, final Object theArg)
    {
        if (theArg instanceof Status)
        {
            myStatus = (Status) theArg;
        }
    }
}
