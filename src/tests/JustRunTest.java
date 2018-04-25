/*
 * TCSS 305 - Autumn 2017 
 * Assignment 6 - Tetris
 */

package tests;

import model.Board;

/**
 * This class prints the board to the console and tests how the game runs.
 * 
 * @author Nicole Kauer
 * @version 2 December 2017
 */
public final class JustRunTest
{
    /** Number of iterations to try. */
    private static final int NUM_TRIES = 150;
    
    /**
     * Private constructor to prevent instantiation.
     */
    private JustRunTest()
    {
        // Do nothing.
    }

    /**
     * Runs a Tetris game where the pieces fall.
     * 
     * @param theArgs ignored
     */
    public static void main(final String[] theArgs)
    {
        final Board gameBoard = new Board();
        gameBoard.newGame();
        
        /* 'for loop' runs for NUM_TRIES iterations */
        
        for (int i = 0; i < NUM_TRIES; i++)
        {
            System.out.println(i);
            System.out.println(gameBoard.toString());
            gameBoard.down();
        }
    }

}
