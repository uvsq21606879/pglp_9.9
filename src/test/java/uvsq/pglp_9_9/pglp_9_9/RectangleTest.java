package uvsq.pglp_9_9.pglp_9_9;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void testConstructeur() throws Exception {
		Rectangle R = new Rectangle("R", new Point(4,2), 10, 3);
		assertEquals(R.toString(), "Rectangle (Point haut à gauche = (4,2), longueur = 10, largeur = 3))");
		
	}
	@Test 
	public void gettersTest() throws Exception {
		Rectangle R = new Rectangle("R", new Point(3,12), 8, 10);

		assertTrue(R.getHautGauche().toString().equals("(3,12)"));
		assertTrue(R.getLongueur() == 8);
		assertTrue(R.getLargeur() == 10);
		assertEquals(R.getFigure(), "R");
	}
	@Test
	public void testMove() throws Exception {
		Rectangle R = new Rectangle("R", new Point(1,1), 10, 1);
		R.move(1, 2);
		assertTrue(R.getHautGauche().toString().equals("(2,3)"));
	}
	@Test
	public void testAffichage() throws Exception {
		 Rectangle R = new Rectangle("R", new Point(4,5), 5, 7);
			R.afficher();
	}
	 @Test (expected = Exception.class)
	    public void testLongueurNegative() throws Exception {
	        @SuppressWarnings("unused")
			Rectangle R = new Rectangle("R", new Point(1,1), -15, -10);
	    }


}
