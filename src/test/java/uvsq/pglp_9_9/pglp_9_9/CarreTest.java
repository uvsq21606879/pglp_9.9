package uvsq.pglp_9_9.pglp_9_9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CarreTest {

	@Test
	public void testConstructeur() throws Exception {
		Carre C1 = new Carre("C1", new Point(4,2), 10);
		assertEquals(C1.toString(), "Carre (longueur = 10, Point haut à gauche = (4,2))");
		
	}
	@Test 
	public void gettersTest() throws Exception {
		Carre C = new Carre("C", new Point(3,12), 8);

		assertTrue(C.getHautGauche().toString().equals("(3,12)"));
		assertTrue(C.getLongueur() == 8);
		assertEquals(C.getFigure(), "C");
	}
	@Test
	public void testMove() throws Exception {
		Carre C = new Carre("C", new Point(1,1), 10);
		C.move(1, 2);
		assertTrue(C.getHautGauche().toString().equals("(2,3)")); 
	}
	@Test
	public void testAffichage() throws Exception {
		Carre C = new Carre("C", new Point(0,0), 5);
		C.afficher();
	}
	 @Test (expected = Exception.class)
	    public void testLongueurNegative() throws Exception {
	        @SuppressWarnings("unused")
			Carre C = new Carre("C", new Point(1,1), -1);
	    }


}
