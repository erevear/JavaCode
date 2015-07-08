/*TCSS 305 Winter 2014
*Assignment 5 - Power Paint 
*/

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import model.Ellipse;
import model.Line;
import model.Pencil;
import model.Rectangle;
import model.Tool;




/**
 * A GUI that contains a panel a user can paint.
 * 
* @author Eva Revear
* @version 2 - 8 - 14
*
*/

@SuppressWarnings("serial")
public class PowerPaintGUI extends JFrame {
    
    /**A constant used to set the initial dimensions of the screen. */
    private static final Dimension FIRST_SIZE = new Dimension(550, 350);
    
    /**A three used to access a spot on the button arrays. */
    private static final int BUTTON_ARRAY_SPOT_THREE = 3;
    
    /**A three used to access a spot on the button arrays. */
    private static final int ARRAY_AND_STROKE_FOUR = 4;
    
    /**The drawing panel. */
    private final DrawingPanel myPanel;
    
    /**A tool to be used by the GUI. */
    private Tool myTool;
    
    /**An array of toggle buttons for the toolbar. */
    private final List<JToggleButton> myBarButtons;
    
    /**A array of radio buttons from the tools menu. */
    private final List<JRadioButtonMenuItem> myRadioButtons;
    
    /**An image used to build an icon. */
    private final ImageIcon myIcon;
    
    /**A color used to paint the icon and tools. */
    private Color myColor;
    
    /**A button for the options menue that adds or removes a grid. */
    private final JCheckBoxMenuItem myGridButton;
    

   
    
    
    /**An action that attaches to the pencil button and draws a pencil line. */
    private final Action myPencilAction = new AbstractAction("Pencil") {
        
        

        @Override
        /**Sets the tool field of the GUI to Line. */
        public void actionPerformed(final ActionEvent theEvent) {
            myTool = new Pencil();
            myPanel.setTool(myTool);
            myRadioButtons.get(1).setSelected(true);
            myBarButtons.get(1).setSelected(true);
            
        }
  
    };
    
    /**Sets the tool to line to draw a line. */
    private final Action myLineAction = new AbstractAction("Line") {
        
        

        @Override
        /**Sets the tool field of the GUI to Line. */
        public void actionPerformed(final ActionEvent theEvent) {
            myTool = new Line();
            myPanel.setTool(myTool);
            myRadioButtons.get(0).setSelected(true);
            myBarButtons.get(0).setSelected(true);
            
            
        }
  
    };
    
    /**Sets the tool to ellipse to draw an ellipse. */
    private final Action myEllipseAction = new AbstractAction("Ellipse") {
        
        

        @Override
        /**Sets the tool field of the GUI to Line. */
        public void actionPerformed(final ActionEvent theEvent) {
            myTool = new Ellipse();
            myPanel.setTool(myTool);
            myRadioButtons.get(2).setSelected(true);
            myBarButtons.get(2).setSelected(true);
        }
  
    };
    
    /**Sets the color the panel will draw in. */
    private final Action myColorAction = new AbstractAction("Color...") {
        
        
//        JColorChooser chooseColor = new JColorChooser();

        @Override
        /**Sets the tool field of the GUI to Line. */
        public void actionPerformed(final ActionEvent theEvent) {
            myColor = JColorChooser.showDialog(PowerPaintGUI.this, 
                                               "Choose a paint color", Color.BLACK);
            if (myColor != null) { 
                myPanel.setColor(myColor);
                myIcon.setImage(makeIcon(myColor));
                myTool.setColor(myColor);
                myBarButtons.get(ARRAY_AND_STROKE_FOUR).setIcon(myIcon);
            }
        }
  
    };
    
