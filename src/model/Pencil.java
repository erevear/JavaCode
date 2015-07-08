/*TCSS 305 Winter 2014
*Assignment 5 - Power Paint 
*/
package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that creates a pencil line.
 * 
* @author Eva Revear
* @version 2 - 8 - 14
*
*/
public class Pencil implements Tool {
    
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
    
    /**A list of points the general path should follow. */
    private final List<Double> myList;
    /**A constructor that sets the initial values of the fields. */
    public Pencil() {
        myInitialX = 0;
        
        myInitialY = 0;
        
        mySecondX = 0;
        
        mySecondY = 0;
        
        myColor = Color.black;
        
        myList = new ArrayList<Double>();
    }

    @Override
    /**This method sets the initial x value.
     * @param theX the first x value */
    public void setInitialX(final int theX) {
        myInitialX = theX;

    }

    @Override
    /**This method sets the initial y value.
     * @param theY the first y value */
    public void setInitialY(final int theY) {
        myInitialY = theY;

    }
    
    

    @Override
    /**Sets the color of the pencil.
     * @param theColor the color of the pencil*/
    public void setColor(final Color theColor) {
        myColor = theColor;

    }

    @Override
    /**returns the color of the pencil.
     * @return the color of the pencil */
    public Color getColor() {
        return myColor;
    }

    @Override
    /**Sets the second x value and adds it to the list of points.
     * @param theX the second x value */
    public void setSecondX(final int theX) {
        mySecondX = theX;
        myList.add(mySecondX);
    }

    @Override
    /**Sets the second y value and adds it to the list of points.
     * @param theX the second y value */
    public void setSecondY(final int theY) {
        mySecondY = theY;
        myList.add(mySecondY);
    }

    @Override
    /**makes a pencil shape.
     * @return a pencil line to the GUI */
    public Shape makeShape() {
       

        final Line2D.Double line = new Line2D.Double(myInitialX, myInitialY, 
                                                     myInitialX + 1, myInitialY + 1);
       
        final GeneralPath path = new GeneralPath(line);
       
        path.lineTo(mySecondX, mySecondY);
        path.closePath();
      
       
        return path;
    }

    @Override
    /**Returns the second x value of the pencil.
     * @return the second x */
    public double getSecondX() {
        
        return mySecondX;
    }

    @Override
    /**Returns the second y value of the pencil.
     * @return the second y */
    public double getSecondY() {
        return mySecondY;
    }

    @Override
    /**Returns the first x value of the pencil.
     * @return the first x */
    public double getInitialX() {
        return myInitialX;
    }

    @Override
    public int getHeight() {
       
        return 0;
    }

    @Override
    public int getWidth() {
      
        return 0;
    }
    @Override
    /**returns pencil as a string.
     * @return pencil */
    public String toString() {
        return "pencil";
    }

}