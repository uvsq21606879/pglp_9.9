package uvsq.pglp_9_9.pglp_9_9;

import uvsq.pglp_9_9.pglp_9_9.DAO.Arg;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
     
        Triangle c1 = new Triangle("C1", new Point(5,2), new Point(7,3), new Point(4,8));
        c1.afficher();
        
        Cercle c2 = new Cercle("C2", new Point(5,2),56);
        c2.afficher();
        
        Carre r = new Carre("r", new Point(1,1), 10);
        r.afficher();
        System.out.println( c1 );
        
        Rectangle R = new Rectangle("R", new Point(4,5), 5, 7);
		R.afficher();
		
		System.out.println(Arg.UN.get());
        
    }
}
