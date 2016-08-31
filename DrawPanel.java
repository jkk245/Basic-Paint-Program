import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/* This class draws the shapes and interacts with the user events that take place in 
 * the draw frame class
 */

public class DrawPanel extends JPanel
{
    
  //stack to store myShape objects   
  private Stack <myShape> shapes;
  
  //linked list to store cleared myShape objects
  private LinkedList <myShape> redrawShapes;
  
  //Jlabel for mouse co-ordinates 
  private JLabel statusLabel;
  
  //current shape
  private myShape currentShapeObject;
 
  //current shape type
  private int currentShapeType;
  
  //primary shape color
  private Color currentShapeColor1;
  
  //second shape color for gradient
  private Color currentShapeColor2;
 
  
  //constant shape value variables
  protected final int LINE = 0;
  protected final int RECTANGLE = 1;
  protected final int OVAL = 2;
  
  //string to store mouse co-ordinates
  private String mousePos;
    
  //stroke width value
  private int strokeValue = 1;
  private float dashValue = 16.0f;
  
  //booleans representing fill, dash, gradient, & loaded image
  private boolean currentShapeFilled;
  private boolean isDashed;
  private boolean isGradient;
  private boolean isLoad;
   
  //stores image picture with buffer of data
  private BufferedImage image;

  
  
  //constructor
  public DrawPanel( JLabel statuslabel )
  {
      
     //set statusLabel
     this.statusLabel = statuslabel;
     
     //set default values
     currentShapeType = LINE;
     currentShapeColor1 = Color.BLACK;
     currentShapeColor2 = Color.WHITE;
       
     shapes  = new Stack();
     redrawShapes = new LinkedList();
     
     //sets background color to white
     setBackground(Color.WHITE);
     
     //register mouse event handlers
     MouseHandler handler = new MouseHandler(); 
     addMouseListener( handler );
     addMouseMotionListener( handler );
     
  }
  
  
  
  //mutator methods
  //sets shape type constant
  public void setShapeType(int currentShapeType) 
  {
    this.currentShapeType = currentShapeType;
  }
  
  //sets shape color
  public void setShapeColor(Color currentShapeColor1)
  {
    this.currentShapeColor1 = currentShapeColor1;
  }
  
  //sets shape fill
  public void setShapeFill(boolean currentShapeFilled)
  {
    this.currentShapeFilled = currentShapeFilled;
  }
  
  //sets dashed line
  public void setDashedLine ( boolean isDashed )
  {
      if ( isDashed ){
          this.isDashed = true;
      }
      else {
          this.isDashed = false;
      }
      
  }
  
  //sets stroke value
  public void setStroke( int strokeValue )
  {
      this.strokeValue = strokeValue;
  }
  
  //sets dash width value
  public void setDashWidth( float dashValue )
  {
      this.dashValue = dashValue;
  }
  
  //sets gradient boolean
  public void setGradient( boolean isGradient )
  {
      if ( isGradient){
          this.isGradient = true;
      }
      else {
          this.isGradient = false;
      }
      
  }
  
  //sets gradient color1
  public void setGradientColor1( Color gradientColor1 )
  {
      this.currentShapeColor1 =  gradientColor1;
  }
  
  //sets gradient color2
  public void setGradientColor2( Color gradientColor2 )
  {
      this.currentShapeColor2 = gradientColor2;
  }   
  

  
  
  //acessor methods  
  //returns shape type constant
  public int getShapeType() 
  {
    return currentShapeType;
  }
  
  //returns gradient boolean
  public boolean getGradientState() 
  {
      return isGradient;
  }
      
   //returns dash boolean
  public boolean getDashState() 
  {
      return isDashed;
  }
  
  //returns shape fill boolean
  public boolean getShapeFill()
  {
    return currentShapeFilled;
  }
  
  //returns stroke value
  public int getStrokeValue() 
  {
      return strokeValue;
  }
  
  //returns dash value
  public float getDashValue() 
  {
      return dashValue;
  }
  
  //returns primary shape color
  public Color getShapeColor1()
  {
    return currentShapeColor1;
  }
  
