import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Icon;
import java.awt.Component;

/* This class draws out a coloured rectangle to be used as an 
 * dynamic color icon 
 */

public class ColorIcon implements Icon  
{
    //constant width and height
    private static int HEIGHT = 14;  
    private static int WIDTH = 14;      
    private Color color;  
    
    //constructor with default color
    public ColorIcon(Color color)  
    {  
        this.color = color;  
    }  
    
    //mutators
    //sets the color
    public void setColor( Color color )
    {
        this.color = color;
    }
    
    //accessors
    //returns the icon height
    public int getIconHeight()  
    {  
        return HEIGHT;  
    }  
    
    //returns the icon width
    public int getIconWidth()  
    {  
        return WIDTH;  
    }  
    
    //draws the color rectangle
    public void paintIcon(Component c, Graphics g, int x, int y)  
    {   
        //sets the current color to the user specified color
        g.setColor(color);  
        
        //fills the rectangle with the user specified color
        g.fillRect(x, y, WIDTH, HEIGHT);  
        
        //sets the current color to black 
        g.setColor(Color.black);  
        
        //draws an outline of the filled rectangle in black
        g.drawRect(x, y, WIDTH, HEIGHT);  
    }
}
    
    
    
    
    
    
    