/*TCSS 305 Winter 2014
*Assignment 5 - Power Paint 
*/

package model;

import view.PowerPaintGUI;

/**
 * A panel that a user can paint.
 * 
* @author Eva Revear
* @version 2 - 8 - 14
*
*/

public final class PowerPaintMain {
    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }
    /**Contains an instance of the powerpaint 
     * gui and calls the start method. 
     * @param theArgs the command line arguments*/
    public static void main(final String[] theArgs) {
        
        
       
        final PowerPaintGUI gui = new PowerPaintGUI();
        gui.start();
    }

}