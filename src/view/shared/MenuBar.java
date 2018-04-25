/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package view.shared;

import controller.Game;
import controller.Status;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * Menu Bar class for Tetris GUI.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 2 December 2017
 */
public final class MenuBar extends JMenuBar implements Observer
{
    /** Generated serializable ID. */
    private static final long serialVersionUID = -111659266189879540L;
    
    /** The game. */
    private final Game myGame;
    
    /** The frame. */
    private final AbstractTetrisFrame myFrame;
    
    /** The logo URL. */
    private final URL myIconURL = (URL) getClass().getResource("logo.png");
    
    /** New game item. */
    private JMenuItem myNewGameItem;
    
    /** Pause game item. */
    private JMenuItem myPauseGameItem;
    
    /** End game item. */
    private JMenuItem myEndGameItem;
    
    /** Replay game item. */
    private JMenuItem myReplayGameItem;
    
    /** Options menu. */
    private JMenu myOptionsMenu;
    
    /**
     * Constructor.
     * 
     * @param theGame the game
     * @param theFrame the frame that has the menu bar
     */
    public MenuBar(final Game theGame, final AbstractTetrisFrame theFrame)
    {
        // Calls JMenuBar default constructor
        super();
        
        // Want exact game
        myGame = theGame;
        myFrame = theFrame;
        
        // Setup and add the MenuBar elements
        setup();
    }
    
    /* Setup methods */
    
    /**
     * Setup and add the Tetris MenuBar elements.
     */
    private void setup()
    {
        add(createGameMenu());
        add(createOptionsMenu());
        add(createHelpMenu());
    }
    
    //--------------------------------------------------------------------------------------//
    
    /**
     * Creates the Game menu for the menu bar.
     * 
     * @return the Game menu
     */
    private JMenu createGameMenu()
    {
        final JMenu gameMenu = new JMenu("Game");
        
        myNewGameItem = createNewGameItem();
        myPauseGameItem = createPauseGameItem();
        myEndGameItem = createEndGameItem();
        myReplayGameItem = createReplayGameItem();
        final JMenuItem exitItem = createExitItem();
        
        myPauseGameItem.setEnabled(false);
        myEndGameItem.setEnabled(false);
        myReplayGameItem.setEnabled(false);
        
        gameMenu.add(myNewGameItem);
        gameMenu.add(myPauseGameItem);
        gameMenu.add(myEndGameItem);
        gameMenu.addSeparator();
        gameMenu.add(myReplayGameItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);
        
        return gameMenu;
    }
    
    /** 
     * Creates the new game item.
     * 
     * @return the new game item
     */
    private JMenuItem createNewGameItem()
    {
        final JMenuItem newGameItem = new JMenuItem("New Game");
        
        /**
         * Listener class for starting a new game.
         * 
         * @author Nicole Kauer (nmekauer@uw.edu)
         * @version 9 December 2017
         */
        class NewGameListener implements ActionListener
        {
            @Override
            public void actionPerformed(final ActionEvent theArg)
            {
                myGame.startNewGame();                
            }   
        }
        
        newGameItem.addActionListener(new NewGameListener());
        newGameItem.setMnemonic(KeyEvent.VK_N);
        final KeyStroke newGameKey = KeyStroke.getKeyStroke(KeyEvent.VK_N, 
                                                            InputEvent.CTRL_MASK);
        newGameItem.setAccelerator(newGameKey);
        
        return newGameItem;
    }
    
    /**
     * Creates the pause game item.
     * 
     * @return the pause game item
     */
    private JMenuItem createPauseGameItem()
    {
        final JMenuItem pauseGameItem = new JMenuItem("Pause Game");
        
        /**
         * Listener for pausing the game.
         * 
         * @author Nicole Kauer (nmekauer@uw.edu)
         * @version 9 December 2017
         */
        class PauseGameListener implements ActionListener
        {
            @Override
            public void actionPerformed(final ActionEvent theArg)
            {
                myGame.pauseGame();                
            }   
        }
        
        pauseGameItem.addActionListener(new PauseGameListener());
        pauseGameItem.setMnemonic(KeyEvent.VK_P);
        final KeyStroke pauseGameKey = KeyStroke.getKeyStroke(KeyEvent.VK_P, 
                                                            InputEvent.CTRL_MASK);
        pauseGameItem.setAccelerator(pauseGameKey);
        
        return pauseGameItem;
    }
    
