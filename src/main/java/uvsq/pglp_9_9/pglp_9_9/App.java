package uvsq.pglp_9_9.pglp_9_9;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Triangle c1 = new Triangle("C1", new Point(5,2), new Point(7,3), new Point(4,8));
        c1.afficher();
        
        Cercle c2 = new Cercle("C2", new Point(5,2),56);
        c2.afficher();
        
    }
}
