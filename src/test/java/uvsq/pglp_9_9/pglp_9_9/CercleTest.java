package uvsq.pglp_9_9.pglp_9_9;

import static org.junit.Assert.*;

import org.junit.Test;

public class CercleTest {

	 @Test
		public void testConstructeur() throws Exception {
		 Cercle C = new Cercle("C", new Point(5,2),56);
			assertEquals(C.toString(), "Cercle (centre = (5,2), rayon = 56)");
			
		}
		@Test 
		public void gettersTest() throws Exception {
			 Cercle C = new Cercle("C", new Point(5,2),56);
	       
	        assertTrue(C.getCentre().toString().equals("(5,2)"));
	        assertTrue(C.getRayon() == 56);
	        assertEquals(C.getFigure(), "C");
		}
	    @Test
	    public void testAfficher() throws Exception {
	        Cercle C = new Cercle("C", new Point(1,1), 2);
	        C.afficher();
	    }
	    @Test
	    public void testMove() throws Exception {
	        Cercle C = new Cercle("r", new Point(1,1), 10);
	        C.move(10, 20);
	        assertTrue(C.getCentre().toString().equals("(11,21)"));
	    }
	    @SuppressWarnings("unused")
	    @Test (expected = Exception.class)
	    public void testLongueurNegative() throws Exception {
	        Cercle r = new Cercle("r", new Point(1,1), -1);
	    }

}
