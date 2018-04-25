/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package tests;

import java.util.Observable;
import java.util.Observer;
import model.Board;
import model.MovableTetrisPiece;

/**
 * This test is to check how to implement the Observer pattern. Game runs similar to the
 * JustRunTest, but with fewer tries and is currently not printing the board. Prints the
 * current drop iteration number and "update called."
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 2 December 2017
 */
public final class ObserverTest implements Observer
{
    /** Number of iterations to try. */
    private static final int NUM_TRIES = 1;

    /**
     * Runs a Tetris game where the pieces fall.
     * 
     * @param theArgs ignored
     */
    public static void main(final String[] theArgs)
    {
        final ObserverTest testClass = new ObserverTest();
        
        final Board gameBoard = new Board();
        
        gameBoard.addObserver(testClass);
        
        gameBoard.newGame();
        
        /* 'for loop' runs for NUM_TRIES iterations */
        
        for (int i = 0; i < NUM_TRIES; i++)
        {
            System.out.println(i);
//            System.out.println(gameBoard.toString());
            gameBoard.down();
        }
    }

    /**
     * Prints to console depending on the notification sent by the Board.
     */
    @Override
    public void update(final Observable theObject, final Object theArg)
    {
        if (theArg instanceof String)
        {
            System.out.println("this was a string");
        }
        else if (theArg instanceof Boolean)
        {
            System.out.println("this was a boolean");
        }
        else if (theArg instanceof Integer[])
        {
            // Not going to be called because the game just drops pieces; never clears lines.
            System.out.println("this was an integer array");
        }
        else if (theArg instanceof MovableTetrisPiece)
        {
            System.out.println("this was a tetris piece");
            final MovableTetrisPiece piece = (MovableTetrisPiece) theArg;
            System.out.println(piece.toString());
        }
        else
        {
            System.out.println("did not identify class");
        }
        
    }
}