    /**
     * Creates the item to end the game. 
     * 
     * @return the end game item
     */
    private JMenuItem createEndGameItem()
    {
        final JMenuItem endGameItem = new JMenuItem("End Game");
        
        /**
         * Listener for ending the game.
         * 
         * @author Nicole Kauer (nmekauer@uw.edu)
         * @version 9 December 2017
         */
        class EndGameListener implements ActionListener
        {
            @Override
            public void actionPerformed(final ActionEvent theArg)
            {
                myGame.stopGame();                
            }   
        }
        
        endGameItem.addActionListener(new EndGameListener());
        endGameItem.setMnemonic(KeyEvent.VK_E);
        final KeyStroke endGameKey = KeyStroke.getKeyStroke(KeyEvent.VK_E, 
                                                            InputEvent.CTRL_MASK);
        endGameItem.setAccelerator(endGameKey);
        
        return endGameItem;
    }
    
    /**
     * Creates the replay game item.
     * 
     * @return the replay game item
     */
    private JMenuItem createReplayGameItem()
    {
        final JMenuItem replayGameItem = new JMenuItem("Replay Game");
        
        /**
         * Listener for replaying the game.
         * 
         * @author Nicole Kauer (nmekauer@uw.edu)
         * @version 9 December 2017
         */
        class ReplayGameListener implements ActionListener
        {
            @Override
            public void actionPerformed(final ActionEvent theArg)
            {
                myGame.replayGame();                
            }   
        }
        
        replayGameItem.addActionListener(new ReplayGameListener());
//        replayGameItem.setMnemonic(KeyEvent.VK_R);
//        final KeyStroke replayGameKey = KeyStroke.getKeyStroke(KeyEvent.VK_R, 
//                                                            InputEvent.CTRL_MASK);
//        replayGameItem.setAccelerator(replayGameKey);
        
        return replayGameItem;
    }
    
    /**
     * Creates the exit program item.
     * 
     * @return the exit item
     */
    private JMenuItem createExitItem()
    {
        final JMenuItem exitItem = new JMenuItem("Exit");
        
        /**
         * Listener to exit the program from menu item.
         * 
         * @author Nicole Kauer (nmekauer@uw.edu)
         * @version 9 December 2017
         */
        class ExitListener implements ActionListener
        {
            @Override
            public void actionPerformed(final ActionEvent theArg)
            {
                myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            }
        }
        
        exitItem.addActionListener(new ExitListener());
        exitItem.setMnemonic(KeyEvent.VK_X);
        final KeyStroke exitKey = KeyStroke.getKeyStroke(KeyEvent.VK_X, 
                                                            InputEvent.CTRL_MASK);
        exitItem.setAccelerator(exitKey);
        
        return exitItem;        
    }
    
    //--------------------------------------------------------------------------------------//
    
    /**
     * Creates the Options menu for the menu bar.
     * 
     * @return the Options menu
     */
    private JMenu createOptionsMenu()
    {
        myOptionsMenu = new JMenu("Options");
        
        final JMenuItem changeItem = createChangeItem();
        
        
        
        
//        final JMenuItem standard = new JMenuItem("Standard");
        
        myOptionsMenu.add(changeItem);
        
        return myOptionsMenu;
    }
    
