import java.awt.Color;

/* This abstract class extends the myShape class hierachy and specifies fill, 
 * gradient, and secondary color for bounded shapes. 
 */

abstract class myBoundedShape extends myShape
{  
    private boolean fill; 
    private boolean isGradient;
    private Color color2;
  
    //constructor to set all co-ordinates, stroke value, dash value, boolean parameters, and colours to default values
    public myBoundedShape( ) 
    {
        this(0,0,0,0,1,16.0f,false,false,false,Color.BLACK,Color.WHITE);
    }
    
    //constructor to set co-oridnates and shape colour
    public myBoundedShape( int x1, int x2, int y1, int y2, int strokeWidth, float dashWidth, boolean isDashed, boolean isGradient, boolean fill, Color color1, Color color2 )
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.strokeWidth = strokeWidth;
        this.dashWidth = dashWidth;
        this.isDashed = isDashed;
        this.isGradient = isGradient;
        this.fill = fill;
        myColor = color1;
        this.color2 = color2;
    }
    
  //calculates upper left x co-ordinate  
  public int getUpperLeftX( )
  {
     return Math.min( getX1( ), getX2( ) );
  }
  
  //calculates upper left y co-ordinate  
  public int getUpperLeftY( )
  {
    return Math.min( getY1( ), getY2( ) );
  }
  
  //calculate width
  public int getWidth( )
  {
    int width = Math.abs( getX2( ) - getX1( ) );
    return width;
  }
  
  //calculate height
  public int getHeight( )
  {
    int height = Math.abs( getY2( ) - getY1( ) );
    return height;
  }
  
  //mutator methods
  //sets boolean fill    
  public void setFill ( boolean fill )
  {
      this.fill = fill;
  }
  
  //sets the gradient boolean
  public void setGradient ( boolean isGradient)
  {
      this.isGradient = isGradient;
  }
  
  //sets the secondary color
  public void setColor2 ( Color color2 )
  {
      this.color2 = color2;
  }
  
  //accesors methods
  //returns boolean fill  
  public boolean getFill( )
  {
      return this.fill;
  }
  
  //returns gradient boolean
  public boolean getGradient( )
  {
      return isGradient;
  }
  
  //returns secondary color
  public Color getColor2( )
  {
      return color2;
  }
  
}

    
    
