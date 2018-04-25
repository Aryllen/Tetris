/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.shared;

import java.awt.EventQueue;

/**
 * Driver class for Tetris.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public final class TetrisMain
{
    /**
     * Private constructor to prevent instantiation.
     */
    private TetrisMain()
    {
        // Do nothing
    }
    
    /**
     * Starts the Tetris GUI.
     * 
     * @param theArgs not utilized
     */
    public static void main(final String[] theArgs)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new StartFrame();
            }
        });

    }

}
