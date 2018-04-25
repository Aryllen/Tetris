/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.themed;

import controller.Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.Board;
import view.shared.AbstractTetrisFrame;

/**
 * Main GUI frame for Hitchhiker's Guide to the Galaxy - themed Tetris.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 2 December 2017
 */
public class TetrisFrameThemed extends AbstractTetrisFrame
{    
    /** Generated serializable ID. */
    private static final long serialVersionUID = -7212628903809884318L;

    /** Preferred size. */
    private static final Dimension PREFERRED_SIZE_THEMED = new Dimension(700, 500);
    
    /** Rigid area dimensions for layout padding. */
    private static final Dimension RIGID_AREA_SIZE = new Dimension(240, 20);
    
    /** Yellow. */
    private static final Color YELLOW = new Color(248, 221, 56);

    /** 
     * Constructor. 
     * 
     * @param theGame the game
     * @param theBoard the game board
     */
    public TetrisFrameThemed(final Game theGame, final Board theBoard)
    {
        super(theGame, theBoard);
        
        setup();
    }
    
    /**
     * Setup the GUI elements and add to the frame for a standard version of the game.
     */
    private void setup()
    {        
        /* Menu bar */
        createMenuBar();
        
        /* Information panel stuff */
        
        // Box to hold information panels
        final Box infoBox = new Box(BoxLayout.Y_AXIS);

        myPreviewPanel = new PreviewPanelThemed();        

        myScoringPanel = new ScoringPanelThemed();
        
        infoBox.add(Box.createRigidArea(RIGID_AREA_SIZE));
        infoBox.add(myPreviewPanel);
        infoBox.add(Box.createRigidArea(RIGID_AREA_SIZE));
        infoBox.add(myScoringPanel);
        infoBox.add(Box.createRigidArea(RIGID_AREA_SIZE));
        
        add(infoBox, BorderLayout.WEST);
        
        /* Game board stuff */
        
        // Box for holding game board
        final Box gameBox = new Box(BoxLayout.Y_AXIS);
        
        // Create board panel and add to frame
        myBoardPanel = new BoardPanelThemed(myGame.getColumns(), myGame.getRows());
        
        gameBox.add(myBoardPanel);
        
        add(gameBox, BorderLayout.CENTER);
  
        /* Making it all work together */
        
        // Register observers and listeners for non-local variables
        registerListeners();
        registerBoardObservers();
        registerGameObservers();
        
        /* Window settings */
        
        getContentPane().setBackground(YELLOW);
        
        final ImageIcon iconImage = new ImageIcon((URL) getClass().getResource("logo.png"));
        setIconImage(iconImage.getImage());
        
        setTitle("Hitchhiker's Guide to the Tetris Galaxy: Less Boring Version");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(PREFERRED_SIZE_THEMED);
        setSize(PREFERRED_SIZE_THEMED);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
