package model;

import java.awt.Color;
import java.awt.Shape;

/*TCSS 305 Winter 2014
*Assignment 5 - Power Paint 
*/

/**
 * An interface that holds shapes.
 * 
* @author Eva Revear
* @version 2 - 8 - 14
*
*/

public interface Tool {
    
    /**sets the initial x coordinate. 
     * @param theX the first x coordinate of the shape*/
    void setInitialX(int theX);
    
    /**sets the initial y coordinate. 
     * @param theY the first y coordinate of the shape*/
    void setInitialY(int theY);
    
    /**Sets the color of the painting.
     * @param theColor the color passed in by the GUI. */
    void setColor(Color theColor);
    
    /**Gets the color of the painting.
     * @return a color */
    Color getColor();
    
    /**sets the second x coordinate.
     * * @param theX the second x coordinate of the shape*/ 
    void setSecondX(int theX);
    
    /**sets the second x coordinate. 
     * * @param theY the first y coordinate of the shape*/
    void setSecondY(int theY);
    
    /**A method that makes the shape.
     * @return a shape based on coordinates */
    Shape makeShape();
    
    /**Gets the second x coordinate.
     * @return the second x value */
    double getSecondX();
    
    /**Gets the second y coordinate.
     * @return the second y value */
    double getSecondY();
    
    /**Gets the first x coordinate.
     * @return the first x value */
    double getInitialX();
    
    /**Gets the height.
     * @return the height of the shape */
    int getHeight();
    
    /**Gets the width.
     * @return the width of the shape */
    int getWidth();
    

}