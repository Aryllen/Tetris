/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.shared;

import controller.Status;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.Timer;

/** 
 * Abstract class for the Tetris board panel.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public abstract class AbstractBoardPanel extends JPanel implements Observer, ActionListener 
{    
    /** Block size. */
    protected static final int DEFAULT_BLOCK_SIZE = 20;

    /** Number of unnecessary rows in Board.toString(). */
    protected static final int NUM_IGNORE = 5;
    
    /** Graphics block. */
    protected static final Rectangle2D BLOCK = new Rectangle2D.Double();
    
    /** Timer delay. */
    protected static final int DELAY = 50;
    
    /** Timer delay for pause screen. */
    protected static final int PAUSE_DELAY = 500;
    
    /** String token. */
    protected static final String TOKEN = "\n";
    
    /** Generated serializable ID. */
    private static final long serialVersionUID = 2980603233819673876L;
      
    /** Timer for instant replay. */
    protected final Timer myTimer;
    
    /** Game status. */
    protected Status myStatus;
    
    /** Holds all painted strings for a game. */
    protected final List<String> myPlays;
    
    /** Iterator for myPlays. */
    protected Iterator<String> myIterator;
    
    /** Number of rows in grid. */
    protected int myRows;
    
    /** Number of columns in grid. */
    protected int myColumns;
    
    /** Block size dependent on window size. */
    protected int myBlockSize;
    
    /** X coordinate for upper left corner of board. */
    protected int myX;
    
    /** Y coordinate for upper right corner of board. */
    protected int myY;
    
    /** Holds block pieces as shapes. */
    protected char[][] myBoardGrid;
    
    /** Current board string. */
    protected String myCurrentString;
    
    /**
     * Constructor.
     * 
     * @param theColumns the number of board columns
     * @param theRows the number of board rows
     */
    protected AbstractBoardPanel(final int theColumns, final int theRows)
    {
        // Calls JPanel default constructor
        super(); 
        
        myRows = theRows;
        myColumns = theColumns;
        myBoardGrid = new char[myRows][myColumns];
        
        myPlays = new ArrayList<String>();
        myTimer = new Timer(DELAY, this);
        myStatus = Status.NO_GAME;
    }
    
    /**
     * Sets the block size. 
     * 
     * @param theSize the size
     */
    public void setBlockSize(final int theSize)
    {
        myBlockSize = theSize;
    }
    
    /**
     * Updates the block size based on current panel size.
     */
    protected void updateBlockSize()
    {        

        setBlockSize(getHeight() / myRows);
        myX = (getWidth() - (myBlockSize * myColumns)) / 2;
        myY = (getHeight() - (myBlockSize * myRows)) / 2;
        
        if (getWidth() < (myBlockSize * myColumns))
        {
            setBlockSize(getWidth() / myColumns);
            myX = (getWidth() - (myBlockSize * myColumns)) / 2;
            myY = (getHeight() - (myBlockSize * myRows)) / 2;
            
        }
        else if (getHeight() < (myBlockSize * myRows))
        {
            setBlockSize(getHeight() / myRows);
            myX = (getWidth() - (myBlockSize * myColumns)) / 2;
            myY = (getHeight() - (myBlockSize * myRows)) / 2;
        }
    }
    
    /**
     * Starts showing of the last game on board.
     */
    public void replayLastGame()
    {
        myIterator = myPlays.listIterator();
        myTimer.setDelay(DELAY);
        myTimer.start();
    }
    
    /**
     * Timer action for replaying the game.
     * 
     * @param theArg ignored
     */
    @Override
    public void actionPerformed(final ActionEvent theArg)
    {  
        if (myStatus == Status.REPLAY_GAME)
        {
            if (myIterator.hasNext())
            {
                myCurrentString = myIterator.next();
                repaint();   
            }
            else
            {
                myTimer.stop();
                myStatus = Status.END_GAME;
                repaint();
            }
        }
        else if (myStatus == Status.PAUSED_GAME)
        {
            repaint();
        }
    
    }
    
    /* Observer response */
    
    /**
     * Updates the board and repaints.
     * 
     * @param theObject the class that notified of a change
     * @param theArg the object passed by the observable class
     */
    @Override
    public void update(final Observable theObject, final Object theArg)
    {
        if (theArg instanceof String)
        {
            myCurrentString = (String) theArg;
            
            repaint();
        }
        else if (theArg instanceof Status)
        {
            myStatus = (Status) theArg;
            
            if (myStatus.equals(Status.RUNNING_GAME))
            {
                myTimer.stop();
                repaint();
            }
            else if (myStatus.equals(Status.END_GAME))
            {
                myTimer.stop();
                repaint();
            }
            else if (myStatus.equals(Status.PAUSED_GAME))
            {
                myTimer.setDelay(PAUSE_DELAY);
                myTimer.start();
                repaint();
            }
            else if (myStatus.equals(Status.REPLAY_GAME))
            {
                replayLastGame();
            }
            else if (myStatus.equals(Status.NEW_GAME))
            {
                myPlays.clear();
            }
        }
        
    }
}