    /**An action that turns the tool to a rectangle. */
    private final Action myRectangleAction = new AbstractAction("Rectangle") {
        
        

        @Override
        /**Sets the tool field of the GUI to Line. */
        public void actionPerformed(final ActionEvent theEvent) {
            myTool = new Rectangle();
            myPanel.setTool(myTool);
            myRadioButtons.get(BUTTON_ARRAY_SPOT_THREE).setSelected(true);
            myBarButtons.get(BUTTON_ARRAY_SPOT_THREE).setSelected(true);
        }
  
    };
    

    /**An action connected to the exit menu item. */
    private final Action myGridAction = new AbstractAction("Grid") {
        
        
        @Override
        /**Closes the application. */
        public void actionPerformed(final ActionEvent theEvent) {
            if (myGridButton.isSelected()) {
                myPanel.setGrid(true);
            }
            if (!myGridButton.isSelected()) {
                myPanel.setGrid(false);
            }
            
            
        }
  
    };
    
    /**A constructor that sets the fields of the GUI. */
    public PowerPaintGUI() {
        super("TCSS 305 PowerPaint");
        
        myIcon = new ImageIcon();
        
        
        myPanel = new DrawingPanel();
        
        myBarButtons = new ArrayList<JToggleButton>();
        
        myRadioButtons = new ArrayList<JRadioButtonMenuItem>();
        
        myColor = Color.BLACK;
        
        myGridButton = new JCheckBoxMenuItem(myGridAction);
    }
    /**builds the frame. */
    public void start() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(myPanel, BorderLayout.CENTER);
        
        add(buildToolBar(), BorderLayout.SOUTH);
        
        setJMenuBar(buildMenu());
        
        setSize(FIRST_SIZE); 
        
        setVisible(true);
        
