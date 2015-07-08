/*TCSS 305 Winter 2014
*Assignment 5 - Power Paint 
*/
package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * A class that creates a rectangle.
 * 
* @author Eva Revear
* @version 2 - 8 - 14
*
*/
public class Rectangle implements Tool {
    
    /**This sets the first X coordinate of the rectangle. */
    private double myInitialX;
    
    /**This sets the first Y coordinate of the rectangle. */
    private double myInitialY;
    
    /**This sets the second X coordinate of the rectangle. */
    private double mySecondX;
    
    /**This sets the second Y coordinate of the rectangle. */
    private double mySecondY;
    
    /**This sets the color of the rectangle. */
    private Color myColor;
    
    /**A constructor that assigns initial values to the fields of the rectangle. */
    public Rectangle() {
        myInitialX = 0;
        
        myInitialY = 0;
        
        mySecondX = 0;
        
        mySecondY = 0;
        
        myColor = Color.black;
    }
    
    @Override
    /**This method sets the initial x value of the rectangle.
     * @param theX is the first x value */
    public void setInitialX(final int theX) {
        myInitialX = theX;

    }

    @Override
    /**This method sets the initial y value of the rectangle.
     * @param theY is the first y value */
    public void setInitialY(final int theY) {
        myInitialY = theY;

    }

    @Override
    /**This method sets the second x value of the rectangle.
     * @param theX is the second x value */
    public void setSecondX(final int theX) {
        mySecondX = theX;

    }

    @Override
    /**This method sets the second Y value of the rectangle.
     * @param theY is the secondY value */
    public void setSecondY(final int theY) {
        mySecondY = theY;

    }

    @Override
    /**Makes and returns a rectangle.
     * @return a rectangle based on the specified coordinates from the GUI */
    public Shape makeShape() {
        Rectangle2D.Double rectangle = new Rectangle2D.Double();
        double leftY = 0;
        if (mySecondY > myInitialY) {
            
            leftY = myInitialY;
        }
        if (myInitialY >= mySecondY) {
            leftY = mySecondY;
        }
        if (mySecondX > myInitialX) {
            rectangle = new Rectangle2D.Double(myInitialX, leftY, 
                                               Math.abs(mySecondX - myInitialX), 
                                               Math.abs(mySecondY - myInitialY));
        }
        if (myInitialX >= mySecondX) {
            rectangle = new Rectangle2D.Double(mySecondX, leftY, 
                                               Math.abs(mySecondX - myInitialX), 
                                               Math.abs(mySecondY - myInitialY));
        }
           
        return rectangle;
    }
    
    @Override
    /**This method sets the color of the rectangle.
     * @param theColor the color of the triangle */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    @Override
    /**accesses the color of the triangle.
     * @return the color */
    public Color getColor() {
        return myColor;
    }

    @Override
    /**This method returns the second x coordinate.
     * @return second x */
    public double getSecondX() {
        
        return mySecondX;
    }

    @Override
    /**This method returns the second y coordinate.
     * @return second y */
    public double getSecondY() {
        return mySecondY;
    }

    @Override
    /**This method returns the first x coordinate.
     * @return first x */
    public double getInitialX() {
        return myInitialX;
    }

    @Override
    /**This method returns the height.
     * @return the height of the rectangle */
    public int getHeight() {
        return (int) mySecondY - (int) myInitialY;
    }

    @Override
    /**This method returns the width coordinate.
     * @return width of the rectangle */
    public int getWidth() {
        return (int) mySecondX - (int) myInitialX;
    }
    @Override
    /**returns rectangle as a string.
     * @return rectangle */
    public String toString() {
        return "rectangle";
    }

}