  //returns secondary shape color
  public Color getShapeColor2()
  {
    return currentShapeColor2;
  }
  
  
  //clears the last shape drawn
  public void clearLastShape()
  {
      if ( !shapes.empty() ){
          
          //adds cleared shape to front of linked list
          redrawShapes.add( shapes.peek() );
          
          //removes most recently drawn shape
          shapes.pop(); 
          repaint();
      }
  }
  
  
  //redraws the last shape removed
  public void redoLastShape()
  {
    if (  redrawShapes.size() != 0 ){  
        
        //adds cleared shape to shapes stack
        shapes.push( redrawShapes.getLast() ); 
        
        //removes added shape from linked list
        redrawShapes.removeLast();
        repaint();
    }
  }
  
  
  //clears all shapes in the stack
  public void clearDrawing()
  {
     //set isLoad to false to clear loaded image
     isLoad = false;
     shapes.clear();
     redrawShapes.clear();
     repaint();
  }
  
  
  
  //inner class to handle mouse events
  public class MouseHandler extends MouseAdapter
  {
      //handles mouse press events
      public void mousePressed( MouseEvent event ) 
      {
          //instantiates current shape object with respective parameters
          if ( currentShapeType == LINE ){
              currentShapeObject = new MyLine(event.getX(),event.getY(),event.getX(),event.getY(),strokeValue,dashValue,isDashed,currentShapeColor1);
          }
          else if ( currentShapeType == RECTANGLE ){
              currentShapeObject = new MyRectangle(event.getX(),event.getY(),event.getX(),event.getY(),strokeValue,dashValue,isDashed,isGradient,currentShapeFilled,currentShapeColor1,currentShapeColor2);
          }
          else if ( currentShapeType == OVAL ){
               currentShapeObject = new MyOval(event.getX(),event.getY(),event.getX(),event.getY(),strokeValue,dashValue,isDashed,isGradient,currentShapeFilled,currentShapeColor1,currentShapeColor2);
          }
          
         //sets location of mouse pressed to starting shape co-ordinates
         currentShapeObject.setX1( event.getX() );
         currentShapeObject.setY1( event.getY() );    
      } 
      
      
      //handles mouse release events
      public void mouseReleased( MouseEvent event ) 
      {
          //sets location of mouse released to ending shape co-ordinates
          currentShapeObject.setX2( event.getX() );
          currentShapeObject.setY2( event.getY() );
          
          //adds current shape to stack to be drawn
          shapes.push( currentShapeObject );
          currentShapeObject = null;
          repaint();
      }
      
      
      //handles mouse movement events
      public void mouseMoved( MouseEvent event )
      {
          mousePos = "(" + event.getX() + " , " + event.getY() + ")";
          
          //updates status label to current mouse co-ordinates
          statusLabel.setText( mousePos );  
      }
      
      
      //handles mouse dragging events
      public void mouseDragged( MouseEvent event ) 
      {
          //sets location of mouse released to current ending shape co-ordinates when dragging
          currentShapeObject.setX2( event.getX() );
          currentShapeObject.setY2( event.getY() );
          repaint();
          
          //updates status label to current mouse co-ordinates
          mousePos = "(" + event.getX() + " , " + event.getY() + ")";
          statusLabel.setText( mousePos );  
          
      }
  }
  
