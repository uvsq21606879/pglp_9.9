package uvsq.pglp_9_9.pglp_9_9;

import static org.junit.Assert.*;

import org.junit.Test;

public class TriangleTest {
	
	@Test
	public void testConstructeur() throws Exception {
		Triangle T = new Triangle("T", new Point(5,2), new Point(7,3), new Point(4,8));
		assertEquals(T.toString(), " Triangle ((5,2), (7,3), (4,8)).");
		
	}
	@Test 
	public void gettersTest() throws Exception {
		Triangle T = new Triangle("T", new Point(5,2), new Point(7,3), new Point(4,8));

		assertTrue(T.getPoint(0).toString().equals("(5,2)"));
		assertTrue(T.getPoint(1).toString().equals("(7,3)"));
		assertTrue(T.getPoint(2).toString().equals("(4,8)"));
		assertEquals(T.getFigure(), "T");
	}
	@Test
	public void testMove() throws Exception {
		Triangle T = new Triangle("T", new Point(5,2), new Point(7,3), new Point(4,8));
		T.move(1, 2);
		assertTrue(T.getPoint(0).toString().equals("(6,4)"));
		assertTrue(T.getPoint(1).toString().equals("(8,5)"));
		assertTrue(T.getPoint(2).toString().equals("(5,10)"));
	}
	@Test
	public void testAffichage() throws Exception {
		Triangle T = new Triangle("T", new Point(5,2), new Point(7,3), new Point(4,8));
		T.afficher();
	}
	 

}
