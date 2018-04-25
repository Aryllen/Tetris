/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.themed;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import view.shared.AbstractScoringPanel;

/**
 * Scoreboard panel for themed Tetris.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public class ScoringPanelThemed extends AbstractScoringPanel
{
    /** Generated serializable ID. */
    private static final long serialVersionUID = -4884699428582676513L;
    
    /** Blue-white. */
    private static final Color BLUE_WHITE = new Color(204, 232, 252);

    /** Yellow. */
    private static final Color YELLOW = new Color(248, 221, 56);
    
    /** Dark blue. */
    private static final Color DARK_BLUE = new Color(45, 109, 201);
    
    /** Light blue. */
    private static final Color LIGHT_BLUE = new Color(105, 161, 254);
    
    /** Dark purple. */
    private static final Color DARK_PURPLE = new Color(87, 50, 183);
    
    /** Light purple. */
    private static final Color LIGHT_PURPLE = new Color(124, 97, 203);
    
    /** Dark green. */
    private static final Color DARK_GREEN = new Color(98, 150, 15);
    
    /** Light green. */
    private static final Color LIGHT_GREEN = new Color(132, 197, 15);
    
    /** Dark orange. */
    private static final Color DARK_ORANGE = new Color(216, 114, 1);
    
    /** Light orange. */
    private static final Color LIGHT_ORANGE = new Color(251, 146, 3);
    
    /** Preferred size. */
    private static final Dimension PREFERRED_SIZE = new Dimension(240, 140);
    
    /** Right bar x offset. */
    private static final int BAR_OFFSET_X_RIGHT = 70;
    
    /** Right circle x offset. */
    private static final int CIRCLE_OFFSET_X_RIGHT = 205;
    
    /** Right bar length. */
    private static final int BAR_LENGTH_RIGHT = 150;
    
    /** Vertical padding for line 1. */
    private static final int VERT_PADDING_LINE_1 = 10;
    
    /** Bar height. */
    private static final int BAR_HEIGHT = 25;
    
    /** Left circle offset for short bars. */
    private static final int CIRCLE_OFFSET_X_LEFT_SHORT = 60;
    
    /** Vertical padding for line 2. */
    private static final int VERT_PADDING_LINE_2 = 35;
    
    /** Vertical padding for line 3. */
    private static final int VERT_PADDING_LINE_3 = 60;
    
    /** Vertical padding for line 4. */
    private static final int VERT_PADDING_LINE_4 = 85;
    
    /** Left bar length for long bars. */
    private static final int BAR_LENGTH_LEFT = 145;
    
    /** Left circle offset for long bars. */
    private static final int CIRCLE_OFFSET_X_LEFT_LONG = 135;
                    
    /** Padding left of text. */
    private static final int LEFT_PADDING = 10;
    
    /** Padding left of number text for score and level. */
    private static final int LEFT_NUM_PADDING_SHORT = 100;
    
    /** Padding left of number text for rows cleared and next level. */
    private static final int LEFT_NUM_PADDING_LONG = 175;
    
    /** Padding above text for first line. */
    private static final int VERTICAL_FIRST_LINE_PADDING = 30;
    
    /** Padding above text for second line. */
    private static final int VERTICAL_SECOND_LINE_PADDING = 55;
    
    /** Padding above text for first line. */
    private static final int VERTICAL_THIRD_LINE_PADDING = 80;
    
    /** Padding above text for second line. */
    private static final int VERTICAL_FOURTH_LINE_PADDING = 105;
    
    /** Font size. */
    private static final int FONT_SIZE = 20;
    
    /** Font. */
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, FONT_SIZE);
    
    /** Block piece to paint. */
    private static final Rectangle2D BLOCK = new Rectangle2D.Double();
    
    /** Ellipse piece to paint. */
    private static final Ellipse2D CIRCLE = new Ellipse2D.Double();

    /**
     * Constructor.
     */
    public ScoringPanelThemed()
    {
        super();

        setup();
    }
    
    /**
     * Sets up the panel. 
     */
    private void setup()
    {
        setPreferredSize(PREFERRED_SIZE);
        setMaximumSize(PREFERRED_SIZE);
        setBackground(YELLOW);
    }
    
    /* Graphics */
    
    /** 
     * Paints score board.
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
   
        // Draw text
        g.setFont(FONT);
        
        paintBackground(g);
        
//      g.setColor(new Color(248, 221, 56)); // Yellow
        g.setColor(BLUE_WHITE.brighter()); // Blue-white brighter
//      g.setColor(Color.WHITE);  
        
        // Score 
        g.drawString(NF.format(myScore), LEFT_NUM_PADDING_SHORT, VERTICAL_FIRST_LINE_PADDING);
        
        // Level
        g.drawString(NF.format(myLevel), LEFT_NUM_PADDING_SHORT, VERTICAL_SECOND_LINE_PADDING);
        
        // Rows cleared
        g.drawString(NF.format(myRowsCleared), LEFT_NUM_PADDING_LONG, 
                     VERTICAL_THIRD_LINE_PADDING);
        
        // Rows until level up
        g.drawString(NF.format(Math.abs(myRowsCleared % LEVEL_UP - LEVEL_UP)), 
                     LEFT_NUM_PADDING_LONG, VERTICAL_FOURTH_LINE_PADDING);
        
    }
    
    /**
     * Paints background items that do not change.
     * 
     * @param theGraphics the graphics
     */
    private void paintBackground(final Graphics2D theGraphics)
    {
        final Graphics2D g = theGraphics;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        // Score 
        
        // Right side
        g.setColor(DARK_PURPLE);
        BLOCK.setFrame(BAR_OFFSET_X_RIGHT, VERT_PADDING_LINE_1, BAR_LENGTH_RIGHT, BAR_HEIGHT);
        CIRCLE.setFrame(CIRCLE_OFFSET_X_RIGHT, VERT_PADDING_LINE_1, BAR_HEIGHT, BAR_HEIGHT);
        g.fill(BLOCK);
        g.fill(CIRCLE);
        // Left side
        g.setColor(LIGHT_PURPLE);
        BLOCK.setFrame(0, VERT_PADDING_LINE_1, BAR_OFFSET_X_RIGHT, BAR_HEIGHT);
        CIRCLE.setFrame(CIRCLE_OFFSET_X_LEFT_SHORT, VERT_PADDING_LINE_1, 
                                                                BAR_HEIGHT, BAR_HEIGHT);
        g.fill(BLOCK);
        g.fill(CIRCLE);
        
        // Level
        
        // Right side
        g.setColor(DARK_BLUE);
        BLOCK.setFrame(BAR_OFFSET_X_RIGHT, VERT_PADDING_LINE_2, BAR_LENGTH_RIGHT, BAR_HEIGHT);
        CIRCLE.setFrame(CIRCLE_OFFSET_X_RIGHT, VERT_PADDING_LINE_2, BAR_HEIGHT, BAR_HEIGHT);
        g.fill(BLOCK);
        g.fill(CIRCLE);
        // Left side
        g.setColor(LIGHT_BLUE);
        BLOCK.setFrame(0, VERT_PADDING_LINE_2, BAR_OFFSET_X_RIGHT, BAR_HEIGHT);
        CIRCLE.setFrame(CIRCLE_OFFSET_X_LEFT_SHORT, VERT_PADDING_LINE_2, 
                                                                BAR_HEIGHT, BAR_HEIGHT);
        g.fill(BLOCK);
        g.fill(CIRCLE);
        
        // Rows cleared
        
        // Right side
        g.setColor(DARK_GREEN);
        BLOCK.setFrame(BAR_OFFSET_X_RIGHT, VERT_PADDING_LINE_3, BAR_LENGTH_RIGHT, BAR_HEIGHT);
        CIRCLE.setFrame(CIRCLE_OFFSET_X_RIGHT, VERT_PADDING_LINE_3, BAR_HEIGHT, BAR_HEIGHT);
        g.fill(BLOCK);
        g.fill(CIRCLE);
        // Left side
        g.setColor(LIGHT_GREEN);
        BLOCK.setFrame(0, VERT_PADDING_LINE_3, BAR_LENGTH_LEFT, BAR_HEIGHT);
        CIRCLE.setFrame(CIRCLE_OFFSET_X_LEFT_LONG, VERT_PADDING_LINE_3, 
                                                                BAR_HEIGHT, BAR_HEIGHT);
        g.fill(BLOCK);
        g.fill(CIRCLE);
        
        // Next level
        
        // Right side
        g.setColor(DARK_ORANGE);
        BLOCK.setFrame(BAR_OFFSET_X_RIGHT, VERT_PADDING_LINE_4, BAR_LENGTH_RIGHT, BAR_HEIGHT);
        CIRCLE.setFrame(CIRCLE_OFFSET_X_RIGHT, VERT_PADDING_LINE_4, BAR_HEIGHT, BAR_HEIGHT);
        g.fill(BLOCK);
        g.fill(CIRCLE);
        // Left side
        g.setColor(LIGHT_ORANGE);
        BLOCK.setFrame(0, VERT_PADDING_LINE_4, BAR_LENGTH_LEFT, BAR_HEIGHT);
        CIRCLE.setFrame(CIRCLE_OFFSET_X_LEFT_LONG, VERT_PADDING_LINE_4, 
                                                                BAR_HEIGHT, BAR_HEIGHT);
        g.fill(BLOCK);
        g.fill(CIRCLE);
        
        // Text 
//        g.setColor(new Color(248, 221, 56)); // Yellow
        g.setColor(BLUE_WHITE.brighter()); // Blue-white brighter
//        g.setColor(Colsor.WHITE);
        
        // Score
        g.drawString("Score: ", LEFT_PADDING, VERTICAL_FIRST_LINE_PADDING);
        
        // Level
        g.drawString("Level: ", LEFT_PADDING, VERTICAL_SECOND_LINE_PADDING);
        
        // Rows cleared
        g.drawString("Rows Cleared: ", LEFT_PADDING, VERTICAL_THIRD_LINE_PADDING);
        
        // Rows until level up
        g.drawString("Next Level In: ", LEFT_PADDING, VERTICAL_FOURTH_LINE_PADDING);
                
        
    }
    
}