   //saving an image
   public void save()
   {
       //Creates a buffered image with the window width, height, and the option for rendering 8-bit RGB color components packed into integer pixels.
       image = new BufferedImage( this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
       
       //Create a blank graphics context on the buffered image
       Graphics2D graphics2D = image.createGraphics(); 
       
       //Paints the current drawn graphicsfrom the draw panel onto the buffered image
       paint( graphics2D );
       
       //user interaction for the file name
       String fileName = JOptionPane.showInputDialog( null, "Enter the file name: ", "Save Option", JOptionPane.INFORMATION_MESSAGE);
       
       try {
           //writes out/safes the file image as a png
           ImageIO.write( image, "png", new File(  fileName + ".png" ) );
       }
       //exception handling
       catch (IOException exception ) {
           JOptionPane.showMessageDialog( null, "SuperPaint application was unable to save your image", "Error Message", JOptionPane.ERROR_MESSAGE );
       }
    }
 
   
  
  //loading an image
  public void load( )
  {
        //user interaction for the file name
        String fileName = JOptionPane.showInputDialog( null, "Enter the file name: ", "Load Option", JOptionPane.INFORMATION_MESSAGE);
        
        //represents an image has been loaded
        isLoad = true;
           
        try {
            //stores the read image into the buffered image
            image = ImageIO.read( new File(  fileName + ".png") );
        } 
        //exception handling
        catch (IOException exception ) {
            JOptionPane.showMessageDialog( null, "SuperPaint application was unable to load your image", "Error Message", JOptionPane.ERROR_MESSAGE );
        }
        
        //refreshed the GUi to display the loaded image
        repaint();    
   }
  
  
  
   //save configuration settings
   public void saveConfig() 
   {
       try {
           
           //create new file if it does not exist
           File file = new File("configfile.txt");
           if ( !file.exists() ) {
               file.createNewFile();
           }
           
           //to write out context onto the txt file
           PrintWriter fileOutput = new PrintWriter( "configfile.txt" );
           
           //writes out the preference controlling variables onto the txt file
           fileOutput.println( currentShapeType );
           fileOutput.println( isGradient );
           fileOutput.println( isDashed );
           fileOutput.println( currentShapeFilled );
           fileOutput.println( strokeValue );
           fileOutput.println( dashValue );
           
           //color is divided into 3 seperate RGB integer values
           fileOutput.println( currentShapeColor1.getRed() );
           fileOutput.println( currentShapeColor1.getGreen() );
           fileOutput.println( currentShapeColor1.getBlue() );
           fileOutput.println( currentShapeColor2.getRed() );
           fileOutput.println( currentShapeColor2.getGreen() );
           fileOutput.println( currentShapeColor2.getBlue() );

           fileOutput.close();
       } 
        //exception handling
       catch (IOException exception) {
           JOptionPane.showMessageDialog( null, "SuperPaint application was unable to save your configuration settings", "Error Message", JOptionPane.ERROR_MESSAGE );
       }
   }
   
   //reading configuration settings
   public void readConfig()
   {
       //integers to store RGB values
       int r1;
       int g1;
       int b1;
       int r2;
       int g2;
       int b2;
       
       try {
           
           //reads file context 
           Scanner fileInput = new Scanner( new File( "configfile.txt" ) );
           
           //set the preference controlling variables to the values in the txt file
           currentShapeType = fileInput.nextInt();
           isGradient = fileInput.nextBoolean();
           isDashed = fileInput.nextBoolean();
           currentShapeFilled = fileInput.nextBoolean();
           strokeValue = fileInput.nextInt();
           dashValue = fileInput.nextFloat();
           r1 = fileInput.nextInt();
           g1 = fileInput.nextInt();
           b1 = fileInput.nextInt();
           r2 = fileInput.nextInt();
           g2 = fileInput.nextInt();
           b2 = fileInput.nextInt();
           currentShapeColor1 = new Color( r1,g1,b1 );
           currentShapeColor2 = new Color( r2,g2,b2 );
            
           fileInput.close();
       }
       //exception handling
       catch ( IOException exception ) {
           JOptionPane.showMessageDialog( null, "SuperPaint application was unable to read your configuration settings", "Error Message", JOptionPane.ERROR_MESSAGE );
       }
       
   }
  
  
   //draws the shape  
   public void paintComponent( Graphics g )
   {
       super.paintComponent( g ); 
       
       //if an image is loaded, the buffered image will be continously drawn until removed
       if ( isLoad ){
           g.drawImage(image, 0, 0, null); 
       }
       
       //draw shapes polymorphically using short-handed for loop
       for ( myShape shape : shapes )
           if ( shape != null ){
           shape.draw( g );
       }
   
       //draws the current shape object before it is added to stack to 
       //show shape while it is being dragged
       if ( currentShapeObject != null ){
           currentShapeObject.draw( g );
       }
   }
   
   
 
}
    
    
    
    
    
    
    
    