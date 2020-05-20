package uvsq.pglp_9_9.pglp_9_9;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import uvsq.pglp_9_9.pglp_9_9.DAO.Arg;

public class DaoCarreTest {

	@Test
	public void DaoConnxionToBDD() throws Exception {
		DaoCarre C= new DaoCarre(BDD.Connect());
		assertNotNull(C);
		
	}
	
	@Test
	public void DaoCarreCreateSearchTest() throws Exception {
		DaoCarre C= new DaoCarre(BDD.Connect());
		Carre carre1 = new Carre("carre1", new Point(1,1), 10);
		C.create(carre1);
		Carre R = C.Search(carre1.getFigure());
		assertNotNull(R);    
	}
	
	@Test
	public void DaoCarreMiseAjourTest() throws Exception {
		DaoCarre C= new DaoCarre(BDD.Connect());
		Carre carre2 = new Carre("carre2", new Point(1,1), 10);
		C.create(carre2);
		carre2.move(1, 2);	
		carre2=C.MiseAjour(carre2);
		assertTrue(carre2.getHautGauche().toString().equals("(2,3)"));
    
	}
	
	@Test
	public void DaoCarreSupprimer() throws Exception {
		DaoCarre C= new DaoCarre(BDD.Connect());
		Carre carre = new Carre("carre", new Point(1,1), 10);
		C.create(carre);
		C.Suprimer(carre);
		Carre R = C.Search(carre.getFigure());
		assertNull(R);    
	}
	
	@Test
	public void DaoCarreGetAllTest() throws Exception {
		DaoCarre C = new DaoCarre(BDD.Connect());
		Carre carre3 = new Carre("carre3", new Point(2,1), 20);
		Carre carre4 = new Carre("carre4", new Point(2,1), 20);
		Carre carre5 = new Carre("carre5", new Point(3,1), 15);
		C.create(carre3);
		C.create(carre4);
		C.create(carre5);
	    ArrayList<Carre> liste = new ArrayList<>();
	    liste = C.getAll();
	    assertNotNull(liste);
	    assertEquals(liste.size(),Arg.CINQ.get());
	    
	}

}
