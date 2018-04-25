/*
 * TCSS 305 - Autumn 2017 
 * Assignment 6 - Tetris
 */

package tests;

import model.Board;

/**
 * This class prints the board to the console and tests how the game ends.
 * 
 * @author Nicole Kauer
 * @version 2 December 2017
 */
public final class GameEndTest
{
    /**
     * Private constructor to prevent instantiation.
     */
    private GameEndTest()
    {
        // Do nothing.
    }

    /**
     * Runs game until the board no longer changes. 
     * 
     * @param theArgs ignored
     */
    public static void main(final String[] theArgs)
    {
        final Board gameBoard = new Board();
        gameBoard.newGame();
        
        /* 'do while' runs until the strings from the last and current gameboard matches */
        
        // Store last string from gameboard
        String lastString;
        
        do
        {
            lastString = gameBoard.toString();
            gameBoard.down();
            System.out.println(gameBoard.toString());
            
        } while (!lastString.equals(gameBoard.toString()));

    }
}
