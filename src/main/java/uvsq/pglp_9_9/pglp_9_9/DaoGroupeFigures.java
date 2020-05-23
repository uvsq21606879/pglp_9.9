package uvsq.pglp_9_9.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class DaoGroupeFigures extends DAO<GroupeFigures> {

	/**
	 * La connexion à la BDD.
	 */
	private Connection connection;
	/**
	 * Constructeur de la classe.
	 * @param Connect connection a la BDD
	 */
	public DaoGroupeFigures(Connection Connection) throws SQLException {
		this.connection=Connection;

	}

	/**
	 * Création de la relation de Groupe et ses composants
	 * @param IdGroupe identifiant du groupe
	 * @param IdComposant identifiant du composant à ajouter au groupe
	 * @throws IOException
	 * @throws SQLException
	 */
	public void createRelationC(String IdGroupe, String IdComposant) throws IOException, SQLException {

		try {

			PreparedStatement prepare = connection.prepareStatement(
					"INSERT INTO RelationComposition (IdGroupe, IdComposant) VALUES(?, ?)");
			prepare.setString(Arg.UN.get(), IdGroupe);
			prepare.setString(Arg.DEUX.get(),  IdComposant);
			prepare.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Echeque lors de la creation de la Relation de Composition");
		}
	}

	/**
	 * @param IdGroupe Groupe à chercher
	 * @return Une liste contenant les composant du Groupe
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Figure> SearchComposition(String IdGroupe) throws FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<Figure> search = new ArrayList<Figure>();
		DaoConstruction construction = new DaoConstruction();
		try {
			PreparedStatement prepare = connection.prepareStatement(
					"SELECT IdComposant FROM RelationComposition WHERE IdGroupe = ?");
			prepare.setString(Arg.UN.get(), IdGroupe);
			ResultSet result = prepare.executeQuery();
			DAO<Cercle> daoCercle = construction.getDaoCercle();
			DAO<Carre> daoCarre = construction.getDaoCarre();
			DAO<Rectangle> daoRectangle = construction.getDaoRectangle();
			DAO<Triangle> daoTriangle = construction.getDaoTriangle();
			while (result.next()) {
				Figure fig = daoCercle.Search(result.getString("IdComposant"));
				if (fig == null) {
					fig = daoCarre.Search(result.getString("IdComposant"));
				}
				if (fig == null) {
					fig = daoRectangle.Search(result.getString("IdComposant"));
				}
				if (fig == null) {
					fig = daoTriangle.Search(result.getString("IdComposant"));
				}
				if (fig == null) {
					fig = this.Search(result.getString("IdComposant"));
				}
				search.add(fig);
			}
			construction.close();
		} catch (SQLException e) {
			construction.close();
			return new ArrayList<Figure>();
		}
		return search;
	}


	/**
	 * Suprimer une figure
	 * @param la figure a supprimer
	 */
	@Override
	public void Suprimer(GroupeFigures figure) throws SQLException {
		try {

			try {
				PreparedStatement prepare = connection.prepareStatement(
						"DELETE FROM RelationComposition WHERE IdGroupe = ?");
				prepare.setString(Arg.UN.get(), figure.getFigure());
				prepare.executeUpdate();
			} catch (SQLException e) {
			}

			try {
				PreparedStatement prepare = connection.prepareStatement(
						"DELETE FROM RelationComposition WHERE IdComposant = ?");
				prepare.setString(Arg.UN.get(), figure.getFigure());
				prepare.executeUpdate();
			} catch (SQLException e) {
			}

			PreparedStatement prepare = connection.prepareStatement(
					"DELETE FROM GroupeFigures WHERE figure = ?");
			prepare.setString(Arg.UN.get(), figure.getFigure());
			prepare.executeUpdate();
			prepare = connection.prepareStatement(
					"DELETE FROM Figure WHERE figure = ?");
			prepare.setString(Arg.UN.get(), figure.getFigure());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Mettre à jour un groupe
	 * @param l'objet à mettre à jour
	 * @return la mise à jour
	 */
	@Override
	public GroupeFigures MiseAjour(GroupeFigures objet) throws IOException, SQLException {

		ArrayList<Figure> contenu = null;
		try {
			contenu = this.SearchComposition(objet.getFigure());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (!contenu.isEmpty()) {
			try {
				PreparedStatement prepare = connection.prepareStatement(
						"DELETE FROM RelationComposition WHERE IdGroupe = ?");
				prepare.setString(Arg.UN.get(), objet.getFigure());
				prepare.executeUpdate();
			} catch (SQLException e) {
			}
			DaoConstruction construction = new DaoConstruction();
			for (Figure f : objet.getListe()) {
				if (f.getClass() == Cercle.class) {
					DAO<Cercle> dao = construction.getDaoCercle();
					dao.MiseAjour((Cercle) f);
				} else if (f.getClass() == Carre.class) {
					DAO<Carre> dao = construction.getDaoCarre();
					dao.MiseAjour((Carre) f);
				} else if (f.getClass() == Rectangle.class) {
					DAO<Rectangle> dao = construction.getDaoRectangle();
					dao.MiseAjour((Rectangle) f);
				} else if (f.getClass() == Triangle.class) {
					DAO<Triangle> dao = construction.getDaoTriangle();
					dao.MiseAjour((Triangle) f);
				} else {
					this.MiseAjour((GroupeFigures) f);
				}
				this.createRelationC(objet.getFigure(), f.getFigure());
			}
			construction.close();
		} else {
			return null;
		}
		System.out.println("Mise à jour de "+objet.getFigure()+" avec succès");
		return objet;
	}
	/**
	 * Chercher une figure dans le GroupeFigures
	 * @param la figure a chercher
	 */
	@Override
	public GroupeFigures Search(String figure)
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		GroupeFigures search = null;
		try {
			PreparedStatement prepare = connection.prepareStatement(
					"SELECT * FROM GroupeFigures WHERE figure = ?");
			prepare.setString(Arg.UN.get(), figure);
			ResultSet result = prepare.executeQuery();
			if (result.next()) {
				search = new GroupeFigures(figure);
				ArrayList<Figure> list = SearchComposition(figure);
				for (Figure f : list) {
					search.Ajouter(f);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return search;
	}
	/**
	 * Retourne la liste de tout les figures dans GroupeFigures
	 */
	@Override
	public ArrayList<GroupeFigures> getAll() {
		ArrayList<GroupeFigures> res = new ArrayList<GroupeFigures>();
		try {
			PreparedStatement prepare = connection.prepareStatement(
					"SELECT figure FROM GroupeFigures");
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				try {
					res.add(this.Search(result.getString("figure")));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<GroupeFigures>();
		}
		return res;
	}
	/**
	 * Creation d'un Groupe de Figure
	 * @param le groupe de figure à créer
	 */
	@Override
	public GroupeFigures create(GroupeFigures object) throws IOException, SQLException {
		DaoConstruction construction = new DaoConstruction();
		try {
			PreparedStatement prepare = connection.prepareStatement(
					"INSERT INTO Figure (figure) VALUES(?)");
			prepare.setString(Arg.UN.get(), object.getFigure());
			prepare.executeUpdate();
			prepare = connection.prepareStatement("INSERT INTO GroupeFigures (figure) VALUES(?)");
			prepare.setString(Arg.UN.get(), object.getFigure());
			prepare.executeUpdate();
			for (Figure f : object.getListe()) {
				if (f.getClass() == Cercle.class) {
					DAO<Cercle> dao = construction.getDaoCercle();
					dao.create((Cercle) f);
				} else if (f.getClass() == Carre.class) {
					DAO<Carre> dao = construction.getDaoCarre();
					dao.create((Carre) f);
				} else if (f.getClass() == Rectangle.class) {
					DAO<Rectangle> dao = construction.getDaoRectangle();
					dao.create((Rectangle) f);
				} else if (f.getClass() == Triangle.class) {
					DAO<Triangle> dao = construction.getDaoTriangle();
					dao.create((Triangle) f);
				} else {
					this.create((GroupeFigures) f);
				}

				createRelationC(object.getFigure(), f.getFigure());

			}
			construction.close();
		} catch (SQLException e) {
			construction.close();
			return null;
		}
		return object;
	}



}
