package uvsq.pglp_9_9.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DaoRectangle extends DAO<Rectangle> {

	/**
	 * La connexion a la BDD.
	 */
	private Connection connection;
	/**
	 * Constructeur de la classe.
	 * @param Connect connection a la BDD
	 */
	public DaoRectangle(Connection Connect) throws SQLException {
		this.connection=Connect;
	}


	/**
	 * Methode pour ajouter un Rectangle au DAO.
	 * @param objet le rectangle a ajouter
	 * @return la creation
	 */

	@Override
	public Rectangle create(Rectangle rectangle) throws IOException, SQLException {

		try {
			PreparedStatement prepare = connection.prepareStatement(
					"INSERT INTO Figure (figure) VALUES(?)");
			prepare.setString(1, rectangle.getFigure());
			prepare.executeUpdate();
			prepare = connection.prepareStatement(
					"INSERT INTO Rectangle (figure,HG_x,HG_y,longueur,largeur)"
							+ " VALUES(?, ?, ?, ?, ?)");
			prepare.setString(Arg.UN.get(), rectangle.getFigure());
			prepare.setInt(Arg.DEUX.get(), rectangle.getHautGauche().getX());
			prepare.setInt(Arg.TROIS.get(), rectangle.getHautGauche().getY());
			prepare.setInt(Arg.QUATRE.get(), rectangle.getLongueur());
			prepare.setInt(Arg.CINQ.get(), rectangle.getLargeur());
			prepare.executeUpdate();
		} catch (SQLException e) {
			return null;
		}
		return rectangle;
	}
	/**
	 * Supprimer un rectangle du DAO
	 * @param rectangle a supprimer
	 */

	@Override
	public void Suprimer(Rectangle rectangle) throws SQLException {

		try {
			PreparedStatement prepare = connection.prepareStatement(
					"DELETE FROM RelationComposition WHERE IdComposant = ?");
			prepare.setString(Arg.UN.get(), rectangle.getFigure());
			prepare.executeUpdate();
		} catch (SQLException e) {

		}

		try {

			PreparedStatement prepare = connection.prepareStatement(
					"DELETE FROM Rectangle WHERE figure = ?");
			prepare.setString(Arg.UN.get(), rectangle.getFigure());
			prepare.executeUpdate();
			prepare = connection.prepareStatement(
					"DELETE FROM Figure WHERE figure = ?");
			prepare.setString(Arg.UN.get(), rectangle.getFigure());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Mettre a jour un rectangle dans le DAO 
	 */
	@Override
	public Rectangle MiseAjour(Rectangle rectangle) throws FileNotFoundException, IOException, SQLException {

		Rectangle RectangleAmodifier = null;
		try {
			RectangleAmodifier = this.Search(rectangle.getFigure());
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		if (RectangleAmodifier != null) {
			try {
				PreparedStatement prepare = connection.prepareStatement(
						"UPDATE Rectangle SET HG_x = ?, HG_y = ?, longueur = ? , largeur = ? WHERE figure = ?");
				prepare.setInt(Arg.UN.get(), rectangle.getHautGauche().getX());
				prepare.setInt(Arg.DEUX.get(), rectangle.getHautGauche().getY());
				prepare.setInt(Arg.TROIS.get(), rectangle.getLongueur());
				prepare.setInt(Arg.QUATRE.get(), rectangle.getLargeur());
				prepare.setString(Arg.CINQ.get(), rectangle.getFigure());
				prepare.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return RectangleAmodifier;
			}
		} else {
			return null;
		}
		System.out.println("Mise à jour de "+rectangle.getFigure()+" avec succès");
		return rectangle;
	}

	/**
	 * Chercher  un rectangle dans le DAO et le retourner.
	 * @param rectangle element a obrenit.
	 * @return le rectangle si il est  touve sinon null.
	 */
	@Override
	public Rectangle Search(String rectangle) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {

		Rectangle rectangleSearch = null;
		try {
			PreparedStatement req = connection.prepareStatement(
					"SELECT * FROM Rectangle WHERE figure = ?");
			req.setString(Arg.UN.get(), rectangle);
			ResultSet res = req.executeQuery();
			if (((java.sql.ResultSet) res).next()) {
				Point point = new Point(res.getInt("HG_x"), res.getInt("HG_y"));
				try {
					rectangleSearch = new Rectangle(rectangle, point, res.getInt("longueur"), res.getInt("largeur"));
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return rectangleSearch;

	}

	/**
	 * Retourne tout les rectangle du DAO
	 */
	@Override
	public ArrayList<Rectangle> getAll() {
		// TODO Auto-generated method stub
		ArrayList<Rectangle> res = new ArrayList<Rectangle>();
		try {
			PreparedStatement prepare = connection.prepareStatement(
					"SELECT figure FROM Rectangle");
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				try {
					res.add(this.Search(result.getString("figure")));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Rectangle>();
		}
		return res;
	}



}
