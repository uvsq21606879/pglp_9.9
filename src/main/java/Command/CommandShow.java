package Command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uvsq.pglp_9_9.pglp_9_9.BDD;
import uvsq.pglp_9_9.pglp_9_9.Carre;
import uvsq.pglp_9_9.pglp_9_9.Cercle;
import uvsq.pglp_9_9.pglp_9_9.DAO;
import uvsq.pglp_9_9.pglp_9_9.DaoConstruction;
import uvsq.pglp_9_9.pglp_9_9.Figure;
import uvsq.pglp_9_9.pglp_9_9.GroupeFigures;
import uvsq.pglp_9_9.pglp_9_9.Rectangle;
import uvsq.pglp_9_9.pglp_9_9.Triangle;
import uvsq.pglp_9_9.pglp_9_9.DAO.Arg;

public class CommandShow implements Command {

	/**
	 * constructeur de la classe.
	 */
	public CommandShow(DrawingTUI draw) {

	}

	/**
	 * Afficher toutes les figures.
	 * @throws SQLException 
	 */
	@Override
	public void execute() throws IOException, SQLException {
		DaoConstruction construction = new DaoConstruction();
		DAO<Cercle> daoCercle = construction.getDaoCercle();
		DAO<Carre> daoCarre = construction.getDaoCarre();
		DAO<Rectangle> daoRectangle = construction.getDaoRectangle();
		DAO<Triangle> daoTriangle = construction.getDaoTriangle();
		DAO<GroupeFigures> daoGroupeFig = construction.getDaoGroupeFigures();
		ArrayList<Figure> figures = new ArrayList<Figure>();
		figures.addAll(daoCercle.getAll());
		figures.addAll(daoCarre.getAll());
		figures.addAll(daoRectangle.getAll());
		figures.addAll(daoTriangle.getAll());
		figures.addAll(daoGroupeFig.getAll());
		for (Figure f : figures) {
			if (!this.ExistInGroupe(f))
				f.afficher();
		}
		construction.close();
	}

	/**
	 * Regarder si une figure existe dans un Groupe.
	 * @param fig figure pour la rechercher
	 * @return vrai si la forme est dans un groupe
	 */
	private boolean ExistInGroupe(Figure fig) {
		Connection connect = BDD.Connect();
		try {
			PreparedStatement prepare = connect.prepareStatement(
					"SELECT * FROM RelationComposition WHERE IdComposant = ?");
			prepare.setString(Arg.UN.get(), fig.getFigure());
			ResultSet result = prepare.executeQuery();
			boolean b = result.next();
			connect.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connect.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}



}
