/*TCSS 305 Winter 2014
*Assignment 5 - Power Paint 
*/
package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * A GUI tool that creates and returns a line with.
 * coordinates from the GUI
 * 
* @author Eva Revear
* @version 2 - 8 - 14
*
*/
public class Line implements Tool {
    
    /**An x coordinate set from a mouse press. */
    private double myInitialX;
    
    /**A y coordinate set from a mouse press. */
    private double myInitialY;
    
    /**An x coordinate set from a mouse release or drag. */
    private double mySecondX;
    
    /**A y coordinate set from a mouse release or drag.  */
    private double mySecondY;
    
    /**A color used to draw the line. */
    private Color myColor;
    
    /**A constructor that sets the initial values of the fields. */
    public Line() {
        myInitialX = 0;
        
        myInitialY = 0;
        
        mySecondX = 0;
        
        mySecondY = 0;
        
        myColor = Color.black;
    }

    @Override
    /**A setter that sets the first x value.
     * @param theX the x coordinate from a mouse press. */
    public void setInitialX(final int theX) {
        myInitialX = theX;
    }
    
    @Override
    /**A setter that sets the first x value.
     * @param theColor the color to draw the line in */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    @Override
    /**An accessor method that returns the color.
     * @return a color. */
    public Color getColor() {
        return myColor;
    }

    @Override
    /**A setter that sets the first y value.
     * @param theY the y coordinate from a mouse press. */
    public void setInitialY(final int theY) {
        myInitialY = theY;
    }

    @Override
    /**A setter that sets the second x value.
     * @param theX the x coordinate from a mouse release or drag. */
    public void setSecondX(final int theX) {
        mySecondX = theX;
    }

    @Override
    /**A setter that sets the second x value.
     * @param theY the y coordinate from a mouse release. */
    public void setSecondY(final int theY) {
        mySecondY = theY;
    }
    
    @Override
    /**An accessor method that returns the second x value.
     * @return an integer. */
    public double getSecondX() {
        return mySecondX;
    }
    
    @Override
    /**An accessor method that returns the second y value.
     * @return an integer. */
    public double getSecondY() {
        return mySecondY;
    }
    
    @Override
    /**An accessor method that returns the first x value.
     * @return an integer. */
    public double getInitialX() {
        return myInitialX;
    }
    
    @Override
    /**An accessor method that returns the width
     * as the second x minus the first one.
     * @return an integer. */
    public int getWidth() {
        return (int) mySecondX - (int) myInitialX;
    }
    
    @Override
    /**An accessor method that returns the height
     * as the second y minus the first one.
     * @return an integer. */
    public int getHeight() {
        return (int) mySecondY - (int) myInitialY;
    }
    
    /**This method makes and returns a line with the given coordinates.
     * @return a line as a shape object */
    public Shape makeShape() {
        final Line2D.Double line = new Line2D.Double(myInitialX, 
                                                     myInitialY, mySecondX, mySecondY);
       
        return line;
    }
    
    @Override
    /**returns line as a string.
     * @return line */
    public String toString() {
        return "line";
    }

}