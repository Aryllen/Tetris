/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.shared;

import controller.Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Initial window that allows user to select what type of Tetris game to play. Initializes 
 * chosen game.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public final class StartFrame extends JFrame 
{
    /**
     * Generated serializable ID. */
    private static final long serialVersionUID = 6383692311623258917L;
    
//    /** Preferred frame size. */
//    private static final Dimension PREFERRED_FRAME_SIZE = new Dimension(500, 300);
    
    /** Yellow. */
    private static final Color YELLOW = new Color(248, 221, 56);
    
//    /** Rigid area dimensions for layout padding. */
//    private static final Dimension RIGID_AREA_SIZE = new Dimension(20, 20);
    
    /** Border stye. */
    private static final Border PANEL_BORDER = 
                    BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, 
                                                     new Color(132, 197, 15), 
                                                     new Color(216, 114, 1));
    
    /** Background color for button panel. */
    private static final Color PANEL_BG = new Color(124, 97, 203);
    
    /** Background color for toggle buttons. */
    private static final Color BUTTON_BG = new Color(132, 197, 15);
    
    /** Number of style options. */
    private static final int NUM_STYLE_OPTIONS = 2;
    
    /** Number of grid options. */
    private static final int NUM_GRID_OPTIONS = 3;
    
    /** Denotes standard Tetris game. */
    private static final String STANDARD_TETRIS_GAME = "Boring";
    
    /** Denotes themed Tetris game. */
    private static final String THEMED_TETRIS_GAME = "Less Boring";
    
    /** Default grid choice. */
    private static final Dimension DEFAULT_GRID = new Dimension(10, 20);    
    
    /** Default grid choice. */
    private static final Dimension SMALL_GRID = new Dimension(5, 10);    
    
    /** Default grid choice. */
    private static final Dimension LARGE_GRID = new Dimension(30, 50);    

    /** The game choice. */
    private String myGameChoice;
    
    /** The grid choice. */
    private Dimension myGridChoice;
    
    /**
     * Constructor.
     */
    public StartFrame()
    {
        super();
        
        myGameChoice = THEMED_TETRIS_GAME;
        myGridChoice = DEFAULT_GRID;
        
        setup();
    }
    
    /**
     * Sets up the GUI.
     */
    private void setup()
    {      
        final JPanel optionsPanel = createGameOptionButtons();
        final JPanel gridPanel = createGridOptionButtons();

        final JPanel confirmPanel = new JPanel();
        final JButton confirmButton = new JButton("Start");
        confirmButton.addActionListener(new StartListener());       
        confirmButton.setBackground(BUTTON_BG);
        confirmPanel.add(confirmButton);
        confirmPanel.setBackground(PANEL_BG);
      
        final Box buttonBox = new Box(BoxLayout.Y_AXIS);

        buttonBox.add(optionsPanel);
        buttonBox.add(gridPanel);
        buttonBox.add(confirmPanel);
        
        add(buttonBox, BorderLayout.CENTER);
        
        getContentPane().setBackground(YELLOW);
        
        final ImageIcon iconImage = new ImageIcon((URL) getClass().getResource("logo.png"));
        setIconImage(iconImage.getImage());
        
        // At current size, without the super cute Marvin, entire title isn't visible
//        setTitle("Hitchhiker's Guide to the Tetris Galaxy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * Creates the panel for game option buttons.
     * 
     * @return the game option panel
     */
    private JPanel createGameOptionButtons()
    {
        final JPanel optionPanel = new JPanel();
        optionPanel.setBackground(PANEL_BG);
        optionPanel.setLayout(new GridLayout(NUM_STYLE_OPTIONS, 0));
        
        // Border with title
        final TitledBorder optionBorder = 
                        BorderFactory.createTitledBorder(PANEL_BORDER, "Style Options", 
                                      TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, 
                                      new Font(Font.SANS_SERIF, Font.BOLD, 12), Color.WHITE);
        optionPanel.setBorder(optionBorder);
        
        /**
         * Game option action.
         */
        class GameOptionAction extends AbstractAction
        {
            /** Generated serializable ID. */
            private static final long serialVersionUID = -2910514219762441991L;
                        
            /**
             * Constructor.
             * 
             * @param theName the name of the game option
             */
            GameOptionAction(final String theName)
            {
                super();
                
                putValue(Action.NAME, theName);
                putValue(Action.SELECTED_KEY, true);
            }

            /**
             * Sets the game option.
             * 
             * @param theEvent ignored
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                setGameChoice((String) this.getValue(Action.NAME));
            }
        }
        
        // Buttons
        final JToggleButton themedOption = 
                        new JToggleButton(new GameOptionAction(THEMED_TETRIS_GAME));
        final JToggleButton standardOption = 
                        new JToggleButton(new GameOptionAction(STANDARD_TETRIS_GAME));
        
        standardOption.setBackground(BUTTON_BG);
        standardOption.setForeground(Color.WHITE);
        themedOption.setBackground(BUTTON_BG);
        themedOption.setForeground(Color.WHITE);
        
        // Button group
        final ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(themedOption);
        optionGroup.add(standardOption);
                
        optionPanel.add(standardOption);
        optionPanel.add(themedOption);
        
        // Add panel to frame
        return optionPanel;
    }
    
    /**
     * Creates the panel with buttons for grid options.
     * 
     * @return the grid options panel
     */
    private JPanel createGridOptionButtons()
    {
        final JPanel gridPanel = new JPanel();
        gridPanel.setBackground(PANEL_BG);
        gridPanel.setLayout(new GridLayout(NUM_GRID_OPTIONS, 0));
        
        // Border with title
        final TitledBorder gridBorder = 
                        BorderFactory.createTitledBorder(PANEL_BORDER, "Grid Options", 
                                       TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, 
                                       new Font(Font.SANS_SERIF, Font.BOLD, 12), Color.WHITE);
        gridPanel.setBorder(gridBorder);
        
        /**
         * Grid option action.
         */
        class GridOptionAction extends AbstractAction
        {
            /** Generated serializable ID. */
            private static final long serialVersionUID = -2910514219762441991L;
            
            /** Grid size. */
            private final Dimension myDimension;
            
            /**
             * Constructor.
             * 
             * @param theName the name of the game option
             * @param theDimension the grid size
             */
            GridOptionAction(final String theName, final Dimension theDimension)
            {
                super();
                
                myDimension = theDimension;
                
                putValue(Action.NAME, theName);
                putValue(Action.SELECTED_KEY, true);
            }

            /**
             * Sets the grid option.
             * 
             * @param theEvent ignored
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                setGridChoice(myDimension);
            }
        }
        
        // Buttons
        final JToggleButton standardOption = new JToggleButton(
                                new GridOptionAction("Nothing New (10x20)", DEFAULT_GRID));
        final JToggleButton smallerOption = new JToggleButton(
                                new GridOptionAction("Ridiculously Small (5x10)", SMALL_GRID));
        final JToggleButton biggerOption = new JToggleButton(
                                new GridOptionAction("Obscenely Large (30x50)", LARGE_GRID));
        
        standardOption.setBackground(BUTTON_BG);
        standardOption.setForeground(Color.WHITE);
        smallerOption.setBackground(BUTTON_BG);
        smallerOption.setForeground(Color.WHITE);
        biggerOption.setBackground(BUTTON_BG);
        biggerOption.setForeground(Color.WHITE);
        
        // Button group
        final ButtonGroup gridSizeGroup = new ButtonGroup();
        gridSizeGroup.add(standardOption);
        gridSizeGroup.add(smallerOption);
        gridSizeGroup.add(biggerOption);
        
        gridPanel.add(smallerOption);
        gridPanel.add(standardOption);
        gridPanel.add(biggerOption);
                        
        return gridPanel;
    }
    
    /** 
     * Sets the game choice.
     * 
     * @param theChoice the game choice
     */
    private void setGameChoice(final String theChoice)
    {
        myGameChoice = theChoice;
    }
    
    /**
     * Sets the grid choice.
     * 
     * @param theSize the size of the grid
     */
    private void setGridChoice(final Dimension theSize)
    {
        myGridChoice = theSize;
    }
    
    /**
     * Closes frame.
     */
    private void disposeWindow()
    {
        dispose();
    }
    
    /**
     * Listener for start button.
     * 
     * @author Nicole Kauer (nmekauer@uw.edu)
     * @version 9 December 2017
     */
    public class StartListener implements ActionListener
    {

        /**
         * Starts the game and disposes of the window.
         * 
         * @param theEvent ignored
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent)
        {
            new Game(myGameChoice, myGridChoice);    
            disposeWindow();
        }
        
    }
    
}
