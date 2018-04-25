/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import view.shared.AbstractScoringPanel;

/**
 * Scoring panel for Tetris GUI.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 2 December 2017
 */
public final class ScoringPanel extends AbstractScoringPanel
{    
    /** Generated serializable ID. */
    private static final long serialVersionUID = -5477908524847486223L;

    /** Preferred size. */
    private static final Dimension PREFERRED_SIZE = new Dimension(200, 140);
    
    /** Padding left of text. */
    private static final int LEFT_PADDING = 10;
    
    /** Padding left of number text for score and level. */
    private static final int LEFT_NUM_PADDING_SHORT = 85;
    
    /** Padding left of number text for rows cleared and next level. */
    private static final int LEFT_NUM_PADDING_LONG = 160;
    
    /** Padding above text for first line. */
    private static final int VERTICAL_FIRST_LINE_PADDING = 30;
    
    /** Padding above text for second line. */
    private static final int VERTICAL_SECOND_LINE_PADDING = 60;
    
    /** Padding above text for first line. */
    private static final int VERTICAL_THIRD_LINE_PADDING = 90;
    
    /** Padding above text for second line. */
    private static final int VERTICAL_FOURTH_LINE_PADDING = 120;
    
    /** Font size. */
    private static final int FONT_SIZE = 20;
    
    /** Font. */
    private static final Font FONT = new Font(Font.SERIF, Font.BOLD, FONT_SIZE);
    
    /**
     * Constructor.
     */
    public ScoringPanel()
    {
        super();

        setup();
    }
    
    /* Setup methods */
    
    /**
     * Sets up the panel. 
     */
    private void setup()
    {
        setPreferredSize(PREFERRED_SIZE);
        setMaximumSize(PREFERRED_SIZE);
        setBackground(Color.LIGHT_GRAY);
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
        
        g.setPaint(Color.BLACK);
        
        // Draw box around edge of panel
        g.draw(new Rectangle2D.Double(0, 0, 
                                      PREFERRED_SIZE.getWidth() - 1, 
                                      PREFERRED_SIZE.getHeight() - 1));
        
        // Draw text
        g.setFont(FONT);
        
        // Score 
        g.drawString("Score: ", LEFT_PADDING, VERTICAL_FIRST_LINE_PADDING);
        g.drawString(NF.format(myScore), LEFT_NUM_PADDING_SHORT, VERTICAL_FIRST_LINE_PADDING);
        
        // Level
        g.drawString("Level: ", LEFT_PADDING, VERTICAL_SECOND_LINE_PADDING);
        g.drawString(NF.format(myLevel), LEFT_NUM_PADDING_SHORT, VERTICAL_SECOND_LINE_PADDING);
        
        // Rows cleared
        g.drawString("Rows Cleared: ", LEFT_PADDING, VERTICAL_THIRD_LINE_PADDING);
        g.drawString(NF.format(myRowsCleared), LEFT_NUM_PADDING_LONG, 
                     VERTICAL_THIRD_LINE_PADDING);
        
        // Rows until level up
        g.drawString("Next Level In: ", LEFT_PADDING, VERTICAL_FOURTH_LINE_PADDING);
        g.drawString(NF.format(Math.abs(myRowsCleared % LEVEL_UP - LEVEL_UP)), 
                     LEFT_NUM_PADDING_LONG, VERTICAL_FOURTH_LINE_PADDING);
        
    }

}