    /**
     * Creates the item to allow user to switch themes or grid size.
     * 
     * @return the change theme/grid item
     */
    private JMenuItem createChangeItem()
    {
        final JMenuItem changeItem = new JMenuItem("Change Theme/Grid Size");
        
        /**
         * Listener for change item. Opens original starting frame and disposes game frame.
         * 
         * @author Nicole Kauer (nmekauer@uw.edu)
         * @version 9 December 2017
         */
        class ChangeGameListener implements ActionListener
        {

            /** 
             * Opens the starting frame and closes game frame.
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {
                new StartFrame();
                myFrame.dispose();
            }
            
        }
        
        changeItem.addActionListener(new ChangeGameListener());
        
        return changeItem;
    }
    
    //--------------------------------------------------------------------------------------//
    
    /**
     * Creates the Help menu for the menu bar.
     * 
     * @return the Help menu
     */
    private JMenu createHelpMenu()
    {
        final JMenu helpMenu = new JMenu("Help");
        final JMenuItem instructions = new JMenuItem("Instructions");
        final JMenuItem scoring = new JMenuItem("Scoring");
        final JMenuItem about = new JMenuItem("About");
        
        instructions.addActionListener(new InstructionsMessage());
        scoring.addActionListener(new ScoringMessage());
        about.addActionListener(new AboutMessage());
        
        helpMenu.add(instructions);
        helpMenu.add(scoring);
        helpMenu.addSeparator();
        helpMenu.add(about);
        
        return helpMenu;
    }

    /** 
     * Updates the enabling of menu items.
     */
    @Override
    public void update(final Observable theObject, final Object theArg)
    {
        if (theArg instanceof Status)
        {
            if (theArg == Status.NO_GAME)
            {
                myNewGameItem.setEnabled(true);
                myPauseGameItem.setEnabled(false);
                myEndGameItem.setEnabled(false);
                myReplayGameItem.setEnabled(false);
            }
            else if (theArg == Status.RUNNING_GAME)
            {
                myNewGameItem.setEnabled(false);
                myPauseGameItem.setEnabled(true);
                myEndGameItem.setEnabled(true);
                myReplayGameItem.setEnabled(false);
                
                myOptionsMenu.setEnabled(false);
            }
            else if (theArg == Status.END_GAME)
            {
                myNewGameItem.setEnabled(true);
                myPauseGameItem.setEnabled(false);
                myEndGameItem.setEnabled(false);
                myReplayGameItem.setEnabled(true);
                
                myOptionsMenu.setEnabled(true);
            }
        }
    }
    
    /* Inner class ActionListeners */
    
    /** 
     * Action listener class for help menu's Instructions.
     */
    public class InstructionsMessage implements ActionListener
    {
        /**
         * Displays message with instructions.
         * 
         * @param theEvent ignored
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent)
        {
            final JOptionPane instructionsPane = new JOptionPane();
            
            instructionsPane.setIcon(new ImageIcon(myIconURL));
            instructionsPane.setMessage("INSTRUCTIONS \n\n"
                                    + "Move left:         'A'  or  '\u2190'\n"
                                    + "Move right:      'D'  or  '\u2192'\n"
                                    + "Move down:    'S'  or  '\u2193'\n"
                                    + "Rotate:             'W'  or  '\u2191'\n"
                                    + "Drop:                 'spacebar'\n"
                                    + "\n"
                                    + "New game:       'ctrl' + 'N'\n"
                                    + "Pause game:   'ctrl' + 'P'\n"
                                    + "End game:        'ctrl' + 'E'\n");
            instructionsPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
          
            final JDialog dialog = instructionsPane.createDialog(null, "Tetris Instructions");
            dialog.setVisible(true);
        }
        
    }
    
    /** 
     * Action listener class for help menu's About.
     */
    public class AboutMessage implements ActionListener
    {
        /**
         * Displays message with instructions.
         * 
         * @param theEvent ignored
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent)
        {
            final JOptionPane aboutPane = new JOptionPane();
            
            aboutPane.setIcon(new ImageIcon(myIconURL));
            aboutPane.setMessage("TETRIS \n\n"
                                    + "TCSS 305 \n"
                                    + "Autumn 2017 \n\n"
                                    + "GUI & Artwork: Nicole Kauer\n"
                                    + "Basic Game Mechanics: TCSS 305 Instructors\n"
                                    + "Hitchhiker's Guide to the Galaxy copyright "
                                    + "Douglas Adams");
            aboutPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
          
            final JDialog dialog = aboutPane.createDialog(null, "About Tetris");
            dialog.setVisible(true);
        }
        
    }
    
    /** 
     * Action listener class for help menu's Scoring.
     */
    public class ScoringMessage implements ActionListener
    {
        /**
         * Displays message with instructions.
         * 
         * @param theEvent ignored
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent)
        {
            final JOptionPane scoringPane = new JOptionPane();
            
            scoringPane.setIcon(new ImageIcon(myIconURL));
            scoringPane.setMessage("SCORING \n\n"
                                    + "1 Row Cleared:     42 * Current Level \n"
                                    + "2 Rows Cleared:   42 * 10 * Current Level \n"
                                    + "3 Rows Cleared:   42 * 100 * Current Level \n"
                                    + "4 Rows Cleared:   42 * 1000 * Current Level \n\n");
            scoringPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
          
            final JDialog dialog = scoringPane.createDialog(null, "Tetris Scoring");
            dialog.setVisible(true);
        }
        
    }
}
