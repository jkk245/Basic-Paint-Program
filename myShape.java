import java.awt.Color;
import java.awt.Graphics;
 
/* This abstract class defines the hierachy of all shapes with it's
 * co-ordinates, stroke width, dash length, and color.
 */

abstract class myShape 
{
    protected int x1; 
    protected int y1; 
    protected int x2; 
    protected int y2;
    protected int strokeWidth;
    protected float dashWidth;
    protected boolean isDashed; 
    protected Color myColor; 
   
  
    
     //constructor to set all co-ordinates, stroke value, dash value, boolean parameters, and colours to default values
    public myShape( ) 
    {
        this(0,0,0,0,1,16.0f,false,Color.BLACK);
    }
    
    //constructor to set co-oridnates and shape colour
    public myShape( int x1, int x2, int y1, int y2, int strokeWidth, float dashWidth, boolean isDashed, Color color)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.strokeWidth = strokeWidth;
        this.dashWidth = dashWidth;
        this.isDashed = isDashed;
        myColor = color;
    }
    
   
    //mutator methods 
    //sets the first x co-ordinate
    public void setX1 ( int x1 )
    {
        this.x1 = x1;
    }
    
    //sets the second x co-ordinate
    public void setX2 ( int x2 )
    {
        this.x2 = x2;
    }
    
    //sets the first y co-ordinate
    public void setY1 ( int y1 )
    {
        this.y1 = y1;
    }
    
    //sets the second y co-ordinate
    public void setY2 ( int y2 )
    {
        this.y2 = y2;
    }
    
    //sets the stroke width
    public void setStrokeWidth ( int strokeWidth )
    {
         this.strokeWidth = strokeWidth;
     }
    
    //sets the dash width
    public void setDashWidth ( float dashWidth )
    {
         this.dashWidth = dashWidth;
     }
    
    //sets dash boolean
    public void setDash( boolean isDashed )
    {
        this.isDashed = isDashed;
    }
    
    //sets the primary color
    public void setColor ( Color color )
    {
        myColor = color;
    }
    

    
    //acessor methods
    
    //returns the first x co-ordinate
    public int getX1( )
    {
        return this.x1;
    }
    
    //returns the second x co-ordinate
    public int getX2( )
    {
        return this.x2;
    }
    
    //returns the first y co-ordinate
    public int getY1( )
    {
        return this.y1;
    }
    
    //returns the second y co-ordinate
    public int getY2( )
    {
        return this.y2;
    }
    
    //returns the stroke width
    public int getStrokeWidth( )
    {
        return strokeWidth;
    }
    
    //returns the dash width
    public float getdashWidth( )
    {
        return dashWidth;
    }
    
    //returns the dash boolean
    public boolean getDash( )
    {
        return isDashed;
    }
    
    //returns the color
    public Color getColor( )
    {
        return myColor;
    }
    
   

    //abstract method to draw a specified shape on the screen
    //speciify shape type to be drawn here
    public abstract void draw( Graphics g ); 
   
    
    
}
    
    
        
    
    
    
    
    
    
    
    
    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        