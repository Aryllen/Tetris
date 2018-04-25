/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;
import model.Board;
import view.basic.TetrisFrameStandard;
import view.themed.TetrisFrameThemed;

/**
 * Class that controls the main gameplay for Tetris.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public final class Game extends Observable implements ActionListener, 
                                                        Observer, PropertyChangeListener
{
    /** Denotes standard Tetris game. */
    private static final String STANDARD_TETRIS_GAME = "Boring";
    
    /** Timer delay. */
    private static final int DELAY = 1000;
    
    /** Timer delay divisor for speeding up time after level up. */
    private static final int DELAY_DIVISOR = 4;
       
    /** Game status. */
    private Status myStatus;
    
    /** Timer. */
    private final Timer myTimer;
    
    /** Game board. */
    private final Board myBoard;
    
    /** Number of rows in board grid. */
    private final int myRows;
  
    /** Number of columns in board grid. */
    private final int myColumns;

    
    /**
     * Constructor.
     * 
     * @param theGameStyleChoice the style choice
     * @param theGridChoice the board grid choice
     */
    public Game(final String theGameStyleChoice, final Dimension theGridChoice)
    {
        super();
        
        myRows = (int) theGridChoice.getHeight();
        myColumns = (int) theGridChoice.getWidth();
        
        // This one works if in the form new Board(int, int)
        myBoard = new Board(myColumns, myRows);
        myTimer = new Timer(DELAY, this);     
        myStatus = Status.NO_GAME;
        
        if (theGameStyleChoice.equals(STANDARD_TETRIS_GAME))
        {
            new TetrisFrameStandard(this, myBoard);
        }
        else
        {
            new TetrisFrameThemed(this, myBoard);
        }
        
        setup();
    }
    
    /** 
     * Setup method.
     */
    private void setup()
    {
        myBoard.addObserver(this);
    }
    
    /* Set/command methods */
    
    /** 
     * Returns the number of columns in the board.
     * 
     * @return the number of columns
     */
    public int getColumns()
    {
        return myColumns;
    }
    
    /** 
     * Returns the number of rows in the board.
     * 
     * @return the number of rows
     */
    public int getRows()
    {
        return myRows;
    }
    
    /** 
     * Starts a new game.
     */
    public void startNewGame()
    {
        notifyOfNewGame();
        
        myBoard.newGame();
        myTimer.restart();
        
        myStatus = Status.RUNNING_GAME;
        setChanged();
        notifyObservers(myStatus);
    }
    
    /** 
     * Changes status and notifies observers.
     */
    private void notifyOfNewGame()
    {
        myStatus = Status.NEW_GAME;
        setChanged();
        notifyObservers(myStatus);
    }
    
    /** 
     * Pauses game.
     */
    public void pauseGame()
    {
        if (myStatus == Status.PAUSED_GAME)
        {
            myTimer.start();
            myStatus = Status.RUNNING_GAME;
            setChanged();
            
            notifyObservers(myStatus);
        }
        else
        {
            myTimer.stop();
            myStatus = Status.PAUSED_GAME;
            setChanged();
            
            notifyObservers(myStatus);
        }
    }

    /**
     * End game.
     */
    public void stopGame()
    {
        myTimer.stop();
        myStatus = Status.END_GAME;
        setChanged();
        
        notifyObservers(myStatus);
    }
    
    /**
     * Instant replay of last played game.
     */
    public void replayGame()
    {
        myStatus = Status.REPLAY_GAME;
        setChanged();
     
        notifyObservers(myStatus);
    }
        
    /* Get methods */
    
    /** 
     * Gets game status.
     * 
     * @return game status
     */
    public Status getStatus()
    {
        return myStatus;
    }
    
    /* Listener/Observer methods */
    
    /**
     * Signals to move the piece down when the timer goes off.
     * 
     * @param theEvent the timer event
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent)
    {
        if (myStatus == Status.RUNNING_GAME)
        {
            myBoard.down();
        }               
    }
    
    /**
     * Ends the game.
     * 
     * @param theObject the object that sent the notification
     * @param theArg the message sent
     */
    @Override
    public void update(final Observable theObject, final Object theArg)
    {
        if (theArg instanceof Boolean && (boolean) theArg)
        {
            myTimer.stop();
            myStatus = Status.END_GAME;
            setChanged();
            notifyObservers(myStatus);
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent)
    {
        if (theEvent.getPropertyName().equals("Level") && myTimer.getDelay() > 0)
        {
            myTimer.setDelay(myTimer.getDelay() - (myTimer.getDelay() / DELAY_DIVISOR));
        }        
    }
    
}
