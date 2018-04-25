/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.basic;

import controller.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.Board;
import view.shared.AbstractTetrisFrame;

/**
 * Main GUI frame for Tetris.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 2 December 2017
 */
public final class TetrisFrameStandard extends AbstractTetrisFrame
{
    /** Generated serializable ID. */
    private static final long serialVersionUID = -8379154414050682418L;
 
    /** Preferred size. */
    private static final Dimension PREFERRED_SIZE_STANDARD = new Dimension(505, 530);
    
    /** Minimum size. */
    private static final Dimension MINIMUM_SIZE = new Dimension(425, 380);
    
    /** Rigid area dimensions for layout padding. */
    private static final Dimension RIGID_AREA_SIZE = new Dimension(240, 20);
    
    /** Rigid aarea dimensions for layout padding on east side of frame. */
    private static final Dimension EAST_RIGID_SPACING = new Dimension(20, 240);

    /** 
     * Constructor. 
     * 
     * @param theGame the game
     * @param theBoard the game board
     */
    public TetrisFrameStandard(final Game theGame, final Board theBoard)
    {
        // Call AbstractTetrisFrame constructor
        super(theGame, theBoard);
 
        setup();
    }
    
    /* Setup methods */
    
    /**
     * Setup the GUI elements and add to the frame.
     */
    private void setup()
    {        
        /* Menu bar */
        createMenuBar();
        
        /* Information panel stuff */
        
        // Box to hold information panels
        final Box infoBox = new Box(BoxLayout.Y_AXIS);

        myPreviewPanel = new PreviewPanel();        

        myScoringPanel = new ScoringPanel();
        
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
        myBoardPanel = new BoardPanel(myGame.getColumns(), myGame.getRows());
        
        gameBox.add(Box.createRigidArea(RIGID_AREA_SIZE)); // Width is irrelevant in this case
        gameBox.add(myBoardPanel);
        gameBox.add(Box.createRigidArea(RIGID_AREA_SIZE));
        
        add(gameBox, BorderLayout.CENTER);
       
        /* Spacing box */
        
        // Box for keeping a certain amount of space to right of game board
        final Box spaceBox = new Box(BoxLayout.Y_AXIS);
        spaceBox.add(Box.createRigidArea(EAST_RIGID_SPACING));
        
        add(spaceBox, BorderLayout.EAST);
        
        /* Making it all work together */
        
        // Register observers and listeners for non-local variables
        super.registerListeners();
        super.registerBoardObservers();
        super.registerGameObservers();
        
        /* Window settings */
        
        final ImageIcon iconImage = new ImageIcon((URL) getClass().getResource("logo.png"));
        setIconImage(iconImage.getImage());
        
        setTitle("Hitchhiker's Guide to the Tetris Galaxy: Boring Version");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(MINIMUM_SIZE);
        setSize(PREFERRED_SIZE_STANDARD);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
