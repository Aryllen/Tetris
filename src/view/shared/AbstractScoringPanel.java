/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.shared;

import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * Abstract class for the Tetris scoreboard.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public abstract class AbstractScoringPanel extends JPanel implements Observer
{
    /** Number format. */
    protected static final NumberFormat NF = NumberFormat.getIntegerInstance();
    
    /** Number of rows cleared for level up. */
    protected static final int LEVEL_UP = 5;
    
    /** Generated serializable ID. */
    private static final long serialVersionUID = -3836354326392731969L;
    
    /** Number of rows that can be cleared. */
    private static final int ONE_ROW = 1;
    
    /** Number of rows that can be cleared. */
    private static final int TWO_ROWS = 2;    
    
    /** Number of rows that can be cleared. */
    private static final int THREE_ROWS = 3;
    
    /** Number of rows that can be cleared. */
    private static final int FOUR_ROWS = 4;
    
    /** Point worth for number of rows cleared. */
    private static final int SINGLE_ROW_CLEARED = 42;
    
    /** Point worth for number of rows cleared. */
    private static final int TWO_ROWS_CLEARED = 420;
    
    /** Point worth for number of rows cleared. */
    private static final int THREE_ROWS_CLEARED = 4200;
    
    /** Point worth for number of rows cleared. */
    private static final int FOUR_ROWS_CLEARED = 42000;
    
    /** 
     * Maximum possible score. Limit set based on the requirement of being less than the
     * maximum value that can be stored in an int, plus the maximum number of digits that 
     * look nice in the panel. 
     */
    private static final int MAX_SCORE = 999999999;

    /** Score. */
    protected int myScore;
    
    /** Total number of rows cleared. */
    protected int myRowsCleared;
    
    /** Level. */
    protected int myLevel;
    
    /**
     * Constructor.
     */
    protected AbstractScoringPanel()
    {
        super();
        
        myScore = 0;
        myRowsCleared = 0;
        myLevel = 1;
    }

    /* Change methods */
    
    /**
     * Updates the score.
     * 
     * @param theNum the number of cleared rows
     */
    private void updateScore(final int theNum)
    {
        if (myScore < MAX_SCORE) 
        {
            switch (theNum)
            {
                case ONE_ROW:
                    myScore += myLevel * SINGLE_ROW_CLEARED;
                    break;
                    
                case TWO_ROWS:
                    myScore += myLevel * TWO_ROWS_CLEARED;
                    break;
                
                case THREE_ROWS:
                    myScore += myLevel * THREE_ROWS_CLEARED;
                    break;
                    
                case FOUR_ROWS:
                    myScore += myLevel * FOUR_ROWS_CLEARED;
                    break;
                
                default:
                    break;
            }
        }
        
    }
    
    /** 
     * Updates the level. 
     */
    private void updateLevel()
    {
        final int currentLevel = myLevel;
        
        myLevel = myRowsCleared / LEVEL_UP + 1;

        firePropertyChange("Level", currentLevel, myLevel);
    }

    /* Observer response */
    
    /**
     * Updates the current preview piece.
     * 
     * @param theObject the class that notified of a change
     * @param theArg the object passed by the observable class
     */
    @Override
    public void update(final Observable theObject, final Object theArg)
    {
        if (theArg instanceof Integer[])
        {            
            final Integer[] cleared = (Integer[]) theArg;
            
            myRowsCleared += cleared.length;
            updateScore(cleared.length);
            updateLevel();
        }
     
        repaint();
    }
}
