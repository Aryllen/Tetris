/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.basic;

import controller.Status;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.StringTokenizer;
import view.shared.AbstractBoardPanel;

/**
 * Game board panel for Tetris.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 2 December 2017
 */
public final class BoardPanel extends AbstractBoardPanel implements Observer, ActionListener
{
    /** Generated serializable ID. */
    private static final long serialVersionUID = -2216026628103883513L;
    
    /**
     * Constructor.
     * 
     * @param theColumns the board columns
     * @param theRows the board rows
     */
    public BoardPanel(final int theColumns, final int theRows)
    {
        // Calls AbstractBoardPanel constructor
        super(theColumns, theRows); 
        
        setup();
    }
    
    /* Setup methods */
    
    /** 
     * Sets up the game board.
     */
    private void setup()
    {
        setSize(new Dimension(DEFAULT_BLOCK_SIZE  * myColumns + DEFAULT_BLOCK_SIZE, 
                              DEFAULT_BLOCK_SIZE * myRows + DEFAULT_BLOCK_SIZE));
        updateBlockSize();

        setBackground(Color.WHITE); 

        repaint();
    }
    
    /** 
     * Paints game board.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) 
    {
        if (myStatus == Status.RUNNING_GAME || myStatus == Status.REPLAY_GAME)
        {
            paintRegularGameBoard(theGraphics);
        }
        else if (myStatus == Status.NO_GAME)
        {
            paintStartImage(theGraphics);
        }
        else if (myStatus == Status.PAUSED_GAME)
        {
            paintPauseImage(theGraphics);
        }
        else if (myStatus == Status.END_GAME)
        {
            paintEndImage(theGraphics);
        }
    }
   
    /**
     * Paints board.
     * 
     * @param theGraphics the graphics
     */
    private void paintRegularGameBoard(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        updateBlockSize();
        
        final StringTokenizer st = new StringTokenizer(myCurrentString, TOKEN);
        
        // Skip first five lines
        for (int i = 0; i < NUM_IGNORE; i++)
        {
            st.nextToken();
        }
        for (int row = 0; row < myRows; row++)
        {
            final String line = st.nextToken();
            
            // Offset by one on column because first character in line should be '|'
            for (int col = 1; col < myColumns + 1; col++)
            {
                if (line.charAt(col) == ' ')
                {
                    BLOCK.setFrame((col - 1) * myBlockSize + myX, row * myBlockSize + myY, 
                                     myBlockSize, myBlockSize);
                    g.setPaint(Color.LIGHT_GRAY);
                    g.fill(BLOCK);
                }
                else
                {
                    BLOCK.setFrame((col - 1) * myBlockSize + myX, row * myBlockSize + myY, 
                                     myBlockSize, myBlockSize);
                    g.setPaint(Color.BLACK);
                    g.fill(BLOCK);
                    g.setPaint(Color.WHITE);
                    g.draw(BLOCK);
                }
            }
        }
        
        // Draw line around panel
        g.setPaint(Color.BLACK);
        BLOCK.setFrame(myX, myY, 
                       myBlockSize * myColumns - 1, myBlockSize * myRows - 1);
        g.draw(BLOCK);    
        
        if (myStatus == Status.RUNNING_GAME)
        {
            myPlays.add(myCurrentString);
        }
    }
    
    /** 
     * Paints starting image on panel when the program first initializes.
     * 
     * @param theGraphics the graphics
     */
    private void paintStartImage(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        updateBlockSize();

        g.setPaint(Color.GRAY);
        BLOCK.setFrame(myX, myY, 
                       myBlockSize * myColumns - 1, myBlockSize * myRows - 1);
        g.fill(BLOCK);
        g.setPaint(Color.BLACK);
        g.draw(BLOCK);
    }
    
    /**
     * Paints image when game is paused.
     * 
     * @param theGraphics the graphics
     */
    private void paintPauseImage(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
     

        updateBlockSize();

        g.setPaint(Color.GRAY);
        BLOCK.setFrame(myX, myY, 
                       myBlockSize * myColumns - 1, myBlockSize * myRows - 1);
        g.fill(BLOCK);
        g.setPaint(Color.BLACK);
        g.draw(BLOCK);
    }
    
    /**
     * Paints image when game is over.
     * 
     * @param theGraphics the graphics
     */
    private void paintEndImage(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
     
        // Paint last game set
        updateBlockSize();
        
        final StringTokenizer st = new StringTokenizer(myCurrentString, TOKEN);
        
        // Skip first five lines
        for (int i = 0; i < NUM_IGNORE; i++)
        {
            st.nextToken();
        }
        for (int row = 0; row < myRows; row++)
        {
            final String line = st.nextToken();
            
            // Offset by one on column because first character in line should be '|'
            for (int col = 1; col < myColumns + 1; col++)
            {
                if (line.charAt(col) == ' ')
                {
                    BLOCK.setFrame((col - 1) * myBlockSize + myX, row * myBlockSize + myY, 
                                     myBlockSize, myBlockSize);
                    g.setPaint(Color.LIGHT_GRAY);
                    g.fill(BLOCK);
                }
                else
                {
                    BLOCK.setFrame((col - 1) * myBlockSize + myX, row * myBlockSize + myY, 
                                     myBlockSize, myBlockSize);
                    g.setPaint(Color.GRAY);
                    g.fill(BLOCK);
                    g.setPaint(Color.WHITE);
                    g.draw(BLOCK);
                }
            }
        }
        
        // Draw line around panel
        g.setPaint(Color.BLACK);
        BLOCK.setFrame(myX, myY, 
                       myBlockSize * myColumns - 1, myBlockSize * myRows - 1);
        g.draw(BLOCK);
    }
}