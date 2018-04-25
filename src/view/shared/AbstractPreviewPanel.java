/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.shared;

import controller.Status;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * Abstract class for the piece preview panel in Tetris. 
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public abstract class AbstractPreviewPanel extends JPanel implements Observer
{
    
    /** Generated serializable ID. */
    private static final long serialVersionUID = -753023795371979470L;
    
    /** Game status. */
    protected Status myStatus;

    /**
     * Constructor.
     */
    protected AbstractPreviewPanel()
    {
        super();
        
        myStatus = Status.NO_GAME;
    }
    
    
}
