/*
 * TCSS 305 - Autumn 2017
 * Assignment 6 - Tetris
 */

package controller;

/**
 * The different game status possibilities.
 * 
 * @author Nicole Kauer (nmekauer@uw.edu)
 * @version 9 December 2017
 */
public enum Status
{
    /** No game. */
    NO_GAME,
    
    /** New game. */
    NEW_GAME,
    
    /** Game running. */
    RUNNING_GAME,
    
    /** Game that ended. */
    END_GAME,
    
    /** Paused game. */
    PAUSED_GAME,
    
    /** Game replay. */
    REPLAY_GAME;
}
