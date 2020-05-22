package uvsq.pglp_9_9.pglp_9_9;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class GroupeFiguresTest {

	 @Test
	    public void testConstructor() {
	        GroupeFigures g = new GroupeFigures("g");
	        assertTrue(g.getListe().isEmpty() && g.getFigure().equals("g"));
	    }
	    @Test
	    public void testAjouter() throws Exception {
	        GroupeFigures g = new GroupeFigures("g");
	        Cercle c = new Cercle("c", new Point(1,1),10);
	        g.Ajouter(c);
	        g.Ajouter(c);
	        g.Ajouter(g);
	        assertTrue(g.getListe().size() == 1 && g.getListe().get(0) == c);
	    }
	    @Test
	    public void testDelete() throws Exception {
	        GroupeFigures g = new GroupeFigures("g");
	        Cercle c = new Cercle("c", new Point(1,1),10);
	        g.Ajouter(c);
	        g.Suprimer(c);
	        assertTrue(g.getListe().isEmpty());
	    }
	    @Test
	    public void testIterate() throws Exception {
	        GroupeFigures g = new GroupeFigures("g");
	        Cercle c = new Cercle("c", new Point(1,1),10);
	        g.Ajouter(c);
	        Iterator<Figure> itf = g.iterator();
	        assertTrue(itf.hasNext() && itf.next() == c && itf.hasNext() == false);
	    }
	    @Test
	    public void testAffiche() throws Exception {
	        GroupeFigures g = new GroupeFigures("g");
	        Cercle c = new Cercle("c", new Point(1,1),10);
	        Cercle c2 = new Cercle("c2", new Point(1,1),10);
	        Cercle c3 = new Cercle("c3", new Point(1,1),10);
	        g.Ajouter(c);
	        g.Ajouter(c2);
	        g.Ajouter(c3);
	        g.afficher();
	    }
	    @Test
	    public void testDeplace() throws Exception {
	        GroupeFigures g = new GroupeFigures("g");
	        Cercle c = new Cercle("c", new Point(1,1),10);
	        g.Ajouter(c);
	        g.move(123,121);
	        assertTrue(c.getCentre().toString().equals("(124,122)"));
	    }

}
