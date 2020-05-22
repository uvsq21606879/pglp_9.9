package uvsq.pglp_9_9.pglp_9_9;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DaoGroupeFiguresTest {

	@Test
	public void DaoGroupeFigureTest() throws Exception {
		DaoCarre C = new DaoCarre(BDD.Connect());
		Carre C3 = new Carre("C6", new Point(2,1), 20);
		Carre C4 = new Carre("C7", new Point(2,1), 20);
		Carre C5 = new Carre("C8", new Point(3,1), 15);
		C.create(C3);
		C.create(C4);
		C.create(C5);
		GroupeFigures g = new GroupeFigures("g");
	    g.Ajouter(C3);
	    g.Ajouter(C4);
	    g.Ajouter(C5);
	    DaoGroupeFigures G = new DaoGroupeFigures(BDD.Connect());
	    assertNotNull(G);
	    G.create(g);
	    ArrayList<GroupeFigures> liste = new ArrayList<>();
	    liste=G.getAll();
	    for(GroupeFigures gf : liste)
	    gf.afficher();
	}
	

}
