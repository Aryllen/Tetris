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
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.shared.AbstractBoardPanel;

/**
 * Draws the Tetris board.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public class BoardPanelThemed extends AbstractBoardPanel
{
    /** Generated serializable ID. */
    private static final long serialVersionUID = 948772207420999256L;
    
    /** Pretty red. */
    private static final Color PRETTY_RED = new Color(110, 31, 60);
    
    /** Yellow. */
    private static final Color YELLOW = new Color(248, 221, 56);
    
    /** Dark green. */
    private static final Color DARK_GREEN = new Color(98, 150, 15);

    /** Semi-transparent white. */
    private static final Color SEMI_TRANS_WHITE = new Color(255, 255, 255, 150);
    
//    /** Blue-white. */
//    private static final Color BLUE_WHITE = new Color(204, 232, 252);
    
    /** Don't Panic background. */
    private Image myDontPanicBG;
    
    /** Cloud background image. */
    private Image myCloudBG;
    
    /** Whale block image. */
    private Image myWhaleBlock;
    
    /** Purple flower block image. */
    private Image myPurpleFlowerBlock;
    
    /** Blue flower block image. */
    private Image myBlueFlowerBlock;
    
    /** Orange flower block image. */
    private Image myOrangeFlowerBlock;
    
    /** Yellow flower block image. */
    private Image myYellowFlowerBlock;
    
    /** Red flower block image. */
    private Image myRedFlowerBlock;
    
    /** Green flower block image. */
    private Image myGreenFlowerBlock;
    
    /** Pause counter. */
    private int myPauseCounter;


    /**
     * Constructor.
     * 
     * @param theColumns the number of board columns
     * @param theRows the number of board rows
     */
    public BoardPanelThemed(final int theColumns, final int theRows)
    {
        // Calls AbstractBoardPanel constructor
        super(theColumns, theRows); 
        
        myPauseCounter = 0;
        
        setupImages();

        setup();
    }
    
    /* Setup methods */
    
    /**
     * Sets up the images.
     */
    private void setupImages() 
    {
        try
        {
            myDontPanicBG = ImageIO.read((URL) getClass().getResource("dont_panic.png"));
            myCloudBG = ImageIO.read((URL) getClass().getResource("clouds.png"));
            myWhaleBlock = ImageIO.read((URL) getClass().getResource("whale.png"));
            myPurpleFlowerBlock = 
                            ImageIO.read((URL) getClass().getResource("flower_purple.png"));
            myBlueFlowerBlock = ImageIO.read((URL) getClass().getResource("flower_blue.png"));
            myRedFlowerBlock = ImageIO.read((URL) getClass().getResource("flower_red.png"));
            myOrangeFlowerBlock = 
                            ImageIO.read((URL) getClass().getResource("flower_orange.png"));
            myYellowFlowerBlock = 
                            ImageIO.read((URL) getClass().getResource("flower_yellow.png"));
            myGreenFlowerBlock = 
                            ImageIO.read((URL) getClass().getResource("flower_green.png")); 
        }
        catch (final IOException exception)
        {
            JOptionPane.showMessageDialog(this, "An image did not load.", "Image Load Error", 
                                          JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /** 
     * Sets up the game board.
     */
    private void setup()
    {
        setSize(new Dimension(DEFAULT_BLOCK_SIZE  * myColumns + DEFAULT_BLOCK_SIZE, 
                              DEFAULT_BLOCK_SIZE * myRows + DEFAULT_BLOCK_SIZE));
        updateBlockSize();
        
        setBackground(PRETTY_RED);
        
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
        
        // Paint background
        myCloudBG.getScaledInstance(myBlockSize * myColumns, myBlockSize * myRows, 
                                    Image.SCALE_DEFAULT);
        g.drawImage(myCloudBG, myX, myY, myBlockSize * myColumns, myBlockSize * myRows, null);
        
        // Paint blocks 
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
                final char block = line.charAt(col);
                
                g.drawImage(getResizedImage(block), (col - 1) * myBlockSize + myX, 
                            row * myBlockSize + myY, myBlockSize, myBlockSize, null);
            }
        }
        
        // Draw line around panel
        g.setPaint(YELLOW);
        BLOCK.setFrame(myX, myY, 
                       myBlockSize * myColumns, myBlockSize * myRows);
        g.draw(BLOCK);    
        
        // Store string in plays
        if (myStatus == Status.RUNNING_GAME)
        {
            myPlays.add(myCurrentString);
        }
    }
    
    /**
     * Resizes and returns block image.
     * 
     * @param theBlockChar the block type
     * @return the resized image
     */
    private Image getResizedImage(final char theBlockChar)
    {
        
        Image tempImage = null;
        
        switch (theBlockChar)
        {
            case 'I':
                tempImage = myWhaleBlock.getScaledInstance(myBlockSize, myBlockSize, 
                                               Image.SCALE_DEFAULT);
                break;
                
            case 'O':
                tempImage = myPurpleFlowerBlock.getScaledInstance(myBlockSize, myBlockSize, 
                                                      Image.SCALE_DEFAULT);
                break;
                
            case 'S':
                tempImage = myRedFlowerBlock.getScaledInstance(myBlockSize, myBlockSize, 
                                                      Image.SCALE_DEFAULT);
                break;
                
            case 'Z':
                tempImage = myYellowFlowerBlock.getScaledInstance(myBlockSize, myBlockSize, 
                                                      Image.SCALE_DEFAULT);
                break;
                
            case 'T':
                tempImage = myBlueFlowerBlock.getScaledInstance(myBlockSize, myBlockSize, 
                                                      Image.SCALE_DEFAULT);
                break;
                
            case 'L':
                tempImage = myOrangeFlowerBlock.getScaledInstance(myBlockSize, myBlockSize, 
                                                      Image.SCALE_DEFAULT);
                break;
                
            case 'J':
                tempImage = myGreenFlowerBlock.getScaledInstance(myBlockSize, myBlockSize, 
                                                      Image.SCALE_DEFAULT);
                break;
                
            default:
                break;
        }
        
        return tempImage;
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

        // Paint background
        myDontPanicBG.getScaledInstance(myBlockSize * myColumns, myBlockSize * myRows, 
                                    Image.SCALE_DEFAULT);
        g.drawImage(myDontPanicBG, myX, myY, myBlockSize * myColumns, myBlockSize * myRows, 
                                                                                        null);
        
//        g.setPaint(BLUE_WHITE);
        BLOCK.setFrame(myX, myY, 
                       myBlockSize * myColumns, myBlockSize * myRows);
//        g.fill(BLOCK);
        g.setPaint(YELLOW);
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
        
        BLOCK.setFrame(myX, myY, 
                       myBlockSize * myColumns, myBlockSize * myRows);

        if (myPauseCounter % 2 == 0)
        {
            g.setPaint(DARK_GREEN);
        }
        else
        {
            g.setPaint(DARK_GREEN.brighter());
        }
        
        g.fill(BLOCK);
        g.setPaint(YELLOW);
        g.draw(BLOCK);
        
        myPauseCounter += 1;
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
     
        // Paint last game set, but with different colors
        updateBlockSize();
        
        // Paint background
        myCloudBG.getScaledInstance(myBlockSize * myColumns, myBlockSize * myRows, 
                                    Image.SCALE_DEFAULT);
        g.drawImage(myCloudBG, myX, myY, myBlockSize * myColumns, myBlockSize * myRows, null);
        
        // Paint blocks 
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
                final char block = line.charAt(col);
                
                g.drawImage(getResizedImage(block), (col - 1) * myBlockSize + myX, 
                            row * myBlockSize + myY, myBlockSize, myBlockSize, null);
            }
        }
        
        // Fill semi-transparent rectangle over board to give indication of game over
        g.setPaint(SEMI_TRANS_WHITE);
        BLOCK.setFrame(myX, myY, 
                       myBlockSize * myColumns, myBlockSize * myRows);
        g.fill(BLOCK);
        
        // Draw line around panel
        g.setPaint(YELLOW);
        g.draw(BLOCK);
    }
}
