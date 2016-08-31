import javax.swing.JFrame;

/* This test class runs the draw frame class.
 */

public class DrawPanelTest
{
    public static void main( String args[] )
    {
        //instantiate DrawFrame
        DrawFrame application = new DrawFrame(); 
        
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.setSize( 800, 500 );
        application.setVisible( true );
    }
}