        setIconImage(new ImageIcon("/./Users/Eva/workspace/erevear-"
                                + "powerpaint/icons/w.gif").getImage());
        
//        setIconImage(new ImageIcon("/C:/Users/Eva/workspace/erevear-"
//                        + "powerpaint/icons/w.gif").getImage());
        
    }
    
    
    
    /**Builds the menu.
     * @return a built menu */
    public final JMenuBar buildMenu() {
        
        final JMenuBar menuBar = new JMenuBar();
        
        final JMenu fileMenu = new JMenu("File");
        
        fileMenu.setMnemonic(KeyEvent.VK_F);
        

        final JMenu helpOption = new JMenu("Help");
        helpOption.setMnemonic(KeyEvent.VK_H);
        
        final JMenu radioMenu = buildToolButtonGroup("Tools");
        radioMenu.setMnemonic(KeyEvent.VK_T);
        
        final JMenuItem clearItem = fileMenu.add(new ClearAction());
        clearItem.setMnemonic(KeyEvent.VK_C);
        fileMenu.addSeparator();
       
        final JMenuItem exitItem = fileMenu.add(new Exit());
        exitItem.setMnemonic(KeyEvent.VK_X);
        
        final JMenuItem aboutItem = helpOption.add(new About());
        aboutItem.setMnemonic(KeyEvent.VK_A);
        
        
        menuBar.add(fileMenu);
        
        menuBar.add(buildOptionsMenu("Options"));
        
      
        
        
        menuBar.add(radioMenu);
        
        menuBar.add(helpOption);
        
        
        return menuBar;
        
        
    }
    
    /**builds the tool bar.
     * @return a tool bar for the GUI. */
    public final JToolBar buildToolBar() {
        final ButtonGroup buttons = new ButtonGroup();
        
        final JToolBar buttonBar = new JToolBar();

        final JToggleButton colorAction = new JToggleButton(myColorAction);
        
        buttonBar.add(colorAction);
        
        myIcon.setImage(makeIcon(Color.BLACK));
        colorAction.setIcon(myIcon);
        
        buttonBar.addSeparator();
        
        final JToggleButton pencilButton = new JToggleButton(myPencilAction);
        buttonBar.add(pencilButton);
        pencilButton.setSelected(true);
        
        final JToggleButton lineButton = new JToggleButton(myLineAction);
        buttonBar.add(lineButton);
       
        

        
        final JToggleButton rectangleAction = new JToggleButton(myRectangleAction);
        buttonBar.add(rectangleAction);
        
        final JToggleButton ellipseButton = new JToggleButton(myEllipseAction);
        buttonBar.add(ellipseButton);
        

                        
        
        
        pencilButton.setMnemonic(KeyEvent.VK_P);
        lineButton.setMnemonic(KeyEvent.VK_L);
        ellipseButton.setMnemonic(KeyEvent.VK_E);
        rectangleAction.setMnemonic(KeyEvent.VK_R);
        colorAction.setMnemonic(KeyEvent.VK_C);
        
        buttons.add(pencilButton);
        buttons.add(lineButton);
        buttons.add(ellipseButton);
        buttons.add(rectangleAction);
        
        
        myBarButtons.add(lineButton);
        myBarButtons.add(pencilButton);
        
        myBarButtons.add(ellipseButton);
        myBarButtons.add(rectangleAction);
        myBarButtons.add(colorAction);
       
        
        
        
        
        return buttonBar;
    }
    
    /**builds a menu for the radio button tool options.
     * @param theName the name to appear on the menu
     * @return a completed JMenu */
    public final JMenu buildToolButtonGroup(final String theName) {
        final ButtonGroup buttons = new ButtonGroup();
        
        final JMenu buttonBar = new JMenu(theName);
        final JMenuItem colorButton = buttonBar.add(myColorAction);
        colorButton.setIcon(myIcon);
        buttonBar.addSeparator();
        final JRadioButtonMenuItem lineButton = new JRadioButtonMenuItem(myLineAction);
       
        final JRadioButtonMenuItem ellipseButton = new JRadioButtonMenuItem(myEllipseAction);
        final JRadioButtonMenuItem rectangleButton = 
                        new JRadioButtonMenuItem(myRectangleAction);
        final JRadioButtonMenuItem pencilButton = new JRadioButtonMenuItem(myPencilAction);
        pencilButton.setSelected(true);
        
        colorButton.setMnemonic(KeyEvent.VK_C);
        lineButton.setMnemonic(KeyEvent.VK_L);
        ellipseButton.setMnemonic(KeyEvent.VK_E);
        rectangleButton.setMnemonic(KeyEvent.VK_R);
        pencilButton.setMnemonic(KeyEvent.VK_P);
        

        
        buttons.add(pencilButton);
        buttons.add(lineButton);
        buttons.add(ellipseButton);
        buttons.add(rectangleButton);
        
        buttonBar.add(pencilButton);
        buttonBar.add(lineButton);

        buttonBar.add(rectangleButton);
        buttonBar.add(ellipseButton);

        
        myRadioButtons.add(lineButton);
        myRadioButtons.add(pencilButton);
        
        myRadioButtons.add(ellipseButton);
        myRadioButtons.add(rectangleButton);
        
        
        return buttonBar;
    }
    
    /**builds a menu for the radio button stroke options.
     * @param theName the name to appear on the menu
     * @return a completed JMenuItem */
    public final JMenuItem buildThicknessButtonGroup(final String theName) {
        final JMenu thicknessButtons = new JMenu(theName);
        thicknessButtons.setMnemonic(KeyEvent.VK_T);
        final ButtonGroup buttons = new ButtonGroup();
        
        final JRadioButtonMenuItem oneButton = new JRadioButtonMenuItem(new StrokeOne());
        oneButton.setSelected(true);
        myTool = new Pencil();
        myPanel.setTool(myTool);
        final JRadioButtonMenuItem twoButton = new JRadioButtonMenuItem(new StrokeTwo());
        final JRadioButtonMenuItem fourButton = new JRadioButtonMenuItem(new StrokeFour());
        oneButton.setMnemonic(KeyEvent.VK_1);
        twoButton.setMnemonic(KeyEvent.VK_2);
        fourButton.setMnemonic(KeyEvent.VK_4);
        
        thicknessButtons.add(oneButton);
        thicknessButtons.add(twoButton);
        thicknessButtons.add(fourButton);
        
        buttons.add(oneButton);
        buttons.add(twoButton);
        buttons.add(fourButton);
        
        return thicknessButtons;
    }
    
    /**builds a menu for the options.
     * @param theName the name to appear on the menu
     * @return a completed JMenuItem */
    public final JMenu buildOptionsMenu(final String theName) {
        final JMenu optionsMenu = new JMenu(theName);
        
        myGridButton.setMnemonic(KeyEvent.VK_G);
        optionsMenu.add(myGridButton);
        
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        optionsMenu.add(buildThicknessButtonGroup("Thickness"));
      
      
        return optionsMenu;
    }
    
    /**Creates an imgage to use as an icon.
     * @param theColor is whatever color the icon should be
     * @return an image */
    private Image makeIcon(final Color theColor) {
        final BufferedImage icon = new BufferedImage(15, 15, BufferedImage.TYPE_3BYTE_BGR);
        final Graphics2D g2 = icon.createGraphics();
        final Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, 15, 15);
        g2.setPaint(theColor);
        g2.fill(rect);
        return icon;
    }
    
    /**An action to attach to the clear button that clears the panel. */
    public class ClearAction extends AbstractAction {
        /**Adds the title to the key. */
        public ClearAction() {
        super("Clear");
        }
        @Override
        /**Clears the GUI.
         * @param theEvent a mouse click event */
        public void actionPerformed(final ActionEvent theEvent) {
            myPanel.clearPanel();
            
        }
  
    }
    
    /**This class sets the paint stroke to one. */
    public class StrokeOne extends AbstractAction {
        
        /**Sets the title of the radiobutton. */
        public StrokeOne() {
            super("1");
        }
        @Override
        /**Sets the tool field of the GUI to Line. 
         * @param theEvent a mouse click event*/
        public void actionPerformed(final ActionEvent theEvent) {
            myPanel.setStroke(1);
            
        }
        
    }
    /**This class sets the paint stroke to two. */
    class StrokeTwo extends AbstractAction {
        /**Sets the title of the button. */
        public StrokeTwo() {
            super("2");
        }
        @Override
        /**Sets the tool field of the GUI to Line. 
         * @param theEvent a mouse click event*/
        public void actionPerformed(final ActionEvent theEvent) {
            myPanel.setStroke(2);
            
        }
        
    }
    
    /**This class sets the paint stroke to four. */
    public class StrokeFour extends AbstractAction {
        /**Sets the title of the button. */
        public StrokeFour() {
            super("4");
        }
        @Override
        /**Sets the tool field of the GUI to Line. 
         * @param theEvent a mouse click event*/
        public void actionPerformed(final ActionEvent theEvent) {
            myPanel.setStroke(ARRAY_AND_STROKE_FOUR);
            
        }
        
    }
    
    /**An action attached to the about button. */
    public class About extends AbstractAction {
        /**Sets the text on the button. */
        public About() {
            super("About...");
        }
        
        @Override
        /**Creates a popup window that gives some information on the application. 
         * @param theEvent a mouse click event*/
        public void actionPerformed(final ActionEvent theEvent) {
            JOptionPane.showMessageDialog(PowerPaintGUI.this, "TCSS 305 2014 PowerPaint", 
                                          "PowerPaint", JOptionPane.PLAIN_MESSAGE);
            
        }
        
    }
    
    /**An action attached to the exit button. */
    public class Exit extends AbstractAction {
        /**Sets the text of the button. */
        public Exit() {
            super("Exit");
        }
        @Override
        /**Closes the application. 
         * @param theEvent a mouse click event*/
        public void actionPerformed(final ActionEvent theEvent) {
            dispose();
            
            
        }
  
    }
    
}