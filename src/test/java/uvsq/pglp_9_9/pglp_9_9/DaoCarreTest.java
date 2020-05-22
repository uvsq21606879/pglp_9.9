package uvsq.pglp_9_9.pglp_9_9;

import static org.junit.Assert.*;


import java.util.ArrayList;


import org.junit.Test;

import uvsq.pglp_9_9.pglp_9_9.DAO.Arg;

public class DaoCarreTest {

	@Test
	public void DaoConnxionToBDD() throws Exception {
		BDD.Bdd();
		BDD.resetBddTables();
		DaoCarre C= new DaoCarre(BDD.Connect());
		assertNotNull(C);
		
	}
	
	@Test
	public void DaoCarreCreateSearchTest() throws Exception {
		DaoCarre C= new DaoCarre(BDD.Connect());
		Carre C1 = new Carre("C1", new Point(1,1), 10);
		C.create(C1);
		Carre R = C.Search(C1.getFigure());
		assertNotNull(R);    
	}
	
	@Test
	public void DaoCarreMiseAjourTest() throws Exception {
		DaoCarre C= new DaoCarre(BDD.Connect());
		Carre C2 = new Carre("C2", new Point(1,1), 10);
		C.create(C2);
		C2.move(1, 2);	
		C2=C.MiseAjour(C2);
		assertTrue(C2.getHautGauche().toString().equals("(2,3)"));
    
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
		Carre C3 = new Carre("C3", new Point(2,1), 20);
		Carre C4 = new Carre("C4", new Point(2,1), 20);
		Carre C5 = new Carre("C5", new Point(3,1), 15);
		C.create(C3);
		C.create(C4);
		C.create(C5);
	    ArrayList<Carre> liste = new ArrayList<>();
	    liste = C.getAll();
	    assertNotNull(liste);
	    assertEquals(Arg.CINQ.get(),liste.size());
	    
	}
	
}
