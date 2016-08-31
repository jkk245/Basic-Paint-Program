import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
 
/* This class specifies a line shape with it's
 * co-ordinates, stroke width, dash length, and color.
 */

public class MyLine extends myShape
{
    //default constructor with no input values   
    public MyLine( ) 
    {
    }
    //constructor with input values
    public MyLine( int x1, int y1, int x2, int y2, int strokeWidth, float dashWidth, boolean isDashed, Color color )
    {
      super( x1, x2, y1, y2, strokeWidth, dashWidth, isDashed, color );       
    } 
     
    //draws the line
    public void draw( Graphics g )
    {
         //cast g to Graphics2D
        Graphics2D g2D = (Graphics2D) g;
        
        //sets anti aliasing for smoother line
        g2D.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        
        g.setColor( getColor( ) );
        
        //sets the stroke to dashed with updated stroke width and dash length
        if  ( isDashed ) {
            g2D.setStroke( new BasicStroke( strokeWidth, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[] { dashWidth ,20.0f}, 0.0f) );
        }
        //sets the stroke with updated stroke width
        else {
            g2D.setStroke( new BasicStroke( strokeWidth ) );
        }
       
        g.drawLine( getX1( ), getY1( ), getX2( ), getY2( ) );
        
        
    } 
} 



  
  
  
  
    
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  