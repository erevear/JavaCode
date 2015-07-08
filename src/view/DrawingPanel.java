/*TCSS 305 Winter 2014
*Assignment 5 - Power Paint 
*/

package view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Line;
import model.Tool;



/**
 * A panel that a user can paint.
 * 
* @author Eva Revear
* @version 2 - 8 - 14
*
*/

@SuppressWarnings("serial")
public class DrawingPanel extends JPanel {
    
    /**a constant to set the size of the frame. */
    private static final Dimension FIRST_SIZE = new Dimension(500, 300);
    
    /**An integer to set the lines of the grid. */
    private static final float GRID_LINES = 10;
    
    /**A string used to compare tools. */
    private static final String PENCIL_STRING = "pencil";
    
    
    /**A float to set the composite. */
    private static final float FLOAT_NUMBER = 0.50f;
    
    /**A tool used to draw on the panel. */
    private Tool myTool;
    
    /**An array that saves shapes. */
    private final List<Shape> myShapes;
    

    /**A point where the mouse is pressed. */
    private Point myStart;
    
    /**The point where the mouse is released. */
    private Point myEnd;
    
    /**The color used to draw in. */
    private Color myColor;
    

    /**An array to store colors. */
    private final List<Color> myColorArray;
    
    /**A point that is initialized when the mouse is dragged. */
    private Point myDragEnd;
    
    
    /**This list holds points. */
    private final List<Point> myPointList;
    
    /**This holds a list of stroke sizes. */
    private final List<BasicStroke> myStrokeList;
    
    /**This is a stroke object that holds the stroke of the shape. */
    private BasicStroke myStrokeSize;
    
    /**A boolean to check whether or not the GUI should paint a grid.*/
    private boolean myGrid;
    
    /**Constructs the drawing panel and initializes the fields. */
    public DrawingPanel() {
        super();
        
        myGrid = false;
        
        myPointList = new ArrayList<Point>();
        
        myStrokeList = new ArrayList<BasicStroke>();
        
        myEnd = new Point();
        
        myStart = new Point();
        
        myDragEnd = new Point();
        
        myShapes = new ArrayList<Shape>();
        
        myColor = Color.black;
        
        myTool = new Line();
        
        myColorArray = new ArrayList<Color>();
        
        myStrokeSize = new BasicStroke(1);
        
        
        build();

    }
    
    
    
    /**Sets the tool.
     * @param theTool the tool passed from the GUI. */
    public void setTool(final Tool theTool) {
        myTool = theTool;
    
    }
    
    /**Sets the color.
     * @param theColor theColor the color passed from the GUI. 
    */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**A method to set the width of line a shape is drawn in.
     * @param theStroke an integer passed in from a
     * radiobutton selection */
    public void setStroke(final int theStroke) {
        final int stroke = theStroke;
        myStrokeSize = new BasicStroke((float) stroke);
    }
    
    /**A query to get the color.
     * @return the color */
    public Color getColor() {
        return myColor;
    }
    
    /**This method clears the panel. */
    public void clearPanel() {
        myShapes.clear();
        myColorArray.clear();
        myStrokeList.clear();
        repaint();
    }
    
    /**Sets grid to true or false depending on the GUI.
     * @param theGrid a boolean passed in through the GUI. */
    public void setGrid(final boolean theGrid) {
        myGrid = theGrid;
    }
    
    /**a method that paints the background.
     * @param g2 a graphic used to paint */
    private void paintBackground(final Graphics2D g2) {
        g2.setPaint(Color.WHITE);
        final Rectangle2D.Double rectangle =
                        new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        g2.draw(rectangle);
        g2.fill(rectangle);
        
        if (myGrid) {
            for (int i = 0; i < rectangle.getWidth(); i += GRID_LINES) {
                final Shape line = new Line2D.Double(i, 0, i, rectangle.getHeight());
                g2.setPaint(Color.BLACK);
                g2.draw(line);
               
            }

            for (int i = 0; i < rectangle.getHeight(); i += GRID_LINES) {
                final Shape line = new Line2D.Double(0, i, rectangle.getWidth(), i);
                g2.setPaint(Color.BLACK);
                g2.draw(line);
            }
           
            repaint();
        }
        
        
        
    }  
    
