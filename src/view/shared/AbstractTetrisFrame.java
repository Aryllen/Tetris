/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.shared;

import controller.Game;
import javax.swing.JFrame;
import model.Board;

/**
 * Abstract class for the main Tetris frame.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public abstract class AbstractTetrisFrame extends JFrame
{
    /** Generated serializable ID. */
    private static final long serialVersionUID = -5653736347482237232L;

    /** The game. */
    protected final Game myGame;
    
    /** Game board. */
    protected Board myBoard;
    
    /** The preview piece panel. */
    protected AbstractPreviewPanel myPreviewPanel;
    
    /** The scoring panel. */
    protected AbstractScoringPanel myScoringPanel;
    
    /** The game board panel. */
    protected AbstractBoardPanel myBoardPanel;
    
    /** The menu bar. */
    protected MenuBar myMenuBar;
    
    /** 
     * Constructor. 
     * 
     * @param theGame the game
     * @param theBoard the game board
     */
    protected AbstractTetrisFrame(final Game theGame, final Board theBoard)
    {
        // Call JFrame default constructor
        super();
        
        // Want exact game and board
        myGame = theGame;
        myBoard = theBoard;
    }
    
    /** 
     * Creates the menu bar for the frame.
     */
    protected void createMenuBar()
    {
        // Create menu bar and set into frame
        myMenuBar = new MenuBar(myGame, this);
        setJMenuBar(myMenuBar);
        myGame.addObserver(myMenuBar);
        myMenuBar.addPropertyChangeListener(myGame);
    }
    
    /** 
     * Registers property change and key listeners.
     */
    protected void registerListeners()
    {
        final TetrisKeyListener keyListener = new TetrisKeyListener(myBoard);
        addKeyListener(keyListener);
        myGame.addObserver(keyListener);
        
        myScoringPanel.addPropertyChangeListener("Level", myGame);        
    }
    
    /** 
     * Registers board observers.
     */
    protected void registerBoardObservers()
    {
        myBoard.addObserver(myPreviewPanel);
        myBoard.addObserver(myScoringPanel);
        myBoard.addObserver(myBoardPanel);
    }
    
    /**
     * Registers game observers.
     */
    protected void registerGameObservers()
    {
        myGame.addObserver(myBoardPanel);
        myGame.addObserver(myPreviewPanel);
    }


}
