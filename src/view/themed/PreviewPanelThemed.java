/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.themed;

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
 * Preview piece panel for themed Tetris.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public class PreviewPanelThemed extends AbstractPreviewPanel 
{
    /** Generated serializable ID. */
    private static final long serialVersionUID = -8085681804131931909L;
    
    /** Saturated pink. */
    private static final Color SATURATED_PINK = new Color(234, 61, 125);
    
    /** Blue-white. */
    private static final Color BLUE_WHITE = new Color(204, 232, 252);
    
    /** Dark pink. */
    private static final Color DARK_PINK = new Color(248, 37, 68);
    
    /** Index for dark pink. */
    private static final int INDEX_DARK_PINK = 0;
    
    /** Dark blue. */
    private static final Color DARK_BLUE = new Color(45, 109, 201);
    
    /** Index for dark blue. */
    private static final int INDEX_DARK_BLUE = 3;
    
    /** Dark purple. */
    private static final Color DARK_PURPLE = new Color(87, 50, 183);
    
    /** Index for dark purple. */
    private static final int INDEX_DARK_PURPLE = 2;
    
    /** Dark green. */
    private static final Color DARK_GREEN = new Color(98, 150, 15);
    
    /** Index for dark green. */
    private static final int INDEX_DARK_GREEN = 4;
    
    /** Dark orange. */
    private static final Color DARK_ORANGE = new Color(216, 114, 1);
    
    /** Index for dark orange. */
    private static final int INDEX_DARK_ORANGE = 6;
    
    /** Dark red. */
    private static final Color DARK_RED = new Color(126, 18, 34);
    
    /** Index for dark red. */
    private static final int INDEX_DARK_RED = 1;
    
    /** Gold. */
    private static final Color GOLD = new Color(243, 197, 88);
    
    /** Index for gold. */
    private static final int INDEX_GOLD = 5;
    
    /** Number of block colors. */
    private static final int NUM_COLORS = 7;
    
    /** Offset in the X direction for odd width pieces. */
    private static final int ODD_WIDTH_X_OFFSET = 25;
    
    /** Offset in the Y direction for I piece. */
    private static final int I_BLOCK_Y_OFFSET = 45;
    
    /** Default offset in the X direction for pieces. */
    private static final int DEFAULT_X_OFFSET = 15;
    
    /** Default offset in the Y direction for pieces. */
    private static final int DEFAULT_Y_OFFSET = 35;
    
    /** Maximum number of block pieces possible in a row. */
    private static final int GRID_ROW_SIZE = 4;
    
    /** Grid size. */
    private static final int GRID_COL_SIZE = 8;
    
    /** Block size. */
    private static final int BLOCK_SIZE = 10;
    
    /** Preferred panel size. */
    private static final Dimension PREFERRED_SIZE = new Dimension(110, 110);
    
    /** Block piece to paint. */
    private static final Rectangle2D BLOCK = new Rectangle2D.Double();

    /** Current preview piece grid. */
    private char[][] myPreviewPiece;
    
    /** The offset in the x direction needed to center piece. */
    private int myXOffset;
    
    /** The offset in the y direction needed to center piece. */
    private int myYOffset;
    
    /** Number of pieces that were displayed. */
    private int myNumPieces;
    
    /**
     * Constructor.
     */
    public PreviewPanelThemed()
    {
        super();
        
        myPreviewPiece = new char[GRID_ROW_SIZE][GRID_COL_SIZE];
        myXOffset = DEFAULT_X_OFFSET;
        myYOffset = DEFAULT_Y_OFFSET;
        myNumPieces = 0;
        
        setup();
    }
    
    /** 
     * Sets up the preview piece panel.
     */
    private void setup()
    {
        setPreferredSize(PREFERRED_SIZE); // Preferred size
        setMaximumSize(PREFERRED_SIZE);   // Don't allow it to expand at all
        setBackground(SATURATED_PINK);
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
        
        g.setColor(BLUE_WHITE);
        BLOCK.setFrame(BLOCK_SIZE, BLOCK_SIZE, PREFERRED_SIZE.getWidth() - 2 * BLOCK_SIZE, 
                       PREFERRED_SIZE.getHeight() - 2 * BLOCK_SIZE);
        g.fill(BLOCK);
        
        g.setColor(getColor());
        
        if (myStatus == Status.RUNNING_GAME)
        {
            // Fill blocks inside panel
            for (int row = 0; row < GRID_ROW_SIZE; row++)
            {
                for (int col = 0; col < GRID_COL_SIZE; col++)
                {
                    if (myPreviewPiece[row][col] != ' ')
                    {
                        BLOCK.setFrame(col * BLOCK_SIZE + myXOffset, 
                                       row * BLOCK_SIZE + myYOffset, 
                                       BLOCK_SIZE, BLOCK_SIZE);
                        g.fill(BLOCK);
                    }
                }
            }
            
//            // Draw white line around actual block piece size
//            for (int row = 0; row < GRID_ROW_SIZE; row += 2)
//            {
//                for (int col = 0; col < GRID_COL_SIZE; col += 2)
//                {
//                    if (myPreviewPiece[row][col] != ' ')
//                    {
//                        BLOCK.setFrame(col * BLOCK_SIZE + myXOffset, 
//            row * BLOCK_SIZE + myYOffset, 
//                                         2 * BLOCK_SIZE, 2 * BLOCK_SIZE);
//                        g.setPaint(Color.WHITE);
//                        g.draw(BLOCK);
//                    }
//                }
//            }
        }
    }
    
    /** 
     * Returns color based on how many pieces have been displayed.
     * 
     * @return the block color
     */
    private Color getColor()
    {
        final int mod = myNumPieces % NUM_COLORS;
        final Color blockColor;
        
        switch (mod)
        {
            case INDEX_DARK_PINK:
                blockColor = DARK_PINK;
                break;
            
            case INDEX_DARK_RED:
                blockColor = DARK_RED;
                break;
                
            case INDEX_DARK_PURPLE:
                blockColor = DARK_PURPLE;
                break;
                
            case INDEX_DARK_BLUE:
                blockColor = DARK_BLUE;
                break;
                
            case INDEX_DARK_GREEN:
                blockColor = DARK_GREEN;
                break;
                
            case INDEX_GOLD:
                blockColor = GOLD;
                break;
            
            case INDEX_DARK_ORANGE:
                blockColor = DARK_ORANGE;
                break;
                
            default:
                blockColor = Color.BLACK;
                break;
        }
        
        return blockColor;
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
            myNumPieces += 1;
            
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
