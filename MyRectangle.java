import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.GradientPaint;

/* This class specifies a rectangle shape with it's
 * co-ordinates, stroke width, dash length, gradient, primary and secondary color.
 */

public class MyRectangle extends myBoundedShape
{
  //default constructor with no input values   
  public MyRectangle( ) 
  {
  }
  
  //constructor with input values   
  public MyRectangle ( int x1, int y1, int x2, int y2, int strokeWidth, float dashWidth, boolean isDashed, boolean isGradient, boolean fill, Color color1, Color color2 )
  {
   super( x1, y1, x2, y2, strokeWidth, dashWidth, isDashed, isGradient, fill, color1, color2 );
  }
  
  //draws the rectangle
  public void draw( Graphics g )
  {
      
      //cast g to Graphics2D
      Graphics2D g2D = (Graphics2D) g;
      
       //sets anti aliasing for smoother line
      g2D.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
      
      //sets the shape's primary color
      g.setColor( getColor( ) );
      
      
      //sets the stroke to dashed with updated stroke width and dash length
      if  ( isDashed ) {
          g2D.setStroke( new BasicStroke( strokeWidth, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[] {dashWidth,20.0f}, 0.0f) );
      }
       //sets the stroke with updated stroke width
      else {
            g2D.setStroke( new BasicStroke( strokeWidth ) );
        }
      
      
      g.drawRect( getUpperLeftX( ), getUpperLeftY( ), getWidth( ), getHeight( ) );
      
      
      //creates gradient with primary and secondary color
      if ( getGradient() ) {
          
          GradientPaint gradient = new GradientPaint( getX1( ), getY1( ), getColor(), getX2( ), getY2( ), getColor2() );
          g2D.setPaint ( gradient );
          
          //fills the gradient
          g.fillRect( getUpperLeftX( ), getUpperLeftY( ), getWidth( ), getHeight( ) );
      }
   
       //fills the shape
      if ( getFill( ) ) {
          g.fillRect( getUpperLeftX( ), getUpperLeftY( ), getWidth( ), getHeight( ) );
      }
      
  } 
   
  
} 