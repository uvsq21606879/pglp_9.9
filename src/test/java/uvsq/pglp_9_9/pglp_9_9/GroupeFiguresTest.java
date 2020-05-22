package uvsq.pglp_9_9.pglp_9_9;

import static org.junit.Assert.*;


import org.junit.Test;

public class GroupeFiguresTest {

	 @Test
	    public void testConstructeur() {
	        GroupeFigures Grp = new GroupeFigures("Grp");
	        assertTrue(Grp.getListe().isEmpty() && Grp.getFigure().equals("Grp"));
	    }
	    @Test
	    public void testAjouter() throws Exception {
	        GroupeFigures Grp = new GroupeFigures("Grp");
	        Cercle C = new Cercle("C", new Point(5,4),10);
	        Grp.Ajouter(C);
	        Grp.Ajouter(C);
	        assertTrue(Grp.getListe().size() == 1 && Grp.getListe().get(0) == C);
	    }
	    @Test
	    public void testSuprimer() throws Exception {
	        GroupeFigures Grp = new GroupeFigures("Grp");
	        Cercle c = new Cercle("C", new Point(1,1),10);
	        Grp.Ajouter(c);
	        Grp.Suprimer(c);
	        assertTrue(Grp.getListe().isEmpty());
	    }
	   
	    @Test
	    public void testAfficher() throws Exception {
	        GroupeFigures Grp = new GroupeFigures("Grp");
	        Cercle C1 = new Cercle("C1", new Point(1,1),10);
	        Cercle C2 = new Cercle("C2", new Point(2,2),10);
	        Cercle C3 = new Cercle("C3", new Point(3,3),10);
	        Grp.Ajouter(C1);
	        Grp.Ajouter(C2);
	        Grp.Ajouter(C3);
	        Grp.afficher();
	    }
	    @Test
	    public void testMove() throws Exception {
	        GroupeFigures Grp = new GroupeFigures("Grp");
	        Cercle C = new Cercle("C", new Point(5,5),10);
	        Grp.Ajouter(C);
	        Grp.move(10,10);
	        assertTrue(C.getCentre().toString().equals("(15,15)"));
	    }

}
