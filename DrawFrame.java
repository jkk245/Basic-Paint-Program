import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent; 
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.GradientPaint;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.JColorChooser;

/* This class handles and displays all the GUI components 
 * for the paint program.
 */

public class DrawFrame extends JFrame
  {
    //inner toolbar for widgets
     private JToolBar toolBar;
     
    //inner dialog for preferences
    private JDialog prefDialog;
    
    //mouse position label
    private JLabel statusLabel;
    
    //description labels for stroke width and dash length
    private JLabel strokeLabel;
    private JLabel dashLabel;
    
    //buttons
    private JButton buttonRedo;
    private JButton buttonUndo;
    private JButton buttonClear;
    private JButton buttonColor1;
    private JButton buttonColor2;
    
    //combo boxes
    private JComboBox colorBox;
    private JComboBox shapeBox;
    
    //check box for fill
    private JCheckBox fillBox;
    
    //menu bar
    private JMenuBar menuBar;
    
    //menu
    private JMenu fileMenu;
    
    //menu items
    private JMenuItem aboutMenuItem;
    private JMenuItem prefsMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem exitMenuItem;
    
    //text field for stroke width
    private JTextField strokeField;
    
    //text field for stroke dash length
    private JTextField dashField;
    
    //check box for dash option
    private JCheckBox dashBox;
    
    //gradient check box
    private JCheckBox gradientBox;
    
    //color icons
    private ColorIcon colorIcon1;
    private ColorIcon colorIcon2;
    
    //color chooser
    private JColorChooser colorChooser;
    private Color colorOne;
    private Color colorTwo;
    
    //DrawPanel class
    DrawPanel shapeDraw;
    
    //constant combo box choices
    private final String TYPES[] = {"Line","Rectangle","Oval"};


    //constructor
    public DrawFrame()
    {
        
      //GUI frame title
      super( "SuperPaint Application" );
      
      //labels
      statusLabel = new JLabel( "(0,0)" );
      strokeLabel = new JLabel( "Stroke width:" );
      dashLabel = new JLabel( "Dash length:" );
      
      //menu bar and menu
      menuBar = new JMenuBar();
      fileMenu = new JMenu( "File" );
      
      
      //menu items
      aboutMenuItem = new JMenuItem( "About" );
      prefsMenuItem = new JMenuItem( "Prefs" );
      saveMenuItem = new JMenuItem( "Save" );
      loadMenuItem = new JMenuItem( "Load" );
      exitMenuItem = new JMenuItem( "Exit" );
      
      
      //toolbar
      toolBar = new JToolBar("ToolBar");
      toolBar.setLayout( new GridLayout(1,3,5,5) );
      
      //instantiate DrawPanel
      shapeDraw = new DrawPanel(statusLabel);
      
      //reads the configuration settings
      shapeDraw.readConfig();
      
      //stroke field specifications
      strokeField = new JTextField( "" + shapeDraw.getStrokeValue() ,5 );
      shapeDraw.setStroke( shapeDraw.getStrokeValue() );
      
      //dash field specifications
      dashField = new JTextField( "" +  shapeDraw.getDashValue() ,5 );
      shapeDraw.setDashWidth( shapeDraw.getDashValue() );               
      dashBox = new JCheckBox( "Dashed", shapeDraw.getDashState() );
      
      //icons with default colors
      colorIcon1 = new ColorIcon( shapeDraw.getShapeColor1() );
      colorIcon2 = new ColorIcon( shapeDraw.getShapeColor2() );
      
      //buttons
      buttonRedo = new JButton( "Redo" );
      buttonUndo = new JButton( "Undo" );
      buttonClear = new JButton( "Clear" );
      buttonColor1 = new JButton( "Gradient Color 1", colorIcon1 );
      buttonColor2 = new JButton( "Gradient Color 2", colorIcon2 );
      
      //combo box
      shapeBox = new JComboBox( TYPES );
      shapeBox.setSelectedIndex( shapeDraw.getShapeType() );

      //check boxes
      fillBox = new JCheckBox( "Fill", shapeDraw.getShapeFill() );
      gradientBox = new JCheckBox( "Gradient", shapeDraw.getGradientState() );
      
      //dialog window 
      prefDialog = new JDialog( this, "Preferences Configuration", true); 
      prefDialog.setSize(700, 160);
      prefDialog.setLayout( new GridLayout(3,4,5,5) );
      
      //color chooser
      colorChooser = new JColorChooser();
      
      

      
      
      
      //about menu item functionality
      aboutMenuItem.addActionListener(
                                     new ActionListener() // anonymous inner class 
                                       { 
        // process about menu item event  
        public void actionPerformed( ActionEvent event )
        {
          //displays an information dialog
          JOptionPane.showMessageDialog( null, "Author: Joseph Yim\nExpiry Date: May 30/2015\nExpected Mark: 200%", "Developer Details", JOptionPane.INFORMATION_MESSAGE );
        }
      }
      );
      
      
      //preferences menu item
      prefsMenuItem.addActionListener(
                                     new ActionListener() // anonymous inner class 
                                       { 
        // process preferences menu item event  
        public void actionPerformed( ActionEvent event )
        {       
          prefDialog.setVisible(true);
        }
      }
      );
      
      
      
      //saving menu item
      saveMenuItem.addActionListener(
                                     new ActionListener() // anonymous inner class 
                                       { 
        // process save menu item event  
        public void actionPerformed( ActionEvent event )
        {   
         shapeDraw.save();
        }
      }
      );
      
      
      
      //loading menu item
      loadMenuItem.addActionListener(
                                     new ActionListener() // anonymous inner class 
                                       { 
        // process save menu item event  
        public void actionPerformed( ActionEvent event )
        {   
         shapeDraw.load();
        }
      }
      );
      
      
      
      //exit menu item functionality
      exitMenuItem.addActionListener(
                                     new ActionListener() // anonymous inner class 
                                       { 
        // process exit menu item event  
        public void actionPerformed( ActionEvent event )
        {
          //terminates the application
          shapeDraw.saveConfig(); 
          System.exit(0);
        }
      }
      );
      
      
      //button redo functionality
      buttonRedo.addActionListener(
                                   new ActionListener() // anonymous inner class 
                                     { 
        // process buttonRedo event  
        public void actionPerformed( ActionEvent event )
        {
          //redraw last shape
          shapeDraw.redoLastShape(); 
        }
      }
      );
      
      
      
      //button undo functionality
      buttonUndo.addActionListener(
                                   new ActionListener() // anonymous inner class 
                                     { 
        // process buttonUndo event  
        public void actionPerformed( ActionEvent event )
        {
          //clear last shape
          shapeDraw.clearLastShape(); 
        }
      }
      );
      
      
      
      //button clear functionality
      buttonClear.addActionListener(
                                    new ActionListener() // anonymous inner class 
                                      { 
        // process buttonClear event  
        public void actionPerformed( ActionEvent event )
        {
          //clear all shapes
          shapeDraw.clearDrawing(); 
        }
      }
      );
      
      
      
      //shape type combo box functionality
      shapeBox.addItemListener(
                               new ItemListener() // anonymous inner class 
                                 { 
        // process shapeBox event  
        public void itemStateChanged( ItemEvent event )
        {
          if ( event.getStateChange() == ItemEvent.SELECTED ){
            
            //set chosen shape type in combo box
            if ( shapeBox.getSelectedIndex() == shapeDraw.LINE ){
              shapeDraw.setShapeType( shapeDraw.LINE );
            }
            else if  ( shapeBox.getSelectedIndex() == shapeDraw.RECTANGLE){
              shapeDraw.setShapeType( shapeDraw.RECTANGLE );
            }
            else if  ( shapeBox.getSelectedIndex() == shapeDraw.OVAL ){
              shapeDraw.setShapeType( shapeDraw.OVAL );
            }
          }
        }
      } //end anonymous inner class
      );
      
      
      //button color1 functionality
      buttonColor1.addActionListener(
                                   new ActionListener() // anonymous inner class 
                                     { 
        // process buttonRedo event  
        public void actionPerformed( ActionEvent event )
        {
         
          colorOne = colorChooser.showDialog( null, "Color Swatches", Color.BLACK );  
          if ( colorOne != null ){
              shapeDraw.setGradientColor1( colorOne );
              colorIcon1.setColor( colorOne );
          }
        }
      }
      );
      
      //button color2 functionality
      buttonColor2.addActionListener(
                                   new ActionListener() // anonymous inner class 
                                     { 
        // process buttonRedo event  
        public void actionPerformed( ActionEvent event )
        {
            colorTwo = colorChooser.showDialog( null, "Color Swatches", Color.WHITE);  
            if ( colorTwo != null ){
                shapeDraw.setGradientColor2( colorTwo );
                colorIcon2.setColor( colorTwo );
            }
        }
      }
      );
      
      
      
      //check box functionality
      fillBox.addItemListener(
                              new ItemListener() // anonymous inner class 
                                { 
        // process fillBox event  
        public void itemStateChanged( ItemEvent event )
        {
          
          //set shape fill if checkbox is selected
          if ( event.getStateChange() == ItemEvent.SELECTED ){
            shapeDraw.setShapeFill( true );
          }
          //set shape fill if checkbox is deselected
          else if  ( event.getStateChange() == ItemEvent.DESELECTED ){
            shapeDraw.setShapeFill( false );
          }
        }
      } 
      );                        
      
      //dashed line functionality
      dashBox.addItemListener(
                              new ItemListener() // anonymous inner class 
                                { 
        // process dashBox event  
        public void itemStateChanged( ItemEvent event )
        {
          
          //set shape fill if checkbox is selected
          if ( event.getStateChange() == ItemEvent.SELECTED ){
            shapeDraw.setDashedLine( true );
          }
          //set shape fill if checkbox is deselected
          else if  ( event.getStateChange() == ItemEvent.DESELECTED ){
            shapeDraw.setDashedLine( false );
          }
        }
      } 
      );   
      
      //gradient fill functionality
      gradientBox.addItemListener(
                               new ItemListener() // anonymous inner class 
                                { 
        // process dashBox event  
        public void itemStateChanged( ItemEvent event )
        {
          
          //set shape fill if checkbox is selected
          if ( event.getStateChange() == ItemEvent.SELECTED ){
            shapeDraw.setGradient( true );
          }
          //set shape fill if checkbox is deselected
          else if  ( event.getStateChange() == ItemEvent.DESELECTED ){
            shapeDraw.setGradient( false );
          }
        }
      } 
      );   
      
      //stroke field functionality
      strokeField.addActionListener( 
                                    new ActionListener() // anonymous inner class 
                                      { 
        //process text field event
        public void actionPerformed( ActionEvent event )
        {
          try {
              
              int textFieldValue = Integer.parseInt( strokeField.getText() );
              
              //value must be between 1 and 100
              if ( textFieldValue >= 0 && textFieldValue <= 100 ) {
                  shapeDraw.setStroke(textFieldValue);
              }
              else {
                  JOptionPane.showMessageDialog( null, "Stroke Width must be between 1 and 100.", "Error Message", JOptionPane.ERROR_MESSAGE );
              }
          }
          //exception handling
          catch ( NumberFormatException exception ) {
                JOptionPane.showMessageDialog( null, "Stroke Width must be an integer.", "Error Message", JOptionPane.ERROR_MESSAGE );
          }
              
          

        }
      }
      );
      
      
      //dash field functionality
      dashField.addActionListener( 
                                    new ActionListener() // anonymous inner class 
                                      { 
        //process text field event
        public void actionPerformed( ActionEvent event )
        {
          try {
              
              int textFieldValue = Integer.parseInt( dashField.getText() );
              
              //value must be between 1 and 100
              if ( textFieldValue >= 0 && textFieldValue <= 100 ) {
                  //convert to float
                  float textFieldValueFloat = textFieldValue*1.0f;
                  shapeDraw.setDashWidth(textFieldValue);
              }
              else {
                  JOptionPane.showMessageDialog( null, "Dash Width must be between 1 and 100.", "Error Message", JOptionPane.ERROR_MESSAGE );
              }
          }
          catch ( NumberFormatException exception ) {
                JOptionPane.showMessageDialog( null, "Dash Width must be an integer.", "Error Message", JOptionPane.ERROR_MESSAGE );
          }

        }
      }
      );
      
  
      //add widgets to tool bar 
      toolBar.add( buttonRedo );
      toolBar.add( buttonUndo );
      toolBar.add( buttonClear );
      
      //add widgets to preferences panel
      prefDialog.add( shapeBox );
      prefDialog.add( buttonColor1 );
      prefDialog.add( buttonColor2 );
      prefDialog.add( gradientBox );
      prefDialog.add( strokeLabel );
      prefDialog.add( strokeField );
      prefDialog.add( dashLabel );
      prefDialog.add( dashField );
      prefDialog.add( dashBox );
      prefDialog.add( fillBox );
       
      //add menu items to menu, and then menu to menu bar
      fileMenu.add( aboutMenuItem );
      fileMenu.add( prefsMenuItem );
      fileMenu.addSeparator(); 
      fileMenu.add( saveMenuItem );
      fileMenu.add( loadMenuItem );
      fileMenu.addSeparator(); 
      fileMenu.add( exitMenuItem );
      menuBar.add( fileMenu );

      //put the menu bar on the JFrame
      setJMenuBar(menuBar); 

      //set location of toolbar and statusLabel in DrawFrame
      add( toolBar, BorderLayout.NORTH);
      add( statusLabel,BorderLayout.SOUTH);
      add( shapeDraw );

   
    }        
  }