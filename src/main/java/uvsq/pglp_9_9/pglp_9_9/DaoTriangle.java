package uvsq.pglp_9_9.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DaoTriangle extends DAO<Triangle> {
	/**
	 * La connexion a la BDD.
	 */
	private Connection connection;
	/**
	 * Constructeur de la classe.
	 * @param Connect connection a la BDD
	 */
	public DaoTriangle(Connection Connect) throws SQLException {
		this.connection=Connect;
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * Methode pour ajouter un triangle au DAO.
	 * @param objet le triangle a ajouter
	 * @return la creation
	 */

	@Override
	public Triangle create(Triangle triangle) throws IOException, SQLException {
		
		try {
			PreparedStatement prepare = connection.prepareStatement(
					"INSERT INTO Figure (figure) VALUES(?)");
			prepare.setString(1, triangle.getFigure());
			prepare.executeUpdate();
			prepare = connection.prepareStatement(
					"INSERT INTO Triangle (figure, " +
					        "point1_x , point1_y , " +
							"point2_x , point2_y ,"  +
							"point3_x , point3_y )"  +
							" VALUES(?, ?, ?, ?, ?, ?, ?)");
			prepare.setString(Arg.UN.get(), triangle.getFigure());
			prepare.setInt(Arg.DEUX.get(), triangle.getPoint(Arg.ZERO.get()).getX());
			prepare.setInt(Arg.TROIS.get(), triangle.getPoint(Arg.ZERO.get()).getY());
			prepare.setInt(Arg.QUATRE.get(), triangle.getPoint(Arg.UN.get()).getX());
			prepare.setInt(Arg.CINQ.get(), triangle.getPoint(Arg.UN.get()).getY());
			prepare.setInt(Arg.SIX.get(), triangle.getPoint(Arg.DEUX.get()).getX());
			prepare.setInt(Arg.SEPT.get(), triangle.getPoint(Arg.DEUX.get()).getY());
			prepare.executeUpdate();
		} catch (SQLException e) {
			return null;
		}
		return triangle;
	}
	/**
	 * Supprimer un triangle du DAO
	 * @param triangle a supprimer
	 */

	@Override
	public void Suprimer(Triangle triangle) throws SQLException {

		try {
			PreparedStatement prepare = connection.prepareStatement(
					"DELETE FROM RelationComposition WHERE IdComposant = ?");
			prepare.setString(Arg.UN.get(), triangle.getFigure());
			prepare.executeUpdate();
		} catch (SQLException e) {

		}

		try {

			PreparedStatement prepare = connection.prepareStatement(
					"DELETE FROM Triangle WHERE figure = ?");
			prepare.setString(Arg.UN.get(), triangle.getFigure());
			prepare.executeUpdate();
			prepare = connection.prepareStatement(
					"DELETE FROM Figure WHERE figure = ?");
			prepare.setString(Arg.UN.get(), triangle.getFigure());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Mettre a jour un triangle dans le DAO 
	 */
	@Override
	public Triangle MiseAjour(Triangle triangle) throws FileNotFoundException, IOException, SQLException {

		Triangle triangleAmodifier = null;
		try {
			triangleAmodifier = this.Search(triangle.getFigure());
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		if (triangleAmodifier != null) {
			try {
				PreparedStatement prepare = connection.prepareStatement(
						  "UPDATE Triangle SET point1_x = ?, point1_y = ?, "
					      + "point2_x = ?, point2_y = ?, point3_x = ?, point3_y = ?"
					      + " WHERE figure = ?");
				prepare.setInt(Arg.UN.get(), triangle.getPoint(Arg.ZERO.get()).getX());
				prepare.setInt(Arg.DEUX.get(), triangle.getPoint(Arg.ZERO.get()).getY());
				prepare.setInt(Arg.TROIS.get(), triangle.getPoint(Arg.UN.get()).getX());
				prepare.setInt(Arg.QUATRE.get(), triangle.getPoint(Arg.UN.get()).getY());
				prepare.setInt(Arg.CINQ.get(), triangle.getPoint(Arg.DEUX.get()).getX());
				prepare.setInt(Arg.SIX.get(), triangle.getPoint(Arg.DEUX.get()).getY());
				prepare.setString(Arg.SEPT.get(), triangle.getFigure());
				prepare.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return triangleAmodifier;
			}
		} else {
			return null;
		}
		System.out.println("Mise à jour de "+triangle.getFigure()+" avec succès");
		return triangle;
	}

	/**
	 * Chercher  un triangle dans le DAO et le retourner.
	 * @param triangle element a obrenit.
	 * @return le triangle si il est  touve sinon null.
	 */
	@Override
	public Triangle Search(String triangle) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		
		Triangle triangleSearch = null;
        try {
            PreparedStatement prepare = connection.prepareStatement(
                    "SELECT * FROM Triangle WHERE figure = ?");
            prepare.setString(Arg.UN.get(), triangle);
            ResultSet res = prepare.executeQuery();
            if (res.next()) {
                  Point p1 =  new Point(res.getInt("point1_x"), res.getInt("point1_y"));
                  Point p2 =  new Point(res.getInt("point2_x"), res.getInt("point2_y"));
                  Point p3 =  new Point(res.getInt("point3_x"), res.getInt("point3_y"));
      
                triangleSearch = new Triangle(triangle, p1, p2, p3);
            }
        } catch (SQLException e) {
            return null;
        }
        return triangleSearch;
	}
	
	/**
	 * Retourne tout les triangle du DAO
	 */
	@Override
	public ArrayList<Triangle> getAll() {
		// TODO Auto-generated method stub
		  ArrayList<Triangle> res = new ArrayList<Triangle>();
	        try {
	            PreparedStatement prepare = connection.prepareStatement(
	                    "SELECT figure FROM Triangle");
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
	            return new ArrayList<Triangle>();
	        }
	        return res;
	}


}