    /**builds the initial state of the panel. */
    private void build() {
        addMouseMotionListener(new DragHandler());
        addMouseListener(new FirstClick());
        addMouseListener(new SecondClick());
        setSize(FIRST_SIZE);
        setBackground(Color.WHITE);
    }
    
    @Override
    /**A method used to paint the component.
     * @param Graphics theG*/
    public void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D graphic2 = (Graphics2D) theG;
        paintBackground(graphic2);
        
     
        graphic2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                  RenderingHints.VALUE_ANTIALIAS_ON);
        graphic2.setComposite(AlphaComposite.getInstance(
                                     AlphaComposite.SRC_OVER, FLOAT_NUMBER));
     
        graphic2.setBackground(Color.WHITE);
        
        
       
  
        int i = 0;
        for (final Shape drawThese: myShapes) {
            graphic2.setStroke(myStrokeList.get(i));
            graphic2.setPaint(myColorArray.get(i));
            graphic2.draw(drawThese);
            i++;
        }

        if (myStart != null && myEnd != null) {
            if (!PENCIL_STRING.equals(myTool.toString())) {
                myTool.setInitialX(myStart.x);
                myTool.setInitialY(myStart.y);
                myTool.setSecondX(myDragEnd.x);
                myTool.setSecondY(myDragEnd.y);
                final Shape shape = myTool.makeShape();
                graphic2.setStroke(myStrokeSize);
                graphic2.setPaint(myColor);
                graphic2.draw(shape);
                graphic2.setPaint(getBackground());

                graphic2.fill(shape);
            
            
                graphic2.dispose();
            }
          
            if (PENCIL_STRING.equals(myTool.toString())) {
                final int size = myPointList.size();
                myTool.setInitialX(myStart.x);
                myTool.setInitialY(myStart.y);
                for (int j = 0; j < size - 1; j++) {
                    myTool.setSecondX((int) myPointList.get(j).getX());
                    myTool.setSecondY((int) myPointList.get(j).getY());
                    graphic2.setStroke(myStrokeSize);
                 
                    final Shape shape = myTool.makeShape();
                    graphic2.draw(shape);
                    myShapes.add(shape);
                    myColorArray.add(myColor);
                    myStrokeList.add(myStrokeSize);
                    myTool.setInitialX((int) myPointList.get(j).getX());
                    myTool.setInitialY((int) myPointList.get(j).getY());
                 
                 
                }
                graphic2.dispose();
              
            }
            
            
        }
    }
    
    
    
    /**A mouse listener that passes in a a press. */
    private class FirstClick extends MouseAdapter {

        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myStart = new Point(theEvent.getX(), theEvent.getY());
            myEnd = new Point(theEvent.getX(), theEvent.getY());
            myDragEnd = myStart;
            myPointList.clear(); 
          
        }

        
    }
    
    /**A mouse listener that passes in a release. */
    private class SecondClick extends MouseAdapter {

        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (!PENCIL_STRING.equals(myTool.toString())) {
                myTool.setInitialX(myStart.x);
                myTool.setInitialY(myStart.y);
                myTool.setSecondX(theEvent.getX());
                myTool.setSecondY(theEvent.getY());
                final Shape shape = myTool.makeShape();
                myShapes.add(shape);
            
                myColorArray.add(myColor);
                myStrokeList.add(myStrokeSize);
                myDragEnd = myStart;
                
                repaint();
            
            } 
            if (PENCIL_STRING.equals(myTool.toString())) {
                myPointList.clear();
                myDragEnd = myStart;
                repaint();
            }

        }

        
    }
    /**a mouse listener that passes in a drag event. */
    private class DragHandler extends MouseAdapter {

        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myDragEnd = new Point(theEvent.getX(), theEvent.getY());
            if (PENCIL_STRING.equals(myTool.toString())) {
                myPointList.add(myDragEnd);
            }
            repaint();
                           
            
           
           
        }

        
    }


}