/*TCSS 305 Winter 2014
*Assignment 5 - Power Paint 
*/
package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * A class that creates an ellipse.
 * 
* @author Eva Revear
* @version 2 - 8 - 14
*
*/
public class Ellipse implements Tool {
    
    /**This sets the first X coordinate of the ellipse. */
    private double myInitialX;
    
    /**This sets the first Y coordinate of the ellipse. */
    private double myInitialY;
    
    /**This sets the second X coordinate of the ellipse. */
    private double mySecondX;
    
    /**This sets the second Y coordinate of the ellipse. */
    private double mySecondY;
    
    /**This sets the color of the ellipse. */
    private Color myColor;
    
    /**Sets the initial field values of the ellipse. */
    public Ellipse() {
        myInitialX = 0;
        
        myInitialY = 0;
        
        mySecondX = 0;
        
        mySecondY = 0;
        
        myColor = Color.black;
    }
    @Override
    /**Sets the initial X value of the ellipse.
     * @param theX the initial x value */
    public void setInitialX(final int theX) {
        myInitialX = theX;

    }

    @Override
    /**Sets the initial y value of the ellipse.
     * @param theY the initial y value */
    public void setInitialY(final int theY) {
        myInitialY = theY;

    }

    @Override
    /**Sets the second X value of the ellipse.
     * @param theX the second x value */
    public void setSecondX(final int theX) {
        mySecondX = theX;

    }

    @Override
    /**Sets the second Y value of the ellipse.
     * @param theX the second Y value */
    public void setSecondY(final int theY) {
        mySecondY = theY;

    }

    @Override
    /**makes a returns an ellipse.
     * @return an ellipse */
    public Shape makeShape() {
        Ellipse2D.Double ellipse = new Ellipse2D.Double();
        double leftY = 0;
        if (mySecondY > myInitialY) {
            leftY = myInitialY;
        }
        if (myInitialY >= mySecondY) {
            leftY = mySecondY;
        }
        if (mySecondX > myInitialX) {
            ellipse = new Ellipse2D.Double(myInitialX, leftY, 
                                           Math.abs(mySecondX - myInitialX), 
                                           Math.abs(mySecondY - myInitialY));
        }
        if (myInitialX >= mySecondX) {
            ellipse = new Ellipse2D.Double(mySecondX, leftY,
                                           Math.abs(mySecondX - myInitialX), 
                                           Math.abs(mySecondY - myInitialY));
        }
           
        return ellipse;
    }
    
    /**This method sets the color of the Ellipse.
     * @param theColor the color of the ellipse */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**This method returns the color.
     * @return the color */
    public Color getColor() {
        return myColor;
    }

    @Override
    /**returns the value of the second X.
     * @return the value of the second x */
    public double getSecondX() {
        
        return mySecondX;
    }

    @Override
    /**returns the value of the second y.
     * @return the value of the second y */
    public double getSecondY() {
        return mySecondY;
    }

    @Override
    /**returns the value of the initial X.
     * @return the value of the initial x */
    public double getInitialX() {
        return myInitialX;
    }

    @Override
    /**This method returns the height of the ellipse.
     * @return the height of the ellipse. */
    public int getHeight() {
        return (int) mySecondY - (int) myInitialY;
    }

    @Override
    /**This method returns the width of the ellipse.
     * @return the width of the ellipse. */
    public int getWidth() {
        return (int) mySecondX - (int) myInitialX;
    }
    
    @Override
    /**returns ellipse as a string.
     * @return ellipse */
    public String toString() {
        return "ellipse";
    }
}