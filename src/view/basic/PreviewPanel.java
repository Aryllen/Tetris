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
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.StringTokenizer;
import model.MovableTetrisPiece;
import view.shared.AbstractPreviewPanel;

/**
 * Preview panel for showing next piece in Tetris.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 2 December 2017
 */
public final class PreviewPanel extends AbstractPreviewPanel
{
    /** Generated serializable ID. */
    private static final long serialVersionUID = -8085681804131931909L;
    
    /** Offset in the X direction for odd width pieces. */
    private static final int ODD_WIDTH_X_OFFSET = 20;
    
    /** Offset in the Y direction for I piece. */
    private static final int I_BLOCK_Y_OFFSET = 40;
    
    /** Default offset in the X direction for pieces. */
    private static final int DEFAULT_X_OFFSET = 10;
    
    /** Default offset in the Y direction for pieces. */
    private static final int DEFAULT_Y_OFFSET = 30;
    
    /** Maximum number of block pieces possible in a row. */
    private static final int GRID_ROW_SIZE = 4;
    
    /** Grid size. */
    private static final int GRID_COL_SIZE = 8;
    
    /** Block size. */
    private static final int BLOCK_SIZE = 10;
    
    /** Preferred panel size. */
    private static final Dimension PREFERRED_SIZE = new Dimension(100, 100);
    
    /** Block piece to paint. */
    private static final Rectangle2D BLOCK = new Rectangle2D.Double();

    /** Current preview piece grid. */
    private char[][] myPreviewPiece;
    
    /** The offset in the x direction needed to center piece. */
    private int myXOffset;
    
    /** The offset in the y direction needed to center piece. */
    private int myYOffset;
//    
//    /** Game status. */
//    private Status myStatus;
    
    /**
     * Constructor.
     */
    public PreviewPanel()
    {
        super();
        
        myPreviewPiece = new char[GRID_ROW_SIZE][GRID_COL_SIZE];
        myXOffset = DEFAULT_X_OFFSET;
        myYOffset = DEFAULT_Y_OFFSET;
//        myStatus = Status.NO_GAME;
        
        setup();
    }
    
    /** 
     * Sets up the preview piece panel.
     */
    private void setup()
    {
        setPreferredSize(PREFERRED_SIZE); // Preferred size
        setMaximumSize(PREFERRED_SIZE);   // Don't allow it to expand at all
        setBackground(Color.LIGHT_GRAY);
    }
    
    /** 
     * Paints preview panel.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) 
    {
        super.paintComponent(theGraphics);
        final Graphics2D g = (Graphics2D) theGraphics;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (myStatus == Status.RUNNING_GAME)
        {
            // Fill blocks inside panel
            for (int row = 0; row < GRID_ROW_SIZE; row++)
            {
                for (int col = 0; col < GRID_COL_SIZE; col++)
                {
                    if (myPreviewPiece[row][col] == ' ')
                    {
                        BLOCK.setFrame(col * BLOCK_SIZE + myXOffset, 
                                       row * BLOCK_SIZE + myYOffset, 
                                         BLOCK_SIZE, BLOCK_SIZE);
                        g.setPaint(Color.LIGHT_GRAY);
                        g.fill(BLOCK);
                        
                    }
                    else
                    {
                        BLOCK.setFrame(col * BLOCK_SIZE + myXOffset, 
                                       row * BLOCK_SIZE + myYOffset, 
                                         BLOCK_SIZE, BLOCK_SIZE);
                        g.setPaint(Color.BLACK);
                        g.fill(BLOCK);
                    }
                }
            }
            
            // Draw white line around actual block piece size
            for (int row = 0; row < GRID_ROW_SIZE; row += 2)
            {
                for (int col = 0; col < GRID_COL_SIZE; col += 2)
                {
                    if (myPreviewPiece[row][col] != ' ')
                    {
                        BLOCK.setFrame(col * BLOCK_SIZE + myXOffset, 
                                       row * BLOCK_SIZE + myYOffset, 
                                         2 * BLOCK_SIZE, 2 * BLOCK_SIZE);
                        g.setPaint(Color.WHITE);
                        g.draw(BLOCK);
                    }
                }
            }
            
            // Draw black line around edge of panel
            g.setPaint(Color.BLACK);
            BLOCK.setFrame(0, 0, PREFERRED_SIZE.getWidth() - 1, 
                           PREFERRED_SIZE.getHeight() - 1);
            g.draw(BLOCK);
        }
        else
        {
            // Draw black line around edge of panel
            g.setPaint(Color.BLACK);
            BLOCK.setFrame(0, 0, PREFERRED_SIZE.getWidth() - 1, 
                           PREFERRED_SIZE.getHeight() - 1);
            g.draw(BLOCK);
        }
    }
        
    /**
     * Updates the current preview piece.
     * 
     * @param theObject the class that notified of a change
     * @param theArg the object passed by the observable class
     */
    @Override
    public void update(final Observable theObject, final Object theArg)
    {
        if (theArg instanceof MovableTetrisPiece)
        {
            final int width = ((MovableTetrisPiece) theArg).getWidth();
            
            // Check if the piece is the I piece (only one with width of 4)
            if (width == GRID_ROW_SIZE)
            {
                myYOffset = I_BLOCK_Y_OFFSET;
                myXOffset = DEFAULT_X_OFFSET;
            }
            // Check if the piece has a width of 3, which is any block except I and O
            else if (width == GRID_ROW_SIZE - 1)
            {
                myYOffset = DEFAULT_Y_OFFSET;
                myXOffset = ODD_WIDTH_X_OFFSET;
            }
            // Check if the piece has any other width, which is really just the O block
            else
            {
                myYOffset = DEFAULT_Y_OFFSET;
                myXOffset = DEFAULT_X_OFFSET;
            }
            
            // Update the grid
            updatePreviewBlock(((MovableTetrisPiece) theArg).toString());

            repaint();
        }
        else if (theArg instanceof Status)
        {
            myStatus = (Status) theArg;
            
            if (myStatus.equals(Status.RUNNING_GAME))
            {
                repaint();
            }
            else 
            {
                repaint();
            }
        }
        
    }
    
    /**
     * Updates the current preview piece grid.
     * 
     * @param theString the block as a string
     */
    private void updatePreviewBlock(final String theString)
    {
        final StringTokenizer st = new StringTokenizer(theString, "\n");
        
        String line = st.nextToken(); // Skip first line
        line = st.nextToken(); // Second line is where the important bits start
        int index = 0;
        
        // Assigns four blocks at a time, within first two rows, for each char in the string
        for (int col = 0; col < GRID_COL_SIZE; col += 2)
        {                       
            myPreviewPiece[0][col] = line.charAt(index);
            myPreviewPiece[0][col + 1] = line.charAt(index);
                
            myPreviewPiece[1][col] = line.charAt(index);
            myPreviewPiece[1][col + 1] = line.charAt(index);
                
            index += 1;
        }
        
        // Reset index and get third line
        index = 0;
        line = st.nextToken();
        
        // Assigns four blocks at a time, within last two rows, for each char in the string
        for (int col = 0; col < GRID_COL_SIZE; col += 2)
        {                       
            myPreviewPiece[2][col] = line.charAt(index);
            myPreviewPiece[2][col + 1] = line.charAt(index);
                
            myPreviewPiece[GRID_ROW_SIZE - 1][col] = line.charAt(index);
            myPreviewPiece[GRID_ROW_SIZE - 1][col + 1] = line.charAt(index);
                
            index += 1;
        }
    }

}
