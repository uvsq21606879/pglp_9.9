package uvsq.pglp_9_9.pglp_9_9;

import static org.junit.Assert.*;

import org.junit.Test;

public class DaoCarreTest {

	@Test
	public void test() throws Exception {
		
		DaoCarre C= new DaoCarre(BDD.Connect());
		assertNotNull(C);
		Carre r = new Carre("r", new Point(1,1), 10);
		r.afficher();
		C.create(r);
		Carre R = C.Search(r.getFigure());
		R.afficher();
		assertNotNull(R);
    
	}
}
